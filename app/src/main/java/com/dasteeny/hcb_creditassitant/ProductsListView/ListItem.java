package com.dasteeny.hcb_creditassitant.ProductsListView;

/**
 * Created by User on 10/10/2017.
 */

public class ListItem {

    private String prodName;
    private String details;

    public ListItem(String prodName, String details) {
        this.prodName = prodName;
        this.details = details;
    }

    public String getProdName() {
        return prodName;
    }

    public String getDetails() {
        return details;
    }
}
