package com.example.zyfypt_no7_406ml.service;

import com.example.zyfypt_no7_406ml.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("api.php/login")
    Call<LoginBean> login(
            @Query("username") String username,
            @Query("password") String password

    );
}
