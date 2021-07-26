package ru.vereshchagina.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Класс конфигурация для Swagger-ui
 */
@Configuration
public class SpringFoxConfig {

    /**
     * метод, содержащий параметры для Swagger-а
     * (указывает на пакет, содержащий контроллер,
     * а также указывает предикат any())
     *
     * @return экземпляр класса Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.vereshchagina.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }

    /**
     * метод содержащий пользовательскую информацию об API
     *
     * @return экземпляр класса ApiInfo с указанными данными
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Справочник контрагентов",
                "Документация API",
                "1.0",
                "",
                new Contact("Vereshchagina Irina", "", "hjr462@mail.ru"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
}
