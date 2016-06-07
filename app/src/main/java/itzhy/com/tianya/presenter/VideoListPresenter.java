package itzhy.com.tianya.presenter;

import itzhy.com.tianya.comm.IBaseView;
import itzhy.com.tianya.contract.VideoContract;
import itzhy.com.tianya.entity.VideoOnline;
import itzhy.com.tianya.model.VideoModel;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoListPresenter implements VideoContract.presenter {

    private VideoModel model;

    public VideoListPresenter() {
        model = new VideoModel();
    }

    @Override
    public void getVideoOnlineList(IBaseView view) {
        Observable.create(new Observable.OnSubscribe<List<VideoOnline>>() {
            @Override
            public void call(Subscriber<? super List<VideoOnline>> subscriber) {

            }
        });
    }


}
