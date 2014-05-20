package com.do1.zhdj.activity.infomation.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

import com.do1.zhdj.activity.infomation.CycleImageActivity;
import com.do1.zhdj.activity.infomation.InfomationActivity;

/**
 * TODO:自定义画廊，实现一次只滑动一张图片，无惯性 User:YanFangqin Date:2013-5-27 ProjectName:thzhd
 */
public class MyGallery extends Gallery {

	private float gTouchStartX;
	private float gTouchStartY;
	private float startX;
	 private static final int OFFSETX = 100;  
	 
	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setImageActivity(CycleImageActivity context) {

	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		int action = ev.getAction();
//		switch (action) {
//		case MotionEvent.ACTION_DOWN:
//			gTouchStartX = ev.getX();
//			gTouchStartY = ev.getY();
//			super.onTouchEvent(ev);
////			setParentScrollAble(false);//当手指触到listview的时候，让父ScrollView交出ontouch权限，也就是让父scrollview 停住不能滚动 
//			break;
//		case MotionEvent.ACTION_MOVE:
//			final float touchDistancesX = Math.abs(ev.getX() - gTouchStartX);
//			final float touchDistancesY = Math.abs(ev.getY() - gTouchStartY);
//			if (touchDistancesY * 2 >= touchDistancesX) {
//				return false;
//			} else {
//				return true;
//			}
//		case MotionEvent.ACTION_CANCEL:
//			break;
//		case MotionEvent.ACTION_UP:
//			break;
//		}
//		return false;
//	}
//	
	
	@Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {  
            startX = ev.getX();  
        } else {  
            float abs = Math.abs(startX - ev.getX());  
            if (abs > OFFSETX) {  
                return true;  
            }  
        }  
        return super.onInterceptTouchEvent(ev);  
    }  
//	
	
//	 @Override 
//	    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
//	 
//	        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  MeasureSpec.AT_MOST); 
//	        super.onMeasure(widthMeasureSpec, expandSpec); 
//	    } 

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		int kEvent;
		if (isScrollingLeft(e1, e2)) {
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);

		if (this.getSelectedItemPosition() == 0) {// 实现后退功能
			this.setSelection(CycleImageActivity.picture.length);
		}
		return false;

	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	// public boolean dispatchTouchEvent(android.view.MotionEvent ev) {
	// return true;
	// };
}
