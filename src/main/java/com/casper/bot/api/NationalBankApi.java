package com.casper.bot.api;

import com.casper.bot.api.adapter.NationalBankApiAdapter;
import com.casper.bot.dto.CurrencyPriceDto;
import lombok.SneakyThrows;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NationalBankApi implements BankApiFacade {

  private static final String BASE_URL = "https://www.nbrb.by/api/exrates/";
  private static final int USD_CURRENCY_ID = 145;

  @Override
  @SneakyThrows
  public Response<CurrencyPriceDto> loadUsdPrice() {
    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build();

    NationalBankApiAdapter nationalBankApiAdapter = retrofit.create(NationalBankApiAdapter.class);

    return nationalBankApiAdapter
        .loadCurrencyPrice(USD_CURRENCY_ID)
        .execute();
  }

}
