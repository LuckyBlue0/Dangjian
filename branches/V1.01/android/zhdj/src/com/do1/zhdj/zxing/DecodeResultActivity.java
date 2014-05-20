package com.do1.zhdj.zxing;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.barcode.zxing.result.ResultHandler;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.mine.my.MySignMeetingListActivity;
import com.do1.zhdj.activity.mine.my.MyToDoDetailActivity;
import com.do1.zhdj.activity.mine.my.MyToDoListActivity;
import com.do1.zhdj.activity.mine.my.QianDaoActivity;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.Entryption;
import com.do1.zhdj.util.ImageCompress;
import com.do1.zhdj.util.StringUtil;

/**
 * 二维码结果页面
 * 
 * 
 */
public class DecodeResultActivity extends BaseActivity {
	/** Called when the activity is first created. */
	private static final String TAG = ResultHandler.class.getSimpleName();
	private Context context;
	private Bitmap mBitmap;
	private String content;
	private ImageView barcodeImage;
	private TextView contentView;
	private int type = 1;
	private TextView proofShopView;
	private TextView proofUrlView;
	private LinearLayout proofLayout;
	private boolean flag = false;
	private String favorInfo = "";
	private Button btnSign, btnScreenAgain;
	private String meetingId = "";
	private String erweimaType = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decode_result);
		context = this;
		mBitmap = ImageCompress.comp(Constants.mBitmap);
		content = (String) getIntent().getCharSequenceExtra("content");
		erweimaType = this.getIntent().getStringExtra("type");
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"二维码签到", 0, "", null, null);
		aq.find(R.id.title).getTextView().setText(getIntent().getStringExtra("title"));
		initViews();
		refresh();
	}

	public void initViews() {
		barcodeImage = (ImageView) this.findViewById(R.id.barcode_image_viewe);
		contentView = (TextView) this.findViewById(R.id.contents_text_view);

		btnSign = (Button) this.findViewById(R.id.btnSign);
		btnSign.setOnClickListener(this);

		btnScreenAgain = (Button) this.findViewById(R.id.btnScreenAgain);
		btnScreenAgain.setOnClickListener(this);
		// proofUrlView = (TextView) this.findViewById(R.id.proofUrl);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSign:
			String id=meetingId.split(":")[1];
			System.out.println("getintentid: " + getIntent().getStringExtra("id"));
			System.out.println("二维码里面的id: " + id);
			if(getIntent().getStringExtra("id").equals(id)) {
				String userId = constants.userInfo.getUserId();
				String url = Constants.SERVER_RUL2 + getString(R.string.meeting_signin);
				Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
				
				paramMap.put("userId", userId);
				paramMap.put("id", id);
				paramMap.put("type", getIntent().getStringExtra("type"));
				String param;
				try {
//					param = Entryption.encode(toJsonString(paramMap));
//					doRequestPostString(0, url, StringUtil.jiami(paramMap));
					doRequest(0, url, StringUtil.jiami(paramMap));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		else {
				ToastUtil.showLongMsg(context, "请确认当前二维码与您要参加的会议二维码是否一致");
			}
//			
			break;
		case R.id.btnScreenAgain:
			finish();
			Intent intent = new Intent(DecodeResultActivity.this, CaptureActivity.class);
			intent.putExtra("type", getIntent().getStringExtra("type"));
			intent.putExtra("title", getIntent().getStringExtra("title"));
			intent.putExtra("id", getIntent().getStringExtra("id"));
			startActivity(intent);
			break;
		case R.id.leftImage:
			finish();
			break;
		}
	}
	
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		System.out.println("成功了..............");
		if(requestId == 0) {
			if(resultObject.isSuccess()) {
//				ToastUtil.showLongMsg(context, "签到成功！");
//				Intent intent = new Intent(DecodeResultActivity.this, QianDaoActivity.class);
//				startActivity(intent);
				finish();
			} else {
				ToastUtil.showLongMsg(context, "签到失败！");
//				Intent intent = new Intent(DecodeResultActivity.this, MyToDoListActivity.class);
//				startActivity(intent);
			}
		}
	}
	
	public void refresh() {
		favorInfo = "";
		flag = false;
		mBitmap = getmBitmap();
		if (mBitmap == null) {
			barcodeImage.setImageBitmap(BitmapFactory.decodeResource(
					getResources(), R.drawable.ic_launcher));
		} else {
			barcodeImage.setImageBitmap(mBitmap);
		}
		if (content != null && content.contains(";")) {
			meetingId = content.split(";")[1];
			contentView.setText(content.split(";")[1]);
		} else {
			contentView.setText(content);
		}
	}

	// 返回监听器
	private OnClickListener backListener = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Log.e(TAG, "返回...");
			onKeyDown(KeyEvent.KEYCODE_BACK, null);
			/*
			 * Intent intent = new Intent(); intent.setClass(context,
			 * IndexActivity.class); startActivity(intent); ((Activity)
			 * context).finish();
			 */
		}
	};

	private static void putExtra(Intent intent, String key, String value) {
		if (value != null && value.length() > 0) {
			intent.putExtra(key, value);
		}
	}

	void launchIntent(Intent intent) {
		if (intent != null) {
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			Log.d(TAG, "Launching intent: " + intent + " with extras: "
					+ intent.getExtras());
			try {
				// context.startActivity(Intent.createChooser(intent, "分享"));
				this.startActivity(intent);
			} catch (ActivityNotFoundException e) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(R.string.app_name);
				builder.setMessage(R.string.msg_intent_failed);
				builder.setPositiveButton(R.string.button_ok, null);
				builder.show();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		refresh();
		super.onResume();
	}

	public Bitmap getmBitmap() {
		return mBitmap;
	}

	public void setmBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}
	
	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

}