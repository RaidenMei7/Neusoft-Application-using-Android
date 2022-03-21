package com.example.zyfypt_no7_406ml.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.fragments.BaseFragment;
import com.example.zyfypt_no7_406ml.fragments.CaseFragment;
import com.example.zyfypt_no7_406ml.fragments.ArticleFragment;
import com.example.zyfypt_no7_406ml.fragments.OwnerFragment;
import com.example.zyfypt_no7_406ml.fragments.TwareFragment;
import com.example.zyfypt_no7_406ml.fragments.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private String sessionID = "";

    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private BottomNavigationView navigationView;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragmentList();//初始化FragmentList
        initViewPager();//初始化ViewPager
        initBottomNV();//初始化BottomNavigationView

    }
    private void initFragmentList() {
        // 将fragment加载到list中
        fragmentList = new ArrayList<>();
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new TwareFragment());
        fragmentList.add(new VideoFragment());
        fragmentList.add(new CaseFragment());
        fragmentList.add(new OwnerFragment());

    }
    private void initViewPager() {
        //实例化viewpager
        viewPager = findViewById(R.id.viewpager);
        //实例化FragmentPagerAdapter
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(),0) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        //设置viewpager的适配器
        viewPager.setAdapter(fragmentPagerAdapter);
        //设置viewpager的页面切换事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("---position=" + position);
                switch (position) {
                    case 0:
                        navigationView.setSelectedItemId(R.id.essay);
                        break;
                    case 1:
                        navigationView.setSelectedItemId(R.id.swf);
                        break;
                    case 2:
                        navigationView.setSelectedItemId(R.id.video);
                        break;
                    case 3:
                        navigationView.setSelectedItemId(R.id.cse);
                        break;
                    case 4:
                        navigationView.setSelectedItemId(R.id.owner);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private void initBottomNV() {
        //实例化BottomNavigationView
        navigationView = findViewById(R.id.bottomnv);
        //设置BottomNavigationView切换事件
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = 0;
                switch (item.getItemId()) {
                    case R.id.essay:
                        id = 0;
                        break;
                    case R.id.swf:
                        id = 1;
                        break;
                    case R.id.video:
                        id = 2;
                        break;
                    case R.id.cse:
                        id = 3;
                        break;
                    case R.id.owner:
                        id = 4;
                        break;
                }
                viewPager.setCurrentItem(id);
                return true;
            }
        });
    }






}