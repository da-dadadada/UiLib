package individual.leobert.uilib.nineimagesetview;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.nineimagesetview </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> OnItemImageClickListener </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/26.
 */

public interface OnItemImageClickListener<T> {
    void onItemImageClick(Context context,
                          /*current click image*/
                          ImageView imageView,
                          /*current click position,start from 0*/
                          int index,
                          /*duplicated，use list.size()*/
                          //int count,
                          /*all data*/
                          List<T> list);
}
