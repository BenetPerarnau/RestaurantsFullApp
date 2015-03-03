package com.example.benet.restaurantsfullapp.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benet.restaurantsfullapp.Model.SpinnerModel;
import com.example.benet.restaurantsfullapp.R;

import java.util.List;

/**
 * Created by Benet on 28/02/15.
 */

public class AdapterSpinnerImg extends ArrayAdapter<SpinnerModel> {

    private Context context;
    private List<SpinnerModel> data;

    public AdapterSpinnerImg(Context context, List<SpinnerModel> data){
        super(context, R.layout.my_spinner, data);
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.my_spinner, parent, false);
        }

        TextView name=(TextView)convertView.findViewById(R.id.spinner_name);
        ImageView img=(ImageView)convertView.findViewById(R.id.spinner_img);

        name.setText(data.get(position).getName());
        img.setImageResource(data.get(position).getImg());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.my_spinner, parent, false);///
        }
        if (convertView.getTag() == null)
        {
            Image_Spinner_Holder redSocialHolder = new Image_Spinner_Holder();
            redSocialHolder.setIcono((ImageView) convertView.findViewById(R.id.spinner_img));
            redSocialHolder.setTextView((TextView) convertView.findViewById(R.id.spinner_name));
            convertView.setTag(redSocialHolder);
        }

        //rellenamos el layout con los datos de la fila que se está procesando
        SpinnerModel redSocial = data.get(position);
        ((Image_Spinner_Holder) convertView.getTag()).getIcono().setImageResource(redSocial.getImg());
        ((Image_Spinner_Holder) convertView.getTag()).getTextView().setText(redSocial.getName());


        return convertView;
    }

    private class Image_Spinner_Holder
    {

        private ImageView icono;

        private TextView textView;

        public ImageView getIcono()
        {
            return icono;
        }

        public void setIcono(ImageView icono)
        {
            this.icono = icono;
        }

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

    }
}

