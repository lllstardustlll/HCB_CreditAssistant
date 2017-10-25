package com.dasteeny.hcb_creditassitant.Utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.ClientProduct;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.CreditOpenDate;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.DueDate;
import com.dasteeny.hcb_creditassitant.ProductsListView.CustomAdapter;
import com.dasteeny.hcb_creditassitant.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 10/24/2017.
 */

public class Retriever {

    private List<ClientProduct> clientProducts;
    private View view;
    private Context context;

    public Retriever(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void retrieve(String... params) {

        Call<GetProductsResponse> call = App.getHcbClient().getProducts(params[0], params[1], params[2], params[3]);

        call.enqueue(new Callback<GetProductsResponse>() {
            @Override
            public void onResponse(Call<GetProductsResponse> call, final Response<GetProductsResponse> response) {
                Log.d("Response code", String.valueOf(response.code()));

                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().getStatus().equals("error")) {

                        clientProducts = response.body().getData().getClientProducts().getClientProduct();
                        clientProducts.add(new ClientProduct("123456789", 24000, "CEL", 0.0, "USD", 240000, new CreditOpenDate(2017, 3, 24, 360, 0, 0, 0), "SS", 0, null, new DueDate(2017, 10, 25, 360, 0, 0, 0), 240000));

                        view.findViewById(R.id.loansProgressBar).setVisibility(View.INVISIBLE);
                        CustomAdapter customAdapter = new CustomAdapter(clientProducts, context);
                        ListView mLoansListView = (ListView) view.findViewById(R.id.loans_list_view);
                        mLoansListView.setAdapter(customAdapter);

                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //TODO: Manage service unavailability
                    Toast.makeText(context, "Проблема с сетью?", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                Log.d("TEST", String.valueOf(t.getCause()));
            }
        });

    }

}