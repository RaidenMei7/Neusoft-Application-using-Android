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
import com.example.zyfypt_no7_406ml.adapters.CollectArticleAdapter;
import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.iface.MyCArticleListener;
import com.example.zyfypt_no7_406ml.model.MyCArticleModel;

import java.util.List;


public class MyCArticleFragment extends BaseFragment {

    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CollectArticleAdapter collectArticleAdapter;
    private List<CollectResult<Article>> list ;
    private int page=1; // 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    private MyCArticleListener myCollectListener = new MyCArticleListener() {
        @Override
        public void onResponse(List<CollectResult<Article>> articleList) {
            if(page==1) {
                list=articleList;
            }
            else {
                list.removeAll(articleList);
                list.addAll(articleList);
            }
            collectArticleAdapter = new CollectArticleAdapter(context,list);
            recyclerView.setAdapter(collectArticleAdapter);
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();
        }
    };
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_article,container,false);
    }

    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context=this.getContext();
        System.out.println("--f1--"+getSessionId());//getSessionId是父类的方法
        initRecyclerView(view);
        MyCArticleModel model=new MyCArticleModel();
        model.getListMyCollect("article",page,getSessionId(),myCollectListener);
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //创建自定义适配器
        collectArticleAdapter = new CollectArticleAdapter(context,list);
        //为控件设置适配器
        recyclerView.setAdapter(collectArticleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (list != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                        page += 1;                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数
                        MyCArticleModel newmodel=new MyCArticleModel();
                        newmodel.getListMyCollect("article",page,getSessionId(),myCollectListener);
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