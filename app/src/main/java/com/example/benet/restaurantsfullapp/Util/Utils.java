package com.example.benet.restaurantsfullapp.Util;

import android.app.Activity;

import com.example.benet.restaurantsfullapp.Model.Restaurant;
import com.example.benet.restaurantsfullapp.Model.SpinnerModel;
import com.example.benet.restaurantsfullapp.R;

import java.util.ArrayList;

/**
 * Created by Benet on 26/02/15.
 */
public class Utils {


    public static ArrayList<Restaurant> getRestaurants(Activity a){

        ArrayList<Restaurant> data=new ArrayList<Restaurant>();

        String [] names=a.getResources().getStringArray(R.array.r_names);
        String [] citys=a.getResources().getStringArray(R.array.r_citys);
        String [] countys=a.getResources().getStringArray(R.array.r_countrys);
        String [] urls=a.getResources().getStringArray(R.array.r_urls);
        String [] imgs=a.getResources().getStringArray(R.array.r_imgs);
        byte aux=1;
        while(data.size()<10){
            for (int i=0; i<names.length; i++){
                data.add(new Restaurant(names[i]+" "+(aux),
                                        countys[i]+" "+(aux),
                                        citys[i]+" "+(aux),
                                        a.getResources().getIdentifier(imgs[i],"mipmap",a.getPackageName()),
                                        urls[i]));
            }
            aux++;
        }

        return data;

    }

    public static ArrayList<SpinnerModel> getItemsSpinner(Activity v){

        ArrayList<SpinnerModel> data=new ArrayList<SpinnerModel>();

        String [] img=v.getResources().getStringArray(R.array.r_imgs);

        //while(data.size()<10) {

            for (int i = 0; i < img.length; i++) {
                data.add(new SpinnerModel(v.getResources().getIdentifier(img[i], "mipmap", v.getPackageName()),
                                "Img restaurant spinner "+(i+1))
                );
            }
        //}
        return  data;
    }
}
