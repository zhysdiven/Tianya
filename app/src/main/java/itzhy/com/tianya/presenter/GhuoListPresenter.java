package itzhy.com.tianya.presenter;

import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.contract.GhuoContract;
import itzhy.com.tianya.entity.GHuobean;
import itzhy.com.tianya.model.GhuoModel;
import itzhy.com.tianya.view.activity.GhuoListActivity;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public class GhuoListPresenter implements GhuoContract.presenter {

    private final GhuoModel ghuoModel;

    public GhuoListPresenter() {
        ghuoModel = new GhuoModel();
    }

    @Override
    public void getGhuoTypeList(IBaseView view, String type, int count, int page) {
        ghuoModel.getGhuoTypeList(getGhuolistCall(view), type, count, page);
    }

    private APICallBack getGhuolistCall(final IBaseView view) {
        return new APICallBack<GHuobean>() {
            @Override
            public void onNetCallBack(GHuobean gHuobean) {
                System.out.println(gHuobean.toString());
                if (view instanceof GhuoListActivity) {
                    ((GhuoListActivity) view).ghuoListResult(gHuobean.isError(), gHuobean.getResults());
                }
            }
        };
    }


}
