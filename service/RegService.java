package com.example.zyfypt_no7_406ml.service;

import com.example.zyfypt_no7_406ml.bean.LoginBean;
import com.example.zyfypt_no7_406ml.bean.RegBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RegService {

    @GET("api.php/reg")
    Call<String>reg(
            @Query("username")String username,
            @Query("password")String password,
            @Query("tel")String tel,
            @Query("roleid")String roleid,
            @Query("email")String email);


}
