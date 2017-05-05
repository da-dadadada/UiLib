package individual.leobert.uilib.photoview.gestures;

import android.view.MotionEvent;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.gestures </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> GestureDetector </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

public interface GestureDetector {
    boolean onTouchEvent(MotionEvent var1);

    boolean isScaling();

    boolean isDragging();

    void setOnGestureListener(OnGestureListener var1);
}
