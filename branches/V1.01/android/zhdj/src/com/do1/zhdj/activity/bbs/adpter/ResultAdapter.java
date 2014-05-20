package com.do1.zhdj.activity.bbs.adpter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

public class ResultAdapter extends BaseAdapter {
	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	private AQuery mAQuery;
	private Handler mHandler;

	public ResultAdapter(Context ctx, List<Map<String, Object>> lsmap, int itemTemplateId, Handler handler) {
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
		}
		// mAQuery = new AQuery(convertView);
		// mAq.id(R.id.person_img).image(listMap.get(position).get("type_person_img").toString());
		// mAQuery.id(R.id.person_img).image(R.drawable.tiezi_icon9);//TODO
		// 临时图片演示
		// mAQuery.id(R.id.person_name).text(listMap.get(position).get("type_person_name").toString());
		return convertView;
	}

}
