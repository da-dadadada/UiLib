package individual.leobert.uilib.vlayoutext.single;

import android.view.View;

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
        if (originView instanceof AutoLooperBanner)
            return new BannerSectionViewHolder((AutoLooperBanner) originView);
        return null;
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

        public BannerSectionViewHolder(AutoLooperBanner bannerLayout) {
            super(bannerLayout);
            this.bannerLayout = bannerLayout;
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
