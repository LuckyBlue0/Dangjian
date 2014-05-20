package com.do1.aqzhdj.activity.bbs.adpter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;

public class DuesNextListAdapter extends BaseAdapter{
	
	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	
	public DuesNextListAdapter(Context ctx, List<Map<String, Object>> lsmap,int itemTemplateId) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(itemTemplateId, null);
		}
		AQuery mAq = new AQuery(convertView);
		
		mAq.id(R.id.tieziId).text(listMap.get(position).get("title").toString());
		mAq.id(R.id.tieziName).text(listMap.get(position).get("creater").toString());
		
		String views = listMap.get(position).get("views").toString();
		int viewsCount = Integer.valueOf(views);
		if(viewsCount > 100){
			views = String.valueOf(viewsCount)+"+";
		}
		
		String replys = listMap.get(position).get("replys").toString();
		int replysCount = Integer.valueOf(replys);
		if(replysCount > 100){
			replys = String.valueOf(replysCount)+"+";
		}
		
		mAq.id(R.id.checkCount).text(Html.fromHtml("查看(" + "<font color=\"#c90100\">" + views + "</font>" + ")"));
		mAq.id(R.id.requestCount).text(Html.fromHtml("回复(" + "<font color=\"#c90100\">" + replys + "</font>" + ")"));
		mAq.id(R.id.sendTime).text(listMap.get(position).get("publish_time").toString());
		
		return convertView;
	}


}
