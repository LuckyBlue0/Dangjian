package com.do1.aqzhdj.activity.bbs.message;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.do1.component.util.ListenerHelper;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;

/**
 * 留言列表
 * @author Yan Fangqin
 * @Date Aug 28, 2013
 * thzhd
 */
public class MessageListActivity extends BaseListActivity implements ItemViewHandler{
	
//	private String status = "0";//回复状态：0：全部；1：已回复；2：未回复
	private String keyword = "";//搜索关键字
	private Context context;
//	private String[] typearray = { "全部", "已回复", "未回复" };
//	private String[] typevalue = { "0", "1", "2" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		aq = new AQuery(this);
		context = this;
		initItems();
		
		setCusItemViewHandler(this);
		String[] keys = new String[]{"Title"};
        int[] ids = new int[]{R.id.title};
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("States", status);
        map.put("keyword", keyword);
        setAdapterParams(keys,ids, R.layout.message_item,map);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if(AppManager.needFlesh){
			AppManager.needFlesh = false;
			doSearch();
		}
	}
	
	public void initItems(){
//		aq.id(R.id.type).text(typearray[0]);
		ListenerHelper.bindOnCLickListener(this, this, R.id.searchbtn,R.id.type);
	}
	
	
	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "留言建议", R.drawable.btn_head_2,"留言", null, this);
	}

	@Override
	public void setRequestMethod() {
		parentResId = R.layout.list_view_for_message;
		method = getString(R.string.get_message_list);
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		//状态
		TextView status = (TextView) itemView.findViewById(R.id.status);
		String s = mSlpControll.getmListData().get(position).get("States").toString();
		status.setText(Html.fromHtml("<font color='#ECA634'>【" + s +"】</font>"));
		//时间
		TextView time = (TextView) itemView.findViewById(R.id.time);
		String t = "回复时间：" + mSlpControll.getmListData().get(position).get("ReplyerTime").toString();
		time.setText(t);
	}
	
	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.itemClick(parent, view, position, id);
		
		Intent intent = new Intent(context,MessageDetailActivity.class);
		intent.putExtra("MessageID", mSlpControll.getmListData().get(position).get("MessageID").toString());
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
			case R.id.searchbtn:
				keyword = aq.id(R.id.keyword).getEditText().getText().toString().trim();
//				if("".equals(keyword)){
//					ToastUtil.showShortMsg(context, "请输入关键字");
//					return;
//				}
				maps.put("keyword", keyword);
				doSearch();
				break;
			case R.id.rightImage:
				Intent intent = new Intent(context,MessageSendActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
//			case R.id.type:
//				new AlertDialog.Builder(this).setTitle("选择回复状态")
//				.setItems(typearray, new OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						status = typevalue[which];
//						aq.id(R.id.type).text(typearray[which]);
//						maps.put("States", status);
//						doSearch();
//					}
//				}).setNegativeButton("取消", null).show();
//				break;
		}
	}

}
