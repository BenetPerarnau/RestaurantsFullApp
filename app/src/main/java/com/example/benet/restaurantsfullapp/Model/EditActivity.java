package com.example.benet.restaurantsfullapp.Model;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.benet.restaurantsfullapp.Model.Adapters.AdapterSpinnerImg;
import com.example.benet.restaurantsfullapp.R;
import com.example.benet.restaurantsfullapp.Util.Constants;
import com.example.benet.restaurantsfullapp.Util.Utils;

import java.util.ArrayList;

public class EditActivity extends ActionBarActivity {

    private Intent intent;
    private EditText name, city, country, url;
    private Spinner spinner;
    private ImageView img;
    private Activity context;
    private Bundle arg;
    private ArrayList<SpinnerModel> dataS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        context=this;
        intent=getIntent();

        Restaurant r= (Restaurant) intent.getSerializableExtra(Constants.SEND_ITEM_EDIT);

         name=(EditText)findViewById(R.id.edit_name);
         city=(EditText)findViewById(R.id.edit_city);
         country=(EditText)findViewById(R.id.edit_country);
         url=(EditText)findViewById(R.id.edit_web);
         spinner=(Spinner)findViewById(R.id.edit_spinner);
         img=(ImageView)findViewById(R.id.edit_img);

        name.setText(r.getName());
        city.setText(r.getCity());
        country.setText(r.getCountry());
        url.setText(r.getUrl());
        img.setImageResource(r.getImg());


        dataS=Utils.getItemsSpinner(this);

        AdapterSpinnerImg adapterSpinnerImg=new AdapterSpinnerImg(this, dataS);

        spinner.setAdapter(adapterSpinnerImg);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                img.setImageResource(dataS.get(position).getImg());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] a={name.getText().toString(),city.getText().toString(), country.getText().toString(), url.getText().toString()};
                boolean blanck=false;
                for(String i:a){
                    if(i.length()==0){blanck=true; break;}
                }
                if(!blanck) {
                    Restaurant r=new Restaurant(name.getText().toString(),
                                                city.getText().toString(),
                                                country.getText().toString(),
                            ((SpinnerModel)spinner.getSelectedItem()).getImg(),
                                                url.getText().toString());
                    intent.putExtra(Constants.SEND_ITEM_EDIT, r);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(context,"Campos en blanco",Toast.LENGTH_LONG).show();
                }

            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
}
