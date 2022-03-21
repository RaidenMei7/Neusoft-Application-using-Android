package com.example.zyfypt_no7_406ml.model;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.ArticleIface;
import com.example.zyfypt_no7_406ml.iface.ArticleListener;
import com.example.zyfypt_no7_406ml.service.ArticleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleModel implements ArticleIface {
    private Retrofit retrofit;

    //构造函数
    public ArticleModel() {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getArticleList(String mod, int page, String SessionID, ArticleListener articleListener) {
        //使用Retrofit----2
        ArticleService articleService
                = retrofit.create(ArticleService.class);
        Call<List<Article>> call
                = articleService.getArticleList(mod, page, SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articleListener.onResponse(response.body());
                } else articleListener.onFail("login fail");
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                articleListener.onFail(t.getMessage());
            }
        });
    }
}
