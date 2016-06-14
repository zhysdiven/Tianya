package itzhy.com.tianya.contract;

import itzhy.com.tianya.comm.IBasePresenter;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.entity.TVPathBean;

import java.util.List;

/**
 * Created by Zhy on 2016/6/14
 * des:
 */
public interface TVPathContract {
    interface view extends IBaseView {
        void onTvPathSuccess(List<TVPathBean> datas);
    }

    interface presenter extends IBasePresenter {
        /**
         * 获取当前频道的线路详细数据
         */
        void getTvPathBeans(view view, String key, String action,String id);
    }
}
