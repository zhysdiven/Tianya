package itzhy.com.tianya;

import android.app.Application;
import android.content.Context;
import itzhy.com.tianya.net.RetrofitManage;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public class MainApplication extends Application {

    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        app = this;
        RetrofitManage.initGhuo();
    }
}
