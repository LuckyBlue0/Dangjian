package com.do1.zhdj.activity.bbs;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.JsonUtil;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseActivity;
import com.do1.zhdj.util.Constants;
import com.do1.zhdj.util.ImageViewTool;
import com.do1.zhdj.util.StringUtil;

/**
 * 候选人信息页面
 * 
 * @author LHQ
 * 
 */
public class BBSPartyerInfoActivity extends BaseActivity implements
		OnClickListener {
	private String id;
	private String userId;
	private Map<String, Object> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bbs_vote_person_info);
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,
				"", "候选人资料", 0, "", this, null);
		id = getIntent().getStringExtra("id");
		userId = getIntent().getStringExtra("userId");

		String url = Constants.SERVER_RUL2
				+ getString(R.string.viewPartyMember);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);// 投票主题id
		map.put("userId", userId);// 被投票的党员id
		doRequest(1, url, StringUtil.jiami(map));
	}

	// 用作显示
	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
		super.onExecuteSuccess(requestId, resultObject);
		JSONObject object = (JSONObject) resultObject.getDataMap()
				.get("member");
		data = JsonUtil.json2Map(object);
		aq.id(R.id.person_name).text(data.get("userName") + "");
		aq.id(R.id.person_noble_things).text(data.get("advancedDeeds") + "");
		aq.id(R.id.person_report).text(data.get("partyWork") + "");
		ImageView imgView = aq.id(R.id.person_img).getImageView();
//		ImageView imgBgView = (ImageView) findViewById(R.id.person_img_bg);
		// ImageView imgBgView = aq.id(R.id.person_img_bg).getImageView();
		String userImgPath = data.get("userImgPath") + "";
		System.out.println(userImgPath);
//		ImageViewTool.getAsyncImageSet(userImgPath, imgView,
//				R.drawable.new_mine_logo);
		ImageViewTool.getAsyncImageBg(userImgPath, imgView,
				R.drawable.new_mine_logo);
//		ImageViewTool.getAsyncImageBg(userImgPath, imgBgView,
//				0);
	}

}
