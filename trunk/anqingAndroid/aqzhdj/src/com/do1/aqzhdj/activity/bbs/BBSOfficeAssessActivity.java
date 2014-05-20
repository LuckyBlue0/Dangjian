package com.do1.aqzhdj.activity.bbs;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.ToastUtil;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseActivity;
import com.do1.aqzhdj.util.Constants;
import com.do1.aqzhdj.util.StringUtil;

/**
 * 机关评议打分页面
 * 
 * @author LHQ
 * 
 */
public class BBSOfficeAssessActivity extends BaseActivity implements
		OnCheckedChangeListener {
	public static final int OFFICEASSESS_RESULTCODE = 2;
	private RadioGroup group;
	private String voteNum;
	private String assessContent;
	private EditText mEditYawpContent;
	private String voteOrg;
	private String voteOrgId;
	private Map<String, Object> data;
	private int isVote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bbs_office_assess);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "评议", R.drawable.btn_head_4, "提交结果", this, this);
		voteOrg = getIntent().getStringExtra("voteOrg");
		voteOrgId = getIntent().getStringExtra("voteOrgId");
		isVote = getIntent().getIntExtra("isVote", -1);
		aq.id(R.id.office_title).text(voteOrg);
		System.out.println("isVote??" + isVote);

		group = (RadioGroup) findViewById(R.id.radio_group);
		group.setOnCheckedChangeListener(this);
		mEditYawpContent = (EditText) findViewById(R.id.edit_assess_content);

		if (isVote == 0) {// 已评议
			System.out.println("isVote::" + isVote);
			aq.id(R.id.rightImage).getView().setVisibility(View.GONE);
			aq.id(R.id.office_assess_reminder).text("您已评议过该机关，感谢您的评议");
			String url = Constants.SERVER_RUL2
					+ getString(R.string.office_getVoteContent);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", voteOrgId);
			map.put("userId", constants.userInfo.getUserId());
			doRequest(1, url, StringUtil.jiami(map));
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.leftImage) {
			this.finish();
		}
		if (v.getId() == R.id.rightImage) {// 提交按钮被点击
			System.out.println("voteNum:" + voteNum);
			if (null == voteNum || ("").equals(voteNum)) {
				ToastUtil.showShortMsg(this, "请先选择");
				return;
			} else {
				assessContent = mEditYawpContent.getText().toString().trim();
				System.out.println("assessContent:" + assessContent);
				if ("4".equals(voteNum) && assessContent.equals("")) {
					ToastUtil.showShortMsg(this, "评议内容不能为空");
					return;
				}
				String url = Constants.SERVER_RUL2
						+ getString(R.string.office_addVoteResult);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", voteOrgId);// 关联被投票机关id
				map.put("userId", constants.userInfo.getUserId());
				map.put("voteNum", voteNum);
				map.put("reason", assessContent);
				doRequest(1, url, StringUtil.jiami(map));
			}
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		aq.id(R.id.edit_assess_content).invisible();
		switch (checkedId) {
		case R.id.btn_radio1:
			voteNum = "1";
			break;
		case R.id.btn_radio2:
			voteNum = "2";
			break;
		case R.id.btn_radio3:
			voteNum = "3";
			break;
		case R.id.radio_asscess:
			voteNum = "4";
			aq.id(R.id.edit_assess_content).visible();
			break;
		default:
			break;
		}
	}

	// 用作显示
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		System.out.println("onExecuteSuccess执行了");
		if (resultObject.getCode() == 0) {
			if (isVote == 1) {// 未
				System.out.println("isVote == 1");
				ToastUtil.showShortMsg(this, "评议成功");
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(10000);
							System.out.println("延时了");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
				Intent intent = new Intent();
				setResult(OFFICEASSESS_RESULTCODE, intent);
				finish();
			} else if (isVote == 0) {// 已
				System.out.println("isVote == 0");
				JSONObject object = (JSONObject) resultObject.getDataMap().get(
						"voteContent");
				data = JsonUtil.json2Map(object);
				int voteResult = Integer.parseInt(data.get("voteResult") + "");
				if (voteResult == 1) {
					aq.id(R.id.btn_radio1).click();
				} else if (voteResult == 2) {
					aq.id(R.id.btn_radio2).click();
				} else if (voteResult == 3) {
					aq.id(R.id.btn_radio3).click();
				} else if (voteResult == 4) {
					aq.id(R.id.radio_asscess).click();
					aq.id(R.id.edit_assess_content).text(
							data.get("reason") + "");
					aq.id(R.id.edit_assess_content).getView().setEnabled(false);
				}
			}
		}
	}
}
