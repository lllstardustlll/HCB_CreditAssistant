package com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.ProductsData.ProductsData;

public class GetProductsResponse {

    private String status;
    private ProductsData data;

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

}