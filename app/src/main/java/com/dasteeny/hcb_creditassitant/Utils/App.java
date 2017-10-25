package com.dasteeny.hcb_creditassitant.Utils;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 10/18/2017.
 */

public class App extends Application {

    private final String API_BASE_URL = "https://ibank24.kz:9443/MobileLightApi_TEST/api/light/LoanMobile/";

    private static HCBClient hcbClient;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        hcbClient = retrofit.create(HCBClient.class);
    }

    public static HCBClient getHcbClient() {
        return hcbClient;
    }

}
