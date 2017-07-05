# LRecyclerView
## summary
LRecyclerView is an extended RecyclerView works on Android. With this library, we can easily use "pull to refresh on start","auto load on end" and “swipe menu for items”

| 版本        | summary     | 
| :--------   | :-----   |  
| ***latest***     1.0.1 	 | 移除了无效方法         |  
|1.0.0| inited|


---

## Why we need LRecyclerView

* It is a general feature or requirement in an application that refreshing data by *pull from start and loading more data when arriving the end*, **especially at the scene of displaying a set of data.**
* It would be shamed us to admit that **the habits of swiping to open/hide an extending menu is trained by iOS system**, but our UX might admire this operating habit and it's a little hard to realize this feature without revising the code of library support PTR*(refers to pull-to-refresh)*

---
## Thanks to
Because many excellent libraries has been written and open-sourced by others, It's no necessary to start from scratch. Thanks to those:

* [XRecyclerView](https://github.com/jianghejie/XRecyclerView) a library to support PTR
* [SwipeRecyclerView](https://github.com/yanzhenjie/SwipeRecyclerView) a library to support swipe-menu,item-drag-to-sort,swipe-to-remove in recyclerview

and other open-sourced libraries required by them.

---

## How to include LRecyclerView in your application

I have released LRecyclerView to bintray, with gradle:

```
compile 'individual.leobert.libs:lrecyclerview:1.0.1'
```

if problem occurs while syncing, try this:

```
repositories {
    jcenter {
        url "http://jcenter.bintray.com/"
    }
}
```
	
* in layout:		

```
<individual.leobert.uilib.lrecyclerview.LRecyclerView
		android:id="@+id/XXX"
		android:layout_width="match_parent"
		android:layout_height="match_parent"/>
```

* get instance via findViewById(int id) in activity or View#findViewById(int id):

```
 final LRecyclerView lRecyclerView =
      (LRecyclerView) 
      findViewById({id});
```

---

## Basic Features

* [pull-to-refresh](#b1)
* [auto-load](#b2)
* [loading effects](#b3)
* [add custom header](#b4)
* [empty view](#b5)
* [item swipe menu](#b6)
* [drag to sort](#b7)

--

<span id = "b1">
### pull-to-refresh
> since 1.0.0


This feature is defaultly enabled, if you have no need to refresh data,you should disable it：

```
Use:LRecyclerView#setPullRefreshEnabled(boolean enabled)

e.g. lRecyclerView.setPullRefreshEnabled(false) 
```
to enable:

```
lRecyclerView.setPullRefreshEnabled(true)
```

to realize refresh data, we need "listener"

```
 public interface LoadingListener {

    void onRefresh();

    void onLoadMore();
 }
```
create an implementation of this interface to visit web-service or local data consistant component to get data, **onRefresh()** for pull-to-refresh and **onLoadMore()** for auto-load.

If you only need refresh, best practice as follow:

```
public abstract class AbsOnRefreshListener implement LRecyclerView.LoadingListener {
	public final void onLoadMore() {
		// no need to listen load more
	} 
} 

class OnRefreshListener extends AbsOnRefreshListener {
	public void onRefresh() {
		//TODO complete your code to get data
	}
}
```
set the listener to LRecyclerView:

```
LRecyclerView#setLoadingListener(LoadingListener listener) 
```
notify LRecyclerView refresh is completed:

```
lRecyclerView.refreshComplete();
```
must be called on UI thread(main thread), and no necessary to care about prerequisite of load more,because:

> ```
> public void refreshComplete() {
>     mRefreshHeader.refreshComplete();
>     setNoMore(false);
> }
  
--

<span id = "b2">
### auto-load
> since 1.0.0

This feature is defaultly disabled, enable it with **LRecyclerView#setLoadingMoreEnabled(true)** if necessary

listener has been introduced at pre-paragraph.

notify LRecyclerView loading is conpleted:

```
lRecyclerView.loadMoreComplete();
```
must be called on UI thread.

If no more data to fetch,use:

```
LRecyclerView#setNoMore(boolean noMore)
```
to mark the flag.

--

<span id = "b3">
### loading effects
> since 1.0.0
 
 use supplied effect
 
 ```
 // ptr style
LRecyclerView#setRefreshProgressStyle(int style);

//loadmore style
LRecyclerView#setLoadingMoreProgressStyle(int style);

 ```
 fully customize:(not suggested)
 
 ```
 LRecyclerView#setRefreshHeader(ArrowRefreshHeader refreshHeader)
 ```
 
 
 change arrow icon
 
 ```
 LRecyclerView#setArrowImageView(int resId)
 ```
 
> supplied from 1.0.0
 
1. "SysProgress"
* "BallPulse"
* "BallGridPulse"
* "BallClipRotate"
* "BallClipRotatePulse"
* "SquareSpin"
* "BallClipRotateMultiple"
* "BallPulseRise"
* "BallRotate"
* "CubeTransition"
* "BallZigZag"
* "BallZigZagDeflect"
* "BallTrianglePath"
* "BallScale"
* "LineScale"
* "LineScaleParty"
* "BallScaleMultiple"
* "BallPulseSync"
* "BallBeat"
* "LineScalePulseOut"
* "LineScalePulseOutRapid"
* "BallScaleRipple"
* "BallScaleRippleMultiple"
* "BallSpinFadeLoader"
* "LineSpinFadeLoader"
* "TriangleSkewSpin"
* "Pacman"
* "BallGridBeat"
* "SemiCircleSpin"

if can taste those all at **PtrSample** 

--

> **continue updating, any changes will be logged at here when release.**

--

<span id = "b4">
### add custom headers
use 

```
LRecyclerView#addHeaderView(View view)
```
to add a custom header

--
### empty view <span id = "b5">
use

```
LRecyclerView#setEmptyView(View emptyView)
```
to set an empty view for display while no data

--
### item swipe menu <span id = "b6">

**something important：**

> We can use X-Y axis to describe the windows, our swipe menu is **always working at the X axis** , so you will be troubled at a horizental-scroll-recyclerview e.g.

> ```
LinearLayoutManager sample = 
	new LinearLayoutManager(getContext(),
           LinearLayoutManager.HORIZONTAL,false);

> lRecycler.setLayoutManager(sample);
```
>
you can display your list, but swipe menu can't be triggered.
**Just remenber: item-swipe-menu only works on vertical-scroll-recyclerview**

To use this feature,**we don't have any necessary to define a class inherits a specific superclass(perhaps named as SwipeMenuAdapter)**,thus,inheritance system won't be broken, sounds like a good message.**However,a swipe menu needs a SwipeLayout**.In another word:

> ViewHolder's itemView must contain a SwipeLayout  wrapping the original view.

so,some operations should be performed:

* onCreateViewHolder will be segmentalized to three parts
	* create an original view by user
	* wrap the originalView and menu in a swipelayout
	* create the viewholder with swipelayout by user
* onBindViewHolder will be segmentalized to two parts
	* bind AdapterViewHolder to swipemenu
	* bind data、listeners and so on as usually

examples:

a clasical adapter like this:

```
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private List<String > data;
    private OnItemClickListener onItemClickListener;

    public SimpleAdapter(@NonNull List<String> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_viewholder,parent,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = 
        	LayoutInflater.form(parent.getContext)
        			.inflate(R.layout.xxx,parent,false);        
        return new SimpleViewHolder(view);    
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setContent(data.get(position));
        holder.bindClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
```

we change it to this style:

```
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private List<String > data;
    private OnItemClickListener onItemClickListener;

    public SimpleAdapter(@NonNull List<String> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    @Override
//    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.simple_viewholder,parent,false);
//        return new SimpleViewHolder(view);
//    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View originView = onCreateItemView(parent, viewType);
        View decorView = onCreateDecorItemView(parent,originView, viewType);

        return onCompatCreateViewHolder(decorView, originView, viewType);
    }


    protected View onCreateDecorItemView(ViewGroup viewGroup,
                                         View originView, int viewType) {
        return originView;
    }

    private SimpleViewHolder onCompatCreateViewHolder(View contentView,
                                        View originView, int viewType) {
        return new SimpleViewHolder(contentView);
    }

    protected View onCreateItemView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_viewholder,parent,false);
        return view;
    }


    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setContent(data.get(position));
        holder.bindClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
```
**you can define it as an abstract class and use generic type.**

the most of the hard works are encapsulated in class SwipeMenuAdapterHelper.

**expand your adapter with interface ISwipeMenuAdapter**

example:

```
public class MAdapter extends SimpleAdapter
            implements ISwipeMenuAdapter {

        private SwipeMenuAdapterHelper swipeMenuAdapterHelper;

        public MAdapter(List<String> data) {
            super(data);
        }

        @Override
        protected View onCreateDecorItemView(ViewGroup viewGroup, View originView, int viewType) {
            if (swipeMenuAdapterHelper != null) {
                return swipeMenuAdapterHelper.helpCreateSwipeView(viewGroup, originView, viewType);
            }
            return originView;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            if (swipeMenuAdapterHelper != null)
                swipeMenuAdapterHelper.helpOnBindViewHolder(holder, position, null);
            super.onBindViewHolder(holder, position);
        }

        @Override
        public void setSwipeMenuAdapterHelper(SwipeMenuAdapterHelper helper) {
            this.swipeMenuAdapterHelper = helper;
        }

        @Override
        public SwipeMenuAdapterHelper getSwipeMenuAdapterHelper() {
            return swipeMenuAdapterHelper;
        }
    }
```

**How to use SwipeMenuAdapterHelper?**

```
SwipeMenuAdapterHelper swipeMenuAdapterHelper = new SwipeMenuAdapterHelper();
swipeMenuAdapterHelper.setSwipeMenuCreator(swipeMenuCreator);
swipeMenuAdapterHelper.setSwipeMenuItemClickListener(menuItemClickListener);
```
SwipeMenuCreator & OnSwipeMenuItemClickListener should be provided.

```
/**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            //TODO: 2017/7/3 attention：use setOrientation to control the style
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            swipeLeftMenu.setOrientation(SwipeMenu.HORIZONTAL);//默认
            swipeRightMenu.setOrientation(SwipeMenu.HORIZONTAL);//默认

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
                Toast.makeText(getBaseContext(), "list第" +
                        getAdjustPosition(adapterPosition) + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == LRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getBaseContext(), "list第" +
                        getAdjustPosition(adapterPosition) + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
```

fast disable swipe if necessary:

```
use:
LRecyclerView#setItemViewSwipeEnabled(boolean canSwipe)
```

see more at [Swipe Menu Pro](#p0)


--

<span id = "b7">
### drag to sort

#### If use long press on item to active:
```
lRecyclerView.setLongPressDragEnabled(true);
```
#### If use an handle on item to active:
set an touchlistener to the handle,when trigger,use
LRecyclerView#startDrag(ViewHolder holder) to active

remember that：
> Changes should affect data,UI comes from data.

register listener to affect data:

```
 /**
     * 当Item移动的时候。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            // 当Item被拖拽的时候。
            Collections.swap(mDataList, fromPosition, toPosition);
            lRecyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            // 
        }
    };
    
    lRecyclerView.setOnItemMoveListener(this.onItemMoveListener);

```

If you need to listen the state

```
 private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder,int actionState) {

            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
                setTitle("状态：拖拽");

                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(getBaseContext(),
                        R.color.white_pressed));
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                setTitle("状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                setTitle("状态：手指松开");

                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView,
                        ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.select_white));
            }
        }};

        lRecyclerView.setOnItemStateChangedListener(this.mOnItemStateChangedListener);
```

---

<span id = "p0">
## Swipe Menu Pro

### icon text and both

see class SwipeMenuItem。

--

### left menu and right menu
we use SwipeMenu#addMenuItem(SwipeMenuItem item) to add items.

In the implementation of SwipeMenuCreator

```
public interface SwipeMenuCreator {

    /**
     * Create menu for recyclerVie item.
     *
     * @param swipeLeftMenu  The menu on the left.
     * @param swipeRightMenu The menu on the right.
     * @param viewType       The view type of the new view.
     */
    void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType);

}
```
add item to the specific menu to build the menu,if one menu contains none item,it won't trigger via swipe.

--

### vertical and horizontal
use 

```
SwipeMenu#setOrientation(@OrientationMode int orientation)
```
to set the orientation of the menu.

* SwipeMenu.HORIZONTAL//默认
* SwipeMenu.VERTICAL

--

### customize by viewType

If you create a group of views to display different type of data in one recyclerview, viewType must be used, the SwipeMenuCreator support create specific swipeMenu for each viewType, use factory may be much more elegant.

--
 
### inside or outside

use CardView as a sample.

If we need to apply Meterial-Design style to our LRecyclerView, we might use CardView as the root container of each item, just like a box.Thus we want to display the swipeMenu in the CardView.

Try as follows:

```
 MAdapter adapter = new MAdapter(genData("item", 20, MODE_APPEND)) {

            @Override
            protected View onCreateDecorItemView(ViewGroup viewGroup, View originView, int viewType) {
                View view = super.onCreateDecorItemView(viewGroup, originView, viewType);

                CardView cardView = new CardView(viewGroup.getContext());

                cardView.addView(view);
                return cardView;
            }

        };
```

However, if you just want the swipemenu lay outside, try as follows:

```
 MAdapter adapter = new MAdapter(genData("item", 20, MODE_APPEND)) {


            @Override
            protected View onCreateItemView(ViewGroup parent, int viewType) {
                CardView cardView = new CardView(parent.getContext());

                View view = super.onCreateItemView(parent, viewType);
                cardView.addView(view);
                return cardView;
            }
        };
```


---

## Flaws

### offseted position
LRecyclerView add ptr-header and custom headers in the recyclerview by an WrapAdapter,so use ViewHolder#getAdapterPosition will get a number gratter  than you want.*(the ptr-header starts at 0,and followed by custom headers)*

you can use **LRecyclerView#getLHeadersCount()** to get the offset.

**isRuledByRealAdapter(int position)** to check whether this item is ruled by your adapter

**getPositionInRealAdapter(int position)** to get the real position

**More attention should be payed when notifying data changes via position,headers should be considered.**

I will try to fix this on next release.

--

### Not fully tested functions

* startSwipe(ViewHolder viewHolder) 
* startDrag(ViewHolder viewHolder)
* smoothOpenLeftMenu(int position)
* smoothOpenLeftMenu(int position, int duration)
* smoothOpenRightMenu(int position)
* smoothOpenRightMenu(int position, int duration)
* smoothOpenMenu(int position, @DirectionMode int direction,int duration)

these calls might be unsafe

--


