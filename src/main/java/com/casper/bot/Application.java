package com.casper.bot;

import com.casper.bot.property.BotProperties;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Slf4j
public class Application {

  public static void main(String[] args) {
    ApiContextInitializer.init();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

    try {
      telegramBotsApi.registerBot(new CurrencyBot(new BotProperties()));
    } catch (Exception exception) {
      log.error("Bot error:", exception);
      System.exit(1);
    }
  }

}
