package com.suguiura.Agendatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendaTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaTaskApplication.class, args);
	}

}
