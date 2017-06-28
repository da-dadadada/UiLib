//package individual.leobert.uilib.fabmenu;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.ShapeDrawable;
//import android.graphics.drawable.shapes.Shape;
//import android.support.annotation.ColorRes;
//import android.support.annotation.DimenRes;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.content.ContextCompat;
//import android.util.AttributeSet;
//
///**
// * <p><b>Package:</b> individual.leobert.uilib.fabmenu </p>
// * <p><b>Project:</b> UiLib </p>
// * <p><b>Classname:</b> AddFloatingActionButton </p>
// * <p><b>Description:</b> TODO </p>
// * Created by leobert on 2017/6/15.
// */
//
//public class AddFloatingActionButton extends FloatingActionButton {
//    int mPlusColor;
//
//    public AddFloatingActionButton(Context context) {
//        this(context, null);
//    }
//
//    public AddFloatingActionButton(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context, attrs);
//    }
//
//    public AddFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        init(context,attrs);
//    }
//
//    void init(Context context, AttributeSet attributeSet) {
//        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.AddFloatingActionButton, 0, 0);
//        mPlusColor = attr.getColor(R.styleable.AddFloatingActionButton_fab_plusIconColor, getColor(android.R.color.white));
//        attr.recycle();
//
//        super.init(context, attributeSet);
//    }
//
//    /**
//     * @return the current Color of plus icon.
//     */
//    public int getPlusColor() {
//        return mPlusColor;
//    }
//
//    public void setPlusColorResId(@ColorRes int plusColor) {
//        setPlusColor(getColor(plusColor));
//    }
//
//    private int getColor(@ColorRes int colorRes) {
//        return ContextCompat.getColor(getContext(),colorRes);
//    }
//
//    public void setPlusColor(int color) {
//        if (mPlusColor != color) {
//            mPlusColor = color;
//            updateBackground();
//        }
//    }
//
//    @Override
//    public void setIcon(int icon) {
//        throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
//    }
//
//    private float getDimension(@DimenRes int dimenRes) {
//        return getResources().getDimension(dimenRes);
//    }
//
//    @Override
//    Drawable getIconDrawable() {
//        final float iconSize = getDimension(R.dimen.fab_icon_size);
//        final float iconHalfSize = iconSize / 2f;
//
//        final float plusSize = getDimension(R.dimen.fab_plus_icon_size);
//        final float plusHalfStroke = getDimension(R.dimen.fab_plus_icon_stroke) / 2f;
//        final float plusOffset = (iconSize - plusSize) / 2f;
//
//        final Shape shape = new Shape() {
//            @Override
//            public void draw(Canvas canvas, Paint paint) {
//                canvas.drawRect(plusOffset, iconHalfSize - plusHalfStroke, iconSize - plusOffset, iconHalfSize + plusHalfStroke, paint);
//                canvas.drawRect(iconHalfSize - plusHalfStroke, plusOffset, iconHalfSize + plusHalfStroke, iconSize - plusOffset, paint);
//            }
//        };
//
//        ShapeDrawable drawable = new ShapeDrawable(shape);
//
//        final Paint paint = drawable.getPaint();
//        paint.setColor(mPlusColor);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setAntiAlias(true);
//
//        return drawable;
//    }
//}