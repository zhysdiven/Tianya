package itzhy.com.tianya.api;

import itzhy.com.tianya.entity.TVItemBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Zhy on 2016/6/8
 * des:
 */
public interface TvOnlineModleAPI {

    @FormUrlEncoded
    @POST("tv/ws.php")
    Observable<TVItemBean> getTVBeans(@Field("key") String key, @Field("action") String action);

}
