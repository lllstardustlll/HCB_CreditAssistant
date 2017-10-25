package com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct;

/**
 * Created by User on 10/18/2017.
 */

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.CreditOpenDate;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.DueDate;

import java.io.Serializable;

public class ClientProduct implements Serializable{

    private String contractNumber;
    private Integer dueAmount;
    private String loanType;
    private Double availableLimit;
    private String currency;
    private Integer creditAmount;
    private CreditOpenDate creditOpenDate;
    private String productType;
    private Integer overdueAmount;
    private Integer overdueNumber;
    private DueDate dueDate;
    private Integer currentCreditAmount;

    public ClientProduct(String contractNumber, Integer dueAmount, String loanType, Double availableLimit, String currency, Integer creditAmount, CreditOpenDate creditOpenDate, String productType, Integer overdueAmount, Integer overdueNumber, DueDate dueDate, Integer currentCreditAmount) {
        this.contractNumber = contractNumber;
        this.dueAmount = dueAmount;
        this.loanType = loanType;
        this.availableLimit = availableLimit;
        this.currency = currency;
        this.creditAmount = creditAmount;
        this.creditOpenDate = creditOpenDate;
        this.productType = productType;
        this.overdueAmount = overdueAmount;
        this.overdueNumber = overdueNumber;
        this.dueDate = dueDate;
        this.currentCreditAmount = currentCreditAmount;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Integer getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Integer dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(Double availableLimit) {
        this.availableLimit = availableLimit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public CreditOpenDate getCreditOpenDate() {
        return creditOpenDate;
    }

    public void setCreditOpenDate(CreditOpenDate creditOpenDate) {
        this.creditOpenDate = creditOpenDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(Integer overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public Integer getOverdueNumber() {
        return overdueNumber;
    }

    public void setOverdueNumber(Integer overdueNumber) {
        this.overdueNumber = overdueNumber;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getCurrentCreditAmount() {
        return currentCreditAmount;
    }

    public void setCurrentCreditAmount(Integer currentCreditAmount) {
        this.currentCreditAmount = currentCreditAmount;
    }
}