package individual.leobert.uilib.photoview;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> Compat </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

public class Compat {
    private static final int SIXTY_FPS_INTERVAL = 16;

    public Compat() {
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        if(Build.VERSION.SDK_INT >= 16) {
            postOnAnimationJellyBean(view, runnable);
        } else {
            view.postDelayed(runnable, 16L);
        }

    }

    @TargetApi(16)
    private static void postOnAnimationJellyBean(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static int getPointerIndex(int action) {
        return Build.VERSION.SDK_INT >= 11?getPointerIndexHoneyComb(action):getPointerIndexEclair(action);
    }

    @TargetApi(5)
    private static int getPointerIndexEclair(int action) {
        return (action & '\uff00') >> 8;
    }

    @TargetApi(11)
    private static int getPointerIndexHoneyComb(int action) {
        return (action & '\uff00') >> 8;
    }
}
