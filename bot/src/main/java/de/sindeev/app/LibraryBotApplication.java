package de.sindeev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.sindeev")
public class LibraryBotApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(LibraryBotApplication.class, args);
	}
}
