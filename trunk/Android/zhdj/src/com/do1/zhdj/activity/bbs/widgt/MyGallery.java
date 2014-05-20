package com.do1.zhdj.activity.bbs.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;
import cn.com.do1.component.util.Log;

import com.do1.zhdj.activity.infomation.CycleImageActivity;
 
/**
 * TODO:自定义画廊，实现一次只滑动一张图片，无惯性
 * User:YanFangqin
 * Date:2013-5-27
 * ProjectName:thzhd
 */
public class MyGallery extends Gallery {
 
    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public MyGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    public void setImageActivity(CycleImageActivity context) {
 
    }
 
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
    	Log.e("111111111111111111111");
        int kEvent;
        if (isScrollingLeft(e1, e2)) {
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else {
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
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
 
}

