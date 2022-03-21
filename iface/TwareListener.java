package com.example.zyfypt_no7_406ml.iface;



import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.Tware;

import java.util.List;

public interface TwareListener {
    void onResponse(List<Tware> twareList);
    void onFail(String error);
}
