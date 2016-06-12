package itzhy.com.tianya.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zhy on 2016/6/12
 * des:
 */
public class TVPathBean implements Parcelable {

    /**
     * LINE_ID : 153
     * LINE_URL : http://liveproxy.fengyunzhibo.com:9222/mweb/brtn/CCTV1_512.m3u8
     * LINE_ERRCNT : 8
     */
    private String LINE_ID;
    private String LINE_URL;
    private String LINE_ERRCNT;

    public String getLINE_ID() {
        return LINE_ID;
    }

    public void setLINE_ID(String LINE_ID) {
        this.LINE_ID = LINE_ID;
    }

    public String getLINE_URL() {
        return LINE_URL;
    }

    public void setLINE_URL(String LINE_URL) {
        this.LINE_URL = LINE_URL;
    }

    public String getLINE_ERRCNT() {
        return LINE_ERRCNT;
    }

    public void setLINE_ERRCNT(String LINE_ERRCNT) {
        this.LINE_ERRCNT = LINE_ERRCNT;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.LINE_ID);
        dest.writeString(this.LINE_URL);
        dest.writeString(this.LINE_ERRCNT);
    }

    protected TVPathBean(Parcel in) {
        this.LINE_ID = in.readString();
        this.LINE_URL = in.readString();
        this.LINE_ERRCNT = in.readString();
    }

    public static final Parcelable.Creator<TVPathBean> CREATOR = new Parcelable.Creator<TVPathBean>() {
        @Override
        public TVPathBean createFromParcel(Parcel source) {
            return new TVPathBean(source);
        }

        @Override
        public TVPathBean[] newArray(int size) {
            return new TVPathBean[size];
        }
    };
}
