package itzhy.com.tianya.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public abstract class BaseRecycleAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected ArrayList<M> items = new ArrayList<M>();
    protected OnItemClickListener mItemClickListener;
    protected OnItemLongClickListener mItemLongClickListener;


    public ArrayList<M> getItems() {
        return items;
    }

    public void add(M object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, M object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends M> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(M... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(M object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public void delete(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    public M getItem(int position) {
        if (position >= 0 && items.size() > 0 && position < items.size()) return items.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        int count = items.size();
        return count;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setOnItemLongClickListener(final OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
    }
}
