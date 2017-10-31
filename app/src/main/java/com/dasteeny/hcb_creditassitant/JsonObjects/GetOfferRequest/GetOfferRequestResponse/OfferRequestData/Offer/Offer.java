package com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse.OfferRequestData.Offer;

/**
 * Created by User on 10/30/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("offerId")
    @Expose
    private String offerId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("templateCode")
    @Expose
    private String templateCode;
    @SerializedName("shortMessage")
    @Expose
    private String shortMessage;
    @SerializedName("fullMessage")
    @Expose
    private String fullMessage;
    @SerializedName("enableOfferCard")
    @Expose
    private Boolean enableOfferCard;
    @SerializedName("pictureSrc")
    @Expose
    private String pictureSrc;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public Boolean getEnableOfferCard() {
        return enableOfferCard;
    }

    public void setEnableOfferCard(Boolean enableOfferCard) {
        this.enableOfferCard = enableOfferCard;
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }

}