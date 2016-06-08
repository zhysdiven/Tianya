package itzhy.com.tianya.view.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by YB-PC-1462A on 2016/5/20.
 */
public class FragmentFactory {

    private static HashMap<String, Fragment> titles;


    public static Fragment newInstance(String title) {
        if (titles == null) {
            titles = new HashMap<String, Fragment>();
        }
        Fragment fragment = titles.get(title);
        if (fragment == null) {
            switch (title) {
                case "main":
                    VideoFragment mainFragment = VideoFragment.newInstance();
                    titles.put("main", mainFragment);
                    return mainFragment;
                case "app":
                    AppFragment appFragment = AppFragment.newInstance();
                    titles.put("app", appFragment);
                    return appFragment;
                case "play":
                    PlayFragment playFragment = PlayFragment.newInstance();
                    titles.put("play", playFragment);
                    return playFragment;
                case "ghuo":
                    GHuoFragment gHuoFragment = GHuoFragment.newInstance();
                    titles.put("ghuo", gHuoFragment);
                    return gHuoFragment;
                default:
                    return null;
            }
        }
        return fragment;
    }

}
