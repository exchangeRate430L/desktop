package com.kss22.exchange.login;

import com.kss22.exchange.Authentication;
import com.kss22.exchange.OnPageCompleteListener;
import com.kss22.exchange.PageCompleter;
import com.kss22.exchange.api.ExchangeService;
import com.kss22.exchange.api.model.Token;
import com.kss22.exchange.api.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login implements PageCompleter {
    private OnPageCompleteListener onPageCompleteListener;
    public TextField usernameTextField;
    public TextField passwordTextField;

    public void login(ActionEvent actionEvent) {
        User user = new User(usernameTextField.getText(), passwordTextField.getText());
        ExchangeService.exchangeApi().authenticate(user).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Authentication.getInstance().saveToken(response.body().getToken());
                Platform.runLater(() -> {
                    onPageCompleteListener.onPageCompleted();
                });
            }

            @Override
            public void onFailure(Call<Token> call, Throwable throwable) {
            }
        });
    }
    public void setOnPageCompleteListener(OnPageCompleteListener
                                                  onPageCompleteListener) {
        this.onPageCompleteListener = onPageCompleteListener;
    }
}
