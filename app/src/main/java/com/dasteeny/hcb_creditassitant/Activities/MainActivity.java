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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dasteeny.hcb_creditassitant.ClassesProducts.Products;
import com.dasteeny.hcb_creditassitant.Fragments.LoansFragment;
import com.dasteeny.hcb_creditassitant.Fragments.PaymentsFragment;
import com.dasteeny.hcb_creditassitant.HCBClient;
import com.dasteeny.hcb_creditassitant.R;
import com.dasteeny.hcb_creditassitant.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initiating drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_loans);
        displaySelectedScreen(R.id.nav_loans);

        //Establishing connection
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        String API_BASE_URL = "https://ibank24.kz:9443/IBMobileLightApi_TEST/api/light/LoanMobile/";
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
        ;
        Retrofit retrofit = builder.build();
        HCBClient hcbClient = retrofit.create(HCBClient.class);

        //Getting products list
        Call<Products> call = hcbClient.getProducts("1747", "7051136179", "0000", "12345");
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                Log.d("Response code", String.valueOf(response.code()));

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.d("TEST", String.valueOf(t.getCause()));

            }
        });

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

    private void displaySelectedScreen(int id){
        Fragment fragment = null;
        String fragmentTag = "";

        switch (id){
            case R.id.nav_loans:
                fragment = new LoansFragment();
                fragmentTag = "Loans";
                break;
            case R.id.nav_payments:
                fragment = new PaymentsFragment();
                fragmentTag = "Payments";
                break;
        }

        if (fragment != null ){
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
