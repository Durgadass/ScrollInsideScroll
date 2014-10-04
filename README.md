Scroll Inside Scroll
==================

This project shows how to add a vertically scrollable view into another vertically scrollable view upto any level of depth.

Placing vertically scrollable view into another vertically scrollable view is a bit tedious in android. Here we show how to place, withour sacrificing the recycling feature of various <code>AbsListView</code>s, <code>ScrollView</code> inside <code>ScrollView</code>, <code>ScrollView</code> inside <code>ListView</code>, <code>ListView</code> inside <code>GridView</code>, etc. And even <code>ListView</code> indide <code>ScrollView</code> which in turn inside another <code>ScrollView</code>, etc.

<b>Note</b>
Eventhough you can nest scroll views to any level, please consider the user experiance. This project contains a complicated xml layout file <code>scroll_in_scroll.xml</code> which has no good user experiance.

<H5>Step 1</H5>
Write a method which determines wheather a <code>View</code> can be scrolled vertically and place the method inside a common utility class as follows. This method is taken from <code>ViewPager.java</code> and modified to find whether a <code>View</code> can vertically scrollable.

	public static boolean canScroll(View v, boolean checkV, int dy, int x, int y) {
		if (v instanceof ViewGroup) {
			final ViewGroup group = (ViewGroup) v;
			final int scrollX = v.getScrollX();
			final int scrollY = v.getScrollY();
			final int count = group.getChildCount();
			for (int i = count - 1; i >= 0; i--) {
				final View child = group.getChildAt(i);
				if (x + scrollX >= child.getLeft()
						&& x + scrollX < child.getRight()
						&& y + scrollY >= child.getTop()
						&& y + scrollY < child.getBottom()
						&& canScroll(child, true, dy,
								x + scrollX - child.getLeft(), y + scrollY
										- child.getTop())) {
					return true;
				}
			}
		}
		return checkV && ViewCompat.canScrollVertically(v, -dy);
	}

<H4>Step 2</H4>
Subclass the enclosing vertically scrollable view, it may be <code>ScrollView</code> or <code>ListView</code>, or the like and override the <code>onInterceptTouchEvent()</code> method as follows.

	public boolean onInterceptTouchEvent(MotionEvent event) {
	  int action = event.getAction();
		float x = event.getX();
		float y = event.getY();
		float dy = y - mLastMotionY;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (Util.canScroll(this, false, (int) dy, (int) x, (int) y)) {
				mLastMotionY = y;
				return false;
			}
			break;
		}
		return super.onInterceptTouchEvent(event);
	}
	

<H4>Step 3</H4>
Subclass the enclosed vertically scrollable view, it may be <code>GridView</code> or <code>ListView</code> or the like and override the <code>onMeasure()</code> method as follows. No need to override this method in <code>ScrollView</code>. Its default implementation behaves in the right way.

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		if (mode == MeasureSpec.UNSPECIFIED) {
			int height = getLayoutParams().height;
			if (height > 0)
				setMeasuredDimension(getMeasuredWidth(), height);
		}
	}
	
<H4>Step 4</H4>
Finally create an xml layout file as given below and see how it works. We have to hard code the <code>layout_height</code> of <code>CustomListView</code>. If you create the view hierarchy programmatically, then set the height via <code>LayoutParams</code>. 

    <com.dass.scroll_inside_scroll.CustomScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent" >
            
            <!-- Other Children -->
            
            <com.dass.scroll_inside_scroll.CustomListView
                android:layout_width="match_parent"
                android:layout_height="300dp" >
            </com.dass.scroll_inside_scroll.CustomListView>
            
            <!-- Other Children -->
            
        </LinearLayout>
    </com.dass.scroll_inside_scroll.CustomScrollView>

In this project the classes <code>CustomListView</code>, <code>CustomExpandableListView</code>, <code>CustomGridView</code> overrides the method <code>onInterceptTouchEvent()</code> in order to place other vertically scrollable views inside them. If we dont add vertically scrollable views inside any of these classes then no need to override <code>onInterceptTouchEvent()</code>.
