package com.do1.aqzhdj.anim;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

/**
 * 3D翻转效果, 适用于activity之间以及view之间的切换
 * @author DZP
 *
 */
public class RotateEffect {
    private TransitionActivityByRotate transitionActivityByRotate;
    
    /**
     * 构造函数
     * @param activity      
     * @param order 
     *      activity顺序标识, order = 1:第一个activity, order = 2:第二个activity
     */
    public RotateEffect(Activity activity, int order) {
        transitionActivityByRotate = new TransitionActivityByRotate(activity, order);
    }
    
    public void applyRotation(ViewGroup layout,float start, float end) {
        //设置中心点
        final float centerX = layout.getWidth() / 2.0f;
        final float centerY = layout.getHeight() / 2.0f;

        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
                centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(transitionActivityByRotate);
        layout.startAnimation(rotation);
    }
    
    public void applyLastRotation(ViewGroup layout,float start, float end) {
        // Find the center of the container
//        final float centerX = layout.getWidth() / 2.0f;
//        final float centerY = layout.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
                 160, 192, 310.0f, false);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(transitionActivityByRotate);
        layout.startAnimation(rotation);
    }   
}
