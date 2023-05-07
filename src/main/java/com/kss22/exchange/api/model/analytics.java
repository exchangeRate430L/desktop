package com.kss22.exchange.api.model;

public class analytics {

    @SerializedName("total_usd_amount")
    public Float totalUsdAmount;
    @SerializedName("total_lbp_amount")
    public Float totalLbpAmount;
    @SerializedName("total_transactions")
    public Integer totalTransactions;

    public analytics(Float totalUsdAmount, Float totalLbpAmount, Integer totalTransactions) {
        this.totalUsdAmount = totalUsdAmount;
        this.totalLbpAmount = totalLbpAmount;
        this.totalTransactions = totalTransactions;
    }
