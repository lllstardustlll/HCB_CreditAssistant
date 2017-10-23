package com.dasteeny.hcb_creditassitant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.ProductsData.ClinetProducts.ClinetProduct.ClientProduct;
import java.util.Date;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.GetProductsResponse.ProductsData.ClinetProducts.ClinetProduct.Dates.DueDate;
import com.dasteeny.hcb_creditassitant.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import at.grabner.circleprogress.CircleProgressView;

public class CreditsActivity extends AppCompatActivity {

    TextView creditsLeftDays;
    TextView creditsDebt;
    TextView creditsNextDateDue;
    TextView creditsSchedule;
    TextView creditsDealTotalDebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Getting data from LoansFragment
        //TODO: Manage debt formatting
        Intent prevIntent = getIntent();
        Gson gson = new Gson();
        ClientProduct clientProduct = gson.fromJson(prevIntent.getStringExtra("myJson"), ClientProduct.class);

        //Getting TextViews
        creditsLeftDays = (TextView) findViewById(R.id.credits_left_days);
        creditsDebt = (TextView) findViewById(R.id.credits_debt);
        creditsNextDateDue = (TextView) findViewById(R.id.credits_next_due_date);
        creditsSchedule = (TextView) findViewById(R.id.credits_schedule);
        creditsDealTotalDebt = (TextView) findViewById(R.id.credits_deal_total_debt);



        if (clientProduct.getProductType().equals("SS")) {
            //Calculating amount of left days
            long difference;
            int leftDays = 0;

            DueDate dd = clientProduct.getDueDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date dueDate = null;
            try {
                dueDate = dateFormat.parse(Integer.toString(dd.getDay()) + "." + Integer.toString(dd.getMonth()) + "." + Integer.toString(dd.getYear()));
            }catch (Exception e) {
                Log.e("DATE ERROR", e.toString());
            }

            getSupportActionBar().setTitle(R.string.loansCashType);
            Date today = Calendar.getInstance().getTime();
            difference = Math.abs(today.getTime() - dueDate.getTime());
            leftDays = (int) (difference / (24 * 60 * 60 * 1000) + 1);
            creditsLeftDays.setText(getResources().getQuantityString(R.plurals.creditsLeftDays, leftDays, leftDays));
            //Setting TextViews
            creditsDebt.setText(String.format(getResources().getString(R.string.creditsDebt), clientProduct.getDueAmount()));
            creditsNextDateDue.setText(String.format(getResources().getString(R.string.creditsNextDateDue), dateFormat.format(dueDate)));
            creditsDealTotalDebt.setText(String.format(getResources().getString(R.string.creditsDealTotalDebt), clientProduct.getCurrentCreditAmount(), clientProduct.getCurrency()));

            //Setting ProgressBar
            CircleProgressView mCircleViewUnderlay = (CircleProgressView) findViewById(R.id.credits_underlay_progress_bar);
            mCircleViewUnderlay.setValue(100);
            CircleProgressView mCircleView = (CircleProgressView) findViewById(R.id.credits_progress_bar);
            if (leftDays > 30) {
                mCircleView.setValue(100);
            }else {
                mCircleView.setValue((float) leftDays / 30 * 100);
            }

        } else {

            getSupportActionBar().setTitle(R.string.loansCashType);
        }

    }

}
