package individual.leobert.uilib.vlayoutext;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> EventViewHolder </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/2.
 */

public abstract class EventViewHolder
        extends RecyclerView.ViewHolder {
    public EventViewHolder(View itemView) {
        super(itemView);
    }

    public final <I> void bindEventListener(I listener) {
        if (listener != null ) {
            onEventListenerSet(listener);
        }
    }

    protected abstract <I> void onEventListenerSet(I listener);

}
