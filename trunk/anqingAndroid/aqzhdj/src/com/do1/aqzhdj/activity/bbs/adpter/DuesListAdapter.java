package com.do1.aqzhdj.activity.bbs.adpter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 优秀党员评选listAdapter
 * 
 * @author LHQ
 * 
 */
public class DuesListAdapter extends BaseAdapter {

	private Context ctx;
	private List<Map<String, Object>> listMap;
	protected LayoutInflater mInflater;
	private int itemTemplateId;

	public DuesListAdapter(Context ctx, List<Map<String, Object>> lsmap, int itemTemplateId) {
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
		return listMap.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return parent;
//		if (convertView == null) {
//			convertView = mInflater.inflate(itemTemplateId, null);
//		}
////		AQuery mAq = new AQuery(convertView);
//		mAq.id(R.id.ico).image(R.drawable.tiezi_icon9);//TODO 临时图片演示
//		mAq.id(R.id.title).text(listMap.get(position).get("type_title").toString());
//		mAq.id(R.id.number).text(listMap.get(position).get("type_number").toString());
//
//		boolean mStatus = "进行中".equals(listMap.get(position).get("type_status"));//获取投票状态
//		int mColor = Color.parseColor(mStatus?"#AC6F0A":"#919190");//TODO 每次都颜色解析！需优化
//		mAq.id(R.id.status).text(listMap.get(position).get("type_status").toString()).textColor(mColor);
//		return convertView;
	} 

}
