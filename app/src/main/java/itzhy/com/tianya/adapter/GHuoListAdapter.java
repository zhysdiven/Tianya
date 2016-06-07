package itzhy.com.tianya.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.entity.GHuobean;

/**
 * Created by YB-PC-1462A on 2016/5/20.
 */
public class GHuoListAdapter extends BaseRecycleAdapter<GHuobean.ResultsEntity, GHuoListAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ghuo_act_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GHuobean.ResultsEntity entity = items.get(position);
        if (entity == null) return;
        holder.txtTitle.setText(entity.getDesc());
        holder.txtAuthor.setText(entity.getWho());
        holder.txtDate.setText(entity.getCreatedAt().substring(0, 10));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) mItemClickListener.onItemClick(v, position);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgaIcon;
        private TextView txtTitle, txtDate, txtAuthor;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgaIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
            txtAuthor = (TextView) itemView.findViewById(R.id.txt_author);
        }
    }

}
