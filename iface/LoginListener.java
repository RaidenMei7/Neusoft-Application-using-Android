package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.LoginBean;

public interface LoginListener {
    void onResponse(LoginBean loginBean);

    void onFail(String msg);

}
