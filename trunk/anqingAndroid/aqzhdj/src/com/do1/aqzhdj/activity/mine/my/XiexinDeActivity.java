package com.do1.aqzhdj.activity.mine.my;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.StringUtil;

public class XiexinDeActivity extends BaseActivity {

	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiexinde);
		// 0 心语  1 心得
		if("1".equals(getIntent().getStringExtra("type"))){
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
					"", "写心得", R.drawable.btn_head_4, "提交", null, null);
		}else if("0".equals(getIntent().getStringExtra("type"))){
			setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
					"", "写心语", R.drawable.btn_head_4, "提交", null, null);
		}
		System.out.println("title: " + getIntent().getStringExtra("title"));
		aq.find(R.id.title).getTextView().setText(getIntent().getStringExtra("title"));
		id=getIntent().getStringExtra("id");
		ListenerHelper.bindOnCLickListener(this, this, R.id.rightImage);
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			if(aq.find(R.id.editxinde).getText().length()>=70){
			request();
			}else {
				Toast.makeText(this, "输入心得体会不能少于70个字", 1).show();
			}
			break;

		default:
			break;
		}
	}



	public void request() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("type", getIntent().getStringExtra("type"));
		map.put("activityId", getIntent().getStringExtra("id"));
		map.put("userId", constants.userInfo.getUserId());
		map.put("source", 1);
		map.put("content", aq.find(R.id.editxinde).getText());
		String url = Constants.SERVER_RUL2
				+ getResources().getString(R.string.xiexinde);
		// doRequestPostString(1, url, StringUtil.jiami(map));
		doRequest(1, url, StringUtil.jiami(map));

	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		Toast.makeText(this, "提交成功", 1).show();
		finish();

	}

}
