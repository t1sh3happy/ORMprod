# ORMprod Project

## Описание
ORMprod — это учебное приложение для управления курсами, пользователями, заданиями и тестами. Реализованы основные сущности предметной области с полноценными связями, REST API, CRUD операции и интеграционные тесты.

---

## Технологии
- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- PostgreSQL (основная БД)
- H2 (для тестов)
- Maven
- Lombok
- JUnit 5, MockMvc (тестирование)

---

## Структура проекта
- `src/main/java/com/example/ormprod/entity` — модели данных (сущности)
- `src/main/java/com/example/ormprod/repository` — репозитории Spring Data
- `src/main/java/com/example/ormprod/service` — бизнес-логика
- `src/main/java/com/example/ormprod/web` — REST контроллеры
- `src/test/java/com/example/ormprod/web` — интеграционные тесты с использованием MockMvc и профиля `test`

---

## Запуск и конфигурация

### Запуск локально
1. Настроить PostgreSQL
2. В `src/main/resources/application.properties` указать параметры подключения к PostgreSQL
3. Собрать проект  
mvn clean install


4. Запустить приложение  
mvn spring-boot:run



### Тестовый профиль
- H2 in-memory используется для тестов с конфигурацией в `src/test/resources/application-test.properties`
- В тестах добавлен `@ActiveProfiles("test")`
- Для запуска тестов:
mvn test

## Основные REST API

- Курсы:  
- `GET /api/courses` — список курсов  
- `POST /api/courses` — создать курс  
- `GET /api/courses/{id}` — получить курс с модулями и уроками  
- `POST /api/courses/{id}/enroll?user={userId}` — записать пользователя на курс  

- Задания:  
- `POST /api/assignments` — создать задание  
- `POST /api/assignments/{id}/submit?user={userId}` — отправка решения

- Тесты (Quiz):  
- `GET /api/quizzes` — список тестов  
- `POST /api/quizzes` — создать тест  
- `POST /api/quizzes/{id}/submit` — отправить результаты теста

- Пользователи:  
- `GET /api/users/{id}/assignments` — получить решения пользователя

## Тестирование

- Тесты покрывают основные CRUD операции и REST контроллеры.
- Используют базу H2 для изоляции.
- Запускаются командой:
mvn test

## Docker

В проекте есть Dockerfile и docker-compose.yml для быстрого развёртывания с PostgreSQL:

1. Соберите jar-файл:
   mvn clean package

2. Запустите весь проект и PostgreSQL:
   docker-compose up

3. Приложение будет доступно на порту 8080, база — на 5432.
