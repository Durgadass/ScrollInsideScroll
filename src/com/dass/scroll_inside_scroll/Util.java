package com.dass.scroll_inside_scroll;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

public class Util {
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
}
