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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dasteeny.hcb_creditassitant.Activities.CreditsActivity;
import com.dasteeny.hcb_creditassitant.ProductsListView.CustomAdapter;
import com.dasteeny.hcb_creditassitant.ProductsListView.ListItem;
import com.dasteeny.hcb_creditassitant.R;

import java.util.ArrayList;

/**
 * Created by User on 10/6/2017.
 */

public class LoansFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getActivity().setTitle("Мои кредиты");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loans, container, false);

        ListView mLoansListView = (ListView) view.findViewById(R.id.loans_list_view);

        final ArrayList<ListItem> dataModels = new ArrayList<>();
        dataModels.add(new ListItem("Кредит на товар", "86 528 тг от 24.10.2017"));
        dataModels.add(new ListItem("Кредит наличными", "395 056 тг от 17.10.2017"));
        dataModels.add(new ListItem("Карточка с лимитом", "64 547 тг от 05.12.2017"));
        dataModels.add(new ListItem("Кредит наличными", "345 253 тг от 16.05.2015"));
        dataModels.add(new ListItem("Кредит на товар", "45 532 тг от 19.02.2016"));
        dataModels.add(new ListItem("Карточка с лимитом", "58 076 тг от 25.10.2013"));
        dataModels.add(new ListItem("Кредит на товар", "86 528 тг от 14.08.2015"));
        dataModels.add(new ListItem("Кредит наличными", "395 056 тг от 21.04.2015"));
        dataModels.add(new ListItem("Карточка с лимитом", "64 547 тг от 05.10.2014"));
        dataModels.add(new ListItem("Кредит наличными", "345 253 тг от 16.05.2015"));
        dataModels.add(new ListItem("Кредит на товар", "45 532 тг от 19.02.2016"));
        dataModels.add(new ListItem("Карточка с лимитом", "58 076 тг от 25.10.2013"));
        dataModels.add(new ListItem("Кредит на товар", "86 528 тг от 14.08.2015"));
        dataModels.add(new ListItem("Кредит наличными", "395 056 тг от 21.04.2015"));
        dataModels.add(new ListItem("Карточка с лимитом", "64 547 тг от 05.10.2014"));
        dataModels.add(new ListItem("Кредит наличными", "345 253 тг от 16.05.2015"));
        dataModels.add(new ListItem("Кредит на товар", "45 532 тг от 19.02.2016"));
        dataModels.add(new ListItem("Карточка с лимитом", "58 076 тг от 25.10.2013"));

        CustomAdapter customAdapter = new CustomAdapter(dataModels, getContext());
        mLoansListView.setAdapter(customAdapter);
        mLoansListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                ListItem listItem = dataModels.get(position);

                String[] debtNdateDue = listItem.getDetails().split(" тг от ");

                String debt = debtNdateDue[0];
                String dateDue = debtNdateDue[1];

                Intent intent = new Intent(getActivity(), CreditsActivity.class);
                intent.putExtra("title", listItem.getProdName());
                intent.putExtra("debt", debt);
                intent.putExtra("dateDue", dateDue);
                startActivity(intent);
            }
        });

        return view;
    }
}
