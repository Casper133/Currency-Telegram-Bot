package com.casper.bot.command.impl;

import static java.lang.String.format;

import com.casper.bot.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@RequiredArgsConstructor
public class UsdPriceCommand implements Command {

  private final TelegramLongPollingBot bot;
  private final Long chatId;

  private static final String MESSAGE_FORMAT = "1 USD = %.2f BYN";

  @Override
  @SneakyThrows
  public void execute() {
    log.info("UsdPriceCommand execute() in chat with id {}", chatId);

    // TODO: Get price from API
    double price = 0.0;

    bot.execute(
        new SendMessage()
            .setChatId(chatId)
            .setText(format(MESSAGE_FORMAT, price))
    );
  }

}
