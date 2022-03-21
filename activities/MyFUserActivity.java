package com.example.zyfypt_no7_406ml.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.adapters.FocusUserAdapter;
import com.example.zyfypt_no7_406ml.bean.FocusResult;
import com.example.zyfypt_no7_406ml.bean.User;
import com.example.zyfypt_no7_406ml.iface.MyFUserListener;
import com.example.zyfypt_no7_406ml.model.MyFUserModel;

import java.util.List;

public class MyFUserActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FocusUserAdapter focusUserAdapter;
    private List<FocusResult<User>> list ;
    private int page=1; // 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置
    private SharedPreferences sp;
    private String sessionID="";  //sessionid

    private MyFUserListener myListener = new MyFUserListener() {
        @Override
        public void onResponse(List<FocusResult<User>> userList) {
            if(page==1) {
                list=userList;
            }
            else {
                list.removeAll(userList);
                list.addAll(userList);
            }
            focusUserAdapter = new FocusUserAdapter(context,list);
            recyclerView.setAdapter(focusUserAdapter);
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fuser);
        context=MyFUserActivity.this;
        sp=context.getSharedPreferences("login",MODE_PRIVATE);
        readSP();
        initRecyclerView();
        MyFUserModel model=new MyFUserModel();
        model.getListMyFocus("userfocus",page,sessionID,myListener);

    }
    private void readSP() {
        sessionID=sp.getString("sessionID",null);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rvfuser);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //创建自定义适配器
        focusUserAdapter = new FocusUserAdapter(context,list);
        //为控件设置适配器
        recyclerView.setAdapter(focusUserAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (list != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                        page += 1;                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数
                        MyFUserModel model=new MyFUserModel();
                        model.getListMyFocus("userfocus",page,sessionID,myListener);
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