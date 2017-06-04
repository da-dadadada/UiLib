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

public abstract class EventViewHolder<I extends EventViewHolder.IEventListener>
        extends RecyclerView.ViewHolder {
    public EventViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bindEventListener(I listener);

    public interface IEventListener {

    }
}
