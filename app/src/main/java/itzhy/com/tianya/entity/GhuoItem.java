package itzhy.com.tianya.entity;

/**
 * Created by Zhy on 2016/5/21
 * des:
 */
public class GhuoItem {

    private String name;
    private String data;
    private int count;
    private int page;

    public GhuoItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
