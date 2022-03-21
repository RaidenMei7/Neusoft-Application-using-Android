package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.bean.Tcase;

import java.util.List;

public interface MyCTcaseListener {
    void onResponse(List<CollectResult<Tcase>> beanList);
    void onFail(String msg);
}
