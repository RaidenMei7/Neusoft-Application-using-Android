package com.example.zyfypt_no7_406ml.model;


import com.example.zyfypt_no7_406ml.bean.Tcase;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.TcaseIface;
import com.example.zyfypt_no7_406ml.iface.TcaseListener;
import com.example.zyfypt_no7_406ml.service.TcaseService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TcaseModel implements TcaseIface {
    private Retrofit retrofit;

    //构造函数
    public TcaseModel() {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getTcaseList(String mod, int page, String SessionID, TcaseListener tcaseListener) {
        //使用Retrofit----2
        TcaseService tcaseService
                = retrofit.create(TcaseService.class);
        Call<List<Tcase>> call
                = tcaseService.getTcaseList(mod, page, SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<Tcase>>() {
            @Override
            public void onResponse(Call<List<Tcase>> call, Response<List<Tcase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tcaseListener.onResponse(response.body());
                } else tcaseListener.onFail("tcase fail");
            }

            @Override
            public void onFailure(Call<List<Tcase>> call, Throwable t) {
                tcaseListener.onFail(t.getMessage());
            }
        });
    }

}
