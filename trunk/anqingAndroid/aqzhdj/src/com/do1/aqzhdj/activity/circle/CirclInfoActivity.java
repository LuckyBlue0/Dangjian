package com.do1.aqzhdj.activity.circle;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.Entryption;
import com.do1.aqzhdj.util.Listenertool;

public class CirclInfoActivity extends BaseActivity {

	private Button btQuite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_info);

		initView();
		init();
	}

	private void initView() {
		btQuite = aq.id(R.id.quite).getButton();
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"群资料", 0, "", this, this);

		aq.id(R.id.circleName).text(Constants.circleInfo.getName());
		aq.id(R.id.circleTitle).text(Constants.circleInfo.getLabels());
		aq.id(R.id.ownName).text(Constants.circleInfo.getCreateUserName());
		if (null == Constants.circleInfo.getInfo()
				|| "".equals(Constants.circleInfo.getInfo())) {
			aq.id(R.id.circleContent).text("暂无介绍");
		} else {
			aq.id(R.id.circleContent).text(Constants.circleInfo.getInfo());
		}

		// HorizontalScrollView scoll = (HorizontalScrollView)
		// findViewById(R.id.scroll);
		// scoll.setVerticalScrollBarEnabled(true);
		Log.e(Constants.circleInfo.getCircletype());
		int circleSuppalType = Integer.valueOf(Constants.circleInfo.getCircletype());
		if (circleSuppalType == 2) {
//			aq.id(R.id.supportCount).text(
//					Html.fromHtml("申请审核" + "<font color=\"#c90100\">("
//							+ Constants.circleInfo.getApplyNum() 
//							+ ")"+ "</font>"));
			btQuite.setText("解散该群");
		} else {
			aq.id(R.id.suppal).gone();
			aq.id(R.id.circleActivityCount).gone();
			if (circleSuppalType == 0) {
				btQuite.setVisibility(View.GONE);
			} else if (circleSuppalType == 1) {
				btQuite.setVisibility(View.VISIBLE);
				btQuite.setText("退出该群");
			}
		}

		int[] resourceIds = { R.id.checkCircleMember, R.id.circleActivityCount,
				R.id.suppal, R.id.quite };
		Listenertool.bindOnCLickListener(this, this, resourceIds);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		Intent intent;
		switch (v.getId()) {
		case R.id.checkCircleMember:
			intent = new Intent(this, CirclMemberListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		case R.id.circleActivityCount:
			intent = new Intent(this, CircleCountActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		case R.id.suppal:
			intent = new Intent(this, ManagementCircleMemberListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, 1);
			break;

		case R.id.quite:
			String content = btQuite.getText().toString();
			if (content.equals("解散该群")) {
				new AlertDialog.Builder(CirclInfoActivity.this).setTitle("解散该群")
						.setMessage("确定解散？").setPositiveButton("确认", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								request("DeleteMSCircle.aspx");
								dialog.dismiss();
							}
						})
						.setNegativeButton("取消", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).show();
			} else if (content.equals("退出该群")) {
				new AlertDialog.Builder(CirclInfoActivity.this).setTitle("退出该群")
				.setMessage("确定退出？").setPositiveButton("确认", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						request("QuitMSCircle.aspx");
						dialog.dismiss();
					}
				})
				.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
			}
			break;

		default:
			break;
		}
	}

	private void request(String urlFomat) {
		try {
			String url = Constants.SERVER_URL + urlFomat;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", constants.userInfo.getUserId());
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
		String content = btQuite.getText().toString();
		if (content.equals("解散该群")) {
			ToastUtil.showShortMsg(this, "解散成功");
		} else if (content.equals("退出该群")) {
			ToastUtil.showShortMsg(this, "退出成功");
		}
//		Intent intent = new Intent(CirclInfoActivity.this, MyCircleActivity.class);
//		startActivity(intent);
		AppManager.needFreshForCircleClass = true;
		finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
//			aq.id(R.id.supportCount).text(
//					Html.fromHtml("申请审核(" + "<font color=\"#c90100\">"
//							+ Constants.circleInfo.getApplyNum() 
//							+ ")"+ "</font>"));
			break;

		default:
			break;
		}
	}
}
