package com.do1.zhdj.activity.mine.user;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.Entryption;

public class ViewMessageActivity extends BaseActivity {

	private EditText contentEdt;// 定义一个文本输入框
	private TextView hasnum;// 用来显示剩余字数
	private int num = 140;// 限制的最大字数
	private ImageButton imCleanMessage;
	private EditText mobileEdt;
	private String contact;
	private String moblie;
	private boolean isPhone = true;
	private boolean isEMail = true;
	private Button btnSubmit;
	private String userId;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_manage);

		userId = Constants.sharedProxy.getString("userId", userId);
		initView();

	}

	private void initView() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "",
				"用户反馈", 0, "", null, null);

		// num = 140;// 限制的最大字数

		contentEdt = (EditText) findViewById(R.id.content);
		mobileEdt = (EditText) findViewById(R.id.contacts);

		hasnum = (TextView) findViewById(R.id.content_count);

		hasnum.setText(num + "");

		// 　下面为EditText文本框添加监听
		contentEdt.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void onTextChanged(CharSequence str, int start, int before,
					int count) {
				temp = str;
			}

			@Override
			public void afterTextChanged(Editable str) {
				int number = num - str.length();
				hasnum.setText("" + number);
				selectionStart = contentEdt.getSelectionStart();
				selectionEnd = contentEdt.getSelectionEnd();
				if (temp.length() > num) {
					str.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					contentEdt.setText(str);
					contentEdt.setSelection(tempSelection);// 设置光标在最后
				}
			}
		});

		imCleanMessage = (ImageButton) findViewById(R.id.cleanMessage);

		imCleanMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				contentEdt.setText("");
				num = 140;
			}
		});

		btnSubmit = (Button) findViewById(R.id.btnSubmit);

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					moblie = mobileEdt.getText().toString().trim();
					contact = contentEdt.getText().toString().trim();
					isPhone = isMobile(moblie);
					isEMail = isEmail(moblie);

					Map<String, Object> map = new HashMap<String, Object>();

					map.put("type", "1");
					map.put("user_id", userId);

					map.put("content", contact);
					if("".equals(contact) || null == contact){
						ToastUtil.showShortMsg(ViewMessageActivity.this, "意见内容不能为空");
						return;
					}
					if ("".equals(contact)) {
						map.put("contact",contact);
						String param = Entryption.encode(toJsonString(map));
						String url = Constants.SERVER_URL
								+ getResources().getString(R.string.message);
						doRequestPostString(1, url, param);
						System.err.println("success!");
						// intent = new Intent(ViewMessageActivity.this,
						// AboutActivity.class);
						// startActivity(intent);
						// finish();

					}else if("".equals(moblie)){
						ToastUtil.showShortMsg(ViewMessageActivity.this, "联系方式不能为空");
						return;
					} else if (!isPhone && !isEMail) {

						Toast.makeText(ViewMessageActivity.this, "手机/邮箱格式不正确",
								Toast.LENGTH_SHORT).show();
						mobileEdt.setText("");
					} else {
						map.put("contact", moblie);
						String param = Entryption.encode(toJsonString(map));
						String url = Constants.SERVER_URL
								+ getResources().getString(R.string.message);
						doRequestPostString(2, url, param);
						System.err.println("success!");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {

		if (requestId == 1) {

			String result = resultObject.getDesc();

			ToastUtil.showShortMsg(this, result);

//			intent = new Intent(ViewMessageActivity.this, AboutActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		} else if (requestId == 2) {
			String result = resultObject.getDesc();

			ToastUtil.showShortMsg(this, result);

//			intent = new Intent(ViewMessageActivity.this, AboutActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		} else {
			String result = resultObject.getDesc();

			ToastUtil.showShortMsg(this, result);
		}
		
		
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		String result = resultObject.getDesc();

		ToastUtil.showShortMsg(this, result);
	}

	// 判断手机格式是否正确
	protected boolean isMobile(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	// 判断email格式是否正确
	protected boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

}
