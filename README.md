## Инструкция запуска авто-тестов
### Подготовительный этап

1. Установить и запустить **IntelliJ IDEA**
2. Установить и запустить **Docker Desktop**
3. Установить **Google Chrome** (или другой браузер)
4. Клонировать проект через GitHub [по ссылке](https://github.com/lizvalk/Diploma) себе на ПК
### Запуск тестов
1. Через терминал запустить три контейнера для формирования тестового окружения: MySQL, PostgreSQL, NodeJS с помощью команды:
```
docker-compose up --build
```
2. В новой вкладке терминала запустить SUT:

**Для MySQL:**
```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```
**Для PostgreSQL:**
```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```
3. Тестируемое приложение должно быть доступно по адресу: _http://localhost:8080/_

4. В новой вкладке терминала запустить авто-тесты командой:

**Для MySQL:**
```
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```
**Для PostgreSQL:**
```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```
5. Сформировать отчёт о тестировании с помощью Allure через команду:
```
./gradlew allureServe
```
Остановить работу allureServe в терминале сочетанием клавиш **CTRL + C**
