package com.do1.aqzhdj.activity.bbs.adpter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.BBSPartyerVoteActivity;

public class PersonAdapter extends BaseAdapter implements OnClickListener{
	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	private AQuery mAQuery;
	private Handler mHandler;

	public PersonAdapter(Context ctx, List<Map<String, Object>> lsmap, int itemTemplateId,Handler handler) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.listMap = lsmap;
		this.mHandler = handler;
		mInflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return listMap.size();
	}

	@Override
	public Object getItem(int position) {
		return listMap.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(itemTemplateId, null);
			convertView.findViewById(R.id.person_frame).setOnClickListener(this);
			convertView.findViewById(R.id.person_name).setOnClickListener(this);
		}
		mAQuery = new AQuery(convertView);
//		mAq.id(R.id.person_img).image(listMap.get(position).get("type_person_img").toString());
		mAQuery.id(R.id.person_img).image(R.drawable.tiezi_icon9);//TODO 临时图片演示
		mAQuery.id(R.id.person_name).text(listMap.get(position).get("type_person_name").toString());
		return convertView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.person_frame :
//				mHandler.sendMessage(mHandler.obtainMessage(BBSVoteActivity.MSG_SELECT_PERSON, v));
				break;
			case R.id.person_name :
//				mHandler.sendMessage(mHandler.obtainMessage(BBSVoteActivity.MSG_SHOW_PERSON,v));
				break;
			default :
				break;
		}
		
	}

}
