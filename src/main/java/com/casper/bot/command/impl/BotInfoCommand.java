package com.casper.bot.command.impl;

import com.casper.bot.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@RequiredArgsConstructor
public class BotInfoCommand implements Command {

  private final TelegramLongPollingBot bot;
  private final Long chatId;

  private static final String INFO_MESSAGE = "<b>Currency Bot</b> — бот для просмотра "
      + "текущего курса иностранной валюты к белорусскому рублю.\n\n"
      + "<b>Доступные команды:</b>\n"
      + "/info — информация о боте\n"
      + "/usd — курс доллара США";

  @Override
  @SneakyThrows
  public void execute() {
    log.info("BotInfoCommand execute() in chat with id {}", chatId);

    bot.execute(
        SendMessage
            .builder()
            .chatId(chatId.toString())
            .text(INFO_MESSAGE)
            .parseMode(ParseMode.HTML)
            .build()
    );
  }

}
