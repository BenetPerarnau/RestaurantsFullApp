package com.example.benet.restaurantsfullapp.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benet.restaurantsfullapp.R;

import java.util.ArrayList;

/**
 * Created by Benet on 26/02/15.
 */

public class AdaperListRestaurants extends BaseAdapter {


    private ArrayList<Restaurant> data;
    private Activity context;
    private int model_item;
    private LayoutInflater layoutInflater;

    public AdaperListRestaurants(Activity context, ArrayList<Restaurant> data, int model_item) {

        this.context=context;
        this.data=data;
        this.model_item=model_item;

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            convertView=layoutInflater.inflate(model_item, parent, false);
        }
        TextView name=(TextView)convertView.findViewById(R.id.item_name);
        TextView city=(TextView)convertView.findViewById(R.id.item_city);
        TextView county=(TextView)convertView.findViewById(R.id.item_country);
        ImageView img=(ImageView)convertView.findViewById(R.id.item_img);

        name.setText(data.get(position).getName());
        city.setText(data.get(position).getCity());
        county.setText(data.get(position).getCountry());
        img.setImageResource(data.get(position).getImg());

        return convertView;
    }
}
