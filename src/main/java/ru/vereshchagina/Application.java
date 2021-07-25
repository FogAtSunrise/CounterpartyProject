package ru.vereshchagina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "ru.vereshchagina.repository")
@EntityScan("ru.vereshchagina.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
