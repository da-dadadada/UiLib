# LRecyclerView
## summary
LRecyclerView is an extended RecyclerView works on Android. With this library, we can easily use "pull to refresh on start","auto load on end" and “swipe menu for items”

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
compile 'individual.leobert.libs:lrecyclerview:1.0.0'
```

if problem occurs while syncing, try this:

```
repositories {
    jcenter {
        url "http://jcenter.bintray.com/"
    }
}
```
	
1. in layout:		

```
<individual.leobert.uilib.lrecyclerview.LRecyclerView
		android:id="@+id/XXX"
		android:layout_width="match_parent"
		android:layout_height="match_parent"/>
```





