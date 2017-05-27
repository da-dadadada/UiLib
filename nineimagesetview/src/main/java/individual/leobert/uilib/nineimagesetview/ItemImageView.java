package individual.leobert.uilib.nineimagesetview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p><b>Package:</b> individual.leobert.uilib.nineimagesetview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> ItemImageView </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/26.
 */

public class ItemImageView extends android.support.v7.widget.AppCompatImageView {

    public ItemImageView(Context context) {
        super(context);
    }

    public ItemImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.mutate().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Drawable drawableUp = getDrawable();
                if (drawableUp != null) {
                    drawableUp.mutate().clearColorFilter();
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}
