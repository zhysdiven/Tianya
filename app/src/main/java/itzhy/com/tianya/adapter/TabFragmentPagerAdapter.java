package itzhy.com.tianya.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> fragments;
	private String[] titles;

	public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
		super(fm);
		this.fragments = fragments;
		this.titles = titles;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}
}
