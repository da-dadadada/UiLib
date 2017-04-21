package individual.leobert.uilib.actionsheet;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * <p><b>Package</b> individual.leobert.uilib.actionsheet
 * <p><b>Project</b> actionsheet
 * <p><b>Classname</b> AbsActionSheetAdapter
 * <p><b>Description</b>: 抽象listadapter
 * <p>
 * Created by leobert on 2017/4/21.
 */
public abstract class AbsActionSheetAdapter extends BaseAdapter {

    private final IActionSheetItemViewProvider itemViewProvider;

    public AbsActionSheetAdapter(IActionSheetItemViewProvider itemViewProvider) {
        this.itemViewProvider = itemViewProvider;
    }

    @Override
    public abstract int getCount();

    @Override
    public abstract Object getItem(int position);

    @Override
    public abstract long getItemId(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return itemViewProvider.getView(position, getItem(position), convertView, parent);
    }
}
