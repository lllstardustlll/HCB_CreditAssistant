package com.dasteeny.hcb_creditassitant.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dasteeny.hcb_creditassitant.Fragments.LoansFragment;
import com.dasteeny.hcb_creditassitant.Fragments.PaymentsFragment;
import com.dasteeny.hcb_creditassitant.R;
import com.dasteeny.hcb_creditassitant.Utils.Retriever;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String ID_REQUEST = "338885";
    private static final String PHONE_NUMBER = "7051136179";
    private static final String DEVICE_ID = "0000";
    private static final String SMS_CODE = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initiating drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                 drawer,
                                                                 toolbar,
                                                                 R.string.navigation_drawer_open,
                                                                 R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Retriever retriever = new Retriever(getWindow().getDecorView().getRootView(), this);
        //String idRequest = retriever.sendSms(PHONE_NUMBER, DEVICE_ID);
        retriever.getOfferRequest(ID_REQUEST, PHONE_NUMBER, DEVICE_ID, SMS_CODE);

        navigationView.setCheckedItem(R.id.nav_loans);
        displaySelectedScreen(R.id.nav_loans);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;
        String fragmentTag = "";

        switch (id) {
            case R.id.nav_loans:
                fragment = new LoansFragment();
                fragmentTag = "Loans";
                break;
            case R.id.nav_payments:
                fragment = new PaymentsFragment();
                fragmentTag = "Payments";
                break;
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, fragmentTag);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }
}
