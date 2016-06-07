package itzhy.com.tianya.contract;

import itzhy.com.tianya.comm.IBasePresenter;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.entity.VideoOnline;

import java.util.List;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public interface VideoContract {

    interface view extends IBaseView {
        void onVideoOnlineListResult(List<VideoOnline> datas);
    }

    interface presenter extends IBasePresenter {
        void getVideoOnlineList(IBaseView view);
    }

}
