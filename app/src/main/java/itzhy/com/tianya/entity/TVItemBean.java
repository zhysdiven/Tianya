package itzhy.com.tianya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zhy on 2016/6/8
 * des:
 */
public class TVItemBean implements Serializable {

    private List<TVBean> been;

    public List<TVBean> getBeen() {
        return been;
    }

    public void setBeen(List<TVBean> been) {
        this.been = been;
    }
}
