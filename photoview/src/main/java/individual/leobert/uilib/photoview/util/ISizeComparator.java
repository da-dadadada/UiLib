package individual.leobert.uilib.photoview.util;

import android.graphics.RectF;
import android.support.annotation.NonNull;

import individual.leobert.uilib.photoview.config.PhotoSize;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview.util </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> ISizeComparator </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/5.
 */

public interface ISizeComparator {
    PhotoSize compare(@NonNull RectF photo, @NonNull RectF container);

    class DefaultSizeComparator implements ISizeComparator {

        @Override
        public PhotoSize compare(@NonNull RectF photo, @NonNull RectF container) {
            if (photo.isEmpty())
                return PhotoSize.small;

            if (photo.width() < container.width()
                    && photo.height() < container.height())
                return PhotoSize.small;
            else if (photo.width() > container.width()
                    && photo.height() > container.height())
                return PhotoSize.large;
            return PhotoSize.middle;
        }
    }
}
