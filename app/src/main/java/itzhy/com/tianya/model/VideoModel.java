package itzhy.com.tianya.model;

import com.google.gson.Gson;
import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.api.TvOnlineModleAPI;
import itzhy.com.tianya.net.RetrofitManage;
import itzhy.com.tianya.utils.LogUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoModel {

    public void getTVbeans(APICallBack callBack, String key, String action) {
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("key", key);
        requestParams.put("action", action);
        LogUtils.d(new Gson().toJson(requestParams));

        RetrofitManage.createVideo(TvOnlineModleAPI.class).getTVBeans(key, action).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callBack);
    }

}
