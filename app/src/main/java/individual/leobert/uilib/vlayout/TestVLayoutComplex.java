package individual.leobert.uilib.vlayout;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import individual.leobert.uilib.R;
import individual.leobert.uilib.TestVideoActivity1;
import individual.leobert.uilib.autolooperbanner.AutoLooperBanner;
import individual.leobert.uilib.vlayoutext.VLayoutSection;
import individual.leobert.uilib.vlayoutext.group.GridSection;
import individual.leobert.uilib.vlayoutext.group.ListSection;
import individual.leobert.uilib.vlayoutext.group.RangeGridSection;
import individual.leobert.uilib.vlayoutext.group.StaggerSection;
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
        delegateAdapter.addAdapter(newListSection(Data1.genTest(10)).getAdapter());
        delegateAdapter.addAdapter(newGrideSection().getAdapter());
        delegateAdapter.addAdapter(newRangeGridSection().getAdapter());


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

    private ListSection<LinearViewHolderSample, Data1> newListSection(List<Data1> datas) {
        VLayoutSection.ViewHolderEventDecor<LinearViewHolderSample, Data1> eventDecor =
                new VLayoutSection.ViewHolderEventDecor<LinearViewHolderSample, Data1>() {
                    @Override
                    public void decor(LinearViewHolderSample holder, Data1 itemData, int position) {
                        holder.ivAvatar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //presenter.on...Called(data)
                                Toast.makeText(TestVLayoutComplex.this, "avatar", Toast.LENGTH_SHORT).show();
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //presenter.on...Called(data)
                                Toast.makeText(TestVLayoutComplex.this, "item", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };


        ListSection<LinearViewHolderSample, Data1> listSection =
                new ListSection<LinearViewHolderSample, Data1>(datas, eventDecor) {


                    @Override
                    protected void decorLayoutHelper(LinearLayoutHelper layoutHelper) {
                        layoutHelper.setDividerHeight(10);
                        layoutHelper.setBgColor(ContextCompat.getColor(TestVLayoutComplex.this,
                                R.color.colorAccent));
                        //test -> useless for linear
                        layoutHelper.setItemCount(5);
                    }

                    @Override
                    protected LinearViewHolderSample onCreateViewHolder(ViewGroup parent, int viewType) {
                        return new LinearViewHolderSample(LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.vlayout_item_linear, parent, false));
                    }

                    @Override
                    protected void onBindViewHolder(LinearViewHolderSample holder, int position, Data1 itemData) {
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        holder.itemView.setLayoutParams(layoutParams);
                        holder.update(itemData);
                    }
                };
        return listSection;
    }


    private GridSection<ImageViewHolder, Integer> newGrideSection() {
        VLayoutSection.ViewHolderEventDecor<ImageViewHolder, Integer> eventDecor =
                new VLayoutSection.ViewHolderEventDecor<ImageViewHolder, Integer>() {
                    @Override
                    public void decor(ImageViewHolder holder, Integer itemData, int position) {
                        holder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //presenter.on...Called(data)
                                Toast.makeText(TestVLayoutComplex.this, "img", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };

        ArrayList<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(R.drawable.v1000_drawable_avatar_default);
        }


        GridSection<ImageViewHolder, Integer> gridSection =
                new GridSection<ImageViewHolder, Integer>(datas, 2, eventDecor) {


                    @Override
                    protected void decorLayoutHelper(GridLayoutHelper layoutHelper) {
                        layoutHelper.setBgColor(ContextCompat.getColor(TestVLayoutComplex.this,
                                R.color.colorPrimary));
                        layoutHelper.setGap(10);
                        layoutHelper.setAutoExpand(true);
//                        layoutHelper.setWeights(new float[] {0.5f,0.5f});
                    }

                    @Override
                    protected ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
                    }

                    @Override
                    protected void onBindViewHolder(ImageViewHolder holder, int position, Integer itemData) {
                        holder.itemView.setLayoutParams(new VirtualLayoutManager
                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        holder.imageView.setImageResource(itemData);

                    }
                };
        return gridSection;
    }



    private RangeGridSection<ImageViewHolder, Integer> newRangeGridSection() {

        ArrayList<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add(R.drawable.v1000_drawable_avatar_default);
        }


        RangeGridSection<ImageViewHolder, Integer> gridSection =
                new RangeGridSection<ImageViewHolder, Integer>(datas, 4) {


                    @Override
                    protected void decorLayoutHelper(RangeGridLayoutHelper layoutHelper) {
                        layoutHelper.setBgColor(Color.GREEN);
                        layoutHelper.setWeights(new float[]{10f,Float.NaN, 20f});
                        layoutHelper.setPadding(15, 15, 15, 15);
                        layoutHelper.setMargin(15, 15, 15, 15);
                        layoutHelper.setHGap(10);
                        layoutHelper.setVGap(10);
                        RangeGridLayoutHelper.GridRangeStyle rangeStyle = new RangeGridLayoutHelper.GridRangeStyle();
                        rangeStyle.setBgColor(Color.RED);
                        rangeStyle.setSpanCount(2);
                        rangeStyle.setWeights(new float[]{46.665f});
                        rangeStyle.setPadding(15, 15, 15, 15);
                        rangeStyle.setMargin(15, 15, 15, 15);
                        rangeStyle.setHGap(5);
                        rangeStyle.setVGap(5);
                        layoutHelper.addRangeStyle(4, 7, rangeStyle);
                        RangeGridLayoutHelper.GridRangeStyle rangeStyle1 = new RangeGridLayoutHelper.GridRangeStyle();
                        rangeStyle1.setBgColor(Color.YELLOW);
                        rangeStyle1.setSpanCount(2);
                        rangeStyle1.setWeights(new float[]{46.665f});
                        rangeStyle1.setPadding(15, 15, 15, 15);
                        rangeStyle1.setMargin(15, 15, 15, 15);
                        rangeStyle1.setHGap(5);
                        rangeStyle1.setVGap(5);
                        layoutHelper.addRangeStyle(8, 11, rangeStyle1);
                    }

                    @Override
                    protected ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
                    }

                    @Override
                    protected void onBindViewHolder(ImageViewHolder holder, int position, Integer itemData) {
                        holder.itemView.setLayoutParams(new VirtualLayoutManager
                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        holder.imageView.setImageResource(itemData);

                    }
                };
        return gridSection;
    }


    private StaggerSection<ImageViewHolder, String> newStaggerSection() {

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            datas.addAll(urls);
        }


        StaggerSection<ImageViewHolder, String> gridSection =
                new StaggerSection<ImageViewHolder, String>(datas, 2) {


                    @Override
                    protected void decorLayoutHelper(StaggeredGridLayoutHelper layoutHelper) {
                        layoutHelper.setBgColor(ContextCompat.getColor(TestVLayoutComplex.this,
                                R.color.cwl_audio_divider_color));
                        layoutHelper.setGap(10);
//                        layoutHelper.setWeights(new float[] {0.5f,0.5f});
                    }

                    @Override
                    protected ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
                    }

                    @Override
                    protected void onBindViewHolder(ImageViewHolder holder, int position, String itemData) {
                        holder.itemView.setLayoutParams(new VirtualLayoutManager
                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        Glide.with(holder.itemView.getContext())
                                .load(itemData)
                                .into(holder.imageView);

                    }
                };
        return gridSection;
    }


}
