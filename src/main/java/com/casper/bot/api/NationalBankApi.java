package com.casper.bot.api;

import com.casper.bot.api.adapter.NationalBankApiAdapter;
import com.casper.bot.dto.CurrencyPriceDto;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@SuppressWarnings("NullableProblems")
public class NationalBankApi implements BankApiFacade, Callback<CurrencyPriceDto> {

  private Consumer<CurrencyPriceDto> onResponseAction;

  private static final String BASE_URL = "https://www.nbrb.by/api/exrates/";
  private static final int USD_CURRENCY_ID = 145;

  @Override
  public void loadUsdPrice(Consumer<CurrencyPriceDto> onResponseAction) {
    this.onResponseAction = onResponseAction;

    Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build();

    NationalBankApiAdapter nationalBankApiAdapter = retrofit.create(NationalBankApiAdapter.class);
    Call<CurrencyPriceDto> currencyPriceCall = nationalBankApiAdapter.loadCurrencyPrice(USD_CURRENCY_ID);
    currencyPriceCall.enqueue(this);
  }

  @Override
  public void onResponse(Call<CurrencyPriceDto> call, Response<CurrencyPriceDto> response) {
    Objects.requireNonNull(call);
    if (response.isSuccessful()) {
      CurrencyPriceDto currencyPriceDto = response.body();
      onResponseAction.accept(currencyPriceDto);
    } else {
      log.error("loadUsdPrice() call error: {}", response.errorBody());
    }
  }

  @Override
  public void onFailure(Call<CurrencyPriceDto> call, Throwable t) {
    log.error("loadUsdPrice() call error:", t);
  }

}
