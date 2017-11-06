package com.dasteeny.hcb_creditassitant.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dasteeny.hcb_creditassitant.R;

/**
 * Created by User on 10/26/2017.
 */

public class OffersFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("Frag", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        TextView fullMsgTextView = (TextView) view.findViewById(R.id.fullMsgTextView);
        TextView shortMsgTextView = (TextView) view.findViewById(R.id.shorMsgTextView);

        Button callBtn = (Button) view.findViewById(R.id.callOfferButton);
        Button orderCallBtn = (Button) view.findViewById(R.id.orderCallOfferButton);
        Button requestOfferBtn = (Button) view.findViewById(R.id.leaveRequestOfferButton);

        String fullMsg = this.getArguments().getString("fullMsg");
        //TODO: Perform gender check and show appropriate welcome message
        String clientName = fullMsg.split(" ")[2];

        shortMsgTextView.setText(String.format(getResources().getString(R.string.offersWelcomeM), clientName));
        fullMsgTextView.setText(fullMsg);

        return view;
    }
}
