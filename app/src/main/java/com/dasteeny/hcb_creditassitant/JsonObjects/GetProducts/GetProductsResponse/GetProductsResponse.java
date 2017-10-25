package com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ProductsData;

public class GetProductsResponse {

    private String status;
    private ProductsData data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductsData getData() {
        return data;
    }

    public void setProductsData(ProductsData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}