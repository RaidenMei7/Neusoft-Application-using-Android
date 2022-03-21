package com.example.zyfypt_no7_406ml.iface;

import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.Project;
import com.example.zyfypt_no7_406ml.bean.Tcase;

import java.util.List;

public interface ProjectListener {
    void onResponse(List<Project> projectList);
    void onFail(String error);
}
