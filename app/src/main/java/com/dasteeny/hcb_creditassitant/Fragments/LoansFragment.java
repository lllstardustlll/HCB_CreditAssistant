package com.dasteeny.hcb_creditassitant.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.dasteeny.hcb_creditassitant.R;
import com.dasteeny.hcb_creditassitant.Utils.Retriever;


/**
 * Created by User on 10/6/2017.
 */

public class LoansFragment extends Fragment {

    public Retriever retriever;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("Frag", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i("Frag", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_loans, container, false);

        ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loansProgressBar);
        spinner.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        retriever = new Retriever(view, getContext());
        retriever.getProducts("0", "7472503904", "0000", "12345");

        return view;
    }

    /*private void fillPage(){

    }

    private Object getData(){
        if (isInetEnabled){

        } else {

        }
    }*/
}