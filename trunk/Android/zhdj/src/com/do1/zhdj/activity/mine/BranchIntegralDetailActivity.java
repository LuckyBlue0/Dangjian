package com.do1.zhdj.activity.mine;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;

/**
 * 个人积分详情
 * auth:YanFangqin
 * data:2013-4-28
 * thzhd
 */
public class BranchIntegralDetailActivity extends BaseListActivity implements ItemViewHandler{

	public Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		aq = new AQuery(this);
		setCusItemViewHandler(this);
		String[] keys = new String[]{"detail_item","score","time"};
        int[] ids = new int[]{R.id.item1,R.id.item2,R.id.item3};
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("branch_id", constants.userInfo.getBranchId());
        setAdapterParams(keys,ids,R.layout.person_integral_item,map);
        aq.id(R.id.tabOne).text(Html.fromHtml(constants.userInfo.getBranchName() + "的总积分：" + "<font color='#c90100'>" + constants.userInfo.getBranchInt() +"</font>"));
	}
	
	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"","组织积分详情", 0,"", null, null);
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.get_branch_integral_info);
		parentResId = R.layout.list_view_for_person_integral_detail;
	}

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		
		TextView date = (TextView) itemView.findViewById(R.id.item3);
		try {
			String s = constants.sdfDate.format(constants.sdfReturn.parse(date.getText().toString()));
			date.setText(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
