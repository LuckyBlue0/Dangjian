package com.do1.aqzhdj.activity.mine.my;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.ValidUtil;
import com.do1.aqzhdj.zxing.CaptureActivity;

public class MySignMeetingListActivity extends BaseListActivity implements
		ItemViewHandler {
	private Map<String, Object> map;
	private String[] keys;
	private int[] ids;
	private String title = "会议签到";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setCusItemViewHandler(this);
		keys = new String[] {"meeting_id", "title", "time",
				"address", "signs", "have_sign", "type"};
		ids = new int[] { R.id.txtMeetingId, R.id.txtTitle, R.id.txtDate,
				R.id.txtAddress, R.id.txtSigns, R.id.txtStatusValue, R.id.txtType};
		map = new HashMap<String, Object>();
		map.put("user_id", constants.userInfo.getUserId());
		setAdapterParams(keys, ids, R.layout.sign_meeting_item, map);
		this.setLoadDataOnResume(true);
	}

	
	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout codeLayout = (LinearLayout) itemView.findViewById(R.id.codeLayout);
		TextView txtStatusLbl = (TextView) itemView.findViewById(R.id.txtStatusLbl);
		ImageView imgCode = (ImageView) itemView.findViewById(R.id.imgCode);
		String status = ((TextView) itemView.findViewById(R.id.txtStatusValue)).getText().toString();
		if("1".equals(status)) {
			txtStatusLbl.setText("已签到");
			txtStatusLbl.setTextColor(0xff8d8d8d);
			imgCode.setVisibility(View.GONE);
		}else if("2".equals(status)) {
			txtStatusLbl.setText("二维码签到");
			txtStatusLbl.setTextColor(0xffffffff);
			codeLayout.setBackgroundResource(R.drawable.sign_meeting_codeon_bg);
			imgCode.setVisibility(View.VISIBLE);
		}else if("3".equals(status)) {
			txtStatusLbl.setText("已过期");
			txtStatusLbl.setTextColor(0xff8d8d8d);
			imgCode.setVisibility(View.GONE);
		}
		
		TextView metTime = (TextView) itemView.findViewById(R.id.txtDate);
		String time = mSlpControll.getmListData().get(position).get("time")+"";
		if(!ValidUtil.isNullOrEmpty(time)){
			time = toTime(time);
			metTime.setText(time);
		}
	}
	
	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,long id) {
		if("2".equals(((TextView) view.findViewById(R.id.txtStatusValue)).getText().toString())) {
			Intent intent = new Intent(this, CaptureActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			String type = ((TextView) view.findViewById(R.id.txtType)).getText().toString();
			intent.putExtra("type", type);
			startActivity(intent);
		}
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "", title, 0,"", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.my_sign_meeting_list);
		
		Hashtable<String, Object> ht = new Hashtable<String, Object>();
		ht.put("one", "one_values");
		ht.get("one").toString();
	}
}
