package com.casper.bot.api;

import com.casper.bot.dto.CurrencyPriceDto;
import retrofit2.Response;

public interface BankApiFacade {

  Response<CurrencyPriceDto> loadUsdPrice();

}
