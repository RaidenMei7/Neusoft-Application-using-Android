package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.CollectResult;

import java.util.List;

public interface MyCArticleListener {
    void onResponse(List<CollectResult<Article>> beanList);
    void onFail(String msg);
}
