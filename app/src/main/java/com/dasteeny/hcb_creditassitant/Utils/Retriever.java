package com.dasteeny.hcb_creditassitant.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse.GetOfferRequestResponse;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetOfferRequest.GetOfferRequestResponse.OfferRequestData.Offer.Offer;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.ClientProduct;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.CreditOpenDate;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.DueDate;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProducts;
import com.dasteeny.hcb_creditassitant.JsonObjects.SendSMSCode.SMS.SMS;
import com.dasteeny.hcb_creditassitant.ProductsListView.CustomAdapter;
import com.dasteeny.hcb_creditassitant.R;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by User on 10/24/2017.
 */

public class Retriever {

    private static final String FILENAME = "savedDataProducts";

    private View view;
    private Context context;

    private String idRequest;
    private List<Offer> offers;
    private List<ClientProduct> clientProducts;


    public Retriever(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    private static boolean isNetworkAvailable(Context context) {
        /*if (context == null) {
            return false;
        }*/
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getProductsFromFile() {

        try {
            Gson gson = new Gson();
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            char[] inputBuffer = new char[256];
            String json = "";
            int charRead;

            while ((charRead = inputStreamReader.read(inputBuffer)) > 0) {
                json += String.copyValueOf(inputBuffer, 0, charRead);
            }
            inputStreamReader.close();

            clientProducts = gson.fromJson(json, ClientProducts.class).getClientProduct();
            //Setting ListView CustomAdapter
            view.findViewById(R.id.loansProgressBar).setVisibility(View.INVISIBLE);
            CustomAdapter customAdapter = new CustomAdapter(clientProducts, context);
            ListView mLoansListView = (ListView) view.findViewById(R.id.loans_list_view);
            mLoansListView.setAdapter(customAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendSms(String... params) {

        if (isNetworkAvailable(context)) {

            Call<SMS> call = App.getHcbClient().sendSMS(params[0], params[1]);
            call.enqueue(new Callback<SMS>() {
                @Override
                public void onResponse(Call<SMS> call, Response<SMS> response) {
                    if (response.isSuccessful()) {
                        if ("success".equals(response.body().getStatus())) {
                            idRequest = response.body().getData().getIdRequest();
                        } else {
                            Log.d("Send SMS", response.body().getMessage());
                        }
                    } else {
                        Log.d("Send SMS", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<SMS> call, Throwable t) {
                    Log.d("Send SMS", t.toString());
                }
            });
        }
        return idRequest;
    }

    public void getProducts(String... params) {

        final Gson gson = new Gson();

        //Check whether there is an active internet connection or not
        if (isNetworkAvailable(context)) {

            Call<GetProductsResponse> call = App.getHcbClient().getProducts(params[0], params[1], params[2], params[3]);
            call.enqueue(new Callback<GetProductsResponse>() {
                @Override
                public void onResponse(Call<GetProductsResponse> call, final Response<GetProductsResponse> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null && !response.body().getStatus().equals("error")) {

                            //Get products list
                            clientProducts = response.body().getData().getClientProducts().getClientProduct();
                            clientProducts.add(new ClientProduct("123456789", 24000, "CEL", 0.0, "USD", 240000, new CreditOpenDate(2017, 3, 24, 360, 0, 0, 0), "SS", 0, null, new DueDate(2017, 10, 26, 360, 0, 0, 0), 240000));

                            //Save ClientProducts object into SharedPreferences
                            try {
                                FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                                String json = gson.toJson(response.body().getData().getClientProducts());
                                fos.write(json.getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Setting ListView CustomAdapter
                            view.findViewById(R.id.loansProgressBar).setVisibility(View.INVISIBLE);
                            CustomAdapter customAdapter = new CustomAdapter(clientProducts, context);
                            ListView mLoansListView = (ListView) view.findViewById(R.id.loans_list_view);
                            mLoansListView.setAdapter(customAdapter);

                        } else {
                            //If service is unavailable
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //TODO: Manage service unavailability
                        Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                    // TODO rewrite this solution
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            //If there is no connection, then read data from local file
            getProductsFromFile();
        }
    }

    public void getOfferRequest(String... params) {

        if (isNetworkAvailable(context)) {

            Call<GetOfferRequestResponse> call = App.getHcbClient().getOfferRequest(params[0], params[1], params[2], params[3]);
            call.enqueue(new Callback<GetOfferRequestResponse>() {
                @Override
                public void onResponse(Call<GetOfferRequestResponse> call, Response<GetOfferRequestResponse> response) {

                    if (response.isSuccessful()) {

                        if ("success".equals(response.body().getStatus())) {

                            offers = response.body().getData().getOffers();
                            NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
                            Menu menuNav = navigationView.getMenu();
                            MenuItem specOffs = menuNav.findItem(R.id.nav_spec_offers);
                            Drawable icon = menuNav.findItem(R.id.nav_spec_offers).getIcon();
                            if (offers != null && offers.size() > 0) {

                                specOffs.setEnabled(true);

                                icon.mutate();
                                icon.setColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

                                SpannableString ss = new SpannableString(specOffs.getTitle());
                                ss.setSpan(new TextAppearanceSpan(context, R.style.NavigationMenuSpecOffsStyle), 0, ss.length(), 0);
                                specOffs.setTitle(ss);

                                TextView badge = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.nav_spec_offers));
                                badge.setGravity(Gravity.CENTER_VERTICAL);
                                badge.setTypeface(null, Typeface.BOLD);
                                badge.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                badge.setText(Integer.toString(offers.size()));

                            }
                        } else {

                            Log.d("Special Offer", response.body().getMessage());

                        }

                    } else {

                        Log.d("Special Offers", response.errorBody().toString());

                    }

                }

                @Override
                public void onFailure(Call<GetOfferRequestResponse> call, Throwable t) {

                    Log.d("Special Offers", t.toString());

                }
            });

        }
    }
}