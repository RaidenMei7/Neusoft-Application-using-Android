package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.Article;

import java.util.List;

public interface ArticleListener {
    void onResponse(List<Article> articleList);
    void onFail(String error);
}
