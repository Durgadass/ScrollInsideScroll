package com.dass.scroll_inside_scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
	private float mLastMotionY;

	public CustomScrollView(Context context) {
		super(context);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
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

}
