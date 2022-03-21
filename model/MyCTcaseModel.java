package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.bean.Tcase;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.MyCTcaseIface;
import com.example.zyfypt_no7_406ml.iface.MyCTcaseListener;
import com.example.zyfypt_no7_406ml.service.MyCollectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCTcaseModel implements MyCTcaseIface {
    private Retrofit retrofit;
    //构造函数
    public MyCTcaseModel()
    {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getListMyCollect(String mod, int page, String SessionID, MyCTcaseListener mycollectListener) {
        //使用Retrofit----2
        MyCollectService myCollectService
                =retrofit.create(MyCollectService.class);
        Call<List<CollectResult<Tcase>>> call
                = myCollectService.getCCaseList(mod,page,SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<CollectResult<Tcase>>>() {
            @Override
            public void onResponse(Call<List<CollectResult<Tcase>>> call, Response<List<CollectResult<Tcase>>> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    mycollectListener.onResponse(response.body());
                }
                else mycollectListener.onFail("fail");
            }
            @Override
            public void onFailure(Call<List<CollectResult<Tcase>>> call, Throwable t) {
                mycollectListener.onFail(t.getMessage ());
            }
        });
    }
}
