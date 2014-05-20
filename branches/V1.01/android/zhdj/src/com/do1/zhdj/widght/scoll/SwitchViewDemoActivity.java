package com.do1.zhdj.widght.scoll;

import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.StartActivity;
import com.do1.zhdj.activity.mine.user.LoginActivity;
import com.do1.zhdj.activity.parent.BaseActivity;

public class SwitchViewDemoActivity extends BaseActivity implements
		OnViewChangeListener, OnClickListener {

	private MyScrollLayout mScrollLayout;
	private ImageView[] mImageViews;
	private int mViewCount;
	private int mCurSel;
	private String type;
	private Bitmap b1;
	private Bitmap b2;
	private Bitmap b3;
	private Bitmap b4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpage);
		init();
		findViewById(R.id.frame4).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (constants.switType == 1) {
					 Intent intent = new Intent(SwitchViewDemoActivity.this,
					 LoginActivity.class);
					 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					 startActivity(intent);
					finish();
				}
//				else if (constants.switType == 2) {
//					Intent intent = new Intent(SwitchViewDemoActivity.this,
//							AboutActivity.class);
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//					finish();
//				}
			else if (constants.switType == 4) {
					finish();
				} else {
					Intent intent = new Intent(SwitchViewDemoActivity.this,
							StartActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	private void init() {
		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);

		b1 = readBitMap(this, R.drawable.welcome1);
		b2 = readBitMap(this, R.drawable.welcome2);
		b3 = readBitMap(this, R.drawable.welcome3);
		b4 = readBitMap(this, R.drawable.welcome4);
		findViewById(R.id.frame1).setBackgroundDrawable(new BitmapDrawable(b1));
		findViewById(R.id.frame2).setBackgroundDrawable(new BitmapDrawable(b2));
		findViewById(R.id.frame3).setBackgroundDrawable(new BitmapDrawable(b3));
		findViewById(R.id.frame4).setBackgroundDrawable(new BitmapDrawable(b4));

		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout);
		mViewCount = mScrollLayout.getChildCount();
		mImageViews = new ImageView[mViewCount];
		for (int i = 0; i < mViewCount; i++) {
			mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
			mImageViews[i].setEnabled(true);
			mImageViews[i].setOnClickListener(this);
			mImageViews[i].setTag(i);
		}
		mCurSel = 0;
		mImageViews[mCurSel].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);
	}

	private void setCurPoint(int index) {
		if (index < 0 || index > mViewCount - 1 || mCurSel == index) {
			return;
		}
		mImageViews[mCurSel].setEnabled(true);
		mImageViews[index].setEnabled(false);
		mCurSel = index;
	}

	@Override
	public void OnViewChange(int view) {
		setCurPoint(view);
	}

	@Override
	public void onClick(View v) {
		int pos = (Integer) (v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}

	public Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (b1 != null && !b1.isRecycled()) {
			b1.recycle();
		}
		if (b2 != null && !b2.isRecycled()) {
			b2.recycle();
		}
		if (b3 != null && !b3.isRecycled()) {
			b3.recycle();
		}
		if (b4 != null && !b4.isRecycled()) {
			b4.recycle();
		}
	}
}