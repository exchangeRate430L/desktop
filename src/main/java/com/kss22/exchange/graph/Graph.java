package com.kss22.exchange.graph;

import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.ExchangeRates;
import com.kss22.exchange.api.model.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Graph implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    public void filter(ActionEvent event) {
        loadData();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call,
                                   Response<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.body();
                List<Transaction> transactionList = null;
                transactionList = exchangeRates.chartData;
                populateGraph(transactionList);
            }

            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable
                    throwable) {
            }
        });

    }

    private void populateGraph(List<Transaction> transactions) {
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Exchange Rate");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Exchange Rate Fluctuation");

        for (Transaction transaction : transactions) {
            String date = transaction.getAddedDate();
            float exchangeRate = transaction.getUsdToLbp() ? transaction.getLbpAmount() / transaction.getUsdAmount()
                    : transaction.getUsdAmount() / transaction.getLbpAmount();
            dataSeries.getData().add(new XYChart.Data<>(date, exchangeRate));
        }

        lineChart.getData().add(dataSeries);
    }
}