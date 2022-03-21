package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.LoginBean;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.LoginListener;
import com.example.zyfypt_no7_406ml.iface.Loginiface;
import com.example.zyfypt_no7_406ml.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel implements Loginiface {
    private Retrofit retrofit;

    public LoginModel()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public void getLoginResult(String username, String pass, final LoginListener loginListener) {
        UserService userService
                =retrofit.create(UserService.class);
        Call<LoginBean> call
                =userService.login(username,pass);
        //使用Retrofit----3
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if(response.isSuccessful()&&response.body()!=null&& response.body().getId()!=null)
                {
                    loginListener.onResponse(response.body());
                }
                else loginListener.onFail("login failed");
            }
            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                loginListener.onFail(t.getMessage ());
            }
        });

    }
}
