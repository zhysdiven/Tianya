package itzhy.com.tianya.api;

import itzhy.com.tianya.entity.GHuobean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public interface GhuoModleAPI {

    @GET("data/{type}/{count}/{page}")
    Observable<GHuobean> getGhuoTypeListdata(@Path("type") String type, @Path("count") int count, @Path("page") int page);

}
