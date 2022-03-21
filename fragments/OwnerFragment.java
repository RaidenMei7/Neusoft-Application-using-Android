package com.example.zyfypt_no7_406ml.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import
        android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.activities.LoginActivity;
import com.example.zyfypt_no7_406ml.activities.MyCollectActivity;
import com.example.zyfypt_no7_406ml.activities.MyFUserActivity;
import com.example.zyfypt_no7_406ml.model.LogoutModel;


public class OwnerFragment extends BaseFragment{
    private TextView tvlogout,tvmycollect;
    private TextView tvmyfocus;
    private SharedPreferences sp;
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_owner,container,false);
    }


    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f1--"+getSessionId());//getSessionId是父类的方法
        initViews(view);

    }

    private void initViews(View view) {
        tvlogout=view.findViewById(R.id.logout);
        tvmycollect=view.findViewById(R.id.mycollect);
        tvmyfocus=view.findViewById(R.id.myfocus);
        sp = context.getSharedPreferences("login",MODE_PRIVATE);
        showMyCollect();
        dologout();
        showMyFocus();
    }

    private void showMyFocus() {
        SpannableString strmyfocus = new SpannableString("Myfocus");
        strmyfocus.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(getActivity(), MyFUserActivity.class);
                startActivity(intent);
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void updateDrawState(TextPaint ds){
                ds.setColor(Color.parseColor("#6D9EEB"));
                ds.setUnderlineText(false);
            }
        },0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvmyfocus.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        tvmyfocus.setText(strmyfocus);
        tvmyfocus.setHighlightColor(Color.parseColor("#00000000"));
    }

    private void showMyCollect() {
        SpannableString strmycollect = new SpannableString("MyCollection");
        strmycollect.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Intent intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);


            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void updateDrawState(TextPaint ds){
                ds.setColor(Color.parseColor("#93C47D"));
                ds.setUnderlineText(false);
            }
        },0,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvmycollect.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        tvmycollect.setText(strmycollect);
        tvmycollect.setHighlightColor(Color.parseColor("#00000000"));
    }

    private void dologout() {
        SpannableString strlogout = new SpannableString("Logout");
        strlogout.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                LogoutModel logoutModel = new LogoutModel();
                logoutModel.logout(getSessionId());
                removeSP();
                Log.i("注销账号", "----sessionID=" + getSessionId());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void updateDrawState(TextPaint ds){
                ds.setColor(Color.parseColor("#B4A7D6"));
                ds.setUnderlineText(false);
            }
        },0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvlogout.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        tvlogout.setText(strlogout);
        tvlogout.setHighlightColor(Color.parseColor("#00000000"));
    }

    private void removeSP(){
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("name");
        editor.remove("pass");
        editor.remove("sessionID");
        editor.remove("remember");
        editor.commit();//提交
    }
}
