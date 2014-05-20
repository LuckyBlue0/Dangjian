package com.do1.aqzhdj.activity.circle;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.DateTool;
import com.do1.aqzhdj.util.Entryption;
import com.do1.aqzhdj.util.Listenertool;

public class SendCircleActivity extends BaseActivity {

	private String title;
	private String startTime;
	private String startRiqi;
	private String startShijian;
	private String endTime;
	private String endRiqi;
	private String endShijian;
	private String address;
	private String content;
	private String circleId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_circle);

		init();
		
	}
	
	private void init() {
		circleId = getIntent().getStringExtra("circleId");
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"发起活动", R.drawable.btn_head_2, "发布", this, this);

		int[] resourceIds = {
				R.id.lay_time1, R.id.lay_time2
				};
		Listenertool.bindOnCLickListener(this, this, resourceIds);
		
		title = Constants.circleInfo.getLabels();
		if (!TextUtils.isEmpty(title)) {
			aq.id(R.id.title).getEditText().setEnabled(false);
			aq.id(R.id.title).text(title);
		}

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		Calendar calendar = Calendar.getInstance();
		Dialog dialog;
		final Dialog dialog2;
		DatePickerDialog.OnDateSetListener dateListener;
		TimePickerDialog.OnTimeSetListener timeListener;
		switch (v.getId()) {
		case R.id.rightImage:
			if (TextUtils.isEmpty(title)) {
				title = aq.id(R.id.title).getEditText().getText().toString();
			}
			// time = aq.id(R.id.time).getText().toString();
			// endTime= aq.id(R.id.endtime).getText().toString();
			address = aq.id(R.id.address).getEditText().getText().toString();
			content = aq.id(R.id.content).getEditText().getText().toString();
			startTime = startRiqi +" "+ startShijian;
			endTime = endRiqi +" "+ endShijian;
			Log.e(startTime);
			checkStr(title, "活动主题不能为空");
			checkStr(startTime, "活动开始时间不能为空");
			checkStr(endTime, "活动结束时间不能为空");
			checkStr(address, "活动地点不能为空");
			checkStr(content, "活动规则不能为空");
			// 隐藏软键盘
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(SendCircleActivity.this
							.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			request();
			break;

			
		case R.id.lay_time1:
			// 时间处理
			timeListener = new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker timerPicker, int hourOfDay,
						int minute) {
					
					startShijian = hourOfDay + ":" + (minute >= 10 ? minute : "0" + minute);
					//3,时间和日期都选择完成，显示时间和日期
					startTime = startRiqi +" "+ startShijian;
					Date date = DateTool.StringToDate(startTime, "yy-MM-dd HH:mm");
					int secondCountOther = DateTool.getSeconds(new Date());
					int secondCount = DateTool.getSeconds(date);
					if(secondCount < secondCountOther + 60){
						ToastUtil.showShortMsg(SendCircleActivity.this, "时间设置不正确");
						startTime = "";
						aq.id(R.id.time_start).text("");
						return;
					}
					aq.id(R.id.time_start).text(startTime);
				}
			};
			dialog2 = new TimePickerDialog(this, timeListener,
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE), true); // 是否为二十四制
			// 日期处理
			dateListener = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					startRiqi = year + "-" + (month + 1) + "-" + dayOfMonth;
					
					//2,选择日期后，显示时间Dialog
					dialog2.show();
				}
			};
			dialog = new DatePickerDialog(this, dateListener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			//1,显示日期Dialog
			dialog.show();
			break;
		case R.id.lay_time2:
			timeListener = new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker timerPicker, int hourOfDay,
						int minute) {
					endShijian = hourOfDay + ":" + (minute >= 10 ? minute : "0" + minute);
					
					endTime = endRiqi +" "+ endShijian;
					
					Date enddate = DateTool.StringToDate(endTime, "yyyy-MM-dd HH:mm");
					Date startdate = DateTool.StringToDate(startTime, "yyyy-MM-dd HH:mm");
					int secondCountOther = DateTool.getSeconds(startdate);
					int secondCount = DateTool.getSeconds(enddate);
					Log.e(secondCount +"---"+secondCountOther+"----"+enddate);
					if(secondCount < secondCountOther){
						ToastUtil.showShortMsg(SendCircleActivity.this, "时间设置不正确");
						endTime = "";
						aq.id(R.id.time_end).text(endTime);
						return;
					}
					aq.id(R.id.time_end).text(endTime);
				}
			};
			dialog2 = new TimePickerDialog(this, timeListener,
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE), true); // 是否为二十四制
			
			dateListener = new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker datePicker, int year,
						int month, int dayOfMonth) {
					endRiqi = year + "-" + (month + 1) + "-" + dayOfMonth;
					
					dialog2.show();
				}
			};
			dialog = new DatePickerDialog(this, dateListener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			dialog.show();
			
			break;
		default:
			break;
		}
		
		
	}
	
	

	private void checkStr(String str, String msg) {
		if (null == str || str.equals("")) {
			ToastUtil.showShortMsg(this, msg);
			return;
		}
	}

	private void request() {
		try {
			String url = Constants.SERVER_URL + "PublishCircleActivity.aspx";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", constants.userInfo.getUserId());
			map.put("community_id", circleId);
			map.put("name", title);
			map.put("time", startTime);
			map.put("endtime", endTime);
			map.put("address", address);
			map.put("content", content);
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
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		ToastUtil.showShortMsg(this, "发布失败，请重新发布");
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		ToastUtil.showShortMsg(this, "发布成功");
		AppManager.needFreshForCircleActivity = true;
		MyCircleActivity.change = true;
		finish();
	}
}
