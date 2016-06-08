package itzhy.com.tianya.presenter;

import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.contract.VideoContract;
import itzhy.com.tianya.entity.TVItemBean;
import itzhy.com.tianya.model.VideoModel;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoListPresenter implements VideoContract.presenter {

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
        return new APICallBack<TVItemBean>() {
            @Override
            public void onNetCallBack(TVItemBean beans) {
                if (beans != null) view.onTvBeansSuccess(beans);
            }
        };
    }


}
