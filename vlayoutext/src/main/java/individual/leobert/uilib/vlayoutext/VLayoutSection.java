package individual.leobert.uilib.vlayoutext;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> VLayoutSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class VLayoutSection {


    public abstract DelegateAdapter.Adapter getAdapter();

    public static abstract class SectionAdapter<VH extends RecyclerView.ViewHolder>
            extends DelegateAdapter.Adapter<VH> {

        protected View useInflate(@LayoutRes int layout, @NonNull ViewGroup parent) {
            return LayoutInflater.from(parent.getContext())
                    .inflate(layout, parent, false);
        }

    }
}
