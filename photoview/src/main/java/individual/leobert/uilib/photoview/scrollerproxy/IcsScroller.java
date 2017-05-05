package individual.leobert.uilib.photoview.scrollerproxy;

import android.annotation.TargetApi;
import android.content.Context;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.scrollerproxy </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IcsScroller </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

@TargetApi(14)
public class IcsScroller extends GingerScroller {
    public IcsScroller(Context context) {
        super(context);
    }

    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }
}