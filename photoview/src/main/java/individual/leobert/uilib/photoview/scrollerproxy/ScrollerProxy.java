package individual.leobert.uilib.photoview.scrollerproxy;

import android.content.Context;
import android.os.Build;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.scrollerproxy </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> ScrollProxy </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

public abstract class ScrollerProxy {
    public ScrollerProxy() {
    }

    public static ScrollerProxy getScroller(Context context) {
        return (ScrollerProxy)(Build.VERSION.SDK_INT < 9?new PreGingerScroller(context):(Build.VERSION.SDK_INT < 14?new GingerScroller(context):new IcsScroller(context)));
    }

    public abstract boolean computeScrollOffset();

    public abstract void fling(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

    public abstract void forceFinished(boolean var1);

    public abstract boolean isFinished();

    public abstract int getCurrX();

    public abstract int getCurrY();
}
