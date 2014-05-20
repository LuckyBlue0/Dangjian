package com.do1.aqzhdj.activity.circle;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import cn.com.do1.component.parse.ResultObject;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class CircleCountActivity extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_count);

		init();
		request();
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"群活动统计", 0, "", this, this);
		aq.id(R.id.circleName).text(Constants.circleInfo.getName());
	}
	
	private void request() {
		try {
			String url = Constants.SERVER_URL + "GetCircleActivityCount.aspx";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("community_id", Constants.circleInfo.getId());
			String param = Entryption.encode(toJsonString(map));
			doRequestPostString(0, url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		aq.id(R.id.activityCount).text(resultObject.getDataMap().get("activity_count").toString());
		aq.id(R.id.count).text(resultObject.getDataMap().get("join_numbers").toString());
	}

}
