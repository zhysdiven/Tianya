package itzhy.com.tianya.contract;

import itzhy.com.tianya.comm.IBasePresenter;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.entity.TVItemBean;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public interface VideoContract {

    interface view extends IBaseView {
        void onTvBeansSuccess(TVItemBean datas);
    }

    interface presenter extends IBasePresenter {
        void getTvBeans(view view, String key, String action);
    }

}
