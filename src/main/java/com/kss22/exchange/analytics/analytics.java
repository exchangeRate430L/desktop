package com.kss22.exchange.analytics;

import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.ExchangeRates;
import javafx.application.Platform;
import javafx.scene.control.Label;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class analytics {

    public Label numBuy;
    public Label numSell;

    public Label lbpChange;
    public Label usdChange;
    public void initialize() {
        fetchRates();
    }

    private void fetchRates() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call,
                                   Response<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.body();
                Platform.runLater(() -> {
                    numBuy.setText(exchangeRates.numBuy.toString());
                    numSell.setText(exchangeRates.numSell.toString());
                    lbpChange.setText(exchangeRates.lbpChange.toString());
                    usdChange.setText(exchangeRates.usdChange.toString());
                });
            }
            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable
                    throwable) {
            }
        });
    }
}
