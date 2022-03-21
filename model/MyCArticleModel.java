package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.MyCArticleIface;
import com.example.zyfypt_no7_406ml.iface.MyCArticleListener;
import com.example.zyfypt_no7_406ml.service.MyCollectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCArticleModel implements MyCArticleIface {
    private Retrofit retrofit;
    //构造函数
    public MyCArticleModel()
    {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getListMyCollect(String mod, int page, String SessionID, MyCArticleListener mycollectListener) {
        //使用Retrofit----2
        MyCollectService myCollectService
                =retrofit.create(MyCollectService.class);
        Call<List<CollectResult<Article>>> call
                = myCollectService.getCarticleList(mod,page,SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<CollectResult<Article>>>() {
            @Override
            public void onResponse(Call<List<CollectResult<Article>>> call, Response<List<CollectResult<Article>>> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    mycollectListener.onResponse(response.body());
                }
                else mycollectListener.onFail("fail");
            }
            @Override
            public void onFailure(Call<List<CollectResult<Article>>> call, Throwable t) {
                mycollectListener.onFail(t.getMessage ());
            }
        });
    }
}
