package individual.leobert.uilib.vlayoutext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import individual.leobert.uilib.autolooperbanner.AutoLooperBanner;
import individual.leobert.uilib.autolooperbanner.IBannerUpdate;
import individual.leobert.uilib.autolooperbanner.ImgRes;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> BannerSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public class BannerSection extends
        SingleSection<BannerSection.BannerSectionViewHolder, List<String>> {

    private Context context;


    public BannerSection(Context context, List<String> datas) {
        super(datas);
        this.context = context;
    }

    public BannerSection(Context context, List<String> sectionData,
                         SectionAdapter.ViewHolderEventDecor decor) {
        super(sectionData, decor);
        this.context = context;
    }

    @Override
    BannerSectionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BannerSectionViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.vlext_section_banner, parent, false),
                getSectionData());
    }

    @Override
    void onBindViewHolder(BannerSectionViewHolder holder) {

    }


    public static class BannerSectionViewHolder extends RecyclerView.ViewHolder {
        AutoLooperBanner bannerLayout;

        //default
        private IBannerUpdate iBannerUpdate = new IBannerUpdate() {
            @Override
            public void UpdateImage(ImgRes<?> res, ImageView imageView) {
                Glide.with(imageView.getContext())
                        .load(res.getRes())
                        .asBitmap()
                        .into(imageView);
            }
        };

        public AutoLooperBanner getBannerLayout() {
            return bannerLayout;
        }

        public BannerSectionViewHolder(View itemView, List<String> datas) {
            super(itemView);
            bannerLayout = (AutoLooperBanner) itemView.findViewById(R.id.vlext_banner);

            bannerLayout.setIBannerUpdate(iBannerUpdate);
            bannerLayout.setViewUrls(datas);
        }

        public void update(List<String> datas) {
            bannerLayout.setIBannerUpdate(iBannerUpdate);
            bannerLayout.setViewUrls(datas);
        }

        public void update(IBannerUpdate iBannerUpdate, List<String> datas) {
            this.iBannerUpdate = iBannerUpdate;
            update(datas);
        }
    }
}
