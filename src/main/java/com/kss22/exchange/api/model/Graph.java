package com.kss22.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class Graph {

    @SerializedName("date")
    public String date;
    @SerializedName("usd_amount")
    public Float usdAmount;
    @SerializedName("lbp_amount")
    public Float lbpAmount;

    public Graph(String date, Float usdAmount, Float lbpAmount) {
        this.date = date;
        this.usdAmount = usdAmount;
        this.lbpAmount = lbpAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(Float usdAmount) {
        this.usdAmount = usdAmount;
    }

    public Float getLbpAmount() {
        return lbpAmount;
    }

    public void setLbpAmount(Float lbpAmount) {
        this.lbpAmount = lbpAmount;
    }
}
