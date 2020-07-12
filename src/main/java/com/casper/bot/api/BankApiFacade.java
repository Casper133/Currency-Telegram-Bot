package com.casper.bot.api;

import com.casper.bot.dto.CurrencyPriceDto;

import java.util.function.Consumer;

public interface BankApiFacade {

  void loadUsdPrice(Consumer<CurrencyPriceDto> onResponseAction);

}
