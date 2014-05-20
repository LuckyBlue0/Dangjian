package com.do1.aqzhdj.activity.bbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.bbs.message.MessageListActivity;
import com.do1.aqzhdj.activity.bbs.wall.WallIndexActivity;
import com.do1.aqzhdj.activity.bbs.zhitongche.ZhitongcheMainActivity;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Listenertool;

public class BBSindexActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bbs_index);
		
		setHeadView(findViewById(R.id.headLayout), 0,"", "互动", 0,"", this, this);
		int[] resourceIds = {R.id.deusBbs,R.id.liuYingQiang,R.id.messageLayout};
		Listenertool.bindOnCLickListener(this, this, resourceIds);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.deusBbs:
			//党员账户，有权限使用论坛，群众帐号没有使用论坛的权限
			if("1".equals(constants.userInfo.getUser_type())){
				intent = new Intent(this, BBStypeListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}else{
				ToastUtil.showLongMsg(BBSindexActivity.this, "您不是党员用户，暂时无法访问！");
			}
			break;
			
		case R.id.liuYingQiang:
			if("1".equals(constants.userInfo.getUser_type())){
				intent = new Intent(this, WallIndexActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}else{
				ToastUtil.showLongMsg(BBSindexActivity.this, "您不是党员用户，暂时无法访问！");
			}
			break;
			
		case R.id.messageLayout:
//			if("1".equals(constants.userInfo.getUser_type())){
				intent = new Intent(this, ZhitongcheMainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
//			}else{
//				ToastUtil.showLongMsg(BBSindexActivity.this, "您不是党员用户，暂时无法访问！");
//			}
			break;

		default:
			break;
		}
	}
	

}
