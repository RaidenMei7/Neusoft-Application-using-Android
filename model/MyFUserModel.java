package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.FocusResult;
import com.example.zyfypt_no7_406ml.bean.User;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.MyFUserIface;
import com.example.zyfypt_no7_406ml.iface.MyFUserListener;
import com.example.zyfypt_no7_406ml.service.FocusService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFUserModel implements MyFUserIface {
    private Retrofit retrofit;
    //构造函数
    public MyFUserModel()
    {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getListMyFocus(String mod, int page, String SessionID, MyFUserListener myFUserListener) {
        //使用Retrofit----2
        FocusService myService
                =retrofit.create(FocusService.class);
        Call<List<FocusResult<User>>> call
                = myService.getFocusUserList(mod,page,SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<FocusResult<User>>>() {
            @Override
            public void onResponse(Call<List<FocusResult<User>>> call, Response<List<FocusResult<User>>> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    myFUserListener.onResponse(response.body());
                }
                else myFUserListener.onFail("fail");
            }
            @Override
            public void onFailure(Call<List<FocusResult<User>>> call, Throwable t) {
                myFUserListener.onFail(t.getMessage ());
            }
        });


    }
}
