package individual.leobert.uilib.photoview.gestures;

import android.content.Context;
import android.os.Build;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.gestures </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> VersionedGestureDetector </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

public final class VersionedGestureDetector {
    public VersionedGestureDetector() {
    }

    public static GestureDetector newInstance(Context context, OnGestureListener listener) {
        int sdkVersion = Build.VERSION.SDK_INT;
        Object detector;
        if(sdkVersion < 5) {
            detector = new CupcakeGestureDetector(context);
        } else if(sdkVersion < 8) {
            detector = new EclairGestureDetector(context);
        } else {
            detector = new FroyoGestureDetector(context);
        }

        ((GestureDetector)detector).setOnGestureListener(listener);
        return (GestureDetector)detector;
    }
}
