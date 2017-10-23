package com.dasteeny.hcb_creditassitant.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.dasteeny.hcb_creditassitant.Activities.CreditsActivity;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.GetProductsResponse;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.ProductsData.ClinetProducts.ClinetProduct.ClientProduct;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.ProductsData.ClinetProducts.ClinetProduct.Dates.CreditOpenDate;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.ProductsData.ClinetProducts.ClinetProduct.Dates.DueDate;
import com.dasteeny.hcb_creditassitant.ProductsListView.CustomAdapter;
import com.dasteeny.hcb_creditassitant.ProductsListView.ListItem;
import com.dasteeny.hcb_creditassitant.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dasteeny.hcb_creditassitant.App.getHcbClient;

/**
 * Created by User on 10/6/2017.
 */

public class LoansFragment extends Fragment {

    private ArrayList<ListItem> prodsList = new ArrayList<>();
    private ListView mLoansListView;
    private ProgressBar loansProgressBar;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loans, container, false);

        mLoansListView = (ListView) view.findViewById(R.id.loans_list_view);
        loansProgressBar = (ProgressBar) view.findViewById(R.id.loansProgressBar);

        //Getting ListView Content and Showing it
        Call<GetProductsResponse> call = getHcbClient().getProducts("0", "7472503904", "0000", "12345");
        call.enqueue(new Callback<GetProductsResponse>() {
            @Override
            public void onResponse(Call<GetProductsResponse> call, final Response<GetProductsResponse> response) {
                Log.d("Response code", String.valueOf(response.code()));

                if (!response.body().getStatus().equals("error")) {

                    final List<ClientProduct> clientProducts = response.body().getData().getClientProducts().getClientProduct();
                    clientProducts.add(new ClientProduct("123456789", 24000, "CEL", 0.0, "USD", 240000,
                            new CreditOpenDate(2017, 3, 24, 360, 0, 0, 0),
                            "SS", 0, null,
                            new DueDate(2017, 10, 25, 360, 0, 0, 0),
                            240000));

                    loansProgressBar.setVisibility(View.INVISIBLE);
                    CustomAdapter customAdapter = new CustomAdapter(clientProducts, getContext());
                    mLoansListView.setAdapter(customAdapter);

                    mLoansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            ClientProduct listItem = clientProducts.get(position);
                            Gson gson = new Gson();
                            String myJson = gson.toJson(listItem);
                            Intent intent = new Intent(getActivity(), CreditsActivity.class);
                            intent.putExtra("myJson", myJson);
                            startActivity(intent);
                        }
                    });
                } else {
                    //TODO: Manage service unavailability
                    Log.d("ERROR", "Service unavailable");
                }
            }

            @Override
            public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                Log.d("TEST", String.valueOf(t.getCause()));
            }
        });

        return view;
    }

}
