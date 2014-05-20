package com.do1.zhdj.activity.bbs;

import java.util.LinkedHashMap;
import java.util.Map;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.ToastUtil;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.StringUtil;
import com.do1.zhdj.util.ValidUtil;

public class MoreIdeaBackActivity extends BaseActivity {
	private EditText mEditText;
	private EditText mEditText2;
	private TextView mContentSize;
	private int maxNum = 140;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_idea_back);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "意见反馈", R.drawable.btn_head_4, "提交", this, this);
		init();
	}

	private void init() {
		mEditText = (EditText) findViewById(R.id.more_ideaback);
		mEditText2 = (EditText) findViewById(R.id.more_submitphone);
		mContentSize = (TextView) findViewById(R.id.content_size);

		mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				maxNum) });
		mContentSize.setText(maxNum + "");
		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				int number = maxNum - s.length();
				mContentSize.setText("" + number);
				if (s.length() >= maxNum) {
					ToastUtil
							.showLongMsg(MoreIdeaBackActivity.this, "您已超过字数限制");
				}
			}
		});
	}

	/**
	 * 提交按钮监听
	 */
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rightImage:
			String ideaInfo = mEditText.getText().toString();
			String phoneUnm = mEditText2.getText().toString();
			System.out.println("phonenum: " + phoneUnm);
			if ((!ValidUtil.isMoble(phoneUnm))
					&& (!ValidUtil.isEmail(phoneUnm))) {
				ToastUtil.showShortMsg(this, "您输入联系方式有误！");
				return;
			}
			if (!ideaInfo.trim().equals("")) {
				String url = Constants.SERVER_RUL2;
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				url += getString(R.string.addSuggestion);
				map.put("type", "1");
				map.put("userName", "");
				map.put("suggestion", ideaInfo);
				map.put("mobile", "");
				doRequest(1, url, StringUtil.jiami(map));
			}else {
				ToastUtil.showShortMsg(this, "请输入意见内容");
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		System.out.println("resultObject:" + resultObject);
		// 请求成功
		if (resultObject.isSuccess()) {
			ToastUtil.showShortMsg(this, "意见提交成功");
			this.finish();
		}
	}

}
