package com.dasteeny.hcb_creditassitant.ClassesSMS;

/**
 * Created by User on 10/12/2017.
 */

public class SMSData {
    private int authorized;
    private int idRequest;

    public SMSData(int authorized, int idRequest) {
        this.authorized = authorized;
        this.idRequest = idRequest;
    }

    public String getAuthorized() {
        return String.valueOf(authorized);
    }

    public String getIdRequest() {
        return String.valueOf(idRequest);
    }
}
