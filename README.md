# Currency-Telegram-Bot
Создать Телеграм-бота для отображения текущего курса доллара США по отношению к белорусскому рублю в формате 1 USD = %f BYN.

## Общие требования
- На команды **/start** и **/info** пользователю выводится описание функционала бота
- На команду **/usd** выводится текущий курс
- Данные берутся из API Национального Банка РБ (https://www.nbrb.by/apihelp/exrates)
- Обработка команд пользователя с помощью паттерна Command

## Требования по стеку технологий
- Java 11
- Gradle 6
- [TelegramBots](https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started)
- Retrofit 2
