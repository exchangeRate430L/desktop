package com.kss22.exchange.graph;

import com.kss22.exchange.Authentication;
import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.Transaction;
import javafx.application.Platform;
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
        ExchangeService.exchangeApi().getTransactions("Bearer " +
                        Authentication.getInstance().getToken())
                .enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call,
                                           Response<List<Transaction>> response) {
                        List<Transaction> transactionsList = response.body();
                        populateGraph(transactionsList);
                    }
                    @Override
                    public void onFailure(Call<List<Transaction>> call,
                                          Throwable throwable) {
                    }
                });

    }

    private void populateGraph(List<Transaction> transactions) {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Exchange Rate Fluctuation");

        Platform.runLater(() -> {
            for (Transaction transaction : transactions) {
                String date = transaction.getAddedDate();
                float exchangeRate = transaction.getLbpAmount() / transaction.getUsdAmount();
                dataSeries.getData().add(new XYChart.Data<>(date, exchangeRate));
            }
            lineChart.getData().add(dataSeries);
        });


        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Exchange Rate");

    }
}