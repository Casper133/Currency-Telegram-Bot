package com.casper.bot.api.adapter;

import com.casper.bot.dto.CurrencyPriceDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NationalBankApiAdapter {

  @GET("rates/{currency_id}")
  Call<CurrencyPriceDto> loadCurrencyPrice(@Path("currency_id") int currencyId);

}
