package itzhy.com.tianya.entity;

/**
 * Created by Zhy on 2016/6/12
 * des:tv节目列表
 */
public class TVListBean {

    /**
     * TV_TIME : 01:00
     * TV_PROG : 晚间气象服务
     */

    private String TV_TIME;
    private String TV_PROG;

    public String getTV_TIME() {
        return TV_TIME;
    }

    public void setTV_TIME(String TV_TIME) {
        this.TV_TIME = TV_TIME;
    }

    public String getTV_PROG() {
        return TV_PROG;
    }

    public void setTV_PROG(String TV_PROG) {
        this.TV_PROG = TV_PROG;
    }

    @Override
    public String toString() {
        return "TVListBean{" +
                "TV_TIME='" + TV_TIME + '\'' +
                ", TV_PROG='" + TV_PROG + '\'' +
                '}';
    }

}
