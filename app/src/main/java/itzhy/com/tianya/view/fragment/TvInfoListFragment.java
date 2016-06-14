package itzhy.com.tianya.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import itzhy.com.tianya.MainApplication;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.BaseRecycleAdapter;
import itzhy.com.tianya.adapter.TVInfoAdapter;
import itzhy.com.tianya.contract.TVInfoContract;
import itzhy.com.tianya.entity.TVListBean;
import itzhy.com.tianya.presenter.VideoListPresenter;
import itzhy.com.tianya.utils.DateUtils;
import itzhy.com.tianya.utils.LogUtils;

import java.util.List;

/**
 * 电视节目列表界面
 */
public class TvInfoListFragment extends Fragment implements TVInfoContract.view {

    RecyclerView tvRecycleList;
    SwipeRefreshLayout refresh;
    VideoListPresenter presenter;
    TextView txtNodata;

    private TVInfoAdapter adapter;

    private String date;
    private String tid;
    private boolean tag; //当前日期标示

    public static TvInfoListFragment newInstance() {
        TvInfoListFragment fragment = new TvInfoListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_info_list, container, false);

        presenter = new VideoListPresenter();
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.tv_refresh);
        refresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        tvRecycleList = (RecyclerView) view.findViewById(R.id.tv_recycle_list);
        tvRecycleList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TVInfoAdapter();
        adapter.setTag(tag);
        tvRecycleList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        txtNodata = (TextView) view.findViewById(R.id.txt_nodata);
        loadData();

        return view;
    }

    private void loadData() {
        presenter.getTvListBeans(this, MainApplication.getKey(), "3", tid, date);
    }

    @Override
    public void onTvListBeansSuccess(List<TVListBean> datas) {
        refresh.setRefreshing(false);
        if (datas != null && datas.size() > 0) {
            int t = DateUtils.getCurTime();
            for (int i = 0; i < datas.size(); i++) {
                String tv_time = datas.get(i).getTV_TIME();
                tv_time = tv_time.replace(":", "");
                LogUtils.i("index:" + i + "," + tv_time);
                int s = Integer.valueOf(tv_time).intValue();
                if (t < s) {
                    int x = i == 0 ? 0 : i - 1;
                    adapter.setIndex(x);
                    LogUtils.d("success index:" + (i - 1) + ", adapter add:" + datas.size());
                    adapter.clear();
                    adapter.addAll(datas);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) tvRecycleList.getLayoutManager();
                    layoutManager.scrollToPositionWithOffset(x, tvRecycleList.getHeight() / 2);
                    return;
                }
            }
            txtNodata.setVisibility(View.GONE);
        } else {
            txtNodata.setVisibility(View.VISIBLE);
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

}
