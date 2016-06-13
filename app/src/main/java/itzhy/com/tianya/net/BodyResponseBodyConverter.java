package itzhy.com.tianya.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import itzhy.com.tianya.utils.LogUtils;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * Created by Zhy on 2016/6/12
 * des:
 */
public class BodyResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    BodyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        LogUtils.e("parse:" + string);
        String json = string.substring(string.indexOf("[{") == -1 ? 0 : string.indexOf("[{"));
        try {
            T beans = adapter.fromJson(json);
//            T beans = gson.fromJson(json, new TypeToken<T>() {
//            }.getType());
            return beans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            value.close();
        }
    }
}
