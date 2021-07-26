# Курсовой проект "Справочник контрагентов"
Автор: Верещагина И.А.

Для работы со справочником:
Подключить модуль к БД. 
Обновить файл application.properties исходя из ваших настроек БД

>spring.datasource.driver-class-name=org.postgresql.Driver 
>
>spring.datasource.url=jdbc:postgresql://localhost:5432/postgres 
>
>spring.datasource.username=postgres 
>
>spring.datasource.password=postgres 

# Запуск приложения

Команда для запуска приложения:
>./gradlew bootrun 

Команда для запуска тестов:
>./gradlew test 

# Swagger Api Documentation
После запуска документация доступна по ссылке: http://localhost:8080/swagger-ui/

Также запуск будет доступен по кнопке на Стартовой странице (http://localhost:8080/)
