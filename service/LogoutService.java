package com.example.zyfypt_no7_406ml.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LogoutService {
    @GET("api.php/logout")
    Call<String> logout(@Header("SessionID") String sessionID);
}
