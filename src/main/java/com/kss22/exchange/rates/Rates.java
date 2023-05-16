package com.kss22.exchange.rates;

import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.ExchangeRates;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rates {
    public Label buyUsdRateLabel;
    public Label sellUsdRateLabel;

    public Label equivalentUsdRateLabel;
    public Label equivalentLbpRateLabel;

    public TextField lbpTextField;
    public TextField usdTextField;

    public void initialize() {
        fetchRates();
    }

    public void set(){
        usdTextField.setText("");
        lbpTextField.setText("");
    }

    private void fetchRates() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call,
                                   Response<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.body();
                Platform.runLater(() -> {
                    buyUsdRateLabel.setText(exchangeRates.lbpToUsd.toString());
                    sellUsdRateLabel.setText(exchangeRates.usdToLbp.toString());
                });
            }
            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable
                    throwable) {
            }
        });
    }
    public void calculator() {
        String usdAmountString = usdTextField.getText().trim();
        String lbpAmountString = lbpTextField.getText().trim();

        // Check if the input is valid
        if (usdAmountString.isEmpty() && lbpAmountString.isEmpty()) {
            return;
        }

        // Convert the input to floats
        float usdAmount = 0;
        float lbpAmount = 0;
        if (!usdAmountString.isEmpty()) {
            try {
                usdAmount = Float.parseFloat(usdAmountString);
            } catch (NumberFormatException e) {
                return;
            }
        }
        if (!lbpAmountString.isEmpty()) {
            try {
                lbpAmount = Float.parseFloat(lbpAmountString);
            } catch (NumberFormatException e) {
                return;
            }
        }

        // Get the exchange rates from the labels
        float lbpToUsd = 0;
        float usdToLbp = 0;
        try {
            lbpToUsd = Float.parseFloat(sellUsdRateLabel.getText());
            usdToLbp = Float.parseFloat(buyUsdRateLabel.getText());
        } catch (NumberFormatException e) {
            return;
        }

        // Calculate the equivalent amounts and update the labels
        if (usdAmount != 0) {
            float equivalentLbp = usdAmount * usdToLbp;
            equivalentLbpRateLabel.setText(String.format("%.2f", equivalentLbp));
        }
        if (lbpAmount != 0) {
            float equivalentUsd = lbpAmount / lbpToUsd;
            equivalentUsdRateLabel.setText(String.format("%.2f", equivalentUsd));
        }
    }

}