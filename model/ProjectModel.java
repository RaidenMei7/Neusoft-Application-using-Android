package com.example.zyfypt_no7_406ml.model;


import com.example.zyfypt_no7_406ml.bean.Project;
import com.example.zyfypt_no7_406ml.common.Common;
import com.example.zyfypt_no7_406ml.iface.ProjectIface;
import com.example.zyfypt_no7_406ml.iface.ProjectListener;
import com.example.zyfypt_no7_406ml.service.ProjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectModel implements ProjectIface {

    private Retrofit retrofit;

    //构造函数
    public ProjectModel() {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getProjectList(String mod, int page, String SessionID, ProjectListener projectListener) {
        //使用Retrofit----2
        ProjectService projectService
                = retrofit.create(ProjectService.class);
        Call<List<Project>> call
                = projectService.getProjectList(mod, page, SessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    projectListener.onResponse(response.body());
                } else projectListener.onFail("project fail");
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                projectListener.onFail(t.getMessage());
            }
        });
    }

}


