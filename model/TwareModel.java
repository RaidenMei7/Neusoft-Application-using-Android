package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.Tware;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.TwareIface;
import com.example.zyfypt_no7_406ml.iface.TwareListener;
import com.example.zyfypt_no7_406ml.service.TwareService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwareModel implements TwareIface {
    private Retrofit retrofit;

    public TwareModel() {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    @Override
    public void getTwareList(String mod, int page, String SessionID, TwareListener twareListener) {
        //使用Retrofit----2
        TwareService twareService
                = retrofit.create(TwareService.class);
        Call<List<Tware>> call
                = twareService.getTwareList(mod, page, SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<Tware>>() {
            @Override
            public void onResponse(Call<List<Tware>> call, Response<List<Tware>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    twareListener.onResponse(response.body());
                } else twareListener.onFail("tware fail");
            }

            @Override
            public void onFailure(Call<List<Tware>> call, Throwable t) {
                twareListener.onFail(t.getMessage());
            }
        });
    }
    }

