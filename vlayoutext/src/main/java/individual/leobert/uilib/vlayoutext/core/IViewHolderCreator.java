package individual.leobert.uilib.vlayoutext.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IViewHolderCreator </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/29.
 */

public interface IViewHolderCreator<VH extends RecyclerView.ViewHolder> {
    VH onCompatCreateViewHolder(View contentView, View originView, int viewType);

    View onCreateItemView(ViewGroup parent, int viewType);
}
