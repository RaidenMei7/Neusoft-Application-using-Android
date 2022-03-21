package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.bean.Video;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.MyCVideoIface;
import com.example.zyfypt_no7_406ml.iface.MyCVideoListener;
import com.example.zyfypt_no7_406ml.service.MyCollectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCVideoModel implements MyCVideoIface {
    private Retrofit retrofit;
    //构造函数
    public MyCVideoModel()
    {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getMyVideoList(String mod, int page, String SessionID, MyCVideoListener mycollectListener) {
        //使用Retrofit----2
        MyCollectService myCollectService
                =retrofit.create(MyCollectService.class);
        Call<List<CollectResult<Video>>> call
                = myCollectService.getCvideoList(mod,page,SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<CollectResult<Video>>>() {
            @Override
            public void onResponse(Call<List<CollectResult<Video>>> call, Response<List<CollectResult<Video>>> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    mycollectListener.onResponse(response.body());
                }
                else mycollectListener.onFail("fail");
            }
            @Override
            public void onFailure(Call<List<CollectResult<Video>>> call, Throwable t) {
                mycollectListener.onFail(t.getMessage ());
            }
        });
    }
}
