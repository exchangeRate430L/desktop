package com.kss22.exchange.platform;

import com.kss22.exchange.Authentication;
import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.ExchangeRates;
import com.kss22.exchange.api.model.Transaction;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Platform {

    public Label buyUsdRateLabel;
    public Label sellUsdRateLabel;
    public TextField lbpTextField;
    public TextField usdTextField;
    public TextField toUserIdTextField;

    public ToggleGroup transactionType;
    public Label equivalentUsdRateLabel;
    public Label equivalentLbpRateLabel;

    public void set(){
        usdTextField.setText("");
        lbpTextField.setText("");
    }
    public void initialize() {
        fetchRates();
    }

    private void fetchRates() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call,
                                   Response<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.body();
                javafx.application.Platform.runLater(() -> {
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

    public void addTransaction(ActionEvent actionEvent) {

        Transaction transaction = new Transaction(
                Float.parseFloat(usdTextField.getText()),
                Float.parseFloat(lbpTextField.getText()),
                ((RadioButton)
                        transactionType.getSelectedToggle()).getText().equals("Sell USD"),
                Integer.parseInt(toUserIdTextField.getText())
        );
        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        ExchangeService.exchangeApi().addTransaction(transaction,authHeader).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object>
                    response) {
                javafx.application.Platform.runLater(() -> {
                    usdTextField.setText("");
                    lbpTextField.setText("");
                    toUserIdTextField.setText("");
                });
            }
            @Override
            public void onFailure(Call<Object> call, Throwable throwable)
            {
                System.out.println(throwable);
            }
        });
    }
}
