package individual.leobert.uilib.photoview.log;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.log </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> Logger </p>
 * <p><b>Description:</b> log interface </p>
 * Created by leobert on 2017/5/3.
 */

public interface Logger {
    int v(String tag, String msg);

    int v(String tag, String msg, Throwable thr);

    int d(String tag, String msg);

    int d(String tag, String msg, Throwable thr);

    int i(String tag, String msg);

    int i(String tag, String msg, Throwable thr);

    int w(String tag, String msg);

    int w(String tag, String msg, Throwable thr);

    int e(String tag, String msg);

    int e(String tag, String msg, Throwable thr);
}
