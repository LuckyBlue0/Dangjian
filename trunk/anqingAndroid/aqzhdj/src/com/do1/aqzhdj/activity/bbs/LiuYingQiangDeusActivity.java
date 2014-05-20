package com.do1.aqzhdj.activity.bbs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Listenertool;

public class LiuYingQiangDeusActivity extends BaseActivity implements
		OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liuyingqiang_deus_activity);

		int[] resourceIds = { R.id.bt_icon };
		Listenertool.bindOnCLickListener(this, this, resourceIds);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_icon:
			String[] type = { "党员活动", "民情连线", "逛遍天河" };
			new AlertDialog.Builder(this).setTitle("留影墙类别")
					.setItems(type, this).setNegativeButton("确定", null).show();

			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case 0:

			break;

		case 1:

			break;

		case 2:

			break;

		default:
			break;
		}
	}

}
