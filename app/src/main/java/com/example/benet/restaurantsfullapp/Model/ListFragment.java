package com.example.benet.restaurantsfullapp.Model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.benet.restaurantsfullapp.R;
import com.example.benet.restaurantsfullapp.Util.Constants;

import java.util.ArrayList;


public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private AdaperListRestaurants adaperList;
    private ArrayList<Restaurant> data;
    private OnFragmentInteractionListener mListener;
    private int auxPos;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView lista=(ListView)getActivity().findViewById(R.id.list);

        adaperList=new AdaperListRestaurants(getActivity(), data, R.layout.item_list);
        lista.setAdapter(adaperList);

        lista.setOnItemClickListener(this);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        lista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(data.get(position).getName());
                auxPos=position;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_contextual,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_delete:

                        data.remove(auxPos);
                        adaperList.notifyDataSetChanged();

                        return true;
                    case R.id.ic_edit:

                        Intent intent=new Intent(getActivity(), EditActivity.class);
                        intent.putExtra(Constants.SEND_ITEM_EDIT, data.get(auxPos));
                        startActivityForResult(intent, Constants.REQUEST_EDIT);

                        return true;
                    default:
                        return false;

                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case Activity.RESULT_OK:

                if(requestCode==Constants.REQUEST_EDIT){

                    Log.e("LISTFRAGMENT", "edit save");

                    Restaurant editRestaurant=(Restaurant)data.getSerializableExtra(Constants.SEND_ITEM_EDIT);
                    this.data.remove(auxPos);
                    this.data.add(auxPos,editRestaurant);
                    this.adaperList.notifyDataSetChanged();
                }


                break;
            case Activity.RESULT_CANCELED:

                if(requestCode==Constants.REQUEST_EDIT){

                    Log.e("LISTFRAGMENT", "edit cancel");

                }

                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if(getArguments()!=null){
            data=(ArrayList<Restaurant>)getArguments().getSerializable(Constants.SEND_DATA_TO_LIST);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.onFragmentInteraction(position);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int pos);
    }

}
