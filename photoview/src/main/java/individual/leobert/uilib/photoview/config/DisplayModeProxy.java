package individual.leobert.uilib.photoview.config;

import android.graphics.RectF;

import individual.leobert.uilib.photoview.PhotoViewAttacher;
import individual.leobert.uilib.photoview.log.LogManager;
import individual.leobert.uilib.photoview.util.ISizeComparator;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.config </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> DisplayModeProxy </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/4.
 */

public class DisplayModeProxy implements IDisplayModeGetter, IDisplayModeProxy {
    public static final String TAG = "DisplayModeProxy";

    /**
     * Aspect Ratio:width/height
     * define a default Aspect Ratio Threshold for over wide picture
     */
    public static final float DEFAULT_OVERWIDE_ASPECT_RATIO_THRESHOLD = 2.0f;

    /**
     * Aspect Ratio:width/height
     * define a default Aspect Ratio Threshold for over high picture
     */
    public static final float DEFAULT_OVERHIGH_ASPECT_RATIO_THRESHOLD = 0.5f;

    private float overwideAspectRatioThreshold;

    private float overhighAspectRatioThreshold;

    private final PhotoViewAttacher photoViewAttacher;

    private IDisplayModeGetter normalPicDisplayModeGetter;

    private IDisplayModeGetter widePicDisplayModeGetter;

    private IDisplayModeGetter highPicDisplayModeGetter;

    private ISizeComparator sizeComparator;

    public DisplayModeProxy(PhotoViewAttacher photoViewAttacher) {
        this.photoViewAttacher = photoViewAttacher;
        setOverHighAspectRatioThreshold(DEFAULT_OVERHIGH_ASPECT_RATIO_THRESHOLD);
        setOverWideAspectRatioThreshold(DEFAULT_OVERWIDE_ASPECT_RATIO_THRESHOLD);

        registerNormalPicDisplayModeGetter(new DefaultNormalPicDisplayModeGetter());
        registerWidePicDisplayModeGetter(new DefaultWidePicDisplayModeGetter());
        registerHighPicDisplayModeGetter(new DefaultHighPicDisplayModeGetter());

        this.registerSizeComparator(new ISizeComparator.DefaultSizeComparator());
    }

    private IDisplayModeGetter chooseDisplayModeGetter(RectF pic) {
        final float aspectRatio = pic.width()/pic.height();
        if (aspectRatio<getOverHighAspectRatioThreshold())
            return highPicDisplayModeGetter;
        else if (aspectRatio>getOverWideAspectRatioThreshold())
            return widePicDisplayModeGetter;
        else
            return normalPicDisplayModeGetter;
    }


    /**
     * set the aspect ratio for wide pic
     *
     * @param ratio pic_width/pic_height
     */
    @Override
    public void setOverWideAspectRatioThreshold(float ratio) {
        this.overwideAspectRatioThreshold = ratio;
    }

    /**
     * set the aspect ratio for high pic
     *
     * @param ratio pic_width/pic_height
     */
    @Override
    public void setOverHighAspectRatioThreshold(float ratio) {
        this.overhighAspectRatioThreshold = ratio;
    }

    /**
     * @return the ratio threshold set for over-wide pic
     */
    @Override
    public float getOverWideAspectRatioThreshold() {
        return this.overwideAspectRatioThreshold;
    }

    /**
     * @return the ratio threshold set for over-height pic
     */
    @Override
    public float getOverHighAspectRatioThreshold() {
        return this.overhighAspectRatioThreshold;
    }

    @Override
    public void registerNormalPicDisplayModeGetter(IDisplayModeGetter normalPicDisplayModeGetter) {
        if (normalPicDisplayModeGetter == null) {
            LogManager.getLogger().w(TAG, "null is not allowed to be registered");
            return;
        }
        this.normalPicDisplayModeGetter = normalPicDisplayModeGetter;
    }

    @Override
    public void registerWidePicDisplayModeGetter(IDisplayModeGetter widePicDisplayModeGetter) {
        if (widePicDisplayModeGetter == null) {
            LogManager.getLogger().w(TAG, "null is not allowed to be registered");
            return;
        }
        this.widePicDisplayModeGetter = widePicDisplayModeGetter;
    }

    @Override
    public void registerHighPicDisplayModeGetter(IDisplayModeGetter highPicDisplayModeGetter) {
        if (highPicDisplayModeGetter == null) {
            LogManager.getLogger().w(TAG, "null is not allowed to be registered");
            return;
        }
        this.highPicDisplayModeGetter = highPicDisplayModeGetter;
    }

    @Override
    public void registerSizeComparator(ISizeComparator iSizeComparator) {
        if (iSizeComparator == null) {
            LogManager.getLogger().w(TAG, "null is not allowed to be registered");
            return;
        }
        this.sizeComparator = iSizeComparator;
    }

    @Override
    public void proxy(RectF pic) {
        RectF container = new RectF(0,0,photoViewAttacher.getImageView().getWidth(),
                photoViewAttacher.getImageView().getHeight());
        PhotoSize size = sizeComparator.compare(pic,container);
        DisplayMode mode = getDisplayMode(size,pic);
        proxy(mode);
    }

    private void proxy(DisplayMode displayMode) {
        photoViewAttacher.setScaleType(DisplayMode.trans(displayMode));
    }

    /**
     * get display mode for photo
     *
     * @param photoSize PhotoSize see at {@link PhotoSize}
     * @return DisplayMode see at {@link DisplayMode}
     */
    @Override
    public DisplayMode getDisplayMode(PhotoSize photoSize,RectF pic) {
        IDisplayModeGetter modeGetter = chooseDisplayModeGetter(pic);
        return modeGetter.getDisplayMode(photoSize,pic);
    }
}
