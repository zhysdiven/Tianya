package itzhy.com.tianya.contract;

import itzhy.com.tianya.comm.IBasePresenter;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.entity.TVListBean;

import java.util.List;

/**
 * Created by Zhy on 2016/6/13
 * des:电视节目列表
 */
public interface TVInfoContract {

    interface view extends IBaseView {
        void onTvListBeansSuccess(List<TVListBean> datas);
    }

    interface presenter extends IBasePresenter {
        void getTvListBeans(view view, String key, String action,String tvID,String date);
    }
}
