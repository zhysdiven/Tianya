package itzhy.com.tianya.api;

import itzhy.com.tianya.utils.LogUtils;
import rx.Subscriber;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public abstract class APICallBack<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        //请求回调统一处理,
        LogUtils.d(t.toString());
        onNetCallBack(t);
    }

    /**
     * 子类分别特殊处理
     */
    public abstract void onNetCallBack(T t);


}
