package ru.tinkoff.academy.rancher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.tinkoff.academy.rancher.conf.GRPCProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(GRPCProperties.class)
public class RancherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RancherServiceApplication.class, args);
	}

}
