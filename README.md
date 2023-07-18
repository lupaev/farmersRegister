# Тестовое задание для Компании ""
### Web приложение для регистрации фермеров в районах
### Функционал:
- Добавление района;
- Редактирование данных района;
- Получение списка районов в реестре/архиве;
- Добавление фермера.
- Получение данных фермера.
- Получение списка фермеров в реестре/архиве;
- Редактирование данных района;

### Используемые технологии:
- Java 8
- Spring 5
- Spring Boot 2.7.12
- База данных Postgres 15
- Liquibase 
- MupStruct 1.5.5
- Lombok 1.18.28
- Maven
- Swagger
- QueryDSL 5.0


### Установка и запуск Docker

#### Для Linux/Unix систем:
``git clone https://github.com/lupaev/farmersRegister.git && cd farmersRegister && ./mvnw clean install && docker-compose up -d``

#### Для Windows:
``git clone https://github.com/lupaev/farmersRegister.git && cd farmersRegister && mvnw.cmd clean install && docker-compose up -d``

для выключения команда:

``docker-compose stop``

Api через swagger: http://localhost:8080/swagger-ui/index.html

PostgreSQL работает на порте 5433
