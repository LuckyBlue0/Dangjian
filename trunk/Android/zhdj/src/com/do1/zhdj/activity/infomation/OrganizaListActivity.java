package com.do1.zhdj.activity.infomation;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.common.WapViewActivity;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.zhdj.util.ValidUtil;

/**
 * 组织风采列表接口
 * auth:YanFangqin
 * data:2013-4-22
 * thzhd
 */
public class OrganizaListActivity extends BaseListActivity implements ItemViewHandler{

	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = this;
		setCusItemViewHandler(this);
		String[] keys = new String[]{"title","branch_name","publish_time","push_time","category"};
        int[] ids = new int[]{R.id.title,R.id.author,R.id.date,R.id.sendTime,R.id.type};
        Map<String, Object> map = new HashMap<String, Object>();
        setAdapterParams(keys,ids,R.layout.notice_item3,map);
	}
	
	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"","组织风采", 0,"", null, null);
	}
	
	@Override
	public void setRequestMethod() {
		method = getString(R.string.organiza_list);
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		TextView text = (TextView) itemView.findViewById(R.id.sendTime);
		if("1".equals(mSlpControll.getmListData().get(position).get("isfirst").toString())){
			itemView.findViewById(R.id.isTop).setVisibility(View.VISIBLE);
		}else{
			itemView.findViewById(R.id.isTop).setVisibility(View.GONE);
		}
		text.setText(timeToDate(mSlpControll.getmListData().get(position).get("push_time").toString()));
		if(!ValidUtil.isNullOrEmpty(mSlpControll.getmListData().get(position).get("category").toString())){
			Spanned s = Html.fromHtml("<font color='#ab6d07'>[" + mSlpControll.getmListData().get(position).get("category").toString() + 
					   "]</font>" + mSlpControll.getmListData().get(position).get("title").toString());
			((TextView)itemView.findViewById(R.id.title)).setText(s);
		}
	}
	
	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		
		Intent intent = new Intent(context,WapViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("url", mSlpControll.getmListData().get(position).get("activity_note_url")+"");
		intent.putExtra("title", "组织风采详情");
		startActivity(intent);
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		
		Intent intent = new Intent(context,WapViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("url", resultObject.getDataMap().get("content_url")+"");
		intent.putExtra("title", "组织风采详情");
		startActivity(intent);
	}

}
