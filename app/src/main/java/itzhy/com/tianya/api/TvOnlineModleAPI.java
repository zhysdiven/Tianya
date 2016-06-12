package itzhy.com.tianya.api;

import itzhy.com.tianya.entity.TVBean;
import itzhy.com.tianya.entity.TVChildBean;
import itzhy.com.tianya.entity.TVListBean;
import itzhy.com.tianya.entity.TVPathCBean;
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

    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVChildBean>> getTVChildBeans(@Field("key") String key, @Field("action") String action, @Field("chid") String childID);

    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVListBean>> getTVListBeans(@Field("key") String key, @Field("action") String action, @Field("tvid") String tvID, @Field("tvdate") String date);

    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<List<TVPathCBean>> getTVCountBean(@Field("key") String key, @Field("action") String action, @Field("tvid") String tvID);

}
