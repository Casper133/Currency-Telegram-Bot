package com.casper.bot;

import com.casper.bot.property.BotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@RequiredArgsConstructor
public class CurrencyBot extends TelegramLongPollingBot {

  private final BotProperties botProperties;

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      Message receivedMessage = update.getMessage();

      SendMessage message = new SendMessage()
          .setChatId(receivedMessage.getChatId())
          .setText(receivedMessage.getText());

      try {
        execute(message);
        log.info("Message sent to user with id {}", receivedMessage.getChatId());
      } catch (TelegramApiException exception) {
        log.error("Error sending message:", exception);
      }
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
