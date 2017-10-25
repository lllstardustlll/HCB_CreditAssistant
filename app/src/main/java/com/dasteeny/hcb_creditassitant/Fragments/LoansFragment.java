package com.dasteeny.hcb_creditassitant.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasteeny.hcb_creditassitant.R;
import com.dasteeny.hcb_creditassitant.Utils.Retriever;


/**
 * Created by User on 10/6/2017.
 */

public class LoansFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loans, container, false);

        Retriever retriever = new Retriever(view, getContext());
        retriever.retrieve("0", "7472503904", "0000", "12345");

        Log.d("TEST", "FINISHED");
        return view;
    }


}
