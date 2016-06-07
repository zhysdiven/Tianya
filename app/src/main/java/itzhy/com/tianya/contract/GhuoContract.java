package itzhy.com.tianya.contract;

import itzhy.com.tianya.comm.IBasePresenter;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.entity.GHuobean;

import java.util.List;

/**
 * Created by YB-PC-1462A on 2016/5/20.
 */
public interface GhuoContract {

    interface view extends IBaseView{
        void init();

        void ghuoListResult(boolean error, List<GHuobean.ResultsEntity> datas);
    }

    interface presenter extends IBasePresenter {
        void getGhuoTypeList(IBaseView view, String type, int count, int page);
    }
}
