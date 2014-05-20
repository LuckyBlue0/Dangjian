package com.do1.aqzhdj.activity.mine;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;

public class TestIndexActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_index);
		
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "学习测试", 0,"", this, null);
		
		String title = getResources().getString(R.string.test_title);
		String content = getResources().getString(R.string.test_content);
		
//		int isTest = Integer.valueOf(constants.userInfo.getIs_can_test());
//		if(isTest == 1){
//			
//		}else if(isTest == 0){
//			final AlertDialog dlg = new AlertDialog.Builder(this).create();
//			dlg.show();
//			Window window = dlg.getWindow();
//			window.setContentView(R.layout.dialog);
//			Button bt = (Button) window.findViewById(R.id.dialog_button_cancel);
//			bt.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					dlg.cancel();
//					TestIndexActivity.this.finish();
//				}
//			});

//		}
		
		Button bt1 = (Button) findViewById(R.id.enter);
		bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TestIndexActivity.this, DuesTestActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
	}
	

}
