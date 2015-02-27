package com.example.benet.restaurantsfullapp.Model;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.benet.restaurantsfullapp.R;
import com.example.benet.restaurantsfullapp.Util.Constants;


public class WebFragment extends Fragment {

    public   WebView web;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        web=(WebView)getActivity().findViewById(R.id.fragment_web);

        changeWebView(getArguments().getString(Constants.SEND_URL_TO_WEB));
    }

    public  void changeWebView(String url){

        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
