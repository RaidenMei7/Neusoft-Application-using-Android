package com.example.zyfypt_no7_406ml.service;

import com.example.zyfypt_no7_406ml.bean.FocusResult;
import com.example.zyfypt_no7_406ml.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FocusService {
    @GET("api.php/exists/mod/{mod}")
    Call<String> exists(
            @Path("mod") String mod,
            @Query("idolid") int idolid,
            @Header("SessionID") String sessionID);

    @GET("api.php/create/mod/{mod}")
    Call<String> focus(
            @Path("mod") String mod,
            @Query("idolid") int idolid,
            @Header("SessionID") String SessionID);

    @GET("api.php/delete/mod/{mod}")
    Call<String> unfocus(
            @Path("mod") String mod,
            @Query("idolid") int idolid,
            @Header("SessionID") String sessionID);

    @GET("api.php/listmyfocus/mod/{mod}")
    Call<List<FocusResult<User>>> getFocusUserList(
            @Path("mod") String mod,
            @Query("page") int page,
            @Header("SessionID") String sessionID);
}
