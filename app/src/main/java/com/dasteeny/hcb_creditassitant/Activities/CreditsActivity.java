package com.dasteeny.hcb_creditassitant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.ClientProduct;

import java.util.Date;

import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.Dates.DueDate;
import com.dasteeny.hcb_creditassitant.R;

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
        Intent prevIntent = getIntent();
        ClientProduct clientProduct = null;
        Bundle extras = prevIntent.getExtras();
        if (extras != null) {
            clientProduct = (ClientProduct) prevIntent.getSerializableExtra("product");
        }

        //Getting TextViews
        creditsLeftDays = (TextView) findViewById(R.id.credits_left_days);
        creditsDebt = (TextView) findViewById(R.id.credits_debt);
        creditsNextDateDue = (TextView) findViewById(R.id.credits_next_due_date);
        creditsSchedule = (TextView) findViewById(R.id.credits_schedule);
        creditsDealTotalDebt = (TextView) findViewById(R.id.credits_deal_total_debt);

        //Depending on type of a product handle its details
        if (clientProduct.getProductType().equals("SS")) {
            //Setting a title
            getSupportActionBar().setTitle(R.string.loansCashType);

            //Calculating amount of left days
            Date dueDate = null;
            Date today = null;
            long difference = 0;
            int leftDays = 0;

            DueDate dd = clientProduct.getDueDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            //Setting ProgressBar
            CircleProgressView mCircleViewUnderlay = (CircleProgressView) findViewById(R.id.credits_underlay_progress_bar);
            mCircleViewUnderlay.setValue(100);
            CircleProgressView mCircleView = (CircleProgressView) findViewById(R.id.credits_progress_bar);

            try {
                dueDate = dateFormat.parse(Integer.toString(dd.getDay()) + "." + Integer.toString(dd.getMonth()) + "." + Integer.toString(dd.getYear()));
                today = Calendar.getInstance().getTime();
                difference = dueDate.getTime() - today.getTime();

                if (today.getTime() <= dueDate.getTime()) {
                    leftDays = (int) (difference / (24 * 60 * 60 * 1000) + 1);
                    creditsLeftDays.setText(getResources().getQuantityString(R.plurals.creditsLeftDays, leftDays, leftDays));
                    if (leftDays > 30) {
                        mCircleView.setValue(100);
                    } else {
                        mCircleView.setValue((float) leftDays / 30 * 100);
                    }

                } else {
                    mCircleView.setValue(0);
                    creditsLeftDays.setText(R.string.creditsPastDueDays);
                }

            } catch (Exception e) {
                Log.e("DATE ERROR", e.toString());
            }

            //Setting TextViews
            creditsDebt.setText(String.format(getResources().getString(R.string.creditsDebt), clientProduct.getDueAmount(), clientProduct.getCurrency()));
            creditsNextDateDue.setText(String.format(getResources().getString(R.string.creditsNextDateDue), dateFormat.format(dueDate)));
            creditsDealTotalDebt.setText(String.format(getResources().getString(R.string.creditsDealTotalDebt), clientProduct.getCurrentCreditAmount(), clientProduct.getCurrency()));

        } else if (clientProduct.getProductType().equals("RD")) {

            getSupportActionBar().setTitle(R.string.loansRDType);
        }

    }

}
