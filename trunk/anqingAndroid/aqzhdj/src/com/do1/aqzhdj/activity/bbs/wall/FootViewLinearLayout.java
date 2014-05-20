package com.do1.aqzhdj.activity.bbs.wall;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 加载footview
 * auth:YanFangqin
 * data:2013-4-25
 * thzhd
 */
public class FootViewLinearLayout extends LinearLayout {
	private Context footContext;
	private TextView msgTextView; // 用于提示的TextView

	public FootViewLinearLayout(Context context) {
		super(context);
		this.footContext = context;
	}

	public FootViewLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.footContext = context;
	}

	/**
	 * 用于自定义样�?
	 * 
	 * @param layoutid
	 *            布局id
	 * @param resid
	 *            用于提示的文本id
	 */
	public void init(int layoutid, int resid) {
		View view = View.inflate(footContext, layoutid, null);
		removeAllViews();
		LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		addView(view, layoutParams);
		msgTextView = (TextView) view.findViewById(resid);
	}

	/**
	 * 使用默认样式
	 */
	public void init() {
		removeAllViews();
		msgTextView = new TextView(footContext);
		msgTextView.setTextSize(18);
		msgTextView.setTextColor(Color.BLACK);
//		msgTextView.setText("正在加载，请稍后...");
//		msgTextView.setGravity(Gravity.CENTER_VERTICAL);
		LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 60);
		addView(msgTextView, layoutParams);
		setGravity(Gravity.CENTER);
	}

	public TextView getMsgTextView() {
		return msgTextView;
	}

	public void setMsgTextView(TextView msgTextView) {
		this.msgTextView = msgTextView;
	}
}