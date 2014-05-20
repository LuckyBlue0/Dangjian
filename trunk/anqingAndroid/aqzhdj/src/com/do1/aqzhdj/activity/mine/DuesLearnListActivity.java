package com.do1.aqzhdj.activity.mine;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.com.do1.component.parse.ResultObject;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.common.WapViewActivity2;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class DuesLearnListActivity extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dues_learn_list);
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "书籍阅览", 0, "", this, null);
		findViewById(R.id.xuexi).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				fillData();
			}
		});
	}
	
	public void fillData(){
		String url = SERVER_URL + getString(R.string.yun_study);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", constants.userInfo.getUserId());
		map.put("user_name", constants.userInfo.getUserName());
		doRequest(1, url, map);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		
		Intent intent = new Intent(this,WapViewActivity2.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("url", resultObject.getDataMap().get("wap_url")+"");
		intent.putExtra("title", "云学习中心");
		startActivity(intent);
	}

}
