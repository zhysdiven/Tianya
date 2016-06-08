package itzhy.com.tianya.view.fragment;

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
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.BaseRecycleAdapter;
import itzhy.com.tianya.adapter.TVItemAdapter;
import itzhy.com.tianya.contract.VideoContract;
import itzhy.com.tianya.entity.TVBean;
import itzhy.com.tianya.entity.TVItemBean;
import itzhy.com.tianya.presenter.VideoListPresenter;
import itzhy.com.tianya.utils.ItemtouchManage;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
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
        new ItemTouchHelper(new ItemtouchManage<TVBean>(adapter, datas, Color.LTGRAY)).attachToRecyclerView(tvRecycleList);
        tvRecycleList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        loadData();
        return view;
    }

    private void loadData() {
        presenter.getTvBeans(this, "8540928", "1");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onTvBeansSuccess(TVItemBean datas) {
        refresh.setRefreshing(false);
        adapter.addAll(datas.getBeen());
    }


}
