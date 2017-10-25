package com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates;

import java.io.Serializable;

/**
 * Created by User on 10/18/2017.
 */

public class DueDate implements Serializable {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer timezone;
    private Integer hour;
    private Integer minute;
    private Integer second;

    public DueDate(Integer year, Integer month, Integer day, Integer timezone, Integer hour, Integer minute, Integer second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.timezone = timezone;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

}