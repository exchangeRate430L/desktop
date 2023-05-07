package com.kss22.exchange.platform;

import com.kss22.exchange.api.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Platform implements Initializable {
    @FXML
    public void usdToLbpClicked(ActionEvent event) {
        fromCurrencyComboBox.setValue("USD");
        toCurrencyComboBox.setValue("LBP");
    }
    @FXML
    public void lbpToUsdClicked(ActionEvent event) {
        fromCurrencyComboBox.setValue("LBP");
        toCurrencyComboBox.setValue("USD");
    }

    @FXML
    private ComboBox<String> fromCurrencyComboBox;
    @FXML
    private ComboBox<String> toCurrencyComboBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button exchangeButton;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> dateColumn;
    @FXML
    private TableColumn<Transaction, String> fromColumn;
    @FXML
    private TableColumn<Transaction, String> toColumn;
    @FXML
    private TableColumn<Transaction, Float> amountColumn;

    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the currency combo boxes
        fromCurrencyComboBox.getItems().addAll("USD", "LBP");
        fromCurrencyComboBox.getSelectionModel().selectFirst();
        toCurrencyComboBox.getItems().addAll("USD", "LBP");
        toCurrencyComboBox.getSelectionModel().selectLast();

        // Set up the transaction table
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromCurrency"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toCurrency"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        transactionTable.setItems(transactions);
    }

    @FXML
    public void exchange(ActionEvent event) {
        String fromCurrency = fromCurrencyComboBox.getValue();
        String toCurrency = toCurrencyComboBox.getValue();
        float amount = Float.parseFloat(amountTextField.getText());

        // TODO: Implement exchange logic using API

        Transaction transaction = new Transaction(100f, 10000000f, true);
        transactions.add(transaction);
    }
}
