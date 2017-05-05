package individual.leobert.uilib.photoview.scrollerproxy;

import android.content.Context;
import android.widget.Scroller;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.scrollerproxy </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> PreGingerScroller </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

public class PreGingerScroller extends ScrollerProxy {
    private final Scroller mScroller;

    public PreGingerScroller(Context context) {
        this.mScroller = new Scroller(context);
    }

    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
        this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
    }

    public void forceFinished(boolean finished) {
        this.mScroller.forceFinished(finished);
    }

    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    public int getCurrY() {
        return this.mScroller.getCurrY();
    }
}