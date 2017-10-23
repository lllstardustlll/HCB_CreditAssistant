package com.dasteeny.hcb_creditassitant.ProductsListView;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by User on 10/10/2017.
 */

public class ListItem {

    private String prodName;
    private int creditAmount;
    private String currency;
    private String openDate;

    public ListItem(String prodName, int creditAmount, String currency, String openDate) {
        this.prodName = prodName;
        this.creditAmount = creditAmount;
        this.currency = currency;
        this.openDate = openDate;
    }

    protected ListItem(Parcel in) {
        prodName = in.readString();
        creditAmount = in.readInt();
        currency = in.readString();
        openDate = in.readString();
    }

    public String getProdName() {
        return prodName;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOpenDate() {
        return openDate;
    }

}
