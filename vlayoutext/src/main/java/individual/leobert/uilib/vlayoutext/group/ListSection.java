package individual.leobert.uilib.vlayoutext.group;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext.group </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> ListSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class ListSection<VH extends EventViewHolder, ID,IEL>
        extends GroupSection<VH, ID,IEL> {


    public ListSection(List<ID> sectionData) {
        super(sectionData);
    }

//    public ListSection(List<ID> sectionData,
//                       ViewHolderDecor decor) {
//        super(sectionData, decor);
//    }

    @Override
    LayoutHelper onCreateLayoutHelper() {
        final LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        decorLayoutHelper(linearLayoutHelper);
        return linearLayoutHelper;
    }

    protected abstract void decorLayoutHelper(final LinearLayoutHelper layoutHelper);
}
