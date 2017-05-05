package individual.leobert.uilib.photoview.gestures;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.gestures </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> OnGestureListener </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */
public interface OnGestureListener {
    void onDrag(float var1, float var2);

    void onFling(float var1, float var2, float var3, float var4);

    void onScale(float var1, float var2, float var3);
}
