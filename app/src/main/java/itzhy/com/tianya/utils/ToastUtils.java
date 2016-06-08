package itzhy.com.tianya.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import itzhy.com.tianya.MainApplication;


/**
 * Created by hx on 2015/9/14.
 */
public class ToastUtils {
    private static ToastUtils mInstance;
    private Toast mToast;

    private final long mDuration = 2000; //显示时间
    private String mLastMessage; //上一条显示的文本
    private boolean mShow = false;//toast是否正在显示

    private int mWhat = 99;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == mWhat) cancelToast();
        }
    };

    private ToastUtils(Context c) {
        mToast = Toast.makeText(c, "", Toast.LENGTH_SHORT);
    }

    public static ToastUtils getInstance(Context c) {
        if (mInstance == null) {
            synchronized (ToastUtils.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtils(c);
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public static ToastUtils getInstance() {
        if (mInstance == null) {
            synchronized (ToastUtils.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtils(MainApplication.getApp());
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 显示Toast
     *
     * @param msg 显示的文本
     */
    public synchronized void showMessage(String msg) {
        mToast.setText(msg);
        if (!msg.equals(mLastMessage) || !mShow) {
            mToast.show();
            mShow = true;
            handler.removeMessages(mWhat);
        }
        mLastMessage = msg;
        handler.sendEmptyMessageDelayed(mWhat, mDuration);
    }

    /**
     * 显示Toast
     *
     * @param msgRes 显示的文本id
     */
    public synchronized void showMessage(int msgRes) {
        String msg = MainApplication.getApp().getString(msgRes);
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        mToast.setText(msg);
        if (!msg.equals(mLastMessage) || !mShow) {
            mToast.show();
            mShow = true;
            handler.removeMessages(mWhat);
        }
        mLastMessage = msg;
        handler.sendEmptyMessageDelayed(mWhat, mDuration);
    }

    /**
     * 关闭Toast
     */
    public synchronized void cancelToast() {
        mToast.cancel();
        mShow = false;
    }
}
