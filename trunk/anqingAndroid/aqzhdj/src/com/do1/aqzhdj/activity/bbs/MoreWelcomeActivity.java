package com.do1.aqzhdj.activity.bbs;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.adpter.MyViewPagerAdapter;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class MoreWelcomeActivity extends BaseActivity {
	

	private View view1, view2, view3, view4;// 需要滑动的view对象
	private ViewPager viewPager;// viewpager
	private List<View> viewList;// 把需要滑动的view添加到这个list中

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_welcome_viewpager);
		init();
	}

	private void init() {
		ViewPager viewpage = (ViewPager) findViewById(R.id.viewpager);
		view1 = View.inflate(this, R.layout.more_welcome_imageview, null);
		view2 = View.inflate(this, R.layout.more_welcome_imageview, null);
		view3 = View.inflate(this, R.layout.more_welcome_imageview, null);
		view4 = View.inflate(this, R.layout.more_welcome_imageview, null);

		((ImageView) view1.findViewById(R.id.welcome_image))
				.setImageDrawable(getResources()
						.getDrawable(R.drawable.guide_1));
		
		((ImageView) view2.findViewById(R.id.welcome_image))
				.setImageDrawable(getResources()
						.getDrawable(R.drawable.guide_2));
		
		((ImageView) view3.findViewById(R.id.welcome_image))
				.setImageDrawable(getResources()
						.getDrawable(R.drawable.guide_3));
		
		((ImageView) view4.findViewById(R.id.welcome_image))
				.setImageDrawable(getResources()
						.getDrawable(R.drawable.guide_4));

		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		viewList.add(view4);
		viewpage.setAdapter(new MyViewPagerAdapter(viewList));
	}

}
