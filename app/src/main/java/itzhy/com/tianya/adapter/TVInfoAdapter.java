package itzhy.com.tianya.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.entity.TVListBean;

/**
 * Created by Zhy on 2016/6/12.
 * des:电视节目列表adapter
 */
public class TVInfoAdapter extends BaseRecycleAdapter<TVListBean, TVInfoAdapter.ViewHolder> {

    private int index;
    private boolean tag;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tv_info_list, parent, false);
        if (tag) {
            view.setBackgroundResource(R.color.color_transparent);
        } else {
            view.setBackgroundResource(R.color.color_gray_bg);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TVListBean entity = items.get(position);
        if (entity == null) return;
        holder.txtName.setText(entity.getTV_PROG());
        holder.txtTime.setText(entity.getTV_TIME());
        if (index == position && tag) {
            holder.txtTag.setVisibility(View.VISIBLE);
        } else {
            holder.txtTag.setVisibility(View.INVISIBLE);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) mItemClickListener.onItemClick(v, position);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtTime, txtTag;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time);
            txtTag = (TextView) itemView.findViewById(R.id.txt_tag);
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }
}
