package com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse.OfferRequestData;

/**
 * Created by User on 10/30/2017.
 */
import java.util.List;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse.OfferRequestData.Offer.Offer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferRequestData {

    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}