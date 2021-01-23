package com.casper.bot;

import com.casper.bot.command.CommandInvoker;
import com.casper.bot.config.BotProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
public class Application {

  public static void main(String[] args) {
    try {
      initBot();
    } catch (Exception exception) {
      log.error("Critical bot error:", exception);
      System.exit(1);
    }
  }

  @SneakyThrows
  private static void initBot() {
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    BotProperties botProperties = new BotProperties();
    CommandInvoker commandInvoker = new CommandInvoker();
    telegramBotsApi.registerBot(new CurrencyBot(botProperties, commandInvoker));
  }

}
