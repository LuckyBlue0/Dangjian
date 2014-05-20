package com.do1.aqzhdj.activity.bbs.adpter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;

public class OfficeListAdapter extends BaseAdapter {
	private Context ctx;
	private List<Map<String, Object>> mListMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	private AQuery mAQuery;

	public OfficeListAdapter(Context ctx, List<Map<String, Object>> lsmap, int itemTemplateId) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.mListMap = lsmap;
		mInflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return mListMap.size();
	}

	@Override
	public Object getItem(int position) {
		return mListMap.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public boolean isEnabled(int position) {
		return Boolean.parseBoolean(mListMap.get(position).get("type_status").toString());
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(itemTemplateId, null);
		}
//		mAQuery = new AQuery(convertView);
//		mAQuery.id(R.id.office_title).text(mListMap.get(position).get("type_title").toString());
//		mAQuery.id(R.id.office_start_time).text(mListMap.get(position).get("type_start_time").toString());
//		mAQuery.id(R.id.office_stop_time).text(mListMap.get(position).get("type_stop_time").toString());
//		mAQuery.id(R.id.office_vote_status).enabled(isEnabled(position));
		return convertView;
	}
}
