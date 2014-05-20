package com.do1.aqzhdj.activity.mine.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.IndexActivity;
import com.do1.aqzhdj.activity.circle.widget.FlingGallery;
import com.do1.aqzhdj.activity.circle.widget.FlingGallery.OnGalleryChangeListener;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class BirthdayActivity extends BaseActivity {

	private AQuery aq = new AQuery(this);

	private FlingGallery mFlingGallery;
//	private PageIndicator mPageIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.birthday);
		init();
	}

	private void init() {
//		int[] onclickListenerIds = { R.id.bt_toIndex };
//		Listenertool.bindOnCLickListener(this, this, onclickListenerIds);

		mFlingGallery = (FlingGallery) findViewById(R.id.flingGallery);
//		mPageIndicator = (PageIndicator) findViewById(R.id.page_indicator);
		mFlingGallery.setAdapter(mBaseAdapter);
		mFlingGallery.setIsGalleryCircular(false);
		mFlingGallery.setGalleryChangeListener(new OnGalleryChangeListener() {
			public void onGalleryChange(int currentItem) {
				if(currentItem == constants.userInfo.getBirthdayMap().size()){
					Intent intent = new Intent(BirthdayActivity.this, IndexActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
					return;
				}
//				mPageIndicator.setActiveDot(currentItem);
//				mPageIndicator.setVisibility(View.GONE);
			}
		});
	}

//	public void onClick(View v) {
//		Intent intent;
//		switch (v.getId()) {
//		case R.id.bt_toIndex:
//			intent = new Intent(this, IndexActivity.class);
//			startActivity(intent);
//			finish();
//			break;
//
//		default:
//			break;
//		}
//	};

	private BaseAdapter mBaseAdapter = new BaseAdapter() {
		
	private	int birthdaytype = 1; 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(BirthdayActivity.this)
						.inflate(R.layout.birthday_item, null);
			}
			if(position > (constants.userInfo.getBirthdayMap().size()-1)){
				return convertView;
			}
			birthdaytype = Integer.valueOf(constants.userInfo.getBirthdayMap().get(position).get("birthday_type").toString());
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.itemImage);
			TextView tx = (TextView) convertView.findViewById(R.id.joinText);
			TextView tx2 = (TextView) convertView.findViewById(R.id.joinText2);
			if(birthdaytype == 1){
				aq.id(imageView).getImageView().setBackgroundResource(R.drawable.dues_birthday);
				aq.id(imageView).image(
						constants.userInfo.getBirthdayMap().get(position)
								.get("img_url").toString().trim(), true, true,200,R.drawable.dues_birthday);
			}else if(birthdaytype == 2){
				aq.id(imageView).getImageView().setBackgroundResource(R.drawable.person_birthday);
				aq.id(imageView).image(
						constants.userInfo.getBirthdayMap().get(position)
								.get("img_url").toString().trim(), true, true,200,R.drawable.person_birthday);
			}else if(birthdaytype == 3){
				tx.setVisibility(View.VISIBLE);
				tx.setText(Html.fromHtml("&nbsp;&nbsp;"+"<font color=\"#FF0000\"><b>" + constants.userInfo.getUserName() + "</b></font>"+getString(R.string.join_dues)));
				tx2.setVisibility(View.VISIBLE); 
				tx2.setText(getString(R.string.join_dues2));
				aq.id(imageView).getImageView().setBackgroundResource(R.drawable.join_dues_birthday);
				aq.id(imageView).image(
						constants.userInfo.getBirthdayMap().get(position)
								.get("img_url").toString().trim(), true, true,200,R.drawable.join_dues_birthday);
			}
			
			
//			mPageIndicator.setDotCount(getCount());
			return convertView;
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public int getCount() {
			return constants.userInfo.getBirthdayMap().size()+1;
		}
	};

}
