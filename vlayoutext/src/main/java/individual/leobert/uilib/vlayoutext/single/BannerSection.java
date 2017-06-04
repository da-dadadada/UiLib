package individual.leobert.uilib.vlayoutext.single;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import individual.leobert.uilib.autolooperbanner.AutoLooperBanner;
import individual.leobert.uilib.autolooperbanner.IBannerUpdate;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> BannerSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class BannerSection<VH extends BannerSection.BannerSectionViewHolder> extends
        SingleSection<VH, List<String>> {

    public BannerSection(List<String> datas) {
        super(datas);
    }

    public BannerSection(List<String> sectionData,
                         ViewHolderDecor decor) {
        super(sectionData, decor);
    }

    @Override
    protected abstract VH onCreateViewHolder(ViewGroup parent);
//    {
//        return new VH(LayoutInflater.from(context)
//                .inflate(R.layout.vlext_section_banner, parent, false),
//                getSectionData());
//    }


    @Override
    protected void onBindViewHolder(BannerSectionViewHolder holder) {

    }


    public abstract static class BannerSectionViewHolder
            extends RecyclerView.ViewHolder {
        protected AutoLooperBanner bannerLayout;

        public AutoLooperBanner getBannerLayout() {
            return bannerLayout;
        }

        public BannerSectionViewHolder(View itemView) {
            super(itemView);
            bannerLayout = init(itemView);
        }

        protected abstract
        @NonNull
        AutoLooperBanner init(View itemView);

        public void update(IBannerUpdate iBannerUpdate, List<String> datas) {
            bannerLayout.setIBannerUpdate(iBannerUpdate);
            bannerLayout.setViewUrls(datas);
        }
    }
}
