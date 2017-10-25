package com.dasteeny.hcb_creditassitant.Utils;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse;
import com.dasteeny.hcb_creditassitant.JsonObjects.SendSMSCode.SMS.SMS;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 10/12/2017.
 */

public interface HCBClient {

    @FormUrlEncoded
    @POST("test")
    Call<String> getTest(@Field("language") String language, @Field("deviceID") String deviceID, @Field("demo") String demo);

    @FormUrlEncoded
    @POST("sendSmsCodeSimple")
    Call<SMS> sendSMS(@Field("phoneNumber") String phonenumber, @Field("deviceID") String deviceID);//, @Field("language") String language, @Field("demo") String demo);

    @FormUrlEncoded
    @POST("getProducts")
    Call<GetProductsResponse> getProducts(@Field("idRequest") String idRequest, @Field("phoneNumber") String phoneNumber, @Field("deviceID") String deviceID, @Field("smsCode") String smsCode);
}
