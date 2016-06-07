package itzhy.com.tianya.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import itzhy.com.tianya.widget.PinnedListview;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoOnlineAdapter extends BaseAdapter implements PinnedListview.PinnedSectionListAdapter {

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
