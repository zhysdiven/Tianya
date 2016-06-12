package itzhy.com.tianya.presenter;

import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.contract.TVChildContract;
import itzhy.com.tianya.contract.VideoContract;
import itzhy.com.tianya.entity.TVBean;
import itzhy.com.tianya.entity.TVChildBean;
import itzhy.com.tianya.model.VideoModel;

import java.util.List;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoListPresenter implements VideoContract.presenter, TVChildContract.presenter {

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
}
