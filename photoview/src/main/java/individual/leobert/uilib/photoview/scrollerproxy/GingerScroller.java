package individual.leobert.uilib.photoview.scrollerproxy;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.scrollerproxy </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GingerScroller </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

@TargetApi(9)
public class GingerScroller extends ScrollerProxy {
    protected final OverScroller mScroller;
    private boolean mFirstScroll = false;

    public GingerScroller(Context context) {
        this.mScroller = new OverScroller(context);
    }

    public boolean computeScrollOffset() {
        if(this.mFirstScroll) {
            this.mScroller.computeScrollOffset();
            this.mFirstScroll = false;
        }

        return this.mScroller.computeScrollOffset();
    }

    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
        this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, overX, overY);
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