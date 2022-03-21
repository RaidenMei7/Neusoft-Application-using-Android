package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.RegBean;

public interface RegListener {
    void onResponse(String success);

    void onFail(String msg);
}
