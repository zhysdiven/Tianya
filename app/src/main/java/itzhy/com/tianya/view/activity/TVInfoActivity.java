package itzhy.com.tianya.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.TabFragmentPagerAdapter;
import itzhy.com.tianya.utils.DateUtils;
import itzhy.com.tianya.utils.LogUtils;
import itzhy.com.tianya.view.fragment.TvInfoListFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TVInfoActivity extends AppCompatActivity {

    public String tID, tName;

    @InjectView(R.id.tab_layout)
    TabLayout tabs;
    @InjectView(R.id.vpage)
    ViewPager pager;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private TabFragmentPagerAdapter adapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvinfo);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        final Intent intent = getIntent();
        tName = intent.getStringExtra("title");
        tID = intent.getStringExtra("tv_id");
        setTitle(tName);

        fragments = new ArrayList<>();
        String[] titles = {"周日", "周一", "周二", "周三", "周四", "周五", "周六", "周四"};

        tabs.setTabMode(TabLayout.MODE_FIXED);
        List<String> week = DateUtils.getAllWeek(new Date(), 0);
        String today = DateUtils.getToday();
        for (int i = 0; i < 7; i++) {
            TvInfoListFragment fragment = new TvInfoListFragment().newInstance();
            fragment.setDate(week.get(i));
            fragment.setTid(tID);
            fragments.add(fragment);
            tabs.addTab(tabs.newTab());
            if (week.get(i).equals(today)) {
                LogUtils.i("today:" + today + "today index:" + i);
                fragment.setTag(true);
            }
        }
        int indexOf = week.indexOf(today); //获取当前时间位置
        LogUtils.i("index:" + indexOf);
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        pager.setAdapter(adapter);
        pager.setCurrentItem(indexOf);
        pager.setOffscreenPageLimit(7);
        tabs.setupWithViewPager(pager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TVInfoActivity.this, TVOnlinePlayActivity.class);
                intent1.putExtra("tv_id", tID);
                intent1.putExtra("name", tName);
                startActivity(intent1);
            }
        });
    }

}
