package individual.leobert.uilib.vlayout;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import individual.leobert.uilib.R;
import individual.leobert.uilib.autolooperbanner.AutoLooperBanner;
import individual.leobert.uilib.vlayoutext.VLayoutSection;
import individual.leobert.uilib.vlayoutext.group.ListSection;
import individual.leobert.uilib.vlayoutext.single.BannerSection;

public class TestVLayoutComplex extends AppCompatActivity {
    final List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vlayout_complex);
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vlayout_complex);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });

        final List<LayoutHelper> helpers = new LinkedList<>();

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper(3);//3px divider

        helpers.add(linearLayoutHelper);

        layoutManager.setLayoutHelpers(helpers);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);

        //..................................

//        delegateAdapter.addAdapter(new SubBannerAdapter(this, urls));
//
        BannerSection bannerSection = newBannerSection();
        delegateAdapter.addAdapter(bannerSection.getAdapter());

        final List<String> urls2 = new ArrayList<>();
        urls2.addAll(urls);
        urls2.addAll(urls);
        urls2.addAll(urls);

        delegateAdapter.addAdapter(newHSRVAdapter(urls2));
        delegateAdapter.addAdapter(new SubLinearAdapter(this, Data1.genTest(10)));


        recyclerView.setAdapter(delegateAdapter);

        delegateAdapter.notifyDataSetChanged();
    }


    private SubSingleSectionAdapter<HorizontalRVViewHolderSample, List<String>>
    newHSRVAdapter(List<String> sds) {
        return new SubSingleSectionAdapter<HorizontalRVViewHolderSample, List<String>>(sds) {
            @Override
            public HorizontalRVViewHolderSample onCreateViewHolder(ViewGroup parent, int viewType) {
                HorizontalRVViewHolderSample viewHolder =
                        new HorizontalRVViewHolderSample(useInflate(R.layout.vl_section_h_scroll_rv, parent));
                viewHolder.setRVAdapter(new ASDF(getSectionDataSet()));

                return viewHolder;
            }

            @Override
            public void onBindViewHolder(HorizontalRVViewHolderSample holder, int position) {
                //ignore
            }
        };
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }

        public void update(String data) {
            Glide.with(itemView.getContext())
                    .load(data)
                    .override(200, 200)
                    .into(imageView);
        }
    }

    private class ASDF extends RecyclerView.Adapter<ImageViewHolder> {

        private List<String> data;

        public ASDF(List<String> data) {
            this.data = data;
        }

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            holder.update(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    private BannerSection newBannerSection() {

        BannerSection section = new BannerSection(this, urls, new VLayoutSection.
                ViewHolderEventDecor<BannerSection.BannerSectionViewHolder, List<String>>() {
            @Override
            public void decor(BannerSection.BannerSectionViewHolder holder, final List<String> itemData, int position) {
                holder.getBannerLayout().setOnBannerItemClickListener(new AutoLooperBanner.OnBannerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.d("lmsg", "click:" + position + "\r\nurl:" + itemData.get(position));
                    }
                });
            }

        });
        return section;
    }


    ListSection<LinearViewHolderSample,String> listSection =
            new ListSection<LinearViewHolderSample,String>(null) {


                @Override
                protected LinearViewHolderSample onCreateViewHolder(ViewGroup parent, int viewType) {
                    return null;
                }

                @Override
                protected void onBindViewHolder(LinearViewHolderSample holder, int position, String itemData) {

                }
            };
}
