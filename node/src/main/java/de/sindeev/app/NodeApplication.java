package de.sindeev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "de.sindeev")
@EnableJpaRepositories("de.sindeev.entity")
@EntityScan("de.sindeev.*")   
public class NodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeApplication.class);
	}
}
