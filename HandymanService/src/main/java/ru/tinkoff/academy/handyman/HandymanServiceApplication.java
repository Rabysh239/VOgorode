package ru.tinkoff.academy.handyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.tinkoff.academy.handyman.conf.GRPCProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(GRPCProperties.class)
public class HandymanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandymanServiceApplication.class, args);
	}

}
