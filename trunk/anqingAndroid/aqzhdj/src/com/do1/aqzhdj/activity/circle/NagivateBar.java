package com.do1.aqzhdj.activity.circle;

import android.R;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.do1.dqdp.android.component.DqdpTextView;
import cn.com.do1.dqdp.android.control.BaseActivity;

import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-18
 * Time: 下午3:43
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class NagivateBar {

    static public void initBarById(int id, BaseActivity context, String title, Object[] leftImgRes, Object[] rightImgRes) {
        int index = Constants.NAGIVATEBAR_ID_START;
        RelativeLayout titleLayout = (RelativeLayout) context.findViewById(id);
        titleLayout.removeAllViews();
        TextView barTitle = new TextView(context);
        barTitle.setText(title);
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        barTitle.setTextSize(25);
        barTitle.setTextColor(context.getResources().getColor(R.color.white));
        titleLayout.addView(barTitle, titleParams);
        if (leftImgRes != null)
            for (int i = 0; i < leftImgRes.length; i++, index++) {
                View btn = getButtonView(index,leftImgRes[i], context);
                RelativeLayout.LayoutParams params = getLayOutParam(0, id, i == 0, index);
                titleLayout.addView(btn, params);
            }
        else
            leftImgRes = new String[0];
        if (rightImgRes != null)
            for (int i = 0; i < rightImgRes.length; i++, index++) {
                View btn = getButtonView(index,rightImgRes[i], context);
                RelativeLayout.LayoutParams params = getLayOutParam(1, id, i == 0, index);
                titleLayout.addView(btn, params);
            }
    }

    private static RelativeLayout.LayoutParams getLayOutParam(int side, int layId, boolean startForSide, int startId) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (side == 0)
            params.setMargins(10, 0, 0, 0);
        else
            params.setMargins(0, 0, 10, 0);
        if (startForSide)
            params.addRule(side == 0 ? RelativeLayout.ALIGN_PARENT_LEFT : RelativeLayout.ALIGN_PARENT_RIGHT);
        else
            params.addRule(side == 0?RelativeLayout.RIGHT_OF:RelativeLayout.LEFT_OF, startId - 1);
        params.addRule(RelativeLayout.CENTER_VERTICAL, layId);
        return params;
    }

    private static View getButtonView(int btnId,Object viewRes, BaseActivity context) {
        DqdpTextView btn = new DqdpTextView(context.getBaseContext());
//        btn.setClickable(true);
        btn.setOnClickListener(context);
        btn.setId(btnId);
        btn.setGravity(Gravity.CENTER);
        if (viewRes instanceof String) {
            btn.setText((String) viewRes);
            btn.setTextSize(15);
            btn.setTextColor(context.getResources().getColor(com.do1.aqzhdj.R.color.head_menu));
            btn.setBackgroundDrawable(context.getResources().getDrawable(getBackImg((String) viewRes)));
        } else{
            btn.setBackgroundDrawable(context.getResources().getDrawable((Integer) viewRes));
        }

        return btn;
    }

    private static int getBackImg(String content) {
        switch (content.length()) {
            case 2: {
                return com.do1.aqzhdj.R.drawable.btn_head_2;
            }
            case 4: {
                return com.do1.aqzhdj.R.drawable.btn_head_4;
            }
            case 5: {
                return com.do1.aqzhdj.R.drawable.btn_head_5;
            }
            default:
                return com.do1.aqzhdj.R.drawable.btn_head_2;
        }
    }
}
