package itzhy.com.tianya;

import android.app.Application;
import android.content.Context;
import itzhy.com.tianya.net.RetrofitManage;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public class MainApplication extends Application {

    private static Context app;
    private static String key = "8540928";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        app = this;
        RetrofitManage.initGhuo();
    }

    public static Context getApp() {
        return app;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String keys) {
        key = keys;
    }
}
