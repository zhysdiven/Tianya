package itzhy.com.tianya.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.BaseRecycleAdapter;
import itzhy.com.tianya.adapter.GHuoListAdapter;
import itzhy.com.tianya.contract.GhuoContract;
import itzhy.com.tianya.entity.GHuobean;
import itzhy.com.tianya.presenter.GhuoListPresenter;
import itzhy.com.tianya.utils.LogUtils;

import java.util.List;

public class GhuoListActivity extends AppCompatActivity implements GhuoContract.view {

    private RecyclerView ghuoRecycleList;
    private GHuoListAdapter adapter;
    private GhuoListPresenter ghuoListPresenter;

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghuo_list);
        init();
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("type"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "撰写干货", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        final SwipeRefreshLayout refresh = (SwipeRefreshLayout) findViewById(R.id.ghuo_refresh);
        refresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        refresh.setRefreshing(false);
                    }
                }, 4000);
            }
        });
        adapter = new GHuoListAdapter();
        ghuoRecycleList = (RecyclerView) findViewById(R.id.ghuo_recycle_list);
        ghuoRecycleList.setLayoutManager(new LinearLayoutManager(this));
        ghuoRecycleList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GhuoListActivity.this, GhuoWebActivity.class);
                intent.putExtra("url", adapter.getItem(position).getUrl());
                intent.putExtra("title", adapter.getItem(position).getDesc());
                startActivity(intent);
            }
        });

        ghuoListPresenter = new GhuoListPresenter();
        ghuoListPresenter.getGhuoTypeList(this, intent.getStringExtra("type"),20,1);
    }

    @Override
    public void ghuoListResult(boolean error, List<GHuobean.ResultsEntity> datas) {
        if (error) {
            Toast.makeText(GhuoListActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
            return;
        }
        LogUtils.i("ghuo size :" +datas.size());
        if (datas != null && datas.size() > 0) {
            adapter.addAll(datas);
        }
    }


}
