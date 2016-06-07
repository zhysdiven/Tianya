package itzhy.com.tianya.model;

import itzhy.com.tianya.api.APICallBack;
import itzhy.com.tianya.api.GhuoModleAPI;
import itzhy.com.tianya.net.RetrofitManage;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public class GhuoModel {

    public void getGhuoTypeList(APICallBack callback, String type, int count, int page) {
        RetrofitManage.createGhuo(GhuoModleAPI.class).getGhuoTypeListdata(type, count, page).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback);
    }


}
