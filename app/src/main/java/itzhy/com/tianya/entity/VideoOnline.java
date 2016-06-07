package itzhy.com.tianya.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Zhy on 2016/5/23
 * des:
 */
public class VideoOnline implements Parcelable {

    private String group;
    private int groupID;
    private String groupName;
    private String name;
    private int id;
    private String guide;
    private String iamgeUrl;
    private List<VideoInfo> videos;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getIamgeUrl() {
        return iamgeUrl;
    }

    public void setIamgeUrl(String iamgeUrl) {
        this.iamgeUrl = iamgeUrl;
    }

    public List<VideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoInfo> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "VideoOnline{" +
                "group='" + group + '\'' +
                ", groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", guide='" + guide + '\'' +
                ", iamgeUrl='" + iamgeUrl + '\'' +
                ", videos=" + videos +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.group);
        dest.writeInt(this.groupID);
        dest.writeString(this.groupName);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.guide);
        dest.writeString(this.iamgeUrl);
        dest.writeTypedList(this.videos);
    }

    public VideoOnline() {
    }

    protected VideoOnline(Parcel in) {
        this.group = in.readString();
        this.groupID = in.readInt();
        this.groupName = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
        this.guide = in.readString();
        this.iamgeUrl = in.readString();
        this.videos = in.createTypedArrayList(VideoInfo.CREATOR);
    }

    public static final Parcelable.Creator<VideoOnline> CREATOR = new Parcelable.Creator<VideoOnline>() {
        @Override
        public VideoOnline createFromParcel(Parcel source) {
            return new VideoOnline(source);
        }

        @Override
        public VideoOnline[] newArray(int size) {
            return new VideoOnline[size];
        }
    };
}
