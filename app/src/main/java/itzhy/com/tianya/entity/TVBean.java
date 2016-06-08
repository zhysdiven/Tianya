package itzhy.com.tianya.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zhy on 2016/6/8
 * des:
 */
public class TVBean implements Parcelable {

    /**
     * CH_ID : C001
     * CH_NAME : 央视频道
     * CH_TYPE : 0
     * CH_HIDE : 0
     */

    private String CH_ID;
    private String CH_NAME;
    private String CH_TYPE;
    private String CH_HIDE;

    public String getCH_ID() {
        return CH_ID;
    }

    public void setCH_ID(String CH_ID) {
        this.CH_ID = CH_ID;
    }

    public String getCH_NAME() {
        return CH_NAME;
    }

    public void setCH_NAME(String CH_NAME) {
        this.CH_NAME = CH_NAME;
    }

    public String getCH_TYPE() {
        return CH_TYPE;
    }

    public void setCH_TYPE(String CH_TYPE) {
        this.CH_TYPE = CH_TYPE;
    }

    public String getCH_HIDE() {
        return CH_HIDE;
    }

    public void setCH_HIDE(String CH_HIDE) {
        this.CH_HIDE = CH_HIDE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CH_ID);
        dest.writeString(this.CH_NAME);
        dest.writeString(this.CH_TYPE);
        dest.writeString(this.CH_HIDE);
    }

    protected TVBean(Parcel in) {
        this.CH_ID = in.readString();
        this.CH_NAME = in.readString();
        this.CH_TYPE = in.readString();
        this.CH_HIDE = in.readString();
    }

    public static final Parcelable.Creator<TVBean> CREATOR = new Parcelable.Creator<TVBean>() {
        @Override
        public TVBean createFromParcel(Parcel source) {
            return new TVBean(source);
        }

        @Override
        public TVBean[] newArray(int size) {
            return new TVBean[size];
        }
    };

    @Override
    public String toString() {
        return "TVBean{" +
                "CH_ID='" + CH_ID + '\'' +
                ", CH_NAME='" + CH_NAME + '\'' +
                ", CH_TYPE='" + CH_TYPE + '\'' +
                ", CH_HIDE='" + CH_HIDE + '\'' +
                '}';
    }
}
