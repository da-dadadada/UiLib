package individual.leobert.uilib.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.ImageView;

import individual.leobert.uilib.photoview.config.DisplayModeProxy;
import individual.leobert.uilib.photoview.config.IDisplayModeProxy;

/**
 * <p><b>Package:</b> individual.leobert.uilib.photoview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> PhotoView </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/3.
 */


public class PhotoView extends ImageView implements IPhotoView {
    private PhotoViewAttacher mAttacher;
    private ScaleType mPendingScaleType;

    public PhotoView(Context context) {
        this(context, (AttributeSet)null);
    }

    public PhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public PhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        super.setScaleType(ScaleType.MATRIX);
        this.init();
    }

    protected void init() {
        if(null == this.mAttacher || null == this.mAttacher.getImageView()) {
            this.mAttacher = new PhotoViewAttacher(this);
        }

        mAttacher.setIDisplayModeProxy(new DisplayModeProxy(mAttacher));

        if(null != this.mPendingScaleType) {
            this.setScaleType(this.mPendingScaleType);
            this.mPendingScaleType = null;
        }

    }

    /** @deprecated */
    public void setPhotoViewRotation(float rotationDegree) {
        this.mAttacher.setRotationTo(rotationDegree);
    }

    public void setRotationTo(float rotationDegree) {
        this.mAttacher.setRotationTo(rotationDegree);
    }

    public void setRotationBy(float rotationDegree) {
        this.mAttacher.setRotationBy(rotationDegree);
    }

    public boolean canZoom() {
        return this.mAttacher.canZoom();
    }

    public RectF getDisplayRect() {
        return this.mAttacher.getDisplayRect();
    }

    public Matrix getDisplayMatrix() {
        return this.mAttacher.getDisplayMatrix();
    }

    public void getDisplayMatrix(Matrix matrix) {
        this.mAttacher.getDisplayMatrix(matrix);
    }

    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return this.mAttacher.setDisplayMatrix(finalRectangle);
    }

    /** @deprecated */
    @Deprecated
    public float getMinScale() {
        return this.getMinimumScale();
    }

    public float getMinimumScale() {
        return this.mAttacher.getMinimumScale();
    }

    /** @deprecated */
    @Deprecated
    public float getMidScale() {
        return this.getMediumScale();
    }

    public float getMediumScale() {
        return this.mAttacher.getMediumScale();
    }

    /** @deprecated */
    @Deprecated
    public float getMaxScale() {
        return this.getMaximumScale();
    }

    public float getMaximumScale() {
        return this.mAttacher.getMaximumScale();
    }

    public float getScale() {
        return this.mAttacher.getScale();
    }

    public ScaleType getScaleType() {
        return this.mAttacher.getScaleType();
    }

    public void setAllowParentInterceptOnEdge(boolean allow) {
        this.mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    public void setMinimumScale(float minimumScale) {
        this.mAttacher.setMinimumScale(minimumScale);
    }

    public void setMediumScale(float mediumScale) {
        this.mAttacher.setMediumScale(mediumScale);
    }

    public void setMaximumScale(float maximumScale) {
        this.mAttacher.setMaximumScale(maximumScale);
    }

    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        this.mAttacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if(null != this.mAttacher) {
            this.mAttacher.update();
        }
    }

    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if(null != this.mAttacher) {
            this.mAttacher.update();
        }

    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if(null != this.mAttacher) {
            this.mAttacher.update();
        }

    }

    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener) {
        this.mAttacher.setOnMatrixChangeListener(listener);
    }

    public void setOnLongClickListener(OnLongClickListener l) {
        this.mAttacher.setOnLongClickListener(l);
    }

    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener) {
        this.mAttacher.setOnPhotoTapListener(listener);
    }

    /** @deprecated */
    @Deprecated
    public PhotoViewAttacher.OnPhotoTapListener getOnPhotoTapListener() {
        return this.mAttacher.getOnPhotoTapListener();
    }

    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener) {
        this.mAttacher.setOnViewTapListener(listener);
    }

    /** @deprecated */
    @Deprecated
    public PhotoViewAttacher.OnViewTapListener getOnViewTapListener() {
        return this.mAttacher.getOnViewTapListener();
    }

    public void setScale(float scale) {
        this.mAttacher.setScale(scale);
    }

    public void setScale(float scale, boolean animate) {
        this.mAttacher.setScale(scale, animate);
    }

    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        this.mAttacher.setScale(scale, focalX, focalY, animate);
    }

    public void setScaleType(ScaleType scaleType) {
        if(null != this.mAttacher) {
            this.mAttacher.setScaleType(scaleType);
        } else {
            this.mPendingScaleType = scaleType;
        }

    }

    public void setZoomable(boolean zoomable) {
        this.mAttacher.setZoomable(zoomable);
    }

    public Bitmap getVisibleRectangleBitmap() {
        return this.mAttacher.getVisibleRectangleBitmap();
    }

    public void setZoomTransitionDuration(int milliseconds) {
        this.mAttacher.setZoomTransitionDuration(milliseconds);
    }

    public IPhotoView getIPhotoViewImplementation() {
        return this.mAttacher;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        this.mAttacher.setOnDoubleTapListener(newOnDoubleTapListener);
    }

    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        this.mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    public void setOnSingleFlingListener(PhotoViewAttacher.OnSingleFlingListener onSingleFlingListener) {
        this.mAttacher.setOnSingleFlingListener(onSingleFlingListener);
    }

    @Override
    public void setIDisplayModeProxy(IDisplayModeProxy displayModeProxy) {
        mAttacher.setIDisplayModeProxy(displayModeProxy);
    }

    protected void onDetachedFromWindow() {
        this.mAttacher.cleanup();
        super.onDetachedFromWindow();
    }

    protected void onAttachedToWindow() {
        this.init();
        super.onAttachedToWindow();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if(null != this.mAttacher) {
            this.mAttacher.update();
        }
        notifyProxyPhotoSet(bm);
    }



    public void notifyProxyPhotoSet(Bitmap bitmap) {
        if (null != mAttacher) {
            RectF rectF = new RectF(0,0,bitmap.getWidth(),bitmap.getHeight());
            mAttacher.notifyProxyPhotoSet(rectF);
        }
    }
}

