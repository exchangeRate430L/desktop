package com.kss22.exchange.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExchangeRates {
    @SerializedName("usd_to_lbp")
    public Float usdToLbp;
    @SerializedName("lbp_to_usd")
    public Float lbpToUsd;
    @SerializedName("combined_data_day")
    public List chartData;

    @SerializedName("num_buy")
    public Float numBuy;

    @SerializedName("num_sell")
    public Float numSell;

    @SerializedName("change_lbp_usd")
    public Float lbpChange;

    @SerializedName("change_usd_lbp")
    public Float usdChange;
}