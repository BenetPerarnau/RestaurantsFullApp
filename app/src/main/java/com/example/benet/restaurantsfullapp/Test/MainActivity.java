package com.example.benet.restaurantsfullapp.Test;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.benet.restaurantsfullapp.Model.AdaperListRestaurants;
import com.example.benet.restaurantsfullapp.Model.ListFragment;
import com.example.benet.restaurantsfullapp.Model.Restaurant;
import com.example.benet.restaurantsfullapp.Model.WebFragment;
import com.example.benet.restaurantsfullapp.R;
import com.example.benet.restaurantsfullapp.Util.Constants;
import com.example.benet.restaurantsfullapp.Util.Utils;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements ListFragment.OnFragmentInteractionListener {


    private ArrayList<Restaurant> data;
    private Bundle bundle;
    private ListFragment listFragment;
    private WebFragment webFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();


        if(findViewById(R.id.container_mobile)!=null){

            fragmentTransaction.add(R.id.container_mobile,listFragment);

        }else{

            fragmentTransaction.add(R.id.c_f_list,listFragment,Constants.LIST_F_TAG);
            webFragment=new WebFragment();
            bundle.putSerializable(Constants.SEND_URL_TO_WEB,data.get(0).getUrl());
            webFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.c_f_detail,webFragment,Constants.WEB_F_TAG);

        }

        fragmentTransaction.commit();
    }


    private void initComponents(){

        data=(ArrayList<Restaurant>) Utils.getRestaurants(this);
        bundle=new Bundle();
        listFragment=new ListFragment();
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        bundle.putSerializable(Constants.SEND_DATA_TO_LIST,data);
        listFragment.setArguments(bundle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int pos) {

        if(findViewById(R.id.container_mobile)!=null){

                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                bundle.putSerializable(Constants.SEND_URL_TO_WEB, data.get(pos).getUrl());
                webFragment=new WebFragment();
                webFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_mobile,webFragment ).commit();

        }else{

            WebFragment webFragment=(WebFragment)this.getFragmentManager().findFragmentByTag(Constants.WEB_F_TAG);

           if(webFragment!=null){
               webFragment.changeWebView(data.get(pos).getUrl());
           }
        }
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}
