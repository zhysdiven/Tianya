package itzhy.com.tianya.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.BaseRecycleAdapter;
import itzhy.com.tianya.adapter.GHuoItemAdapter;
import itzhy.com.tianya.entity.GhuoItem;
import itzhy.com.tianya.utils.ItemtouchManage;
import itzhy.com.tianya.view.activity.GhuoListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GHuoFragment extends Fragment {

    RecyclerView ghuoRecycleList;

    private List<GhuoItem> datas = new ArrayList<GhuoItem>();
    private GHuoItemAdapter adapter;

    public static GHuoFragment newInstance() {
        GHuoFragment fragment = new GHuoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghuo, container, false);
        final SwipeRefreshLayout refresh = (SwipeRefreshLayout) view.findViewById(R.id.ghuo_refresh);
        refresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                        refresh.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 4000);
            }
        });
        ghuoRecycleList = (RecyclerView) view.findViewById(R.id.ghuo_recycle_list);
        ghuoRecycleList.setLayoutManager(new GridLayoutManager(container.getContext(), 2));
        loadData();
        adapter = new GHuoItemAdapter();
        adapter.addAll(datas);
        new ItemTouchHelper(new ItemtouchManage<GhuoItem>(adapter, datas, Color.LTGRAY)).attachToRecyclerView(ghuoRecycleList);
        ghuoRecycleList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GhuoListActivity.class);
                intent.putExtra("type",datas.get(position).getName());
                startActivity(intent);
            }
        });
        return view;
    }

    private void loadData() {
        datas.clear();
        datas.add(new GhuoItem("all"));
        datas.add(new GhuoItem("Android"));
        datas.add(new GhuoItem("iOS"));
        datas.add(new GhuoItem("App"));
        datas.add(new GhuoItem("休息视频"));
        datas.add(new GhuoItem("福利"));
        datas.add(new GhuoItem("前端"));
        datas.add(new GhuoItem("瞎推荐"));
        datas.add(new GhuoItem("拓展资源"));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
