package com.kss22.exchange.analytics;

import com.kss22.exchange.Authentication;
import com.kss22.exchange.api.ExchangeService;
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
import java.util.ArrayList;
import java.util.List;
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
        ExchangeService.exchangeApi().getAnalytics("Bearer " +
                                Authentication.getInstance().getToken(),
                        fromDate.toString(), toDate.toString())
                .enqueue(new Callback<com.kss22.exchange.api.model.analytics>() {

                    @Override
                    public void onResponse(Call<com.kss22.exchange.api.model.analytics> call,
                                           Response<com.kss22.exchange.api.model.analytics> response) {
                        // Create a list of com.kss22.exchange.api.model.analytics objects from the response body
                        List<com.kss22.exchange.api.model.analytics> analyticsList = new ArrayList<>();
                        analyticsList.add(response.body());
                        // Set the items of the tableView with the created list
                        tableView.getItems().setAll(analyticsList);
                    }

                    @Override
                    public void onFailure(Call<com.kss22.exchange.api.model.analytics> call,
                                          Throwable throwable) {
                    }
                });
    }
}
