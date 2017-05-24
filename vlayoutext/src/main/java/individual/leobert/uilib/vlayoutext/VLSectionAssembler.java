package individual.leobert.uilib.vlayoutext;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> VLSectionAssembler </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/24.
 */

public class VLSectionAssembler {
    private final RecyclerView recyclerView;

    private final DelegateAdapter delegateAdapter;

    private VLSectionAssembler(RecyclerView recyclerView, DelegateAdapter delegateAdapter) {
        this.recyclerView = recyclerView;
        this.delegateAdapter = delegateAdapter;
    }

    public static VLSectionAssembler getAssembler(RecyclerView recyclerView, DelegateAdapter delegateAdapter) {
        return new VLSectionAssembler(recyclerView, delegateAdapter);
    }

    public VLSectionAssembler add(VLayoutSection section) {
        delegateAdapter.addAdapter(section.getAdapter());
        return this;
    }

    public void assembler() {
        recyclerView.setAdapter(delegateAdapter);
    }
}
