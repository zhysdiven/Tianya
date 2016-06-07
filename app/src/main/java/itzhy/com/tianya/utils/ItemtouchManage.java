package itzhy.com.tianya.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * Created by YB-PC-1462A on 2016/5/20.
 */
public class ItemtouchManage<T> extends ItemTouchHelper.Callback {

    private List<T> lists;
    private RecyclerView.Adapter adapter;
    private int color;

    public ItemtouchManage(RecyclerView.Adapter adapter, List<T> lists) {
        this(adapter, lists, 0);
    }

    public ItemtouchManage(RecyclerView.Adapter adapter, List<T> lists, int color) {
        this.lists = lists;
        this.adapter = adapter;
        this.color = color;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag;
        int swipFlag;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN;
            swipFlag = 0;
        } else {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipFlag = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlag, swipFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int afterPosition = viewHolder.getLayoutPosition();
        int tagPosition = target.getLayoutPosition();
        if (afterPosition > tagPosition) {
            for (int i = afterPosition; i > tagPosition; i--)
                Collections.swap(lists, i, i - 1);
        } else {
            for (int i = afterPosition; i < tagPosition; i++)
                Collections.swap(lists, i, i + 1);
        }
        adapter.notifyItemMoved(afterPosition, tagPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int layoutPosition = viewHolder.getLayoutPosition();
        lists.remove(layoutPosition);
        adapter.notifyItemRemoved(layoutPosition);
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(color);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

}

