package itzhy.com.tianya.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.entity.TVChildBean;

/**
 * Created by Zhy on 2016/6/12.
 * des:
 */
public class TVListAdapter extends BaseRecycleAdapter<TVChildBean, TVListAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TVChildBean entity = items.get(position);
        if (entity == null) return;
        holder.txtTitle.setText(entity.getTV_NAME());
        holder.imgaIcon.setImageURI(Uri.parse(entity.getTV_IMAGE()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) mItemClickListener.onItemClick(v, position);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgaIcon;
        private TextView txtTitle;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgaIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        }
    }

}
