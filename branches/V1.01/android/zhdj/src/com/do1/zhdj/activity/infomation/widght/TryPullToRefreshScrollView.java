package com.do1.zhdj.activity.infomation.widght;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * TODO:自定义scrollView
 * User:YanFangqin
 * Date:2013-5-23
 * ProjectName:thzhd
 */
public class TryPullToRefreshScrollView extends ScrollView {

	private float gTouchStartX;
	private float gTouchStartY;
	private float startX;
	 private static final int OFFSETX = 10; 


	public TryPullToRefreshScrollView(Context context) {
		super(context);
		init(context);

	}

	public TryPullToRefreshScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);

	}

	public TryPullToRefreshScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);

	}

	private void init(Context context) {

	}

	public static void ScrollToPoint(final View scroll, final View inner,
			final int i) {
		Handler mHandler = new Handler();

		mHandler.post(new Runnable() {
			public void run() {
				if (scroll == null || inner == null) {
					return;
				}

				int offset = inner.getMeasuredHeight() - scroll.getHeight() - i;

				if (offset < 0) {
					offset = 0;
				}

				scroll.scrollTo(0, offset);

				scroll.invalidate();
			}
		});
	}
	
	@Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {  
            startX = ev.getX();  
        } else {  
            float abs = Math.abs(startX - ev.getX());  
            if (abs > OFFSETX) {  
                return false;  
            }  
        }  
        return super.onInterceptTouchEvent(ev);  
    }  

}