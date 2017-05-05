package individual.leobert.uilib.photoview.config;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.config </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IDisplayModeProxy </p>
 * <p><b>Description:</b> a Proxy to lazy load display-config for PhotoView </p>
 * Created by leobert on 2017/5/4.
 */

public interface IDisplayModeProxy {
    /**
     * set the aspect ratio for wide pic
     * @param ratio pic_width/pic_height
     */
    void setOverWideAspectRatioThreshold(float ratio);

    /**
     * set the aspect ratio for high pic
     * @param ratio pic_width/pic_height
     */
    void setOverHighAspectRatioThreshold(float ratio);

    /**
     * @return the ratio threshold set for over-wide pic
     */
    float getOverWideAspectRatioThreshold();

    /**
     * @return the ratio threshold set for over-height pic
     */
    float getOverHighAspectRatioThreshold();

    void registerNormalPicDisplayModeGetter(IDisplayModeGetter normalPicDisplayModeGetter);

    void registerWidePicDisplayModeGetter(IDisplayModeGetter widePicDisplayModeGetter);

    void registerHighPicDisplayModeGetter(IDisplayModeGetter highPicDisplayModeGetter);

}
