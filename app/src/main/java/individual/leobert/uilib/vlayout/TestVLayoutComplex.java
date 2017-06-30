package individual.leobert.uilib.vlayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import individual.leobert.uilib.autolooperbanner.IBannerUpdate;
import individual.leobert.uilib.autolooperbanner.ImgRes;
import individual.leobert.uilib.lrecyclerview.LRecyclerView;
import individual.leobert.uilib.lrecyclerview.ProgressStyle;
import individual.leobert.uilib.lrecyclerview.swipe.Closeable;
import individual.leobert.uilib.lrecyclerview.swipe.OnSwipeMenuItemClickListener;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenu;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuAdapterHelper;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuCreator;
import individual.leobert.uilib.lrecyclerview.swipe.SwipeMenuItem;
import individual.leobert.uilib.temp.MAdapter;
import individual.leobert.uilib.vlayoutext.VLSectionAssembler;
import individual.leobert.uilib.vlayoutext.group.ListSection;
import individual.leobert.uilib.vlayoutext.single.BannerSection;

public class TestVLayoutComplex extends AppCompatActivity {
    final List<String> urls = new ArrayList<>();
    private DelegateAdapter delegateAdapter;

    private int refreshTime = 0;
    private int times = 0;
    private LRecyclerView recyclerView;

    private ListSection<LinearViewHolderSample,
            Data1,
            LinearViewHolderSample.IEventListener> listSection;

    private IBannerUpdate iBannerUpdate = new IBannerUpdate() {
        @Override
        public void UpdateImage(ImgRes<?> res, ImageView imageView) {
            Glide.with(TestVLayoutComplex.this)
                    .load(res.getRes())
                    .asBitmap()
                    .into(imageView);
        }
    };

    private Context getContext() {
        return this;
    }

    private SwipeMenuAdapterHelper swipeMenuAdapterHelper;

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                        .setImage(R.mipmap.ic_action_add) // 图标。
                        .setWidth(width) // 宽度。
                        .setHeight(height); // 高度。
                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem addItem = new SwipeMenuItem(getContext())
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };


    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {

        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == LRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getBaseContext(), "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == LRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getBaseContext(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }

            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vlayout_complex);
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");

        recyclerView = (LRecyclerView) findViewById(R.id.vlayout_complex);
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

        delegateAdapter = new DelegateAdapter(layoutManager, false);

        //..................................

        swipeMenuAdapterHelper = new SwipeMenuAdapterHelper();
        swipeMenuAdapterHelper.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuAdapterHelper.setSwipeMenuItemClickListener(menuItemClickListener);

        //............................

        BannerSection bannerSection = newBannerSection();

        final List<String> urls2 = new ArrayList<>();
        urls2.addAll(urls);
        urls2.addAll(urls);
        urls2.addAll(urls);

        listSection = newListSection(Data1.genTest(4, "init"));
        VLSectionAssembler.getAssembler(recyclerView, delegateAdapter)
                .add(bannerSection)
//                .add(newHSRVAdapter(urls2))
                .add(listSection)
//                .add(newGrideSection())
//                .add(newRangeGridSection())
                .assembler();


        delegateAdapter.notifyDataSetChanged();
        initLRecyclerFeature(recyclerView);
    }

    private void initLRecyclerFeature(final LRecyclerView mRecyclerView) {
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        TextView headerView = new TextView(this);
        headerView.setText("header 1");
        mRecyclerView.addHeaderView(headerView);

        mRecyclerView.setLoadingListener(new LRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        refreshTime++;
                        listSection.setData(Data1.genTest(4, "refresh " + refreshTime));
                        times = 0;
                        mRecyclerView.refreshComplete();
                        mRecyclerView.setNoMore(false);
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 1) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            listSection.addData(new Data1(), mRecyclerView.getLHeadersCount());
                            mRecyclerView.loadMoreComplete();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            listSection.addData(new Data1(), mRecyclerView.getLHeadersCount());
                            mRecyclerView.loadMoreComplete();
                            mRecyclerView.setNoMore(true);

                        }
                    }, 1000);
                }
                times++;
            }
        });
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

        BannerSection section =
                new BannerSection(urls, iBannerUpdate) {
                    @Override
                    public View onCreateItemView(ViewGroup parent) {
                        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.vlext_section_banner, parent, false);
                        AutoLooperBanner autoLooperBanner =
                                (AutoLooperBanner) viewGroup.findViewById(R.id.vlext_banner);
                        viewGroup.removeView(autoLooperBanner);
                        return autoLooperBanner;
                    }

                    @Override
                    protected AutoLooperBanner.OnBannerItemClickListener getBannerItemClickListener(List<String> datas) {
                        return new AutoLooperBanner.OnBannerItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                final String msg = "click:" + position + "\r\nurl:" +
                                        getItemDataByPosition(position);
                                Log.d("lmsg", msg);
                                Toast.makeText(TestVLayoutComplex.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        };
                    }
                };
//        MAdapter<BannerSection.BannerSectionViewHolder, List<String>, Void> mAdapter
//                = new MAdapter<>(section);
//        mAdapter.setSwipeMenuAdapterHelper(swipeMenuAdapterHelper);
//        section.setAdapter(mAdapter);
        return section;
    }

    private ListSection<LinearViewHolderSample, Data1,
            LinearViewHolderSample.IEventListener> newListSection(List<Data1> datas) {
        ListSection<LinearViewHolderSample, Data1,
                LinearViewHolderSample.IEventListener> listSection =
                new ListSection<LinearViewHolderSample,
                        Data1,
                        LinearViewHolderSample.IEventListener>(datas) {

                    @Override
                    public void onBindViewHolder(LinearViewHolderSample holder,
                                                 int position, Data1 itemData) {
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        holder.itemView.setLayoutParams(layoutParams);
                        holder.update(itemData);
                    }

                    @Override
                    public LinearViewHolderSample onCompatCreateViewHolder(View contentView,
                                                                           View originView,
                                                                           int viewType) {
                        return new LinearViewHolderSample(contentView);
                    }

                    @Override
                    public View onCreateItemView(ViewGroup parent, int viewType) {
                        return LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.vlayout_item_linear, parent, false);
                    }

                    private LinearViewHolderSample.IEventListener listener;

                    @Override
                    public LinearViewHolderSample.IEventListener getItemEventListener() {
                        if (listener == null) {
                            listener = new LinearViewHolderSample.IEventListener() {

                                @Override
                                public void onAvatarClick(int position) {
                                    Toast.makeText(TestVLayoutComplex.this, "avatar", Toast.LENGTH_SHORT).show();
                                    getItemDataByPosition(position).setTitle("changed title");
                                    getAdapter().notifyItemChanged(position + recyclerView.getLHeadersCount());

                                }
                            };
                        }
                        return listener;
                    }


                    @Override
                    protected void decorLayoutHelper(LinearLayoutHelper layoutHelper) {
                        layoutHelper.setDividerHeight(10);
                        layoutHelper.setBgColor(ContextCompat.getColor(TestVLayoutComplex.this,
                                android.R.color.transparent));
                        //test -> useless for linear
                        layoutHelper.setItemCount(5);
                    }
                };
        MAdapter<LinearViewHolderSample, Data1,
                LinearViewHolderSample.IEventListener> adapter = new MAdapter<>(listSection);
        adapter.setSwipeMenuAdapterHelper(swipeMenuAdapterHelper);
        listSection.setSectionAdapter(adapter);
        return listSection;
    }


    /////////////////////////////////
    //  以下测试代码块暂时废弃
    /////////////////////////////////

//    private GridSection<ImageViewHolder, Integer> newGrideSection() {
//        VLayoutSection.ViewHolderDecor<ImageViewHolder, Integer> eventDecor =
//                new VLayoutSection.ViewHolderDecor<ImageViewHolder, Integer>() {
//                    @Override
//                    public void decor(ImageViewHolder holder, Integer itemData, int position) {
//                        holder.imageView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //presenter.on...Called(data)
//                                Toast.makeText(TestVLayoutComplex.this, "img", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                };
//
//        ArrayList<Integer> datas = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            datas.add(R.drawable.v1000_drawable_avatar_default);
//        }
//
//
//        GridSection<ImageViewHolder, Integer> gridSection =
//                new GridSection<ImageViewHolder, Integer>(datas, 2, eventDecor) {
//
//
//                    @Override
//                    protected void decorLayoutHelper(GridLayoutHelper layoutHelper) {
//                        layoutHelper.setBgColor(ContextCompat.getColor(TestVLayoutComplex.this,
//                                R.color.colorPrimary));
//                        layoutHelper.setGap(10);
//                        layoutHelper.setAutoExpand(true);
////                        layoutHelper.setWeights(new float[] {0.5f,0.5f});
//                    }
//
//                    @Override
//                    protected ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
//                                .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
//                    }
//
//                    @Override
//                    protected void onBindViewHolder(ImageViewHolder holder, int position, Integer itemData) {
//                        holder.itemView.setLayoutParams(new VirtualLayoutManager
//                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//                        holder.imageView.setImageResource(itemData);
//
//                    }
//                };
//        return gridSection;
//    }
//
//
//    private RangeGridSection<ImageViewHolder, Integer> newRangeGridSection() {
//
//        ArrayList<Integer> datas = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            datas.add(R.drawable.v1000_drawable_avatar_default);
//        }
//
//
//        RangeGridSection<ImageViewHolder, Integer> gridSection =
//                new RangeGridSection<ImageViewHolder, Integer>(datas, 4) {
//
//
//                    @Override
//                    protected void decorLayoutHelper(RangeGridLayoutHelper layoutHelper) {
//                        layoutHelper.setBgColor(Color.GREEN);
//                        layoutHelper.setWeights(new float[]{10f, Float.NaN, 20f});
//                        layoutHelper.setPadding(15, 15, 15, 15);
//                        layoutHelper.setMargin(15, 15, 15, 15);
//                        layoutHelper.setHGap(10);
//                        layoutHelper.setVGap(10);
//                        RangeGridLayoutHelper.GridRangeStyle rangeStyle = new RangeGridLayoutHelper.GridRangeStyle();
//                        rangeStyle.setBgColor(Color.RED);
//                        rangeStyle.setSpanCount(2);
//                        rangeStyle.setWeights(new float[]{46.665f});
//                        rangeStyle.setPadding(15, 15, 15, 15);
//                        rangeStyle.setMargin(15, 15, 15, 15);
//                        rangeStyle.setHGap(5);
//                        rangeStyle.setVGap(5);
//                        layoutHelper.addRangeStyle(4, 7, rangeStyle);
//                        RangeGridLayoutHelper.GridRangeStyle rangeStyle1 = new RangeGridLayoutHelper.GridRangeStyle();
//                        rangeStyle1.setBgColor(Color.YELLOW);
//                        rangeStyle1.setSpanCount(2);
//                        rangeStyle1.setWeights(new float[]{46.665f});
//                        rangeStyle1.setPadding(15, 15, 15, 15);
//                        rangeStyle1.setMargin(15, 15, 15, 15);
//                        rangeStyle1.setHGap(5);
//                        rangeStyle1.setVGap(5);
//                        layoutHelper.addRangeStyle(8, 11, rangeStyle1);
//                    }
//
//                    @Override
//                    protected ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
//                                .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
//                    }
//
//                    @Override
//                    protected void onBindViewHolder(ImageViewHolder holder, int position, Integer itemData) {
//                        holder.itemView.setLayoutParams(new VirtualLayoutManager
//                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//                        holder.imageView.setImageResource(itemData);
//
//                    }
//                };
//        return gridSection;
//    }
//
//
//    private StaggerSection<ImageViewHolder, String> newStaggerSection() {
//
//        ArrayList<String> datas = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            datas.addAll(urls);
//        }
//
//
//        StaggerSection<ImageViewHolder, String> gridSection =
//                new StaggerSection<ImageViewHolder, String>(datas, 2) {
//
//
//                    @Override
//                    protected void decorLayoutHelper(StaggeredGridLayoutHelper layoutHelper) {
//                        layoutHelper.setBgColor(ContextCompat.getColor(TestVLayoutComplex.this,
//                                R.color.cwl_audio_divider_color));
//                        layoutHelper.setGap(10);
////                        layoutHelper.setWeights(new float[] {0.5f,0.5f});
//                    }
//
//                    @Override
//                    protected ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
//                                .inflate(R.layout.vl_section_item_h_scroll_rv, parent, false));
//                    }
//
//                    @Override
//                    protected void onBindViewHolder(ImageViewHolder holder, int position, String itemData) {
//                        holder.itemView.setLayoutParams(new VirtualLayoutManager
//                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//                        Glide.with(holder.itemView.getContext())
//                                .load(itemData)
//                                .into(holder.imageView);
//
//                    }
//                };
//        return gridSection;
//    }


}
