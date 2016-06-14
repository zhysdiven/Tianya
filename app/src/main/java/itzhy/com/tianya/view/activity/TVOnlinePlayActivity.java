package itzhy.com.tianya.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.*;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import itzhy.com.tianya.MainApplication;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.TVPathAdapter;
import itzhy.com.tianya.comm.Action;
import itzhy.com.tianya.contract.TVPathContract;
import itzhy.com.tianya.entity.TVPathBean;
import itzhy.com.tianya.presenter.VideoListPresenter;
import itzhy.com.tianya.utils.DateUtils;
import itzhy.com.tianya.utils.LogUtils;
import itzhy.com.tianya.utils.ToastUtils;
import org.videolan.libvlc.EventHandler;
import org.videolan.libvlc.IVideoPlayer;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.LibVlcException;
import org.videolan.vlc.util.VLCInstance;

import java.util.List;

public class TVOnlinePlayActivity extends Activity implements SurfaceHolder.Callback, IVideoPlayer, View.OnClickListener, TVPathContract.view {

    @InjectView(R.id.video)
    SurfaceView surfaceView;
    @InjectView(R.id.video_loading)
    LinearLayout videoLoading;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.txt_play)
    TextView txtPlay;
    @InjectView(R.id.txt_playing)
    TextView txtPlaying;
    @InjectView(R.id.txt_name)
    TextView txtName;
    @InjectView(R.id.txt_time)
    TextView txtTime;
    @InjectView(R.id.txt_kb)
    TextView txtKb;
    @InjectView(R.id.iv_lock)
    ImageView ivLock;
    @InjectView(R.id.rlayout_title)
    FrameLayout layoutTitle;
    @InjectView(R.id.iv_play)
    ImageView ivPlay;
    @InjectView(R.id.txt_cur_time)
    TextView txtCurTime;
    @InjectView(R.id.sb_process)
    SeekBar sbProcess;
    @InjectView(R.id.txt_max_time)
    TextView txtMaxTime;
    @InjectView(R.id.btn_path)
    Button btnPath;
    @InjectView(R.id.llayout_bottom)
    FrameLayout layoutBottom;

    private LibVLC mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;

    private int mVideoHeight;
    private int mVideoWidth;
    private int mVideoVisibleHeight;
    private int mVideoVisibleWidth;
    private int mSarNum;
    private int mSarDen;
    private int mCurrentSize = Action.SURFACE_FILL;

    private boolean isLock;
    private boolean isShowTitle;

    private VideoListPresenter presenter;
    private List<TVPathBean> datas;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tvonline_play);
        ButterKnife.inject(this);

        initMedia();
        initListener();
        initData();
    }

    private void initMedia() {
        try {
            mMediaPlayer = VLCInstance.getLibVlcInstance();
        } catch (LibVlcException e) {
            e.printStackTrace();
        }
        mSurfaceHolder = surfaceView.getHolder();
        mSurfaceHolder.setFormat(PixelFormat.RGB_888);
        mSurfaceHolder.addCallback(this);

        mMediaPlayer.eventVideoPlayerActivityCreated(true);
        EventHandler em = EventHandler.getInstance();
        em.addHandler(mVlcHandler);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        surfaceView.setKeepScreenOn(true);
        surfaceView.setOnClickListener(this);
    }

    private void initData() {
        id = getIntent().getStringExtra("tv_id");
        txtName.setText(getIntent().getStringExtra("name"));

        presenter = new VideoListPresenter();
        presenter.getTvPathBeans(this, MainApplication.getKey(), "7", id);
        txtTime.setText(DateUtils.getCurTime());
        sbProcess.setEnabled(false);
    }

    private void initListener() {
        ivLock.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btnPath.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mMediaPlayer != null) {
            if (!mMediaPlayer.isPlaying()) {
                surfaceView.setKeepScreenOn(true);
                mMediaPlayer.play();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            surfaceView.setKeepScreenOn(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.eventVideoPlayerActivityCreated(false);

            EventHandler em = EventHandler.getInstance();
            em.removeHandler(mVlcHandler);
        }
    }

    private Handler mVlcHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null || msg.getData() == null) return;
            switch (msg.getData().getInt("event")) {
                case EventHandler.MediaPlayerTimeChanged:
                    LogUtils.i("event===========================>MediaPlayerTimeChanged");
                    break;
                case EventHandler.MediaPlayerPositionChanged:
                    LogUtils.i("event===========================>MediaPlayerPositionChanged");
                    break;
                case EventHandler.MediaPlayerPlaying:
                    LogUtils.i("event===========================>MediaPlayerPlaying");
                    //
                    mHandler.removeMessages(Action.HANDLER_BUFFER_END);
                    mHandler.sendEmptyMessage(Action.HANDLER_BUFFER_END);
                    break;
                case EventHandler.MediaPlayerBuffering:
                    LogUtils.i("event===========================>MediaPlayerBuffering");
                    break;
                case EventHandler.MediaPlayerLengthChanged:
                    LogUtils.i("event===========================>MediaPlayerLengthChanged");
                    break;
                case EventHandler.MediaPlayerEndReached:
                    LogUtils.i("event===========================>MediaPlayerEndReached");
                    //播放完成
                    break;
            }
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.i("mHandler ==> what:" + msg.what);
            switch (msg.what) {
                case Action.HANDLER_BUFFER_START:
                    showLoading();
                    break;
                case Action.HANDLER_BUFFER_END:
                    hideLoading();
                    setLayoutVisible(View.GONE);
                    isShowTitle = false;
                    break;
                case Action.HANDLER_SURFACE_SIZE:
                    changeSurfaceSize();
                    break;
            }
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        setSurfaceSize(mVideoWidth, mVideoHeight, mVideoVisibleWidth, mVideoVisibleHeight, mSarNum, mSarDen);
        LogUtils.i("onConfigurationChanged ==> mVideoWidth:" + mVideoWidth + ",mVideoHeight:" + mVideoHeight + ",mVideoVisibleWidth:" + mVideoVisibleWidth + ",mVideoVisibleHeight:" + mVideoVisibleHeight + ",mSarNum:" + mSarNum + ",mSarDen:" + mSarDen);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mMediaPlayer != null) {
            mSurfaceHolder = holder;
            mMediaPlayer.attachSurface(holder.getSurface(), this);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        LogUtils.i("surfaceChanged ==> width:" + width + ",height:" + height);
        mSurfaceHolder = holder;
        if (mMediaPlayer != null) {
            mMediaPlayer.attachSurface(holder.getSurface(), this);//, width, height
        }
        if (width > 0) {
            mVideoHeight = height;
            mVideoWidth = width;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mMediaPlayer != null) {
            mMediaPlayer.detachSurface();
        }
    }

    @Override
    public void setSurfaceSize(int width, int height, int visible_width, int visible_height, int sar_num, int sar_den) {
        mVideoHeight = height;
        mVideoWidth = width;
        mVideoVisibleHeight = visible_height;
        mVideoVisibleWidth = visible_width;
        mSarNum = sar_num;
        mSarDen = sar_den;
        mHandler.removeMessages(Action.HANDLER_SURFACE_SIZE);
        mHandler.sendEmptyMessage(Action.HANDLER_SURFACE_SIZE);
    }

    private void showLoading() {
        videoLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        videoLoading.setVisibility(View.GONE);
    }

    private void changeSurfaceSize() {
        changeSurfaceSize(mCurrentSize);
    }

    private void changeSurfaceSize(int size) {
        // get screen size
        int dw = getWindowManager().getDefaultDisplay().getWidth();
        int dh = getWindowManager().getDefaultDisplay().getHeight();

        // calculate aspect ratio
        double ar = (double) mVideoWidth / (double) mVideoHeight;
        // calculate display aspect ratio
        double dar = (double) dw / (double) dh;

        switch (size) {
            case Action.SURFACE_BEST_FIT:
                if (dar < ar) dh = (int) (dw / ar);
                else dw = (int) (dh * ar);
                break;
            case Action.SURFACE_FIT_HORIZONTAL:
                dh = (int) (dw / ar);
                break;
            case Action.SURFACE_FIT_VERTICAL:
                dw = (int) (dh * ar);
                break;
            case Action.SURFACE_FILL:
                break;
            case Action.SURFACE_16_9:
                ar = 16.0 / 9.0;
                if (dar < ar) dh = (int) (dw / ar);
                else dw = (int) (dh * ar);
                break;
            case Action.SURFACE_4_3:
                ar = 4.0 / 3.0;
                if (dar < ar) dh = (int) (dw / ar);
                else dw = (int) (dh * ar);
                break;
            case Action.SURFACE_ORIGINAL:
                dh = mVideoHeight;
                dw = mVideoWidth;
                break;
        }
        mSurfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        lp.width = dw;
        lp.height = dh;
        surfaceView.setLayoutParams(lp);
        surfaceView.invalidate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.i("keycode:" + keyCode + ",event:" + event.getAction());
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            int volume = mMediaPlayer.getVolume();
            LogUtils.i("voice ++++++++++++++++++" + volume);
            mMediaPlayer.setVolume(volume + 1);
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            int volume = mMediaPlayer.getVolume();
            LogUtils.i("voice ------------------" + volume);
            mMediaPlayer.setVolume(volume - 1);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video:
                LogUtils.d("click ==> video");
                if (isShowTitle) {
                    setLayoutVisible(View.GONE);
                    isShowTitle = false;
                } else {
                    setLayoutVisible(View.VISIBLE);
                    isShowTitle = true;
                }
                break;
            case R.id.iv_play: //播放|暂停
                LogUtils.d("click ==> iv_play");
                if (mMediaPlayer.isPlaying()) {
                    ivPlay.setImageResource(android.R.drawable.ic_media_pause);
                    mMediaPlayer.pause();
                } else {
                    ivPlay.setImageResource(android.R.drawable.ic_media_play);
                    mMediaPlayer.play();
                }
                break;
            case R.id.iv_lock: //锁屏
                LogUtils.d("click ==> iv_lock");
                if (isLock) {
                    ivLock.setImageResource(R.mipmap.ic_lock_open_wht_24dp);
                    isLock = false;
                } else {
                    ivLock.setImageResource(R.mipmap.ic_lock_outline_wht_24dp);
                    isLock = true;
                }
                setLayoutVisible(View.GONE);
                break;
            case R.id.iv_back:
                LogUtils.d("click ==> iv_back");
                finish();
                break;
            case R.id.btn_path: //线路选择
                LogUtils.d("click ==> btn_path");
                new AlertDialog.Builder(this).setAdapter(new TVPathAdapter(datas), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mMediaPlayer.stop();
                        videoLoading.setVisibility(View.VISIBLE);
                        mMediaPlayer.playMRL(datas.get(which).getLINE_URL());
                    }
                }).create().show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("线路选择");
        menu.add(0, 1, 0, "线路一");
        menu.add(0, 2, 0, "线路二");
        menu.add(0, 3, 0, "线路三");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                ToastUtils.getInstance(this).showMessage("线路一");
                break;
        }
        return true;
    }

    private void setLayoutVisible(int visible) {
        layoutTitle.setVisibility(visible);
        layoutBottom.setVisibility(visible);
    }


    @Override
    public void onTvPathSuccess(List<TVPathBean> datas) {
        if (datas != null && datas.size() > 0) {
            this.datas = datas;
            TVPathBean bean = datas.get(0);
//            "http://liveproxy.fengyunzhibo.com:9222/mweb/brtn/CCTV1_512.m3u8"
            mMediaPlayer.playMRL(bean.getLINE_URL());
        }
    }

}
