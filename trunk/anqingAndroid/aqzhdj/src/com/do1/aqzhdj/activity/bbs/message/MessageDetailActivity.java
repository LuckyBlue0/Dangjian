package com.do1.aqzhdj.activity.bbs.message;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

/**
 * 留言详情
 * @author Yan Fangqin
 * @Date Aug 28, 2013
 * thzhd
 */
public class MessageDetailActivity extends BaseActivity{

	private String MessageID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_detail);
		
		aq = new AQuery(this);
		MessageID = getIntent().getStringExtra("MessageID") != null ? getIntent().getStringExtra("MessageID") : "";
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "留言详情",0, "", null, null);
		
		fillData();
	}
	
	public void initItems(){
		
	}
	
	public void fillData(){
		String url = SERVER_URL + getString(R.string.get_message_detail);
		Map<String, String> map = new HashMap<String, String>();
		map.put("MessageID", MessageID);
		doRequest(1, url, map);
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		
		Map<String, Object> map = resultObject.getDataMap();
		aq.id(R.id.send_name).text(map.get("username")+"");
		aq.id(R.id.send_time).text("留言时间：" + map.get("CreateTime")+"");
		aq.id(R.id.send_content).text(map.get("Content")+"");
		
		if("".equals(map.get("Reply_info")+"")){
			aq.id(R.id.replyLayout).gone();
		}else{
			aq.id(R.id.replyLayout).visible();
			Map<String, Object> reply = JsonUtil.json2Map(map.get("Reply_info")+"");
			aq.id(R.id.reply_name).text(reply.get("ReplyUser")+"");
			aq.id(R.id.reply_time).text("回复时间：" + reply.get("ReplyerTime")+"");
			aq.id(R.id.reply_content).text(reply.get("ReplyContent")+"");
		}
	}
}
