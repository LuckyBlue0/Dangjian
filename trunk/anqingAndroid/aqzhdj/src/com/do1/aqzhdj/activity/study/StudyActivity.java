package com.do1.aqzhdj.activity.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.TestIndexActivity;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.activity.study.cunguang.CunguangActivity;
import com.do1.aqzhdj.activity.study.gongkao.ZhaoluActivity;
import com.do1.aqzhdj.activity.study.xietong.XietongActivity;
import com.do1.aqzhdj.activity.study.xietong.XietongLoginActivity;
import com.do1.aqzhdj.activity.study.yuancheng.YuanchengActivity;

public class StudyActivity extends BaseActivity {
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study);
		setHeadView(findViewById(R.id.headLayout), 0, "", "移动办公", 0, "", this,
				this);
		ListenerHelper.bindOnCLickListener(this, this, R.id.xuexiceshi,
				R.id.wangshangdangxiao, R.id.zaixiankecheng, R.id.dianzishuji,
				R.id.yuancheng, R.id.cunguang, R.id.gongkao, R.id.xietong);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.xuexiceshi:
			intent = new Intent(this, TestIndexActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.wangshangdangxiao:
			Toast.makeText(this, "此模块正在建设中...", 0).show();
			break;

		case R.id.zaixiankecheng:
			Toast.makeText(this, "此模块正在建设中...", 0).show();
			break;
			//远程管理
		case R.id.yuancheng:
//			Toast.makeText(this, "此模块正在建设中...", 0).show();
			intent=new Intent(this,YuanchengActivity.class);
			startActivity(intent);
			break;
			//村官
		case R.id.cunguang:
//			Toast.makeText(this, "此模块正在建设中...", 0).show();
			intent=new Intent(this,CunguangActivity.class);
			startActivity(intent);
			break;
			//公考招录
		case R.id.gongkao:
//			Toast.makeText(this, "此模块正在建设中...", 0).show();
			intent=new Intent(this,ZhaoluActivity.class);
			startActivity(intent);
			break;
			//协同办公
		case R.id.xietong:
//			Toast.makeText(this, "此模块正在建设中...", 0).show();
			intent=new Intent(this,XietongLoginActivity.class);
			startActivity(intent);
			break;

		case R.id.dianzishuji:
			Toast.makeText(this, "此模块正在建设中...", 0).show();
			break;

		}
	}

}
