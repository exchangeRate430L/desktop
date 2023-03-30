module com.kss22.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    opens com.kss22.exchange to javafx.fxml;
    opens com.kss22.exchange.api.model to gson;
    exports com.kss22.exchange;
    exports com.kss22.exchange.rates;
    opens com.kss22.exchange.rates to javafx.fxml;
}