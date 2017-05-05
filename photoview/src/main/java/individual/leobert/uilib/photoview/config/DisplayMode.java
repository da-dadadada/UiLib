package individual.leobert.uilib.photoview.config;

import android.graphics.Matrix;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.config </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> DisplayMode </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/4/29.
 */

public enum DisplayMode {

    /**
     * Scale the image using {@link Matrix.ScaleToFit#FILL}.
     * From XML, use this syntax: <code>android:scaleType="fitXY"</code>.
     */
    FIT_XY,
    /**
     * Scale the image using {@link Matrix.ScaleToFit#START}.
     * From XML, use this syntax: <code>android:scaleType="fitStart"</code>.
     */
    FIT_START,
    /**
     * Scale the image using {@link Matrix.ScaleToFit#CENTER}.
     * From XML, use this syntax:
     * <code>android:scaleType="fitCenter"</code>.
     */
    FIT_CENTER,
    /**
     * Scale the image using {@link Matrix.ScaleToFit#END}.
     * From XML, use this syntax: <code>android:scaleType="fitEnd"</code>.
     */
    FIT_END,
    /**
     * Center the image in the view, but perform no scaling.
     * From XML, use this syntax: <code>android:scaleType="center"</code>.
     */
    CENTER,
    /**
     * Scale the image uniformly (maintain the image's aspect ratio) so
     * that both dimensions (width and height) of the image will be equal
     * to or larger than the corresponding dimension of the view
     * (minus padding). The image is then centered in the view.
     * From XML, use this syntax: <code>android:scaleType="centerCrop"</code>.
     */
    CENTER_CROP,
    /**
     * Scale the image uniformly (maintain the image's aspect ratio) so
     * that both dimensions (width and height) of the image will be equal
     * to or less than the corresponding dimension of the view
     * (minus padding). The image is then centered in the view.
     * From XML, use this syntax: <code>android:scaleType="centerInside"</code>.
     */
    CENTER_INSIDE;

    private static HashMap<DisplayMode,ImageView.ScaleType> maps= new HashMap<>();
    static {
        maps.put(FIT_XY, ImageView.ScaleType.FIT_XY);
        maps.put(FIT_START, ImageView.ScaleType.FIT_START);
        maps.put(FIT_CENTER, ImageView.ScaleType.FIT_CENTER);
        maps.put(FIT_END, ImageView.ScaleType.FIT_END);

        maps.put(CENTER, ImageView.ScaleType.CENTER); //default
        maps.put(CENTER_CROP, ImageView.ScaleType.CENTER_CROP);
        maps.put(CENTER_INSIDE, ImageView.ScaleType.CENTER_INSIDE);
    }

    public static ImageView.ScaleType trans(DisplayMode displayMode) {
        if (displayMode == null)
            return ImageView.ScaleType.CENTER;
        if (maps.containsKey(displayMode))
            return maps.get(displayMode);
        else
            return ImageView.ScaleType.CENTER;
    }

}
