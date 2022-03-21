package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.FocusResult;
import com.example.zyfypt_no7_406ml.bean.User;

import java.util.List;

public interface MyFUserListener {
    void onResponse(List<FocusResult<User>> beanList);
    void onFail(String msg);

}
