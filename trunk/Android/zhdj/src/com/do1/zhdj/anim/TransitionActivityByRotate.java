package com.do1.zhdj.anim;

import android.app.Activity;
import android.view.animation.Animation;

import com.do1.zhdj.activity.StartActivity;

/**
 * 用于翻转动画效果时, 两个Activity之间的切换处理
 * @author DZP
 *
 */
public class TransitionActivityByRotate implements Animation.AnimationListener {
    private Activity activity;
    private int order;      //activity顺序标识, order = 1:第一个activity, order = 2:第二个activity
    
    public TransitionActivityByRotate(Activity activity, int order) {
        this.activity = activity;
        this.order = order;
    }
    
    //@Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub
        doAnimationEnd(order);
    }

    //@Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub
        
    }

    //@Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub
        
    }
    
    public void doAnimationEnd(int _order) {
        switch (_order) {
        case 1:
            //((IndexActivity) activity).indexLayout.post(new SwapViews());
            //((TranslateLayout) ac).jumpToSecond();
            //((StartActivity) activity).startLayout.post(new SwapViews());
            ((StartActivity) activity).naviToIndex();
            break;
        case 2:
            //((IndexActivity) activity).pop();
            //((IndexActivity) activity).indexLayout.post(new SwapViews());
            //((Second) ac).jumpToFirst();
            break;
        }
    }
    
    /*private final class SwapViews implements Runnable {
        public void run() {
            switch (order) {
            case 1:
                //((IndexActivity) activity).naviToFavor();
                ((StartActivity) activity).naviToIndex();
                break;
            case 2:
               ((IndexActivity) activity).pop();
                break;
            }
        }
    }*/
}
