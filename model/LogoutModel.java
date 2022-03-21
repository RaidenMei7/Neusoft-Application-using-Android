package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.LogoutIface;
import com.example.zyfypt_no7_406ml.service.LogoutService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogoutModel implements LogoutIface {
    private Retrofit retrofit;
    //构造函数
    public LogoutModel()
    {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void logout(String sessionID) {
        //使用Retrofit----2
        LogoutService logoutService
                =retrofit.create(LogoutService.class);
        Call<String> call
                =logoutService.logout(sessionID);
    }

}
