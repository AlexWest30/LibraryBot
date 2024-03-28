package de.sindeev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.sindeev")
public class NodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeApplication.class);
	}
}
