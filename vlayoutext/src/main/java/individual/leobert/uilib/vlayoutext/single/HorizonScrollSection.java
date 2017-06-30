package individual.leobert.uilib.vlayoutext.single;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import individual.leobert.uilib.vlayoutext.EventViewHolder;
import individual.leobert.uilib.vlayoutext.R;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> HorizonScrollSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/23.
 */

public abstract class HorizonScrollSection<ID> extends
        SingleSection<HorizonScrollSection.HorizonScrollSectionViewHolder, ID> {

    private RecyclerView.Adapter sectionRvAdapter;

    private List<RecyclerView.ItemDecoration> itemDecorations;

    public HorizonScrollSection(ID sectionData,
                                List<RecyclerView.ItemDecoration> itemDecorations) {
        super(sectionData);
        this.sectionRvAdapter = newSectionRvAdapter(getSectionData());
        this.itemDecorations = itemDecorations;
    }

    public abstract RecyclerView.Adapter newSectionRvAdapter(ID itemData);


    @Override
    public void onBindViewHolder(HorizonScrollSectionViewHolder holder,
                                 int position, ID itemData) {
        holder.setRVAdapter(sectionRvAdapter);
    }

    @Override
    public HorizonScrollSectionViewHolder onCompatCreateViewHolder(View contentView, View originView) {
        return new HorizonScrollSectionViewHolder(contentView, itemDecorations);
    }

    @Override
    public View onCreateItemView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vlext_section_horizon_scroll_rv, parent, false);
    }

    public static class HorizonScrollSectionViewHolder extends EventViewHolder {

        RecyclerView recyclerView;

        public HorizonScrollSectionViewHolder(View itemView, List<RecyclerView.ItemDecoration> itemDecorations) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.vlext_h_scroll_rv);
            init(itemDecorations);
        }

        private void init(List<RecyclerView.ItemDecoration> itemDecorations) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            for (RecyclerView.ItemDecoration itemDecoration : itemDecorations) {
                recyclerView.addItemDecoration(itemDecoration);
            }
        }

        public void setRVAdapter(RecyclerView.Adapter adapter) {
            recyclerView.setAdapter(adapter);
        }

        @Deprecated
        public void notifyDataSetChanged() {
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        @Override
        protected <I> void onEventListenerSet(I listener) {

        }
    }
}
