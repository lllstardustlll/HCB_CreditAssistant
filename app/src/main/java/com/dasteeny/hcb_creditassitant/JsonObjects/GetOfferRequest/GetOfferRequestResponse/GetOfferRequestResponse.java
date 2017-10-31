package com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse;

/**
 * Created by User on 10/30/2017.
 */

import com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse.OfferRequestData.OfferRequestData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOfferRequestResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private OfferRequestData data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OfferRequestData getData() {
        return data;
    }

    public void setData(OfferRequestData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }
}