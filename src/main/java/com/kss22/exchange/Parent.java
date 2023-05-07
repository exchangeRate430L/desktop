package com.kss22.exchange;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Parent implements Initializable, OnPageCompleteListener {
    public BorderPane borderPane;
    public Button transactionButton;
    public Button loginButton;
    public Button registerButton;
    public Button logoutButton;
    public Button analyticsButton;
    public Button graphButton;
    public Button platformButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateNavigation();
    }

    public void ratesSelected() {
        swapContent(Section.RATES);
    }

    public void transactionsSelected() {
        swapContent(Section.TRANSACTIONS);
    }

    public void loginSelected() {
        swapContent(Section.LOGIN);
    }

    public void registerSelected() {
        swapContent(Section.REGISTER);
    }

    public void logoutSelected() {
        Authentication.getInstance().deleteToken();
        swapContent(Section.RATES);
    }
    public void analyticsSelected() {
        swapContent(Section.Analytics);
    }
    public void graphSelected() {
        swapContent(Section.GRAPH);
    }
    public void platformSelected() {
        swapContent(Section.Platform);
    }

    private void swapContent(Section section) {
        try {
            URL url = getClass().getResource(section.getResource());
            FXMLLoader loader = new FXMLLoader(url);
            borderPane.setCenter(loader.load());
            if (section.doesComplete()) {
                ((PageCompleter)
                        loader.getController()).setOnPageCompleteListener(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateNavigation();
    }
    private void updateNavigation() {
        boolean authenticated = Authentication.getInstance().getToken() !=
                null;
        transactionButton.setManaged(authenticated);
        transactionButton.setVisible(authenticated);
        loginButton.setManaged(!authenticated);
        loginButton.setVisible(!authenticated);
        registerButton.setManaged(!authenticated);
        registerButton.setVisible(!authenticated);
        logoutButton.setManaged(authenticated);
        logoutButton.setVisible(authenticated);
        analyticsButton.setManaged(!authenticated);
        analyticsButton.setVisible(!authenticated);
        graphButton.setManaged(!authenticated);
        graphButton.setVisible(!authenticated);
        platformButton.setManaged(!authenticated);
        platformButton.setVisible(!authenticated);
    }

    private enum Section {
        RATES,
        TRANSACTIONS,
        LOGIN,
        REGISTER,
        Analytics,
        GRAPH,
        Platform;

        public String getResource() {
            return switch (this) {
                case RATES -> "/com/kss22/exchange/rates/rates.fxml";
                case TRANSACTIONS -> "/com/kss22/exchange/transactions/transactions.fxml";
                case LOGIN -> "/com/kss22/exchange/login/login.fxml";
                case REGISTER -> "/com/kss22/exchange/register/register.fxml";
                default -> null;
                case Analytics -> "/com/kss22/exchange/analytics/analytics.fxml";
                case GRAPH -> "/com/kss22/exchange/graph/graph.fxml";
                case Platform -> "/com/kss22/exchange/platform/platform.fxml";
            };
        }
        public boolean doesComplete() {
            return switch (this) {
                case LOGIN, REGISTER -> true;
                default -> false;
            };
        }
    }
    @Override
    public void onPageCompleted() {
        swapContent(Section.RATES);
    }
}
