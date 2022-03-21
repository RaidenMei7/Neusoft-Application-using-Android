package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.bean.Video;

import java.util.List;

public interface MyCVideoListener {
    void onResponse(List<CollectResult<Video>> beanList);
    void onFail(String msg);
}
