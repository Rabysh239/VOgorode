package ru.tinkoff.academy.landscape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.tinkoff.academy.landscape.conf.GRPCProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(GRPCProperties.class)
public class LandscapeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandscapeServiceApplication.class, args);
	}

}
