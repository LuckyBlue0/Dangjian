package com.do1.aqzhdj.activity.mine.branch;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.BaseListActivity;
import com.do1.aqzhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.aqzhdj.util.ValidUtil;

public class BranchListActivity extends BaseListActivity implements
		ItemViewHandler {

	private String type = "1";
	private String branchId;
	private EditText etBranchSearch;
	private ImageButton imBranchSearchBtn;
	private String txtSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setCusItemViewHandler(this);
		type = getIntent().getStringExtra("type");
		// branchId = constants.userInfo.getBranchId();
		initView();

		String[] keys = new String[] { "cname", "mobile", "branch_Name" };
		int[] ids = new int[] { R.id.cname, R.id.mobile, R.id.address };

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("branch_id", branchId);
		map.put("keyword", "");
		setAdapterParams(keys, ids, R.layout.branchlist_item, map);
	}

	private void initView() {
		if ("1".equals(type)) {
			// type = "1";
			branchId = constants.userInfo.getBranchId();
		}
		if ("2".equals(type)) {
			// type = "2";
			branchId = "";
		}
		etBranchSearch = (EditText) findViewById(R.id.branch_searchtxt);
		txtSearch = etBranchSearch.getText().toString();

		imBranchSearchBtn = (ImageButton) findViewById(R.id.branch_searchbtn);
		imBranchSearchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				txtSearch = etBranchSearch.getText().toString();
				String[] keys = new String[] { "cname", "mobile", "branch_Name" };
				int[] ids = new int[] { R.id.cname, R.id.mobile, R.id.address };
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("type", type);
				map.put("branch_id", branchId);
				map.put("keyword", txtSearch != null ? txtSearch : "");
				setAdapterParams(keys, ids, R.layout.branchlist_item, map);
				doSearch();
			}
		});
	}

	@Override
	public void handleItemView(BaseAdapter adapter, final int position,
			View itemView, ViewGroup parent) {

		final OnClickListener clickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.branch_phone:
					String phone = mSlpControll.getmListData().get(position)
							.get("mobile").toString();
					if (ValidUtil.isMoble(phone)) {
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
						startActivity(intent);
					} else {
						Toast.makeText(BranchListActivity.this, "电话号码不正确",Toast.LENGTH_SHORT).show();
					}

					break;
				case R.id.branch_message:
					String number = mSlpControll.getmListData().get(position).get("mobile").toString();
					if (ValidUtil.isNumeric(number)) {
						Intent sendIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + number));
						startActivity(sendIntent);
					} else {
						Toast.makeText(BranchListActivity.this, "电话号码不正确",Toast.LENGTH_SHORT).show();
					}

					break;
				}
			}
		};
		bindClickListener(itemView, clickListener, R.id.branch_message,
				R.id.branch_phone);
	}

	// // 判断手机格式是否正确
	// protected boolean isMobile(String mobiles) {
	// Pattern p = Pattern
	// .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	// Matcher m = p.matcher(mobiles);
	//
	// return m.matches();
	// }

	private void bindClickListener(View item, OnClickListener clickListener,
			int... ids) {
		for (int id : ids) {
			item.findViewById(id).setOnClickListener(clickListener);
		}
	}

	@Override
	protected void itemClick(AdapterView<?> parent, View view,
			final int position, long id) {

	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd, "","1".equals(type) ? "支部通讯录" : "党务工作者通讯录", 0, "", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.branch);
		parentResId = R.layout.branchlist;
	}

}
