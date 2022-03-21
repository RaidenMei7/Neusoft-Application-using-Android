package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.LoginBean;
import com.example.zyfypt_no7_406ml.bean.RegBean;

public interface RegIface {

    void getRegResult(
            String username,
            String password,
            String tel,
            String roleid,
            String email,
            RegListener regListener
    );
}
