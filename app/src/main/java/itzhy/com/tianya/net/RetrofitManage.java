package itzhy.com.tianya.net;

import itzhy.com.tianya.comm.AppConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YB-PC-1462A on 2016/5/21.
 */
public class RetrofitManage {

    private static Retrofit ghuoRetrofit;

    static {
    }

    public static void initGhuo() {
        ghuoRetrofit = new Retrofit.Builder().client(new OkHttpClient()).baseUrl(AppConfig.BASE_URL_GHUO).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static <T> T createGhuo(Class<T> clazz) {
        return ghuoRetrofit.create(clazz);
    }


}
