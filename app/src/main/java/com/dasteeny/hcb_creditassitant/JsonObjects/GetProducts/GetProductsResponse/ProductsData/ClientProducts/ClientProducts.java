package com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts;

/**
 * Created by User on 10/18/2017.
 */

import java.util.List;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.ClientProduct;

public class ClientProducts {

    private List<ClientProduct> clientProduct = null;

    public List<ClientProduct> getClientProduct() {
        return clientProduct;
    }

    public void setClientProduct(List<ClientProduct> clientProduct) {
        this.clientProduct = clientProduct;
    }

}