module com.kss22.exchange {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kss22.exchange to javafx.fxml;
    exports com.kss22.exchange;
}