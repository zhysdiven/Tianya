package itzhy.com.tianya.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoInfo implements Parcelable {

    private String name;
    private String href;
    private int width;
    private int height;
    private int bitrate;

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.href);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.bitrate);
    }


    protected VideoInfo(Parcel in) {
        this.name = in.readString();
        this.href = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.bitrate = in.readInt();
    }

    public static final Parcelable.Creator<VideoInfo> CREATOR = new Parcelable.Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel source) {
            return new VideoInfo(source);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };


    @Override
    public String toString() {
        return "VideoInfo{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", bitrate=" + bitrate +
                '}';
    }
}
