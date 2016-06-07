package itzhy.com.tianya.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import itzhy.com.tianya.view.fragment.FragmentFactory;

import java.util.ArrayList;

/**
 * Created by YB-PC-1462A on 2016/5/20.
 */
public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<String> titles;


    public PageAdapter(FragmentManager fm, ArrayList<String> title) {
        super(fm);
        this.titles = title;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.newInstance(titles.get(position));
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
