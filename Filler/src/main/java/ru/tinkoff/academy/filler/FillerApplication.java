package ru.tinkoff.academy.filler;

import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.tinkoff.academy.filler.client.HandymanClient;
import ru.tinkoff.academy.filler.client.LandscapeClient;
import ru.tinkoff.academy.filler.client.RancherClient;
import ru.tinkoff.academy.filler.data.PaymentSystem;
import ru.tinkoff.academy.filler.dto.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class FillerApplication implements ApplicationRunner {
    private final HandymanClient handymanClient;
    private final RancherClient rancherClient;
    private final LandscapeClient landscapeClient;
    private final String filePath;
    private final static Random random = new Random();
    private final static String[] fixedSkills = new String[]{"копать", "сажать", "поливать"};

    public FillerApplication(
            HandymanClient handymanClient,
            RancherClient rancherClient,
            LandscapeClient landscapeClient,
            @Value("${path.stabina}") String filePath
    ) {
        this.handymanClient = handymanClient;
        this.rancherClient = rancherClient;
        this.landscapeClient = landscapeClient;
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        SpringApplication.run(FillerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        long totalCount = 0;
        long createdCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            Pattern pattern = Pattern.compile("'.*?'");
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                List<String> values = new ArrayList<>();
                while (matcher.find()) {
                    values.add(matcher.group().replaceAll("'", ""));
                }
                String type = values.get(0);
                String login = values.get(1);
                String email = values.get(2);
                String phone = values.get(3);
                String[] names = login.split(" ");
                String lastName = names[names.length - 1];
                String firstName = Arrays.stream(names).limit(names.length - 1).collect(Collectors.joining(" "));
                try {
                    switch (type) {
                        case "handyman" -> {
                            createHandyman(login, firstName, lastName, email, phone);
                            if (totalCount % 100 == 0) landscapeClient.getHandymanPaymentSystemStatistics();
                        }
                        case "rancher" -> {
                            createRancher(login, firstName, lastName, email, phone);
                            if (totalCount % 100 == 0) landscapeClient.getRancherStatistics(0, 100);
                        }
                        default -> throw new IllegalArgumentException(String.format("Unexpected user type %s", type));
                    }
                    createdCount++;
                } catch (RetryableException e) {
                    log.error(e.getMessage());
                } finally {
                    totalCount++;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info(String.format("Created %s/%s", createdCount, totalCount));
    }

    private void createRancher(String login, String firstName, String lastName, String email, String phone) {
        CreatingRancherDto creatingRancherDto = CreatingRancherDto.builder()
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .latitude(genLatitude())
                .longitude(genLatitude() * 2)
                .fields(genFields())
                .build();

        rancherClient.create(creatingRancherDto);
    }

    private void createHandyman(String login, String firstName, String lastName, String email, String phone) {
        CreatingHandymanDto creatingHandymanDto = CreatingHandymanDto.builder()
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .photo(Base64.encodeBase64String(genBytes()))
                .latitude(genLatitude())
                .longitude(genLatitude() * 2)
                .skills(genSkills())
                .accounts(genAccounts())
                .build();

        handymanClient.create(creatingHandymanDto);
    }

    private byte[] genBytes() {
        int length = random.nextInt(1, 10);
        var byteArray = new byte[length];
        random.nextBytes(byteArray);
        return byteArray;
    }

    private List<InnerSkillDto> genSkills() {
        int n = random.nextInt(1, 4);
        return Arrays.asList(fixedSkills).subList(0, n).stream().map(it -> InnerSkillDto.builder().name(it).build()).toList();
    }

    private List<InnerAccountDto> genAccounts() {
        int n = random.nextInt(1, 5);
        return IntStream.range(0, n)
                .mapToObj(i -> InnerAccountDto.builder()
                        .paymentSystem(PaymentSystem.values()[random.nextInt(PaymentSystem.values().length)])
                        .cardNumber(genCardNumber())
                        .build()
                ).toList();
    }

    private String genCardNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
            if (i % 4 == 3 && i != 15) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    private List<InnerFieldDto> genFields() {
        int n = random.nextInt(0, 4);
        return IntStream.range(0, n).mapToObj(i -> InnerFieldDto.builder()
                .address(generateAddress())
                .latitude(genLatitude())
                .longitude(genLatitude() * 2)
                .area(random.nextDouble(20, 100))
                .build()
        ).toList();
    }

    private double genLatitude() {
        return random.nextDouble(-90, 90);
    }

    private static String generateAddress() {
        StringBuilder sb = new StringBuilder();
        String[] streetNames = {"Main", "Oak", "Maple", "Cedar", "Elm", "Pine", "Spruce"};
        String[] streetTypes = {"Street", "Avenue", "Road", "Lane", "Boulevard"};
        int streetNumber = random.nextInt(1000) + 1;
        String streetName = streetNames[random.nextInt(streetNames.length)];
        String streetType = streetTypes[random.nextInt(streetTypes.length)];
        String city = "City" + (random.nextInt(10) + 1);
        String state = "State" + (random.nextInt(5) + 1);
        int zipCode = random.nextInt(90000) + 10000;
        sb.append(streetNumber).append(" ").append(streetName).append(" ").append(streetType).append(", ");
        sb.append(city).append(", ").append(state).append(" ").append(zipCode);
        return sb.toString();
    }
}
