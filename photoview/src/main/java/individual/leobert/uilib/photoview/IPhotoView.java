package individual.leobert.uilib.photoview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

import individual.leobert.uilib.photoview.config.IDisplayModeProxy;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IPhotoView </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */

public interface IPhotoView {
    float DEFAULT_MAX_SCALE = 3.0F;
    float DEFAULT_MID_SCALE = 1.75F;
    float DEFAULT_MIN_SCALE = 1.0F;
    int DEFAULT_ZOOM_DURATION = 200;

    boolean canZoom();

    RectF getDisplayRect();

    boolean setDisplayMatrix(Matrix var1);

    Matrix getDisplayMatrix();

    void getDisplayMatrix(Matrix var1);


    float getMinimumScale();


    float getMediumScale();


    float getMaximumScale();

    float getScale();

    ImageView.ScaleType getScaleType();

    void setAllowParentInterceptOnEdge(boolean var1);


    void setMinimumScale(float var1);


    void setMediumScale(float var1);

    void setMaximumScale(float var1);

    void setScaleLevels(float var1, float var2, float var3);

    void setOnLongClickListener(View.OnLongClickListener var1);

    void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener var1);

    void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener var1);


    void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener var1);

    void setRotationTo(float var1);

    void setRotationBy(float var1);


    void setScale(float var1);

    void setScale(float var1, boolean var2);

    void setScale(float var1, float var2, float var3, boolean var4);

    void setScaleType(ImageView.ScaleType var1);

    void setZoomable(boolean var1);


    Bitmap getVisibleRectangleBitmap();

    void setZoomTransitionDuration(int var1);

    IPhotoView getIPhotoViewImplementation();

    void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener var1);

    void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener var1);

    void setOnSingleFlingListener(PhotoViewAttacher.OnSingleFlingListener var1);

    void setIDisplayModeProxy(IDisplayModeProxy displayModeProxy);
}
