package itzhy.com.tianya.entity;

/**
 * Created by Zhy on 2016/6/12
 * des:当前频道的线路数量
 */
public class TVPathCBean {

    private String LINE_COUNT;

    public String getLINE_COUNT() {
        return LINE_COUNT;
    }

    public void setLINE_COUNT(String LINE_COUNT) {
        this.LINE_COUNT = LINE_COUNT;
    }

    @Override
    public String toString() {
        return "TVPathCBean{" +
                "LINE_COUNT='" + LINE_COUNT + '\'' +
                '}';
    }
}
