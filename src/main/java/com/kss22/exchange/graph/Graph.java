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
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Graph implements Initializable {
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;
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
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (response.isSuccessful()) {
                            Platform.runLater(() -> {
                                populateGraph(response.body());
                            });
                        } else {
                            // TODO: Handle response error
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable throwable) {
                        // TODO: Handle failure case
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