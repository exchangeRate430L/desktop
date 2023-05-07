module com.kss22.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    requires java.prefs;
    opens com.kss22.exchange to javafx.fxml;
    opens com.kss22.exchange.api.model to javafx.base, gson;
    opens com.kss22.exchange.login to javafx.fxml;
    opens com.kss22.exchange.transactions to javafx.fxml;
    opens com.kss22.exchange.register to javafx.fxml;
    exports com.kss22.exchange;
    exports com.kss22.exchange.rates;
    opens com.kss22.exchange.rates to javafx.fxml;
    opens com.kss22.exchange.graph to javafx.fxml;
    opens com.kss22.exchange.analytics to javafx.fxml;
}