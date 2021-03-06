package ru.vereshchagina.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Класс конфигурации для подключения JPA-репозитория и сущности
 */
@Configuration
@EnableJpaRepositories(value = "ru.vereshchagina.dao")
@EntityScan("ru.vereshchagina.entity")
@EnableEncryptableProperties
public class ServiceConfig {
}
