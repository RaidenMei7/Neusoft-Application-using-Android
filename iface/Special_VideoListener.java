package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.Video;

import java.util.List;

public interface Special_VideoListener {
    void onResponse(List<Video> video);
    void onFail(String str);
}
