package com.kss22.exchange.analytics;

import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.ExchangeRates;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class analytics implements Initializable {
    @FXML
    private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private TableView<com.kss22.exchange.api.model.analytics> tableView;
    @FXML private TableColumn<com.kss22.exchange.api.model.analytics, Long> numTransactions;
    @FXML private TableColumn<com.kss22.exchange.api.model.analytics, Long> totalUsdAmount;
    @FXML private TableColumn<com.kss22.exchange.api.model.analytics, Long> totalLbpAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numTransactions.setCellValueFactory(new PropertyValueFactory<>("numTransactions"));
        totalUsdAmount.setCellValueFactory(new PropertyValueFactory<>("totalUsdAmount"));
        totalLbpAmount.setCellValueFactory(new PropertyValueFactory<>("totalLbpAmount"));
        loadData();
    }

    @FXML
    public void filter(ActionEvent event) {
        loadData();
    }


    private void loadData() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();
        if (fromDate == null || toDate == null) {
            return;
        }
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call,
                                   Response<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.body();
//                numTransactions = exchangeRates.numBuy;
            }

            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable
                    throwable) {
            }
        });
    }
}
