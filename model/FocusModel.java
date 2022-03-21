package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.FocusIface;
import com.example.zyfypt_no7_406ml.iface.FocusListener;
import com.example.zyfypt_no7_406ml.service.FocusService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FocusModel implements FocusIface {
    private Retrofit retrofit;
    //构造函数
    public FocusModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    //关注
    @Override
    public void focus(String mod, int idolid, String Sessionid, FocusListener listener) {
        //使用Retrofit----2
        FocusService focusService=retrofit.create(FocusService.class);
        Call<String> call=focusService.focus(mod,idolid,Sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response!=null)
                {  if(response.body().trim().toString().equals("0"))
                    listener.onResponse("1");//关注失败
                else if(!response.body().trim().toString().contains("error"))
                    listener.onResponse("2");//关注成功
                else
                    listener.onResponse("关注："+response.body());


                }
                else   listener.onFail("focus onresponse fail");//
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("关注失败："+t.toString());
            }
        });
    }
    //取消关注
    @Override
    public void unfocus(String mod, int idolid, String Sessionid, FocusListener listener) {
        //使用Retrofit----2
        FocusService  focusService=retrofit.create(FocusService.class);
        Call<String> call=focusService.unfocus(mod,idolid,Sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response!=null)
                {  if(response.body().trim().toString().equals("0"))
                    listener.onResponse("3");//取消关注失败
                else if (response.body().trim().toString().equals("1"))
                    listener.onResponse("4");//取消关注成功
                else
                    listener.onResponse("取消关注："+response.body());

                }
                else   listener.onFail("unfocus onresponse fail");//
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("取消关注失败："+t.toString());
            }
        });
    }
    //判断是否关注
    @Override
    public void exist(String mod, int idolid, String Sessionid, FocusListener listener) {
        //使用Retrofit----2
        final FocusService  focusService=retrofit.create(FocusService.class);
        Call<String> call=focusService.exists(mod,idolid,Sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response!=null)
                {  if(response.body().trim().toString().equals("0"))//已关注
                    listener.onResponse("5");
                else if (response.body().trim().toString().equals("1"))//未关注
                    listener.onResponse("6");
                else
                    listener.onResponse("判断关注："+response.body());

                }
                else   listener.onFail("focus exist onresponse fail");//
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("判断关注失败："+t.toString());
            }
        });
    }
}
