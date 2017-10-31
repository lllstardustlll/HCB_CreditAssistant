package com.dasteeny.hcb_creditassitant.JsonObjects.SendSMSCode.SMS;

import com.dasteeny.hcb_creditassitant.JsonObjects.SendSMSCode.SMS.SMSData.SMSData;

/**
 * Created by User on 10/12/2017.
 */

public class SMS {

    private String status;
    private SMSData data;
    private String message;

    public SMS() {
    }

    public String getStatus() {
        return status;
    }

    public SMSData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
