package itzhy.com.tianya.entity;

/**
 * Created by Zhy on 2016/6/12
 * des:
 */
public class TVChildBean {

    /**
     * TV_ID : T0041
     * TV_NAME : 北京卫视
     * TV_IMAGE : http://www.zhituo-app.com/images/tv/199.png
     * CH_ID : C002
     * CH_ID2 : null
     */
    private String TV_ID;
    private String TV_NAME;
    private String TV_IMAGE;
    private String CH_ID;
    private Object CH_ID2;

    public String getTV_ID() {
        return TV_ID;
    }

    public void setTV_ID(String TV_ID) {
        this.TV_ID = TV_ID;
    }

    public String getTV_NAME() {
        return TV_NAME;
    }

    public void setTV_NAME(String TV_NAME) {
        this.TV_NAME = TV_NAME;
    }

    public String getTV_IMAGE() {
        return TV_IMAGE;
    }

    public void setTV_IMAGE(String TV_IMAGE) {
        this.TV_IMAGE = TV_IMAGE;
    }

    public String getCH_ID() {
        return CH_ID;
    }

    public void setCH_ID(String CH_ID) {
        this.CH_ID = CH_ID;
    }

    public Object getCH_ID2() {
        return CH_ID2;
    }

    public void setCH_ID2(Object CH_ID2) {
        this.CH_ID2 = CH_ID2;
    }

    @Override
    public String toString() {
        return "TVChildBean{" +
                "TV_ID='" + TV_ID + '\'' +
                ", TV_NAME='" + TV_NAME + '\'' +
                ", TV_IMAGE='" + TV_IMAGE + '\'' +
                ", CH_ID='" + CH_ID + '\'' +
                ", CH_ID2=" + CH_ID2 +
                '}';
    }
}
