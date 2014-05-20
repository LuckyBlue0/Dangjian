package com.do1.aqzhdj.activity.bbs.message;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.ValidUtil;

/**
 * 发表留言
 * 
 * @author Yan Fangqin
 * @Date Aug 28, 2013 thzhd
 */
public class MessageSendActivity extends BaseActivity {

	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_send);
		aq = new AQuery(this);
		context = this;
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", "发表留言",R.drawable.btn_head_2, "提交", null, this);
		
		initItems();
	}

	public void initItems() {
		if(!"".equals(constants.userInfo.getUserName())){
			aq.id(R.id.personName).text(constants.userInfo.getUserName());
		}
		if(!"".equals(constants.userInfo.getContactAddr())){
			aq.id(R.id.lianxidizhi).text(constants.userInfo.getContactAddr());
		}
		if(!"".equals(constants.userInfo.getMobile())){
			aq.id(R.id.lianxifangshi).text(constants.userInfo.getMobile());
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			if(valid()){
				submit();
			}
			break;
		}
	}
	
	public void submit(){
		String url = SERVER_URL + getString(R.string.submit_message);
		Map<String, String> map = new HashMap<String, String>();
		map.put("Title", aq.id(R.id.title).getText().toString().trim());
		map.put("Content", aq.id(R.id.content).getText().toString().trim());
		map.put("userID", constants.userInfo.getUserId());
		map.put("phone", aq.id(R.id.lianxifangshi).getText().toString().trim());
		map.put("Address", aq.id(R.id.lianxidizhi).getText().toString().trim());
		doRequest(1, url, map);
	}
	
	public boolean valid(){
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.title).getText().toString())){
			ToastUtil.showShortMsg(context, "请输入标题");
			return false;
		}
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.content).getText().toString())){
			ToastUtil.showShortMsg(context, "请输入留言内容");
			return false;
		}
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.personName).getText().toString())){
			ToastUtil.showShortMsg(context, "请输入姓名");
			return false;
		}
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.lianxifangshi).getText().toString())){
			ToastUtil.showShortMsg(context, "请输入联系方式");
			return false;
		}
		if(ValidUtil.isNullOrEmpty(aq.id(R.id.lianxidizhi).getText().toString())){
			ToastUtil.showShortMsg(context, "请输入地址");
			return false;
		}
		return true;
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		AppManager.needFlesh = true;
		finish();
	}
}
