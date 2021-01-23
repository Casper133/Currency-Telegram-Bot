package com.casper.bot.command.impl;

import static java.lang.String.format;

import com.casper.bot.api.BankApiFacade;
import com.casper.bot.api.NationalBankApi;
import com.casper.bot.command.Command;
import com.casper.bot.dto.CurrencyPriceDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import retrofit2.Response;

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
    BankApiFacade bankApiFacade = new NationalBankApi();
    Response<CurrencyPriceDto> response = bankApiFacade.loadUsdPrice();

    if (!response.isSuccessful() || response.body() == null) {
      log.error("loadUsdPrice() call error: {}", response.errorBody());
      return;
    }

    CurrencyPriceDto currencyPriceDto = response.body();
    handleLoadPriceResponse(currencyPriceDto);
  }

  @SneakyThrows
  private void handleLoadPriceResponse(CurrencyPriceDto currencyPriceDto) {
    log.info("Successful loadUsdPrice() call to bank API, chat id == {}", chatId);
    double price = currencyPriceDto.getPrice();

    bot.execute(
        SendMessage
            .builder()
            .chatId(chatId.toString())
            .text(format(MESSAGE_FORMAT, price))
            .build()
    );
  }

}
