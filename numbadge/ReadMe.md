# Sences for using
this is a libaray to display a num-badge in app,you cannot use it to display a badge at conner of the shortcut/launcher.
# How to use NumBadge
	step1 include lib into project
enable repo:
	
	repositories {
	    jcenter {
	        url "http://jcenter.bintray.com/"
	    }
	}
	
use dependency in gradle:
`compile 'individual.leobert.libs:numbadge:1.0.0'`

sync gradle dependencies.

---

	step2 define in layout:
e.g.

	 <individual.leobert.uilib.numbadge.NumBadge
	            android:id="@+id/numbadge_1"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            numbudge:backgroundShape="oval" />
if you want to use attrs in xml,remenber to define xlmns:

	xmlns:numbudge="http://schemas.android.com/apk/res-auto"
	
in version 1.0.0,only "backgroundShape" attribute provided. It's an enum includes 'oval'(default) and 'rectAngle'

---

	step3 update with api:

* **public void updateWithPointMode();**

	only display a point;

* **public void updateWithFriendlyMode(int num, int max);**

	**param max**:the max num to display,if num is lager than it,will display as [MAX_BADGE_NUM]+;

	e.g. use MAX_BADGE_NUM = 99,when you set 123 via #updateWithFriendlyMode(num,MAX_BADGE_NUM); it will displays as 99+
   
* **public void updateWithActualMode(int num);**

	display the number you give,specially use for number "1",and different from #updateWithFriendlyMode(int num,int max) when ***num*** is lager than ***max***

* **public void clear();**

	clear the num and hide display

* **public void setBackgroundShape(Shape bgShape);**

	change "backgroundShape"