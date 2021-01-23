package com.casper.bot;

import com.casper.bot.command.CommandInvoker;
import com.casper.bot.command.impl.BotInfoCommand;
import com.casper.bot.command.impl.UsdPriceCommand;
import com.casper.bot.config.BotProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@RequiredArgsConstructor
public class CurrencyBot extends TelegramLongPollingBot {

  private final BotProperties botProperties;
  private final CommandInvoker commandInvoker;

  private static final String START_COMMAND = "/start";
  private static final String INFO_COMMAND = "/info";
  private static final String USD_COMMAND = "/usd";

  @Override
  public void onUpdateReceived(Update update) {
    try {
      if (update.hasMessage() && update.getMessage().hasText()) {
        Message message = update.getMessage();
        handleMessage(message.getChatId(), message.getText().toLowerCase());
      }
    } catch (Exception exception) {
      handleException(exception, update);
    }
  }

  private void handleMessage(Long chatId, String messageText) {
    switch (messageText) {
      case START_COMMAND:
      case INFO_COMMAND:
        commandInvoker.setCommand(new BotInfoCommand(this, chatId));
        break;
      case USD_COMMAND:
        commandInvoker.setCommand(new UsdPriceCommand(this, chatId));
        break;
      default:
        return;
    }

    commandInvoker.executeCommand();
  }

  @SneakyThrows
  private void handleException(Exception exception, Update update) {
    log.error("Bot error:", exception);

    if (update.hasMessage()) {
      this.execute(
          SendMessage
              .builder()
              .chatId(update.getMessage().getChatId().toString())
              .text("Произошла ошибка. Попробуйте снова.")
              .build()
      );
    }
  }

  @Override
  public String getBotUsername() {
    return botProperties.getBotUsername();
  }

  @Override
  public String getBotToken() {
    return botProperties.getBotToken();
  }

}
