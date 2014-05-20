package com.do1.aqzhdj.activity.common;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import cn.com.do1.dqdp.android.control.BaseActivity;

import com.do1.aqzhdj.R;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-24
 * Time: 下午4:13
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleMidleSwitcher {
    private View view;
    private Button currentOn;
    Button leftBtn;
    Button rightBtn;

    public CircleMidleSwitcher(BaseActivity ctx, int viewId) {
        view = ctx.findViewById(viewId);
    }

    public void setText(String left, String right) {
        leftBtn = (Button) view.findViewById(R.id.button1);
        rightBtn = (Button) view.findViewById(R.id.button2);
        leftBtn.setText(left);
        rightBtn.setText(right);
        currentOn = leftBtn;
    }

    public boolean switchMidle(int btnId) {
        if (currentOn.getId() == btnId) return false;
        Drawable bgDrawable=currentOn.getBackground();
        ColorStateList textColors = currentOn.getTextColors();
        if(leftBtn.getId()==currentOn.getId()){
            leftBtn.setBackgroundDrawable(rightBtn.getBackground());
            leftBtn.setTextColor(rightBtn.getTextColors());
            rightBtn.setBackgroundDrawable(bgDrawable);
            rightBtn.setTextColor(textColors);
            currentOn=rightBtn;
        }else{
            rightBtn.setBackgroundDrawable(leftBtn.getBackground());
            leftBtn.setBackgroundDrawable(bgDrawable);
            rightBtn.setTextColor(leftBtn.getTextColors());
            leftBtn.setTextColor(textColors);
            currentOn=leftBtn;
        }
        return true;
    }
}
