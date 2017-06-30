package individual.leobert.uilib.vlayoutext.single;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import individual.leobert.uilib.autolooperbanner.AutoLooperBanner;
import individual.leobert.uilib.autolooperbanner.IBannerUpdate;
import individual.leobert.uilib.vlayoutext.EventViewHolder;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> BannerSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class BannerSection extends
        SingleSection<BannerSection.BannerSectionViewHolder, List<String>> {

    private IBannerUpdate iBannerUpdate;


    public BannerSection(List<String> sectionData, IBannerUpdate iBannerUpdate) {
        super(sectionData);
        this.iBannerUpdate = iBannerUpdate;
    }

    @Override
    public BannerSection.BannerSectionViewHolder onCompatCreateViewHolder(View contentView, View originView) {

        return new BannerSectionViewHolder(contentView);
    }


    protected AutoLooperBanner.OnBannerItemClickListener
    getBannerItemClickListener(List<String> datas) {
        return null;
    }


    @Override
    public void onBindViewHolder(BannerSectionViewHolder holder,
                                 int position, List<String> itemData) {
        holder.setPositionInSection(position);
        holder.setup(iBannerUpdate, itemData);

        AutoLooperBanner.OnBannerItemClickListener listener =
                getBannerItemClickListener(getSectionData());
        if (listener != null)
            holder.getBannerLayout().setOnBannerItemClickListener(listener);
    }


    public static class BannerSectionViewHolder
            extends EventViewHolder {
        private AutoLooperBanner bannerLayout;

        public AutoLooperBanner getBannerLayout() {
            return bannerLayout;
        }

        public BannerSectionViewHolder(View itemView) {
            super(itemView);
            this.bannerLayout = getAutoLooperBanner(itemView);
            if (bannerLayout == null)
                throw new IllegalArgumentException("itemView must be neither" +
                        "instance of AutoLooperBanner or contains ALB");
        }

        private AutoLooperBanner getAutoLooperBanner(View itemView) {
            if (itemView instanceof AutoLooperBanner)
                return (AutoLooperBanner) itemView;
            List<View> unvisited = new ArrayList<>();
            unvisited.add(itemView);
            while (!unvisited.isEmpty()) {
                View child = unvisited.remove(0);
                if (!(child instanceof ViewGroup)) { // view
                    continue;
                }
                if (child instanceof AutoLooperBanner)
                    return (AutoLooperBanner) child;
                ViewGroup group = (ViewGroup) child;
                final int childCount = group.getChildCount();
                for (int i = 0; i < childCount; i++) unvisited.add(group.getChildAt(i));
            }
            return null;
        }

        @Override
        protected <I> void onEventListenerSet(I listener) {

        }


        public void setup(IBannerUpdate iBannerUpdate, List<String> datas) {
            bannerLayout.setIBannerUpdate(iBannerUpdate);
            bannerLayout.setViewUrls(datas);
        }
    }
}
