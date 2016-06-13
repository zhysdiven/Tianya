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
import itzhy.com.tianya.MainApplication;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.BaseRecycleAdapter;
import itzhy.com.tianya.adapter.TVListAdapter;
import itzhy.com.tianya.contract.TVChildContract;
import itzhy.com.tianya.entity.TVChildBean;
import itzhy.com.tianya.presenter.VideoListPresenter;
import itzhy.com.tianya.utils.LogUtils;

import java.util.List;


public class TVListActivity extends AppCompatActivity implements TVChildContract.view {

    private RecyclerView ghuoRecycleList;
    private TVListAdapter adapter;
    private VideoListPresenter presenter;

    private int page = 0;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvlist);
        init();
    }

    public void init() {
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("name"));

        id = intent.getStringExtra("id");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "点击了", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
                }, 2000);
            }
        });
        adapter = new TVListAdapter();
        ghuoRecycleList = (RecyclerView) findViewById(R.id.ghuo_recycle_list);
        ghuoRecycleList.setLayoutManager(new LinearLayoutManager(this));
        ghuoRecycleList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(TVListActivity.this, TVInfoActivity.class);
                intent.putExtra("tv_id", adapter.getItem(position).getTV_ID());
                intent.putExtra("title", adapter.getItem(position).getTV_NAME());
                startActivity(intent);
            }
        });

        presenter = new VideoListPresenter();
        presenter.getTvChildBeans(this, MainApplication.getKey(), "4", id);
    }

    @Override
    public void onTvBeansSuccess(List<TVChildBean> datas) {
        LogUtils.i("ghuo size :" + datas.size());
        if (datas != null && datas.size() > 0) {
            adapter.clear();
            adapter.addAll(datas);
        }
    }


}
