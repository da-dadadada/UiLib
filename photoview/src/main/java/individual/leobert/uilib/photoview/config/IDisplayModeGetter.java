package individual.leobert.uilib.photoview.config;

import android.graphics.RectF;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.config </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IPhotoConfigProvider </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/4/29.
 */

public interface IDisplayModeGetter {
    /**
     * get display mode for photo
     *
     * @param photoSize PhotoSize see at {@link PhotoSize}
     * @return DisplayMode see at {@link DisplayMode}
     */
    DisplayMode getDisplayMode(PhotoSize photoSize, RectF pic);

    /**
     * a default display config for wide picture
     */
    final class DefaultWidePicDisplayModeGetter implements IDisplayModeGetter {

        /**
         * get display mode for photo
         *
         * @param photoSize PhotoSize see at {@link PhotoSize}
         * @param pic
         * @return DisplayMode see at {@link DisplayMode}
         */
        @Override
        public DisplayMode getDisplayMode(PhotoSize photoSize, RectF pic) {
            switch (photoSize) {
                case small:
                    return DisplayMode.CENTER;
                case middle:
                    return DisplayMode.CENTER_INSIDE;
                case large:
                    return DisplayMode.CENTER_INSIDE;
                default:
                    return DisplayMode.CENTER_INSIDE;
            }
        }
    }

    /**
     * a default display config for normal picture(not wide or high pic)
     */
    final class DefaultNormalPicDisplayModeGetter implements IDisplayModeGetter {
        @Override
        public DisplayMode getDisplayMode(PhotoSize photoSize, RectF pic) {
            switch (photoSize) {
                case small:
                    return DisplayMode.CENTER;
                case middle:
                    return DisplayMode.CENTER_INSIDE;
                case large:
                    return DisplayMode.CENTER_INSIDE;
                default:
                    return DisplayMode.CENTER_INSIDE;
            }
        }
    }

    /**
     * a default display config for high picture
     */
    final class DefaultHighPicDisplayModeGetter implements IDisplayModeGetter {
        @Override
        public DisplayMode getDisplayMode(PhotoSize photoSize, RectF pic) {
            switch (photoSize) {
                case small:
                    return DisplayMode.CENTER;
                case middle:
                    return DisplayMode.CENTER_INSIDE;
                case large:
                    return DisplayMode.CENTER_INSIDE;
                default:
                    return DisplayMode.CENTER_INSIDE;
            }
        }
    }
}
