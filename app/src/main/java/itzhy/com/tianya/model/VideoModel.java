package itzhy.com.tianya.model;

import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.api.TvOnlineModleAPI;
import itzhy.com.tianya.net.RetrofitManage;
import itzhy.com.tianya.utils.LogUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoModel {

    /**
     * 获取所有频道组
     */
    public void getTVbeans(APICallBack callBack, String key, String action) {
        LogUtils.d(key + "," + action);

        RetrofitManage.createVideo(TvOnlineModleAPI.class).getTVBeans(key, action).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callBack);
    }

    /**
     * 获取对应的子频道
     */
    public void getTVChildbeans(APICallBack callBack, String key, String action, String id) {
        LogUtils.d(key + "," + action + "," + id);

        RetrofitManage.createVideo(TvOnlineModleAPI.class).getTVChildBeans(key, action, id).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callBack);
    }

    /**
     * 获取对应日期的节目列表
     */
    public void getTVListbeans(APICallBack callBack, String key, String action, String id, String date) {
        LogUtils.d(key + "," + action + "," + id + "," + date);

        RetrofitManage.createVideo(TvOnlineModleAPI.class).getTVListBeans(key, action, id, date).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callBack);
    }

}
