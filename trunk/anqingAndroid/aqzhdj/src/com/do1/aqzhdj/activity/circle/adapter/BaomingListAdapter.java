package com.do1.aqzhdj.activity.circle.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;

public class BaomingListAdapter extends BaseAdapter {
	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;

	private AQuery aq = null;

	public BaomingListAdapter(Context ctx,
			List<Map<String, Object>> lsmap, int itemTemplateId) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.listMap = lsmap;
		mInflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return listMap.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(itemTemplateId, null);
		}
		aq = new AQuery(convertView);
		aq.id(R.id.memberImage).image(
				listMap.get(position).get("user_head").toString(), true, true,
				0, R.drawable.logo_bg);
		aq.id(R.id.name).text(listMap.get(position).get("cname").toString());

		int type = Integer
				.valueOf(listMap.get(position).get("user_type").toString());
		switch (type) {
		case 1:
			aq.id(R.id.memberIcon).visible();
			break;

		case 2:
			aq.id(R.id.memberIcon).gone();
			break;

		default:
			break;
		}

		return convertView;
	}
	
}
