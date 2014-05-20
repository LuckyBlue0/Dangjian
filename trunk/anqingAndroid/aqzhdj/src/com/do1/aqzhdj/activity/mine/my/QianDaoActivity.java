package com.do1.aqzhdj.activity.mine.my;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ListenerHelper;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.StringUtil;

public class QianDaoActivity extends BaseActivity {
	TextView bt = null;
	Intent intent;
	private PopupWindow mPop;
	private View layout;
	TextView txt_nostart;
	LinearLayout layout_qiandao;
	String type;
	Map<String, Object> map;
	String title, createUserName, startTime, addresss, content, joinUser;
	String isstart; // 开展状态 0 未开始 1 进行中 2 已结束
	String headtitle;
	LinearLayout xindelayout = null;
	private String endtime;
	String mettingid;

	// private boolean thread_fag = false;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qiandao);
		xindelayout = (LinearLayout) findViewById(R.id.layout_xinde);
		type = getIntent().getStringExtra("type");
		isstart = getIntent().getStringExtra("isstart");

		aq.find(R.id.title).getTextView()
				.setText(getIntent().getStringExtra("title"));
		bt = (TextView) findViewById(R.id.title);
		bt.setOnClickListener(this);
		ListenerHelper.bindOnCLickListener(this, this, R.id.qingjia,
				R.id.chakan);
		layout = View.inflate(this, R.layout.mytodo_dialog, null);

		if (type.equals("4")) {
			aq.find(R.id.nostart_txt).getTextView().setVisibility(View.GONE);
			layout_qiandao = (LinearLayout) findViewById(R.id.layout_qiandao); // 签到的布局
			layout_qiandao.setVisibility(View.VISIBLE);
			aq.find(R.id.qingjia).getButton().setText("我要报名");
		}
		if (type.equals("1")) {
			headtitle = "三会一课";
			xindelayout.setVisibility(View.GONE);
		} else if (type.equals("2")) {
			headtitle = "支部活动";
			xindelayout.setVisibility(View.GONE);
		} else if (type.equals("3")) {
			headtitle = "民主生活会";
		} else if (type.equals("4")) {
			headtitle = "志愿者活动";
		}
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", headtitle, 0, "", null, null);
		request(); // 请求详细信息
		// 设置头部
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		request();
	}

	public void sethead() {

		// ===========设置状态=================
		LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout_qiandao);
		if (!type.equals("4")) {

			if (type.equals("3")) { // 民主生活会可以显示查看志愿心语
				aq.find(R.id.txt_xinyu).getTextView()
						.setText("签到后还可以查看大家的会议心得");

				System.out.println("报名状态: " + map.get("signUpStatus"));
				if ("1".equals(map.get("signInStatus"))) { // 报名状态 0: 未报名 1: 报名了
					aq.find(R.id.chakan).getButton()
							.setVisibility(View.VISIBLE);
					aq.find(R.id.chakan).getButton().setText("查看心得体会");

				} else {
					aq.find(R.id.chakan).getButton().setVisibility(View.GONE);
				}
			}

			if ("0".equals(isstart)) {// 未开始
				// img.setBackgroundResource(R.drawable.nostart);
				if ("0".equals(map.get("forLeaveStatus"))) { // 未请假
					aq.find(R.id.qingjia).getButton().setText("我要请假");
				} else {
					aq.find(R.id.qingjia).getButton().setText("取消请假");
				}
			} else if ("1".equals(isstart)) {// 会议进 行
				// img.setBackgroundResource(R.drawable.jinxing);
				if ("0".equals(map.get("signInStatus"))) { // 0 未签到
					aq.find(R.id.nostart_txt).getTextView()
							.setVisibility(View.GONE);
					aq.find(R.id.yijing).getTextView().setText("已签到");
					if ("1".equals(map.get("forLeaveStatus"))) { // 1已请假
						aq.find(R.id.qingjia).getButton().setText("已请假");
						aq.find(R.id.qingjia).getButton()
						.setBackgroundResource(R.drawable.yiqiandao);
					} else {

						aq.find(R.id.qingjia).getButton().setText("我要签到");
					}

				} else { // 已经签到
					aq.find(R.id.nostart_txt).getTextView()
							.setVisibility(View.GONE);
					aq.find(R.id.yijing).getTextView().setText("已签到");
					aq.find(R.id.qingjia).getButton().setText("已签到");
					aq.find(R.id.qingjia).getButton()
							.setBackgroundResource(R.drawable.yiqiandao);
				}
				layout1.setVisibility(View.VISIBLE);
				// layout1.setVisibility(View.VISIBLE);
			} else if ("2".equals(isstart)) { // 已结束
				// img.setBackgroundResource(R.drawable.jieshu);
				layout1.setVisibility(View.VISIBLE);
				aq.find(R.id.yijing).getTextView().setText("已签到");
				aq.find(R.id.nostart_txt).getTextView().setText("会议已结束");
				aq.find(R.id.qingjia).getButton().setVisibility(View.GONE);
			}
		} else { // 志愿者
			System.out.println("报名状态: " + map.get("signUpStatus"));
			if ("1".equals(map.get("signUpStatus"))) { // 报名状态 0: 未报名 1: 报名了
				aq.find(R.id.chakan).getButton().setBackgroundResource(R.drawable.zybg_button);
				aq.find(R.id.chakan).getButton().setTextColor(getResources().getColor(R.color.white));
				aq.find(R.id.chakan).getButton().setClickable(true);
			} else {
				aq.find(R.id.chakan).getButton().setBackgroundResource(R.drawable.zhiyuanbg);
				aq.find(R.id.chakan).getButton().setClickable(false);
			}

			if ("0".equals(isstart)) {// 未开始
				// img.setBackgroundResource(R.drawable.nostart);
				if ("1".equals(map.get("signUpStatus"))) { // 未请假
					aq.find(R.id.qingjia).getButton().setText("取消报名");

				} else {
					aq.find(R.id.qingjia).getButton().setText("我要报名");
				}
			} else if ("1".equals(isstart)) {
				aq.find(R.id.qingjia).getButton().setVisibility(View.GONE);
				aq.find(R.id.nostart_txt).getTextView()
						.setVisibility(View.VISIBLE);
				aq.find(R.id.nostart_txt).getTextView().setText("活动进行中");

			} else if ("2".equals(isstart)) {
				layout1.setVisibility(View.VISIBLE);
				aq.find(R.id.qingjia).getButton().setVisibility(View.GONE);
				aq.find(R.id.nostart_txt).getTextView()
						.setVisibility(View.VISIBLE);
				aq.find(R.id.nostart_txt).getTextView().setText("活动已结束");
			}
		}
	}

	// 请求详细信息
	public void request() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", getIntent().getStringExtra("id"));
		map.put("userId", constants.userInfo.getUserId());
		map.put("type", type);

		String url = Constants.SERVER_RUL2
				+ getResources().getString(R.string.my_todo_detail);
		doRequest(1, url, StringUtil.jiami(map));
	}

	private void initPopWindow(View layout) {
		if (mPop == null) {
			mPop = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		/*
		 * if (mPop.isShowing()) { mPop.dismiss();
		 * 
		 * }
		 */
	}

	public void dismissPopupWindow(boolean boo) {
		if (mPop != null && mPop.isShowing()) {
			mPop.dismiss();
			if (boo) {
				mPop = null;
				// thread_flag = false;
			}
		}
	}

	// 成功之后
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		if (requestId == 1) { // 请求详细信息
			// map = resultObject.getDataMap();
			Map<String, Object> map1 = resultObject.getDataMap();
			try {
				// aq.find(R.id.qingjia).getButton().setBackgroundResource(R.drawable.yiqiandao);//
				// 成功之后设置背景为灰色
				map = JsonUtil
						.json2Map(new JSONObject(map1.get("details") + ""));
				if (type.equals("4")) { // 志愿者报名人数
					aq.find(R.id.renshu).getTextView()
							.setText(map.get("signUpCount").toString());
				} else { // 签到人数
					aq.find(R.id.renshu).getTextView()
							.setText(map.get("signInCount").toString());
				}

				mettingid = map.get("id").toString();

				title = map.get("title").toString();
				createUserName = map.get("createUserName").toString();
				startTime = map.get("startTime").toString();
				addresss = map.get("address").toString();
				content = Html.fromHtml(map.get("content").toString())
						.toString();
				joinUser = map.get("joinUser").toString();
				endtime = map.get("endTime").toString();
				sethead();
			} catch (Exception e) {

			}
		} else { // 请求请假
			// if("我要请假".equals(aq.find(R.id.qingjia).getButton().getText())){
			// Toast.makeText(this, "请假成功", 1).show();
			// aq.find(R.id.qingjia).getButton().setText("我已请假");
			// aq.find(R.id.qingjia).getButton().setBackgroundResource(R.drawable.yiqiandao);
			// }else
			// if("我要报名".contains(aq.find(R.id.qingjia).getButton().getText())){
			// // dismissPopupWindow(false);
			// Toast.makeText(this, "报名成功", 1).show();
			// aq.find(R.id.qingjia).getButton().setText("我已报名");
			// aq.find(R.id.qingjia).getButton().setBackgroundResource(R.drawable.yiqiandao);
			// }
			finish();
		}

	}

	@Override
	// public void onExecuteSuccess(int requestId, ResultObject resultObject) {
	// Toast.makeText(this, "请假成功", 1).show();
	// dismissPopupWindow(false);
	// }
	// 请求请假集中不同的状态
	public void tankuan(String shenqing, String isqueren, String queren,
			final Map<String, Object> map, final String url) {

		initPopWindow(layout);
		// thread_flag = false;
		mPop.showAtLocation(layout, Gravity.CENTER, 0, 0);// 在屏幕居中，无偏移
		System.err.println("layout2>>>" + layout.toString());
		((TextView) layout.findViewById(R.id.dialog_title)).setText(shenqing);
		((TextView) layout.findViewById(R.id.dialog_middle)).setText(isqueren);
		((Button) layout.findViewById(R.id.mytodo_confirm)).setText(queren);
		layout.findViewById(R.id.mytodo_confirm).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						doRequest(2, url, StringUtil.jiami(map));
						dismissPopupWindow(false);
					}
				});
		layout.findViewById(R.id.mytodo_cancel).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// mPop.dismiss();
						dismissPopupWindow(false);
					}
				});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		int id = v.getId();
		switch (id) {
		case R.id.title:
			intent = new Intent(QianDaoActivity.this,
					MyToDoDetailActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("type", getIntent().getStringExtra("type"));
			intent.putExtra("id", getIntent().getStringExtra("id"));
			System.out.println("title");
			intent.putExtra("title", title);
			intent.putExtra("createUserName", createUserName);
			intent.putExtra("startTime", startTime);
			intent.putExtra("addresss", addresss);
			intent.putExtra("content", content);
			intent.putExtra("joinUser", joinUser);
			intent.putExtra("endtime", endtime);
			startActivity(intent);
			break;
		case R.id.qingjia:
			if (type.equals("4")) {
				if ("我要报名".equals(aq.find(R.id.qingjia).getButton().getText())) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("userId", constants.userInfo.getUserId());
					map.put("id", getIntent().getStringExtra("id"));
					map.put("type", 1); // 请假类型
					String url = Constants.SERVER_RUL2
							+ getResources().getString(R.string.baoming);
					tankuan("报名申请", "是否确认要申请报名？", "确认申请", map, url);
				} else if ("取消报名".equals(aq.find(R.id.qingjia).getButton()
						.getText())) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("userId", constants.userInfo.getUserId());
					map.put("id", getIntent().getStringExtra("id"));
					map.put("type", 2); // 报名类型
					String url = Constants.SERVER_RUL2
							+ getResources().getString(R.string.baoming);
					tankuan("取消报名申请", "是否确认要取消报名？", "确认申请", map, url);
				}

			}

			else if ("我要请假".equals(aq.find(R.id.qingjia).getButton().getText())) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("userId", constants.userInfo.getUserId());
				map.put("id", getIntent().getStringExtra("id"));
				map.put("type", 1); // 请假类型
				String url = Constants.SERVER_RUL2
						+ getResources().getString(R.string.qingjia);
				tankuan("请假申请", "是否确认要申请请假？", "确认申请", map, url);
			} else if ("取消请假".equals(aq.find(R.id.qingjia).getButton()
					.getText())) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("userId", constants.userInfo.getUserId());
				map.put("id", getIntent().getStringExtra("id"));
				map.put("type", 2); // 请假类型
				String url = Constants.SERVER_RUL2
						+ getResources().getString(R.string.qingjia);
				tankuan("取消请假申请", "是否确认要取消请假？", "确认申请", map, url);
			}

			else if ("我要签到".equals(aq.find(R.id.qingjia).getButton().getText())) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("userId", constants.userInfo.getUserId());
				map.put("id", getIntent().getStringExtra("id"));
				map.put("type", 1); // 请假类型
				// String url = Constants.SERVER_RUL2
				// + getResources().getString(R.string.qingjia);
				// tankuan("签到申请", "是否确认要申请签到？", "确认申请", map, url);
				// Toast.makeText(this, "我要签到", 1).show();
				Intent intent = new Intent(this,
						com.do1.aqzhdj.zxing.CaptureActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// String type = ((TextView)
				// view.findViewById(R.id.txtType)).getText().toString();
				System.out.println("type: "
						+ getIntent().getStringExtra("type"));
				System.out.println("id: " + mettingid);
				intent.putExtra("id", mettingid);
				intent.putExtra("type", getIntent().getStringExtra("type"));
				intent.putExtra("title", getIntent().getStringExtra("title"));
				startActivity(intent);
			}
			break;
		case R.id.chakan:
			// Toast.makeText(this, "查看了志愿心语", 1).show();
			System.out.println("isstart:" + isstart);
			if (!"0".equals(isstart)) {
				intent = new Intent(QianDaoActivity.this, XinDeActivity.class);
				System.out.println("id:" + getIntent().getStringExtra("id"));
				intent.putExtra("id", getIntent().getStringExtra("id"));
				intent.putExtra("type", getIntent().getStringExtra("type"));
				intent.putExtra("title", getIntent().getStringExtra("title"));

				System.out.println("title: " + title);
				// intent.putExtra("id", getIntent().getStringExtra("id"));
				startActivity(intent);
				break;
			} else if ("0".equals(isstart)) {
				if (type.equals("4")) {
					Toast.makeText(this, "活动未开始不能查看志愿心语", 1).show();
				} else if (type.equals("3")) {
					Toast.makeText(this, "会议未开始不能查看心得体会", 1).show();
				}
			}

		}
	}

}
