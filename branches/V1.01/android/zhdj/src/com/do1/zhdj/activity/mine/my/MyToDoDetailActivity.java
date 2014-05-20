package com.do1.zhdj.activity.mine.my;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ToastUtil;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.Entryption;
import com.do1.zhdj.util.StringUtil;
import com.do1.zhdj.zxing.CaptureActivity;

public class MyToDoDetailActivity extends BaseActivity {

	private TextView tvTitle;
	private TextView tvType;
	private TextView tvOrganiser;
	private TextView tvMeetingTime;
	private TextView tvStartTime;
	private TextView tvMeetingAddr;
	private TextView tvContent;
//	private TextView tvActors;
	private Button btnJoin;
	private Button btnLeave;
	private String id;
//	private String todoId;
	private PopupWindow mPop;
	private View layout;
	private Map<String, Object> map;
	private String titleTxt;
	private String type;
	private Thread thread;
	private boolean thread_flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytodo_detail);

		id = getIntent().getStringExtra("id");
//		relId = getIntent().getStringExtra("rel_id");
		type = getIntent().getStringExtra("type");
		initView();

//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.put("id", id);
//		map.put("userId", constants.userInfo.getUserId());
//		map.put("type", type);
//		
////		map.put("todo_rel_id", relId);
//		// System.err.println("todoId2>>>>" + todoId);
//		// System.err.println("relId2>>>>" + relId);
//		try {
////			String param = Entryption.encode(toJsonString(map));
//
//			String url = Constants.SERVER_RUL2
//					+ getResources().getString(R.string.my_todo_detail);
////			doRequestPostString(1, url, StringUtil.jiami(map));
//			doRequest(1, url, StringUtil.jiami(map));
//			System.err.println("success!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		if(type.equals("3")){
			aq.id(R.id.mytodo_leave).gone();
			aq.id(R.id.mytodo_join).getButton().setBackgroundResource(R.drawable.bt_login);
		}else{
			aq.id(R.id.mytodo_leave).visible();
			aq.id(R.id.mytodo_join).getButton().setBackgroundResource(R.drawable.btn_mytodo_join);
		}
		if(type.equals("2")){
//			aq.id(R.id.mytodo_meetingtimetxt).text("活动开始时间");
//			aq.id(R.id.mytodo_meetingaddr).text("活动地点");
		}

	}

	private void initView() {
		// mytodo_meetingaddr mytodo_content mytodo_actors
		// mytodo_join mytodo_leave

		tvTitle = (TextView) findViewById(R.id.mytodo_title);
		tvType = (TextView) findViewById(R.id.mytodo_typetxt);
		tvOrganiser = (TextView) findViewById(R.id.mytodo_organisertxt);
		// tvMeetingTime = (TextView) findViewById(R.id.mytodo_meetingtime);
		tvStartTime = (TextView) findViewById(R.id.mytodo_meetingtimetxt);
		tvMeetingAddr = (TextView) findViewById(R.id.mytodo_meetingaddress);

		tvContent = (TextView) findViewById(R.id.mytodo_content);
//		tvActors = (TextView) findViewById(R.id.mytodo_actors);

		btnJoin = (Button) findViewById(R.id.mytodo_join);
		btnLeave = (Button) findViewById(R.id.mytodo_leave);
		
		if ("1".equals(type))
			type = "三会一课";
		else if ("2".equals(type))
			type = "支部活动";
		else if ("3".equals(type))
			type = "民主生活会";
		else if ("4".equals(type))
			type = "志愿活动";
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				type+"详情", 0, "", null, null);

		tvTitle.setText(getIntent().getStringExtra("title"));
		tvType.setText("会议类型:"+type);
		tvOrganiser.setText("发起人:"+getIntent().getStringExtra("createUserName"));
		tvStartTime.setText(getIntent().getStringExtra("startTime"));
		tvMeetingAddr.setText("会议地点:"+getIntent().getStringExtra("addresss"));

		//tvContent.setText(map.get("content").toString());
		tvContent.setText(getIntent().getStringExtra("content"));
//		tvActors.setText(getIntent().getStringExtra("joinUser"));
		
		aq.find(R.id.mytodo_endmeetingtimetxt).getTextView().setText(getIntent().getStringExtra("endtime"));

		btnJoin.setOnClickListener(this);
		btnLeave.setOnClickListener(this);

		// initPopWindow();
		layout = View.inflate(this, R.layout.mytodo_dialog, null);

		// PopupWindow personalSettingWindow = new PopupWindow(
		// personalSettingView, 345, 500, true);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mytodo_join:
			initPopWindow(layout);
			thread_flag = false;
			mPop.showAtLocation(layout, Gravity.CENTER, 0, 0);// 在屏幕居中，无偏移
			System.err.println("layout1>>>" + layout.toString());
			((TextView) layout.findViewById(R.id.dialog_title)).setText("我要报名");
			((TextView) layout.findViewById(R.id.dialog_middle))
					.setText("是否确认要报名？");
			((Button) layout.findViewById(R.id.mytodo_confirm)).setText("确认报名");
			layout.findViewById(R.id.mytodo_confirm).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							Map<String, Object> map = new HashMap<String, Object>();

							map.put("user_id", constants.userInfo.getUserId());
//							map.put("todo_id", todoId);
//							map.put("todo_rel_id", relId);

							try {
								String param = Entryption
										.encode(toJsonString(map));

								String url = Constants.SERVER_URL
										+ getResources().getString(
												R.string.my_todo_join);
								doRequestPostString(2, url, param);

								System.err.println("success!");
								dismissPopupWindow(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
			layout.findViewById(R.id.mytodo_cancel).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							//mPop.dismiss();
							dismissPopupWindow(false);
						}
					});

			break;
		case R.id.mytodo_leave:
			initPopWindow(layout);
			thread_flag = false;
			mPop.showAtLocation(layout, Gravity.CENTER, 0, 0);// 在屏幕居中，无偏移
			System.err.println("layout2>>>" + layout.toString());
			((TextView) layout.findViewById(R.id.dialog_title)).setText("请假申请");
			((TextView) layout.findViewById(R.id.dialog_middle))
					.setText("是否确认要申请请假？");
			((Button) layout.findViewById(R.id.mytodo_confirm)).setText("确认申请");
			layout.findViewById(R.id.mytodo_confirm).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
//							Map<String, Object> map = new HashMap<String, Object>();
//
//							map.put("user_id", constants.userInfo.getUserId());
//							map.put("todo_id", todoId);
//							map.put("todo_rel_id", relId);
//
//							try {
//								String param = Entryption
//										.encode(toJsonString(map));
//
//								String url = Constants.SERVER_URL
//										+ getResources().getString(
//												R.string.my_todo_join);
//								doRequestPostString(3, url, param);
//								System.err.println("success!");
//								dismissPopupWindow(true);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
							Intent intent = new Intent(MyToDoDetailActivity.this, CaptureActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//							String type = ((TextView) view.findViewById(R.id.txtType)).getText().toString();
							intent.putExtra("type", type);
							startActivity(intent);
						}
					});
			layout.findViewById(R.id.mytodo_cancel).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							//mPop.dismiss();
							dismissPopupWindow(false);
						}
					});
			break;
		case R.id.leftImage:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		if (requestId == 1) {
//			map = resultObject.getDataMap();
			Map<String, Object> map = resultObject.getDataMap();
			try {
				Map<String, Object> maps = JsonUtil.json2Map(new JSONObject(map
						.get("details") + ""));
				// if (map != null) {

				if ("1".equals(type))
					type = "三会一课";
				else if ("2".equals(type))
					type = "支部活动";
				else if ("3".equals(type))
					type = "民主生活会";
				else if ("4".equals(type))
					type = "志愿活动";
				
				
				
				
//				tvTitle.setText(maps.get("title").toString());
//				tvType.setText(type);
//				tvOrganiser.setText(maps.get("createUserName").toString());
//				tvStartTime.setText(maps.get("startTime").toString());
//				tvMeetingAddr.setText(maps.get("addresss").toString());
//
//				//tvContent.setText(map.get("content").toString());
//				tvContent.setText(Html.fromHtml(maps.get("content").toString()));
//				tvActors.setText(maps.get("joinUser").toString());
				// }
			}catch(Exception e){
				
			}
			
		}
//		if (requestId == 2) {
//			ToastUtil.showLongMsg(this, "报名成功。");
//			finish();
//			/*View joinView = View.inflate(this, R.layout.mytodo_dialog_success,
//					null);
//			initPopWindow(joinView);
//			System.err.println("join>>" + joinView.toString());
//			mPop.showAtLocation(joinView, Gravity.CENTER, 0, 0);// 在屏幕居中，无偏移
//			//ToastUtil.showShortMsg(this, resultObject.getDesc().toString());
//			thread_flag = true;*/
//			//new Thread(new MyThread()).start();
//		}
//		if (requestId == 3) {
//			//View leaveView = View.inflate(this, R.layout.mytodo_dialog_success, null);
//			//initPopWindow(leaveView);
//			//System.err.println("leave>>" + leaveView.toString());
//			//mPop.showAtLocation(leaveView, Gravity.CENTER, 0, 0);// 在屏幕居中，无偏移
//			ToastUtil.showLongMsg(this, "请假申请成功。");
//			finish();
//			//thread_flag = true;
//			//new Thread(new MyThread()).start();
//		}

	}

	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

	private void initPopWindow(View layout) {
		if (mPop == null) {
			mPop = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		/*if (mPop.isShowing()) {
			mPop.dismiss();

		}*/
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				//mPop.dismiss();
				dismissPopupWindow(true);
			}
		}
	};

	private class MyThread implements Runnable {
		@Override
		public void run() {
			while (thread_flag) {
				try {
					Thread.sleep(3 * 1000);// 线程暂停10秒，单位毫秒
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);// 发送消息
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void dismissPopupWindow(boolean boo) {
		if(mPop != null && mPop.isShowing()) {
			mPop.dismiss();
			if(boo) {
				mPop = null;
				thread_flag = false;
			}
		}
	}

}
