package itzhy.com.tianya.presenter;

import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.contract.TVChildContract;
import itzhy.com.tianya.contract.TVInfoContract;
import itzhy.com.tianya.contract.TVPathContract;
import itzhy.com.tianya.contract.VideoContract;
import itzhy.com.tianya.entity.TVBean;
import itzhy.com.tianya.entity.TVChildBean;
import itzhy.com.tianya.entity.TVListBean;
import itzhy.com.tianya.entity.TVPathBean;
import itzhy.com.tianya.model.VideoModel;

import java.util.List;

/**
 * Created by Zhy on 2016/5/23
 * des:电视直播台presenter
 */
public class VideoListPresenter implements VideoContract.presenter, TVChildContract.presenter, TVInfoContract.presenter, TVPathContract.presenter {

    private VideoModel model;

    public VideoListPresenter() {
        model = new VideoModel();
    }


    /**
     * 获取TV频道组
     */
    @Override
    public void getTvBeans(VideoContract.view view, String key, String action) {
        model.getTVbeans(getTvBeansCallback(view), key, action);
    }

    /**
     * 获取TV频道组的回调
     */
    private APICallBack getTvBeansCallback(final VideoContract.view view) {
        return new APICallBack<List<TVBean>>() {
            @Override
            public void onNetCallBack(List<TVBean> beans) {
                if (beans != null) view.onTvBeansSuccess(beans);
            }
        };
    }

    /**
     * 获取对应子频道
     */
    @Override
    public void getTvChildBeans(TVChildContract.view view, String key, String action, String id) {
        model.getTVChildbeans(getTvChildBeansCallback(view), key, action, id);
    }

    private APICallBack getTvChildBeansCallback(final TVChildContract.view view) {
        return new APICallBack<List<TVChildBean>>() {
            @Override
            public void onNetCallBack(List<TVChildBean> beans) {
                if (beans != null) view.onTvBeansSuccess(beans);
            }
        };
    }


    /**
     * 获取对应日期的节目列表
     */
    @Override
    public void getTvListBeans(TVInfoContract.view view, String key, String action, String tvID, String date) {
        model.getTVListbeans(getTvListBeansCallback(view), key, action, tvID, date);
    }

    private APICallBack getTvListBeansCallback(final TVInfoContract.view view) {
        return new APICallBack<List<TVListBean>>() {
            @Override
            public void onNetCallBack(List<TVListBean> beans) {
                if (beans != null) view.onTvListBeansSuccess(beans);
            }
        };
    }

    /**
     * 获取当前频道的线路详细数据
     */
    @Override
    public void getTvPathBeans(TVPathContract.view view, String key, String action, String id) {
        model.getTVPathbeans(getTvPathBeansCallback(view), key, action, id);
    }

    /**
     * 获取当前频道的线路详细数据回调
     */
    private APICallBack getTvPathBeansCallback(final TVPathContract.view view) {
        return new APICallBack<List<TVPathBean>>() {
            @Override
            public void onNetCallBack(List<TVPathBean> beans) {
                if (beans != null) view.onTvPathSuccess(beans);
            }
        };
    }


}
