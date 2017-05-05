package individual.leobert.uilib.photoview.config;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.config </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IPhotoConfigProvider </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/4/29.
 */

public interface IDisplayModeGetter {
    /**
     * smallPhoto:both width and height of the photo are smaller than the container's
     * @return DisplayMode see at {@link DisplayMode}
     */
    DisplayMode getDisplayModeForSmallPhoto();

    /**
     * middlePhoto:only width or height of the photo is smaller than the container's
     * @return DisplayMode see at {@link DisplayMode}
     */
    DisplayMode getDisplayModeForMiddlePhoto();

    /**
     * largePhoto:both width and height of the photo are larger than /equals the container's
     * @return DisplayMode see at {@link DisplayMode}
     */
    DisplayMode getDisplayModeForLargePhoto();

    /**
     * a default display config for wide picture
     */
    final class DefaultWidePicDisplayModeGetter implements IDisplayModeGetter{
        @Override
        public DisplayMode getDisplayModeForSmallPhoto() {
            return null;
        }

        @Override
        public DisplayMode getDisplayModeForMiddlePhoto() {
            return null;
        }

        @Override
        public DisplayMode getDisplayModeForLargePhoto() {
            return null;
        }
    }

    /**
     * a default display config for normal picture(not wide or high pic)
     */
    final class DefaultNormalPicDisplayModeGetter implements IDisplayModeGetter{
        @Override
        public DisplayMode getDisplayModeForSmallPhoto() {
            return null;
        }

        @Override
        public DisplayMode getDisplayModeForMiddlePhoto() {
            return null;
        }

        @Override
        public DisplayMode getDisplayModeForLargePhoto() {
            return null;
        }
    }

    /**
     * a default display config for high picture
     */
    final class DefaultHighPicDisplayModeGetter implements IDisplayModeGetter{
        @Override
        public DisplayMode getDisplayModeForSmallPhoto() {
            return null;
        }

        @Override
        public DisplayMode getDisplayModeForMiddlePhoto() {
            return null;
        }

        @Override
        public DisplayMode getDisplayModeForLargePhoto() {
            return null;
        }
    }
}
