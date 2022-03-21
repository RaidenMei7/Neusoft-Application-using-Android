package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.LoginBean;
import com.example.zyfypt_no7_406ml.bean.RegBean;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.RegIface;
import com.example.zyfypt_no7_406ml.iface.RegListener;
import com.example.zyfypt_no7_406ml.service.RegService;
import com.example.zyfypt_no7_406ml.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegModel implements RegIface {

    private Retrofit retrofit;

    public RegModel()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public void getRegResult(String username, String password, String tel, String roleid, String email, RegListener regListener) {
        RegService regService
                =retrofit.create(RegService.class);
        Call<String> call
                =regService.reg(username,password,tel,roleid,email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    regListener.onResponse(response.body());
                }
                else regListener.onFail("register failed");
            };


            @Override
            public void onFailure(Call<String> call, Throwable t) {

            regListener.onFail(t.getMessage());

            }
        });
    }
}

