package com.do1.aqzhdj.activity.circle;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;

public class GuanggaoDetailActivty extends BaseActivity{
	
	private TextView titleText,timeText,addressText,contentText;
	
	private String id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guanggao_detail);
		
		id = getIntent().getStringExtra("ad_id");
		
		init();
		request();
	}
	
	private void init() {
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"内容详情", 0, "", this, this);
		
		titleText = aq.id(R.id.activityName).getTextView();
		timeText = aq.id(R.id.activityTime).getTextView();
		addressText = aq.id(R.id.activityAddress).getTextView();
		contentText = aq.id(R.id.activityContent).getTextView();
	}

	private void request() {
		try {
			String url = Constants.SERVER_URL + "GetCircleAdvertDetail.aspx";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ad_id",id);
			String param = Entryption.encode(xxtoJsonString(map));
			doRequestPostString(1, url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public String xxtoJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		Map<String, Object> map = resultObject.getDataMap();
		titleText.setText(map.get("name").toString());
		timeText.setText(map.get("time_start").toString()+"至"+map.get("time_end").toString());
		addressText.setText(map.get("address").toString());
		contentText.setText(map.get("content").toString());
		
		aq.id(R.id.imageDetail).image(map.get("photo_main").toString(), true, true, 0, 0);
		
	}
	

}
