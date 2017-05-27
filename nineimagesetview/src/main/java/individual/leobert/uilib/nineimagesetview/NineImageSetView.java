package individual.leobert.uilib.nineimagesetview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * <p><b>Package:</b> individual.leobert.uilib.nineimagesetview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> NineImageSetView </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/26.
 */

public class NineImageSetView<T> extends ViewGroup implements Observer {

    public final static int STYLE_GRID = 0;     // 宫格布局
    public final static int STYLE_FILL = 1;     // 全填充布局

    private final float ITEM_ASPECT_SINGLE = (10f) / (23f);
    private final float ITEM_ASPECT_GRID = 1f;

    private int mRowCount;       // 行数
    private int mColumnCount;    // 列数

    private int mShowStyle;     // 显示风格
    private int mGap;           // 宫格间距
    //    private int mSingleImgSize; // 单张图片时的尺寸
    private GridSize mGridSize;   // 宫格大小,即图片大小

    private List<ImageView> mImageViewList = new ArrayList<>();
    private boolean isFirstLayout = true;
    private int oldShowCount = 0;

    private NineImageSetViewAdapter<T> mAdapter;
    private OnItemImageClickListener<T> mItemImageClickListener;

    private final int mMaxSize = 9;

    static class GridSize {
        int w;
        int h;
    }


    public NineImageSetView(Context context) {
        this(context, null);
    }

    public NineImageSetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NineImageSetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mGridSize = new GridSize();
        if (attrs == null) {
            this.mGap = 0;
            this.mShowStyle = STYLE_FILL;
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineImageSetView);
        this.mGap = (int) typedArray.getDimension(R.styleable.NineImageSetView_itemGap, 0);
        this.mShowStyle = typedArray.getInt(R.styleable.NineImageSetView_showStyle, STYLE_FILL);
        typedArray.recycle();
    }

    @Override
    public void update(Observable o, Object arg) {
        onDataChanged();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height;
        int totalWidth = width - getPaddingLeft() - getPaddingRight();
        if (mAdapter != null && mAdapter.getItemCount() > 0) {
//            if (mImgDataList.size() == 1 && mSingleImgSize != -1) {
//                mGridSize = mSingleImgSize > totalWidth ? totalWidth : mSingleImgSize;
//            } else {
            if (mAdapter.getItemCount() == 1) {
                mGridSize.w = totalWidth;
                mGridSize.h = (int) (mGridSize.w * ITEM_ASPECT_SINGLE);
                height = mGridSize.h + getPaddingTop() + getPaddingBottom();
            } else {
                mImageViewList.get(0).setScaleType(ImageView.ScaleType.CENTER_CROP);
                mGridSize.w = (totalWidth - mGap * (mColumnCount - 1)) / mColumnCount;
                mGridSize.h = mGridSize.w;
                height = mGridSize.h * mRowCount + mGap * (mRowCount - 1) + getPaddingTop() + getPaddingBottom();
            }
            setMeasuredDimension(width, height);
        } else {
            height = width;
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChildrenView();
    }

    /**
     * 布局 ImageView
     */
    private void layoutChildrenView() {
        if (mAdapter == null) {
            return;
        }
        int showCount = getNeedShowCount(mAdapter.getItemCount());
        for (int i = 0; i < showCount; i++) {
            ImageView childrenView = (ImageView) getChildAt(i);
            if (mAdapter != null) {
                mAdapter.onDisplayImage(getContext(), childrenView, mAdapter.getItem(i));
            }
            int rowNum = i / mColumnCount;
            int columnNum = i % mColumnCount;
            int left = (mGridSize.w + mGap) * columnNum + getPaddingLeft();
            int top = (mGridSize.h + mGap) * rowNum + getPaddingTop();
            int right = left + mGridSize.w;
            int bottom = top + mGridSize.h;

            childrenView.layout(left, top, right, bottom);
        }
    }

    private void onDataChanged() {
        if (mAdapter == null)
            addViewByData(null);
        else
            addViewByData(mAdapter.getDatas());
        super.requestLayout();
    }

    /**
     * 设置图片数据
     *
     * @param lists 图片数据集合
     */
    private void addViewByData(List lists) {
        if (lists == null || lists.isEmpty()) {
            this.setVisibility(GONE);
            return;
        } else {
            this.setVisibility(VISIBLE);
        }

        int newShowCount = getNeedShowCount(lists.size());

        int[] gridParam = calculateGridParam(newShowCount, mShowStyle);
        mRowCount = gridParam[0];
        mColumnCount = gridParam[1];
        if (isFirstLayout) {
            int i = 0;
            while (i < newShowCount) {
                ImageView iv = getImageView(i);
                if (iv == null) {
                    return;
                }
                addView(iv, generateDefaultLayoutParams());
                i++;
            }
        } else {
            if (oldShowCount > newShowCount) {
                removeViews(newShowCount, oldShowCount - newShowCount);
            } else if (oldShowCount < newShowCount) {
                for (int i = oldShowCount; i < newShowCount; i++) {
                    ImageView iv = getImageView(i);
                    if (iv == null) {
                        return;
                    }
                    addView(iv, generateDefaultLayoutParams());
                }
            }
        }
        isFirstLayout = false;
        oldShowCount = getNeedShowCount(mAdapter.getItemCount());
    }

    private int getNeedShowCount(int size) {
        if (//mMaxSize > 0 &&
                size > mMaxSize) {
            return mMaxSize;
        } else {
            return size;
        }
    }

    /**
     * 获得 ImageView
     * 保证了 ImageView 的重用
     *
     * @param position 位置
     */
    private ImageView getImageView(final int position) {
        if (position < mImageViewList.size()) {
            return mImageViewList.get(position);
        } else {
            if (mAdapter != null) {
                ImageView imageView = mAdapter.generateImageView(getContext());
                mImageViewList.add(imageView);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        mAdapter.onItemImageClick(getContext(), (ImageView) v, position, mAdapter.getDatas());
                        if (mItemImageClickListener != null) {
                            mItemImageClickListener.onItemImageClick(getContext(),
                                    (ImageView) v, position, mAdapter.getDatas());
                        }
                    }
                });
                return imageView;
            } else {
                Log.e("NineGirdImageView", "Your must set a NineGridImageViewAdapter for NineGirdImageView");
                return null;
            }
        }
    }

    /**
     * 设置 宫格参数
     *
     * @param imagesSize 图片数量
     * @param showStyle  显示风格
     * @return 宫格参数 gridParam[0] 宫格行数 gridParam[1] 宫格列数
     */
    protected static int[] calculateGridParam(int imagesSize, int showStyle) {
        int[] gridParam = new int[2];
        switch (showStyle) {
            case STYLE_FILL:
                if (imagesSize < 4) {
                    gridParam[0] = 1;
                    gridParam[1] = imagesSize;
                } else if (imagesSize <= 4) {
                    gridParam[0] = 2;
                    gridParam[1] = 2;
                } else {
                    gridParam[0] = imagesSize / 3 + (imagesSize % 3 == 0 ? 0 : 1);
                    gridParam[1] = 3;
                }
                break;
            default:
            case STYLE_GRID:
                gridParam[0] = imagesSize / 3 + (imagesSize % 3 == 0 ? 0 : 1);
                gridParam[1] = 3;
        }
        return gridParam;
    }

    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    public void setAdapter(@NonNull NineImageSetViewAdapter<T> adapter) {
//        if (adapter == null)
//            throw new IllegalArgumentException("adapter cannot be null");
        if (mAdapter != null)
            mAdapter.deleteObserver(this);
        mAdapter = adapter;
        mAdapter.addObserver(this);
        onDataChanged();
    }

    /**
     * 设置宫格间距
     *
     * @param gap 宫格间距 px
     */
    public void setGap(int gap) {
        mGap = gap;
    }

    /**
     * 设置显示风格
     *
     * @param showStyle 显示风格
     */
    public void setShowStyle(int showStyle) {
        mShowStyle = showStyle;
    }

//    /**
//     * 设置只有一张图片时的尺寸大小
//     *
//     * @param singleImgSize 单张图片的尺寸大小
//     */
//    public void setSingleImgSize(int singleImgSize) {
//        mSingleImgSize = singleImgSize;
//    }

//    /**
//     * 设置最大图片数
//     *
//     * @param maxSize 最大图片数
//     */
//    public void setMaxSize(int maxSize) {
//        mMaxSize = maxSize;
//    }

    public void setItemImageClickListener(OnItemImageClickListener<T> itemImageViewClickListener) {
        mItemImageClickListener = itemImageViewClickListener;
    }
}
