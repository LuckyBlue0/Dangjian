package com.do1.aqzhdj.activity.mine.my;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

/**
 * 我的待办首页
 * @author dzp
 *
 */
public class MyToDoIndexActivity extends BaseActivity {
	private RelativeLayout partyMeetingTodo, branchActivityTodo, volActivityTodo, partyCareTodo;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytodo_index);
		context = this;
		
		partyMeetingTodo = (RelativeLayout) this.findViewById(R.id.party_meeting_todo);
		branchActivityTodo = (RelativeLayout) this.findViewById(R.id.branch_todo);
		volActivityTodo = (RelativeLayout) this.findViewById(R.id.vol_activity_todo);
		partyCareTodo = (RelativeLayout) this.findViewById(R.id.party_care_todo);
		
		partyMeetingTodo.setOnClickListener(todoClickListener);
		branchActivityTodo.setOnClickListener(todoClickListener);
		volActivityTodo.setOnClickListener(todoClickListener);
		partyCareTodo.setOnClickListener(todoClickListener);
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "我的待办", 0,"", null, null);
		getTodoCounts();
	}
	
	public void getTodoCounts() {
		String userId = constants.userInfo.getUserId();
		String url = Constants.SERVER_URL + getString(R.string.my_todo_count);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", userId);
		String param;
		try {
			param = Entryption.encode(toJsonString(paramMap));
			doRequestPostString(0, url, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toJsonString(Map<String, Object> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data = resultObject.getDataMap();
		
		String partyMeetingCount = data.get("party_meeting_todos").toString();
		String branchActivityCount = data.get("branch_activity_todos").toString();
//		String volActivityCount = data.get("vol_activity_todos").toString();
		String partyCareCount = data.get("party_care_todos").toString();
		
		TextView txtPartyMeeting = (TextView) this.findViewById(R.id.party_meeting_todo_count);
		TextView txtBranchActivity = (TextView) this.findViewById(R.id.branch_todo_count);
//		TextView txtVolActivity = (TextView) this.findViewById(R.id.vol_activity_todo_count);
		TextView txtPartyCare = (TextView) this.findViewById(R.id.party_care_todo_count);
		
		txtPartyMeeting.setText(Html.fromHtml("(" + "<font color=\"#c90100\">" + partyMeetingCount + "</font>" + ")"));
		txtBranchActivity.setText(Html.fromHtml("(" + "<font color=\"#c90100\">" + branchActivityCount + "</font>" + ")"));
//		txtVolActivity.setText(Html.fromHtml("(" + "<font color=\"#c90100\">" + volActivityCount + "</font>" + ")"));
		txtPartyCare.setText(Html.fromHtml("(" + "<font color=\"#c90100\">" + partyCareCount + "</font>" + ")"));
	}
	
	private OnClickListener todoClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			String type = "";
			String title = "";
			switch (v.getId()) {
			case R.id.party_meeting_todo:
				intent = new Intent(context, MyToDoListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				type = "1";
				title = "三会一课";
				intent.putExtra("type", type);
				intent.putExtra("title", title);
				startActivity(intent);
				break;
			case R.id.branch_todo:
				intent = new Intent(context, MyToDoListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				type = "2";
				title = "支部活动";
				intent.putExtra("type", type);
				intent.putExtra("title", title);
				startActivity(intent);
				break;
			case R.id.vol_activity_todo:
				intent = new Intent(context, MyToDoListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				type = "3";
				title = "志愿活动";
				intent.putExtra("type", type);
				intent.putExtra("title", title);
				startActivity(intent);
				break;
			case R.id.party_care_todo:
				intent = new Intent(context, MyToDoListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				type = "4";
				title = "党内关爱";
				intent.putExtra("type", type);
				intent.putExtra("title", title);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
}
