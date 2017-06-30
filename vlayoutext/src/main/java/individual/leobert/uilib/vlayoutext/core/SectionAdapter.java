package individual.leobert.uilib.vlayoutext.core;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> SectionAdapter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/29.
 */

public class SectionAdapter<VH extends EventViewHolder, ID,IEL>
        extends DelegateAdapter.Adapter<VH> {

    private ISectionAdapterComponent<ID, VH,IEL> adapterComponent;

    public SectionAdapter(@NonNull ISectionAdapterComponent<ID, VH,IEL> adapterComponent) {
        this.adapterComponent = adapterComponent;
    }

    public ID getSectionItemData(int position) {
        return adapterComponent.getItemDataByPosition(position);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return adapterComponent.createLayoutHelper();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View originView = onCreateItemView(parent, viewType);
        View decorView = onCreateDecorItemView(parent,originView, viewType);

        return onCompatCreateViewHolder(decorView, originView, viewType);
    }


    protected View onCreateDecorItemView(ViewGroup viewGroup,
                                         View originView, int viewType) {
        return originView;
    }

    private VH onCompatCreateViewHolder(View contentView, View originView, int viewType) {
        return adapterComponent.onCompatCreateViewHolder(contentView, originView, viewType);
    }

    private View onCreateItemView(ViewGroup parent, int viewType) {
        return adapterComponent.onCreateItemView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setPositionInSection(position);
        adapterComponent.onBindViewHolder(holder,position,
                getSectionItemData(position));
        holder.bindEventListener(adapterComponent.getItemEventListener());
    }

    @Override
    public int getItemCount() {
        return adapterComponent.getItemDataCount();
    }
}