package com.nutriroute.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nutriroute.R;

public class UserStoresFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.user_fragment_stores, container, false);

        // Initialize the WebView
        webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView
        webView.getSettings().setJavaScriptEnabled(true);

        // Load the Google Maps URL for a specific location (San Francisco example)
        String url = "https://www.google.com/maps/embed/v1/place?q=37.7749,-122.4194&zoom=12";
        webView.loadUrl(url);

        return view;
    }
}