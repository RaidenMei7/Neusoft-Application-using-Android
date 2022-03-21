package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.Video;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.Latest_VideoIface;
import com.example.zyfypt_no7_406ml.iface.Latest_VideoListener;
import com.example.zyfypt_no7_406ml.service.Latest_VideoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Latest_VideoModel implements Latest_VideoIface {
    private Retrofit retrofit;

    //构造函数
    public Latest_VideoModel() {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getVideoList(String mod, int page, String sessionID, final Latest_VideoListener latest_videoListener) {
        Latest_VideoService latest_videoService
                = retrofit.create(Latest_VideoService.class);
        Call<List<Video>> call
                = latest_videoService.getVideoList(mod, page, sessionID);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    latest_videoListener.onResponse(response.body());
                } else latest_videoListener.onFail("fail");
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                latest_videoListener.onFail(t.getMessage());
            }
        });
    }
}
