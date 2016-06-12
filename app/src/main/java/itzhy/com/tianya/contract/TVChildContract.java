package itzhy.com.tianya.contract;

import itzhy.com.tianya.comm.IBasePresenter;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.entity.TVChildBean;

import java.util.List;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public interface TVChildContract {

    interface view extends IBaseView {
        void onTvBeansSuccess(List<TVChildBean> datas);
    }

    interface presenter extends IBasePresenter {
        void getTvChildBeans(view view, String key, String action, String id);
    }

}
