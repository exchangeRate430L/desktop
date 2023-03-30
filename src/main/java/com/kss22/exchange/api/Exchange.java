package com.kss22.exchange.api;

import com.kss22.exchange.api.model.ExchangeRates;
import com.kss22.exchange.api.model.Transaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface Exchange {
    @GET("/exchangeRate")
    Call<ExchangeRates> getExchangeRates();
    @POST("/transaction")
    Call<Object> addTransaction(@Body Transaction transaction);
}