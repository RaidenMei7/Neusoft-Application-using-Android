package com.example.zyfypt_no7_406ml.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.Tware;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.TwareListener;
import com.example.zyfypt_no7_406ml.model.TwareModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.example.zyfypt_no7_406ml.R;

import java.util.List;

public class ViewTwareActivity extends AppCompatActivity {

    private String pdf;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tware);

        System.out.println("----查看课件详情");

        pdf  = getIntent().getStringExtra("pdf");

        webView = findViewById(R.id.webview);
        webView.loadUrl(Common.IMAGEURL+pdf);
    }
}