package com.do1.aqzhdj.activity.mine.my;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class GuanaiActivity extends BaseActivity{
	
	private String relId;
	private String todoId;
	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guanai);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"待办详情", 0, "", null, null);
		
		aq.id(R.id.know).getButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GuanaiActivity.this.finish();
			}
		});
		
		todoId = getIntent().getStringExtra("todo_id");
		relId = getIntent().getStringExtra("rel_id");
		type = getIntent().getStringExtra("type");
		Log.e(todoId+relId+type);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("user_id", constants.userInfo.getUserId());
		map.put("todo_id", todoId);
		map.put("todo_rel_id", relId);
		request(1,"GetMyTaskDetailEx.aspx",map);
	}
	
	private void request(int requestId,String urlFomat,Map<String, Object> map){
		try {
			String url = Constants.SERVER_URL + urlFomat;
			String param = Entryption.encode(toJsonString(map));
			setProgressMode(3);
			doRequestPostString(requestId, url, param);
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
		Map<String, Object> map = resultObject.getDataMap();
		aq.id(R.id.guanai_title).text(map.get("title").toString());
		aq.id(R.id.mytodo_type).text(map.get("type").toString());
		aq.id(R.id.mytodo_starttime).text(map.get("send_time").toString());
		
		aq.id(R.id.mytodo_qiuzhu_people_c).text(map.get("help_person").toString());
		aq.id(R.id.mytodo_qiuzhu_type_c).text(map.get("help_type").toString());
		aq.id(R.id.mytodo_qiuzhu_count_c).text(map.get("help_count").toString());
		aq.id(R.id.mytodo_qiuzhu_money_c).text(map.get("help_name").toString());
		aq.id(R.id.mytodo_qiuzhu_time_c).text(map.get("help_expiry_date").toString());
		aq.id(R.id.mytodo_qiuzhu_detail_c).text(map.get("help_content").toString());
		
		aq.id(R.id.mytodo_juanzhu_people_c).text(map.get("offer_person").toString());
		aq.id(R.id.mytodo_juanzhu_type_c).text(map.get("offer_type").toString());
		aq.id(R.id.mytodo_juanzhu_money_c).text(map.get("offer_name").toString());
		aq.id(R.id.mytodo_juanzhu_count_c).text(map.get("offer_count").toString());
		aq.id(R.id.mytodo_juanzhu_time_c).text(map.get("offer_expiry_date").toString());
		aq.id(R.id.mytodo_juanzhu_detail_c).text(map.get("offer_content").toString());
	}
}
