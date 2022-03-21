package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.Tcase;

import java.util.List;

public interface TcaseListener {
    void onResponse(List<Tcase> tcaseList);
    void onFail(String error);
}
