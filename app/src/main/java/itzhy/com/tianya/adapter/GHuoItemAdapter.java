package itzhy.com.tianya.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.entity.GhuoItem;

/**
 * Created by YB-PC-1462A on 2016/5/20.
 */
public class GHuoItemAdapter extends BaseRecycleAdapter<GhuoItem, GHuoItemAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ghuo_fm_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtName.setText(items.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) mItemClickListener.onItemClick(v, position);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgaIcon;
        private TextView txtName;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgaIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }

}
