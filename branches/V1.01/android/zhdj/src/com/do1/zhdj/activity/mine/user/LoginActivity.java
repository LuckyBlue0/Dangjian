package com.do1.zhdj.activity.mine.user;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;
import cn.com.do1.component.util.Log;
import cn.com.do1.component.util.ToastUtil;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.IndexActivity;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.info.UserInfo;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.Listenertool;
import com.do1.zhdj.util.StringUtil;

public class LoginActivity extends BaseActivity {

	private static final int LOGIN = 1;
	AQuery aq = new AQuery(this);
	private String userId = "";
	private String password = "";
	// private boolean isAuto;
	boolean isUser = true;
	boolean isMoblie = false;
	boolean isCard = false;
	ColorStateList onColor;
	ColorStateList offColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
	}

	private void init() {
		setHeadView(findViewById(R.id.headLayout), 0, "", "登录", 0, "", null,
				this);

		int[] resourdId = { R.id.login, R.id.toRegister, R.id.user,
				R.id.mobile, R.id.card };
		Listenertool.bindOnCLickListener(this, this, resourdId);

		onColor = aq.id(R.id.usersize).getTextView().getTextColors();
		offColor = aq.id(R.id.mobilesize).getTextView().getTextColors();

	}

	public void request(int type) {
		try {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
//			map.put("platformType",
//					SecurityUtil.encryptToDES(Constants.MICHI, "1"));
//			map.put("username",
//					SecurityUtil.encryptToDES(Constants.MICHI, userId));
//			map.put("userPwd",
//					SecurityUtil.encryptToDES(Constants.MICHI, password));
//			map.put("digest", SecurityUtil
//					.encryptToMD5("1" + userId + password).toLowerCase());
			
//			Map<String, String> map2 = new HashMap<String, String>();
//			map2.put("requestJson", toJsonString(map));
			map.put("platformType","1");
			map.put("username", userId);
			map.put("userPwd",password);
			map.put("deviceId", Constants.deviceId);
			String url = Constants.SERVER_RUL2
					+ getResources().getString(R.string.login);
			// doRequestPostString(LOGIN, url,"requestJson="+toJsonString(map));
			doRequest(LOGIN, url, StringUtil.jiami(map));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toJsonString(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json + "";
	}

	@Override
	public void onClick(View v) {
		View userView = aq.id(R.id.user).getView();
		View mobileView = aq.id(R.id.mobile).getView();
		View cardView = aq.id(R.id.card).getView();
		EditText edit = aq.id(R.id.userId).getEditText();

		Intent intent;
		switch (v.getId()) {
		case R.id.login:
			userId = aq.id(R.id.userId).getEditText().getText() + "";
			password = aq.id(R.id.pwd).getEditText().getText() + "";
			if ("".equals(userId)) {
				ToastUtil.showShortMsg(this, "用户名称不能为空,请重新输入...");
				return;
			}

			if ("".equals(password)) {
				ToastUtil.showShortMsg(this, "密码不能为空,请重新输入...");
				return;
			}

			if (isUser && !isMoblie && !isCard) {
				if (!valid(userId)) {
					ToastUtil.showShortMsg(this, "用户名格式不正确");
					return;
				}
				request(1);
			} else if (!isUser && isMoblie && !isCard) {
				if (!validMoble(userId)) {
					ToastUtil.showShortMsg(this, "手机号码格式不正确");
					return;
				}
				request(2);
			} else if (!isUser && !isMoblie && isCard) {
				if (!validIDcard(userId)) {
					ToastUtil.showShortMsg(this, "身份证格式不正确");
					return;
				}
				request(3);
			}
			break;

		case R.id.toRegister:
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;

		case R.id.user:
			if (!isUser) {
				isUser = true;
				isMoblie = false;
				isCard = false;
				aq.id(R.id.userId).text(null);
				aq.id(R.id.pwd).text(null);
				aq.id(R.id.usersize).getTextView().setTextColor(onColor);
				aq.id(R.id.mobilesize).getTextView().setTextColor(offColor);
				aq.id(R.id.cardsize).getTextView().setTextColor(offColor);
				userView.setBackgroundResource(R.drawable.tab_bt);
				mobileView.setBackgroundDrawable(null);
				cardView.setBackgroundDrawable(null);
				edit.setHint("请输入用户名");
			}
			break;

		case R.id.mobile:
			if (!isMoblie) {
				isUser = false;
				isMoblie = true;
				isCard = false;
				aq.id(R.id.userId).text(null);
				aq.id(R.id.pwd).text(null);
				aq.id(R.id.usersize).getTextView().setTextColor(offColor);
				aq.id(R.id.mobilesize).getTextView().setTextColor(onColor);
				aq.id(R.id.cardsize).getTextView().setTextColor(offColor);
				userView.setBackgroundDrawable(null);
				mobileView.setBackgroundResource(R.drawable.tab_bt);
				cardView.setBackgroundDrawable(null);
				edit.setHint("请输入手机号码");
			}
			break;

		case R.id.card:
			if (!isCard) {
				Log.e("" + isCard);
				isUser = false;
				isMoblie = false;
				isCard = true;
				aq.id(R.id.userId).text(null);
				aq.id(R.id.pwd).text(null);
				aq.id(R.id.usersize).getTextView().setTextColor(offColor);
				aq.id(R.id.mobilesize).getTextView().setTextColor(offColor);
				aq.id(R.id.cardsize).getTextView().setTextColor(onColor);
				userView.setBackgroundDrawable(null);
				mobileView.setBackgroundDrawable(null);
				cardView.setBackgroundResource(R.drawable.tab_bt);
				edit.setHint("请输入身份证号码");
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 匹配身份证号码
	 * 
	 * @param _IDcard
	 * @return
	 */
	private boolean validIDcard(String _IDcard) {
		Pattern p = Pattern
				.compile("((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))");
		Matcher m = p.matcher(_IDcard);
		return m.matches();
	}

	/*
	 * 验证手机号，11位，且必须含全是数字
	 */
	private boolean validMoble(String _mobile) {
		if (_mobile.length() != 11) {
			return false;
		}
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(_mobile);
		return m.matches();
	}

	private boolean valid(String _userId) {
		Pattern p = Pattern.compile("[0-9a-zA-Z]*");
		Matcher m = p.matcher(_userId);
		return m.matches();
	}
	
	

	@Override
	public void onNetworkError(int requestId) {
		// TODO Auto-generated method stub
		super.onNetworkError(requestId);
		Constants.SERVER_RUL2="http://183.63.138.179:2013/zhdj/interface/";
		Constants.SERVER_IMG="http://183.63.138.179:2013/zhdj/";
		Constants.SERVER_HTML="http://183.63.138.179:2013/zhdj";
		request(1);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		System.out.println("11111111111111");
		ToastUtil.showShortMsg(this, "登录成功");
		setAutoLogin();
		// Map<String, Object> resultMap = json2Map(new JSONObject(dataKey));
		Map<String, Object> map = resultObject.getDataMap();
		try {
			Map<String, Object> userMap = JsonUtil.json2Map(new JSONObject(map
					.get("loginUserInfo") + ""));
			// Object dataObject = getValueFromMap(resultMap, key, null);
			System.out.println("map: " + map);
			constants.userInfo = UserInfo.getInstance();
			Constants.sharedProxy
					.putString("keyId", userMap.get("userId") + "");
			Constants.sharedProxy.putString("keyType", userMap.get("userType")
					+ "");
			Constants.sharedProxy.commit();
			Constants.loginInfo.setLogin(true);
			constants.userInfo.setUserId(userMap.get("userId") + "");
			constants.userInfo.setUserName(userMap.get("userName") + "");
			constants.userInfo.setName(userMap.get("name") + "");
//			System.out.println("name: " + userMap.get("name"));
			constants.userInfo.setPassword(password);
			constants.userInfo.setBranchId(userMap.get("organizationId")
					.toString());
			constants.userInfo.setUser_type(userMap.get("userType").toString());
			// constants.userInfo.setIs_can_test(map.get("is_can_test").toString());

			// List<Map<String, Object>> birthdays = new ArrayList<Map<String,
			// Object>>();
			// birthdays =
			// JsonUtil.jsonArray2List(map.get("birthdays").toString());
			// constants.userInfo.setBirthdayMap(birthdays);
			constants.userInfo.setLogin(true);
			// HttpHelper.setGlobalParam(
			// getResources().getString(R.string.server_url), "user_id",
			// constants.userInfo.getUserId());
			// if (!birthdays.isEmpty()) {
			// Intent intent = new Intent(this, BirthdayActivity.class);
			// startActivity(intent);
			// } else {
			Intent intent = new Intent(this, IndexActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			// }
			finish();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
	}

	@Override
	public void onExecuteFail(int requestId, ResultObject resultObject) {
		ToastUtil.showShortMsg(this, "输入的账号或密码有误");
		// Map<String, Object> map = resultObject.getDataMap();
		// System.out.println("11111111111: "+ map.get("code"));
		// System.out.println("11111111111: " + map.get("desc"));
		// System.out.println(map.get("userType"));
	}

	private void setAutoLogin() {
		Constants.sharedProxy.putString("userId", userId);
		Constants.sharedProxy.putString("password", password);
		Constants.sharedProxy.putBoolean("isAuto", true);
		Constants.sharedProxy.commit();
	}
}
