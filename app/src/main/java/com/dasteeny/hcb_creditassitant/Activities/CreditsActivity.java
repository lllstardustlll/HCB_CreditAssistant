package com.dasteeny.hcb_creditassitant.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dasteeny.hcb_creditassitant.R;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Getting data from LoansFragment
        Intent prevIntent = getIntent();
        String title = prevIntent.getStringExtra("title");
        String debt = prevIntent.getStringExtra("debt");//.replaceAll("\\s+", "");
        String dateDue = prevIntent.getStringExtra("dateDue");
        getSupportActionBar().setTitle(title);

        //Getting TextViews
        TextView creditsLeftDays = (TextView) findViewById(R.id.credits_left_days);
        TextView creditsDebt = (TextView) findViewById(R.id.credits_debt);
        TextView creditsNextDateDue = (TextView) findViewById(R.id.credits_next_due_date);
        TextView creditsSchedule = (TextView) findViewById(R.id.credits_schedule);
        TextView creditsDealTotalDebt = (TextView) findViewById(R.id.credits_deal_total_debt);

        //Calculating amount of left days
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date dateDueDate = dateFormat.parse(dateDue);
            Date today = Calendar.getInstance().getTime();

            long difference = Math.abs(today.getTime() - dateDueDate.getTime());
            int leftDays = (int) (difference / (24 * 60 * 60 * 1000) + 1);
            creditsLeftDays.setText(getResources().getQuantityString(R.plurals.creditsLeftDays, leftDays, leftDays));
        }catch (Exception e){
            Log.e("DIDN'T WORK", "exception " + e);
        }
        //Setting TextViews
        creditsDebt.setText(String.format(getResources().getString(R.string.creditDebt), debt));
        creditsNextDateDue.setText(String.format(getResources().getString(R.string.creditNextDateDue), dateDue));
        creditsDealTotalDebt.setText(String.format(getResources().getString(R.string.creditDealTotalDebt), debt));

        //Setting ProgressBar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.credits_progress_bar);
        progressBar.setProgress(100);
    }

}
