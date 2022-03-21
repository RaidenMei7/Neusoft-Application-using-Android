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

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.adapters.Special_VideoAdapter;
import com.example.zyfypt_no7_406ml.bean.Video;
import com.example.zyfypt_no7_406ml.iface.Special_VideoListener;
import com.example.zyfypt_no7_406ml.model.Special_VideoModel;

import java.util.List;


public class Fragment32 extends BaseFragment {

    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示布局效果
    private List<Video> list;
    private Special_VideoAdapter special_videoAdapter;
    private int page=1; // 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    private Special_VideoListener special_videoListener = new Special_VideoListener() {
        @Override
        public void onResponse(List<Video> videoList) {
            if(page==1)
            {
                list=videoList;
            }
            else {
                list.removeAll(videoList);
                list.addAll(videoList);
            }
            special_videoAdapter=new Special_VideoAdapter(list,context);
            recyclerView.setAdapter(special_videoAdapter);
        }
        @Override
        public void onFail(String msg) {

        }
    };
    public Fragment32() {
    }
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_32,container,false);
    }

    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context=this.getContext();
        System.out.println("--f32--"+getSessionId());//getSessionId是父类的方法

        recyclerView = view.findViewById(R.id.fragment32);
        //创建默认的线性LayoutManager
        layoutManager = new LinearLayoutManager(this.getContext());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(list!=null){
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                        page += 1;
                        //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                        getData();
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        getData();
    }
    public void getData() {
        Special_VideoModel special_videoModel = new Special_VideoModel();
        special_videoModel.getVideoList("video",page,getSessionId(),special_videoListener);
    }
}