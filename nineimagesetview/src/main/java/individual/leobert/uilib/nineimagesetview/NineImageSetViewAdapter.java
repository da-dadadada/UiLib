package individual.leobert.uilib.nineimagesetview;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;
import java.util.Observable;

/**
 * <p><b>Package:</b> individual.leobert.uilib.nineimagesetview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> NineImageSetViewAdapter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/26.
 */

public abstract class NineImageSetViewAdapter<T> extends Observable {

    private List<T> datas;

    public NineImageSetViewAdapter(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public final int getItemCount() {
        if (datas == null)
            return 0;
        return datas.size();
    }

    public final T getItem(int index) {
        if (datas == null)
            return null;
        return datas.get(index);
    }

    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    protected ImageView generateImageView(Context context) {
        ItemImageView imageView = new ItemImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    public void notifyDataSetChanged() {
        setChanged();
        notifyObservers();
    }

    public void setDatas(List<T> newDatas) {
        datas = newDatas;
        notifyDataSetChanged();
    }


}
