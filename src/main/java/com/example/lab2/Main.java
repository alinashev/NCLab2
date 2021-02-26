package com.example.lab2;

import com.example.lab2.controller.ControllerAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example.lab2.controller")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
