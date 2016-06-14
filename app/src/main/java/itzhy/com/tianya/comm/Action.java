package itzhy.com.tianya.comm;

/**
 * Created by Zhy on 2016/6/14
 * des:
 */
public class Action {

    public static final int HANDLER_BUFFER_START = 1;
    public static final int HANDLER_BUFFER_END = 2;
    public static final int HANDLER_SURFACE_SIZE = 3;

    public static final int SURFACE_BEST_FIT = 0;
    public static final int SURFACE_FIT_HORIZONTAL = 1;
    public static final int SURFACE_FIT_VERTICAL = 2;
    public static final int SURFACE_FILL = 3;
    public static final int SURFACE_16_9 = 4;
    public static final int SURFACE_4_3 = 5;
    public static final int SURFACE_ORIGINAL = 6;
    public int mCurrentSize = /*SURFACE_BEST_FIT*/SURFACE_FILL;

}
