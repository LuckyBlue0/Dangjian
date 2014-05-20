package com.do1.aqzhdj.activity.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.com.do1.component.parse.ResultObject;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.DuesBbsNextListActivity;
import com.do1.aqzhdj.activity.bbs.adpter.DuesListAdapter;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.info.UserInfo;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class DuesBbsListActivity extends BaseActivity implements OnItemClickListener{
	
	public static List<Map<String, Object>> mlistMap = new ArrayList<Map<String,Object>>();
	private ListView listView;
	private DuesListAdapter adapter;
	
	private String id;
	private String title;
	private int type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_bbs_list);
		
		init();
		request();
	}
	
	private void init() {
		id = getIntent().getExtras().getString("id");
		title = getIntent().getExtras().getString("title");
		type = getIntent().getExtras().getInt("type");
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", title, 0, "", this, null);
		
		listView = (ListView) findViewById(R.id.list);
		adapter = new DuesListAdapter(DuesBbsListActivity.this, mlistMap,
				R.layout.dues_bbs_list_item);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
	}
	
	private void request(){
		try {
			String url = Constants.SERVER_URL +"GetPartyForumTypeList.aspx";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parent_type_id", id);
			if(type == 1){
				map.put("branchID", UserInfo.getInstance().getBranchId());
			}
			String param = Entryption.encode(toJsonString(map));
			doRequestPostString(0, url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public String toJsonString(Map<String, Object> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		mlistMap.clear();
		mlistMap.addAll(resultObject.getListMap());
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(constants.userInfo.getUser_type().equals("1") ){
			Intent intent = new Intent(this, DuesBbsNextListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("id", mlistMap.get(position).get("type_id").toString());
			intent.putExtra("title", mlistMap.get(position).get("type_name").toString());
			startActivity(intent);
		}else{
			new AlertDialog.Builder(DuesBbsListActivity.this).setTitle("提示")
			.setMessage("该功能只开发给党员使用,如果您还不是党员,可以联系天河党委申请入党，获取党员权限").setPositiveButton("知道了", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					DuesBbsListActivity.this.finish();
				}
			});
		}
		
	}

}
