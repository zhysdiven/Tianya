package itzhy.com.tianya.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YB-PC-1462A on 2016/5/21.
 */
public class GHuobean implements Parcelable {

    /**
     * error : false
     * results : [{"_id":"573c24f16776591ca2f31b86","createdAt":"2016-05-18T16:16:49.305Z","desc":"高仿微信手势滑动返回","publishedAt":"2016-05-19T12:09:29.617Z","source":"chrome","type":"Android","url":"https://github.com/hanhailong/SwipeBackSample","used":true,"who":"大熊"}]
     */

    private boolean error;
    /**
     * _id : 573c24f16776591ca2f31b86
     * createdAt : 2016-05-18T16:16:49.305Z
     * desc : 高仿微信手势滑动返回
     * publishedAt : 2016-05-19T12:09:29.617Z
     * source : chrome
     * type : Android
     * url : https://github.com/hanhailong/SwipeBackSample
     * used : true
     * who : 大熊
     */
    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity implements Parcelable {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        protected ResultsEntity(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Creator<ResultsEntity> CREATOR = new Creator<ResultsEntity>() {
            @Override
            public ResultsEntity createFromParcel(Parcel source) {
                return new ResultsEntity(source);
            }

            @Override
            public ResultsEntity[] newArray(int size) {
                return new ResultsEntity[size];
            }
        };
    }

    @Override
    public String toString() {
        return "GHuobean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    protected GHuobean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsEntity>();
        in.readList(this.results, ResultsEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GHuobean> CREATOR = new Parcelable.Creator<GHuobean>() {
        @Override
        public GHuobean createFromParcel(Parcel source) {
            return new GHuobean(source);
        }

        @Override
        public GHuobean[] newArray(int size) {
            return new GHuobean[size];
        }
    };
}
