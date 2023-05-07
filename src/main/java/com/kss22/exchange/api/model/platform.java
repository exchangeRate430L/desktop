package com.kss22.exchange.api.model;

import com.google.gson.annotations.SerializedName;


public class platform {

    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("seller_id")
    private String sellerId;
    @SerializedName("buyer_id")
    private String buyerId;
    @SerializedName("usd_amount")
    private float usdAmount;
    @SerializedName("lbp_amount")
    private float lbpAmount;

    public platform(String transactionId, String sellerId, String buyerId, float usdAmount, float lbpAmount) {
        this.transactionId = transactionId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.usdAmount = usdAmount;
        this.lbpAmount = lbpAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public float getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(float usdAmount) {
        this.usdAmount = usdAmount;
    }

    public float getLbpAmount() {
        return lbpAmount;
    }

    public void setLbpAmount(float lbpAmount) {
        this.lbpAmount = lbpAmount;
    }
}
