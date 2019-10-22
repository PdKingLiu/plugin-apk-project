package com.plugin.demo.plugindemo2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.plugin.demo.plugindemo2.adapter.PostAdapter;
import com.plugin.demo.plugindemo2.bean.Post;
import com.plugin.demo.plugindemo2.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HotPostActivity extends Activity {

    private RecyclerView rvPostList;
    private PostAdapter postAdapter;
    private List<Post> postList;

    private SwipeRefreshLayout srlRefresh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_hot_post);
        initActionBar();
        initView();
        initList();
        initListener();
        srlRefresh.setRefreshing(true);
        ToastUtils.showToast(HotPostActivity.this, "进入插件模块！");
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            requestRefresh();
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initActionBar() {
        if (getActionBar() != null) {
            getActionBar().hide();
            getWindow().setStatusBarColor(Color.WHITE);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
        }
    }

    private void initListener() {
        srlRefresh.setOnRefreshListener(() -> new Handler().postDelayed(this::requestRefresh,
                2000));
    }

    private void requestRefresh() {
        BmobQuery<Post> postBmobQuery = new BmobQuery<>();
        postBmobQuery.order("-createdAt");
        postBmobQuery.include("author,scan");
        postBmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                srlRefresh.setRefreshing(false);
                if (e == null || list != null) {
                    postList.clear();
                    postList.addAll(list);
                    runOnUiThread(() -> postAdapter.notifyDataSetChanged());
                } else {
                    ToastUtils.showToast(HotPostActivity.this, "刷新失败，请重新尝试");
                }
            }
        });
    }

    private void initList() {
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList, this);
        postAdapter.setListener((view, i) -> {
            Intent intent = new Intent();
            intent.setClassName("com.plugin.demo.plugindemo2.post_details", "com.plugin.demo" +
                    ".plugindemo2.post_details.PostDetailActivity");
            intent.putExtra("postId", postList.get(i).getObjectId());
            getApplicationContext().startActivity(intent);
        });
        rvPostList.setLayoutManager(new LinearLayoutManager(this));
        rvPostList.setAdapter(postAdapter);
    }

    private void initView() {
        rvPostList = findViewById(R.id.rv_post_list);
        srlRefresh = findViewById(R.id.srl_refresh);
        srlRefresh.setColorSchemeColors(0xfffea419);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        }
    }
}
