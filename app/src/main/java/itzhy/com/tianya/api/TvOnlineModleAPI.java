package itzhy.com.tianya.api;

import itzhy.com.tianya.entity.*;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

import java.util.List;

/**
 * Created by Zhy on 2016/6/8
 * des:tv在线直播api
 */
public interface TvOnlineModleAPI {

    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVBean>> getTVBeans(@Field("key") String key, @Field("action") String action);


    /**
     * 获取TV子频道
     */
    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVChildBean>> getTVChildBeans(@Field("key") String key, @Field("action") String action, @Field("chid") String childID);

    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVListBean>> getTVListBeans(@Field("key") String key, @Field("action") String action, @Field("tvid") String tvID, @Field("tvdate") String date);

    /**
     *
     */
    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVPathCBean>> getTVCountBean(@Field("key") String key, @Field("action") String action, @Field("tvid") String tvID);


    /**
     * 获取当前TV频道线路
     */
    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVPathBean>> getTVPathBean(@Field("key") String key, @Field("action") String action, @Field("tvid") String tvID);


}
