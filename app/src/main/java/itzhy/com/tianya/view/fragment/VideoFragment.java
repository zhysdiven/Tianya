package itzhy.com.tianya.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import itzhy.com.tianya.MainApplication;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.BaseRecycleAdapter;
import itzhy.com.tianya.adapter.TVItemAdapter;
import itzhy.com.tianya.contract.VideoContract;
import itzhy.com.tianya.entity.TVBean;
import itzhy.com.tianya.presenter.VideoListPresenter;
import itzhy.com.tianya.utils.ItemtouchManage;
import itzhy.com.tianya.view.activity.TVListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class VideoFragment extends Fragment implements VideoContract.view {

    RecyclerView tvRecycleList;
    SwipeRefreshLayout refresh;
    VideoListPresenter presenter;

    private List<TVBean> datas = new ArrayList<TVBean>();
    private TVItemAdapter adapter;

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
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
        tvRecycleList.setLayoutManager(new GridLayoutManager(container.getContext(), 2));
        adapter = new TVItemAdapter();
        adapter.addAll(datas);
        tvRecycleList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TVListActivity.class);
                intent.putExtra("id", adapter.getItem(position).getCH_ID());
                intent.putExtra("name",adapter.getItem(position).getCH_NAME());
                startActivity(intent);
            }
        });
        loadData();
        return view;
    }

    private void loadData() {
        presenter.getTvBeans(this, MainApplication.getKey(), "1");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onTvBeansSuccess(List<TVBean> datas) {
        refresh.setRefreshing(false);
        adapter.clear();
        this.datas = datas;
        adapter.addAll(datas);
        new ItemTouchHelper(new ItemtouchManage<TVBean>(adapter, datas, Color.LTGRAY)).attachToRecyclerView(tvRecycleList);
    }


}
