package individual.leobert.uilib.temp;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import individual.leobert.uilib.lrecyclerview.swipe.ISwipeMenuAdapter;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuAdapterHelper;
import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.core.ISectionAdapterComponent;
import individual.leobert.uilib.vlayoutext.core.SectionAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.temp </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> MAdapter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/30.
 */

public class MAdapter<VH extends EventViewHolder, ID, IEL>
        extends SectionAdapter<VH, ID, IEL>
        implements ISwipeMenuAdapter {

    private SwipeMenuAdapterHelper swipeMenuAdapterHelper;

    public MAdapter(@NonNull ISectionAdapterComponent<ID, VH, IEL> adapterComponent) {
        super(adapterComponent);
    }

    @Override
    protected View onCreateDecorItemView(ViewGroup viewGroup, View originView, int viewType) {
        if (swipeMenuAdapterHelper != null) {
            Log.d("lmsg","create swipe menu");
            return swipeMenuAdapterHelper.helpCreateSwipeView(viewGroup, originView, viewType);

        } else {
            Log.d("lmsg","swipehelper is null");
        }
        return originView;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (swipeMenuAdapterHelper != null)
            swipeMenuAdapterHelper.heloOnBindViewHolder(holder, position, null);
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void setSwipeMenuAdapterHelper(SwipeMenuAdapterHelper helper) {
        this.swipeMenuAdapterHelper = helper;
    }

    @Override
    public SwipeMenuAdapterHelper getSwipeMenuAdapterHelper() {
        return swipeMenuAdapterHelper;
    }
}
