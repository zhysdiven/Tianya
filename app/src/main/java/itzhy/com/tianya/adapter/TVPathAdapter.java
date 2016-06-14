package itzhy.com.tianya.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.entity.TVPathBean;

import java.util.List;

/**
 * Created by Zhy on 2016/6/14
 * des:
 */
public class TVPathAdapter extends BaseAdapter {

    private List<TVPathBean> datas;

    public TVPathAdapter(List<TVPathBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tvpath_list, parent, false);
        TextView txtName = (TextView) view.findViewById(R.id.txt_path_name);
        TVPathBean bean = datas.get(position);
        txtName.setText("线路" + bean.getLINE_ERRCNT());
        return view;
    }


}
