package individual.leobert.uilib.vlayoutext.core;

import android.support.v7.widget.RecyclerView;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> ISectionAdapterComponent </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/29.
 */

public interface ISectionAdapterComponent<ID, VH extends RecyclerView.ViewHolder,IEL>
        extends IDataProvider<ID>,
        ILayoutHelperProvider,
        IViewHolderCreator<VH> ,
        IItemEventListenerProvider<IEL>{

    void onBindViewHolder(VH vh, int position, ID itemData);
}
