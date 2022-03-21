package com.example.zyfypt_no7_406ml.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.adapters.TcaseAdapter;
import com.example.zyfypt_no7_406ml.bean.Tcase;
import com.example.zyfypt_no7_406ml.iface.TcaseListener;
import com.example.zyfypt_no7_406ml.model.TcaseModel;


import java.util.List;


public class Fragment41 extends BaseFragment {
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TcaseAdapter caseAdapter;
    private List<Tcase> list ;
    private int page=1; // 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置
    private TcaseListener caseListener = new TcaseListener() {
        @Override
        public void onResponse(List<Tcase> caseList) {
            if(page==1) {
                list=caseList;
            }
            else {
                list.removeAll(caseList);
                list.addAll(caseList);
            }
            caseAdapter = new TcaseAdapter(context,list);
            recyclerView.setAdapter(caseAdapter);
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();
        }
    };


    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_41,container,false);
    }


    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f1--"+getSessionId());//getSessionId是父类的方法
        this.context=this.getContext();
        initRecyclerView(view);
        TcaseModel model=new TcaseModel();
        model.getTcaseList("tcase",page,getSessionId(),caseListener);
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.fragment41);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //创建自定义适配器
        caseAdapter = new TcaseAdapter(context,list);
        //为控件设置适配器
        recyclerView.setAdapter(caseAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (list != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                        page += 1;                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数
                        TcaseModel newmodel = new TcaseModel();
                        newmodel.getTcaseList("tcase", page, getSessionId(), caseListener);
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
    }


}