package com.do1.zhdj.activity.mine.my;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.com.do1.component.parse.ResultObject;
import cn.com.do1.component.util.Log;

import com.do1.zhdj.R;
import com.do1.zhdj.activity.parent.BaseListActivity;
import com.do1.zhdj.activity.parent.BaseListActivity.ItemViewHandler;
import com.do1.zhdj.util.ImageViewTool;

public class MyToDoListActivity extends BaseListActivity implements
		ItemViewHandler {
	private Map<String, Object> map;
	private String[] keys;
	private int[] ids;
	private String type;
	private String title = "我的待办";
	private EditText txtKeyword;
	private ImageView imgSearch;
	private Intent intent;
	public LinearLayout daibanlayout, yibanlayout;
	Button daibanbt, yibanbt;
	PopupWindow popupWindow;
	View contentView;
	TextView txt1, txt2, txt3;
	String sanhuitype = "1";// 1. 我的会议 2 我的党课
	String sanhuistatus = "0"; // 默认0代办 1 已办
	String isstart = "0";
	String  count="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.mytodo_list);

		System.out.println("type: " + type);

		daibanlayout = (LinearLayout) findViewById(R.id.layout_daiban);
		yibanlayout = (LinearLayout) findViewById(R.id.layout_yiban);
		daibanbt = (Button) findViewById(R.id.bt_daiban);
		yibanbt = (Button) findViewById(R.id.bt_yiban);
		daibanlayout.setOnClickListener(this);
		yibanlayout.setOnClickListener(this);
		daibanbt.setOnClickListener(this);
		yibanbt.setOnClickListener(this);
		type = this.getIntent().getStringExtra("type");
		// type进行判断
		if (type.equals("1")) {
			initpop();
			txt1 = (TextView) contentView.findViewById(R.id.all);
			txt2 = (TextView) contentView.findViewById(R.id.mymetting);
			txt3 = (TextView) contentView.findViewById(R.id.dangke);
			txt1.setOnClickListener(this);
			txt2.setOnClickListener(this);
			txt3.setOnClickListener(this);
		} else if (type.equals("4")) {
			aq.find(R.id.jiantoupic).getImageView().setVisibility(View.GONE);
			aq.find(R.id.bt_daiban).getButton().setText("所有活动");
			aq.find(R.id.bt_yiban).getButton().setText("我已报名");
		} else {
			aq.find(R.id.jiantoupic).getImageView().setVisibility(View.GONE);
		}

		Log.e(type);
		title = this.getIntent().getStringExtra("title");
		setCusItemViewHandler(this);
		keys = new String[] { "id", "id", "title", "createUserName",
				"createTime" };
		ids = new int[] { R.id.txtTodoId, R.id.txtRelId, R.id.txtTitle,
				R.id.txtAuthor, R.id.txtTime };
		map = new LinkedHashMap<String, Object>();

		map.put("userId", constants.userInfo.getUserId());
		// map.put("keyword", aq.find(id))
		if (type.equals("1")) {
			map.put("type", sanhuitype); // 代办类型 0 全部, 1 我的党课, 2 我的会议
		}
		map.put("status", sanhuistatus); // 状态 0代办 1 已办

		setAdapterParams(keys, ids, R.layout.mytodo_item, map);
		
		

		txtKeyword = (EditText) this.findViewById(R.id.txtKeyword);
		imgSearch = (ImageView) this.findViewById(R.id.imgSearch);
		imgSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search();
			}
		});
		this.setLoadDataOnResume(true);

	}
	
	public void success(ResultObject data) {
		System.out.println("count: " + data.getDataMap().get("toDoCount"));
//		if(type.equals("1")&&type.equals("2")){
		aq.find(R.id.nullText).getTextView().setVisibility(View.GONE);
			count=data.getDataMap().get("toDoCount").toString();
//		}
		if (!count.equals("")&&count!=null&&!count.equals("0")) { // 传过来的count
			setHeadView(findViewById(R.id.headLayout),
					R.drawable.btn_back_thzhd, "", title+"("+count+")", 0, "", null, null);
		} else {
			setHeadView(findViewById(R.id.headLayout),
					R.drawable.btn_back_thzhd, "", title, 0, "", null, null);
		}
	}

	// 搜索三会一课的数据
	public void sanhuisearch() {
		keys = new String[] { "id", "id", "title", "createUserName",
				"createTime" };
		ids = new int[] { R.id.txtTodoId, R.id.txtRelId, R.id.txtTitle,
				R.id.txtAuthor, R.id.txtTime };
		map = new LinkedHashMap<String, Object>();

		map.put("userId", constants.userInfo.getUserId());
		// map.put("keyword", aq.find(id))
		map.put("type", sanhuitype); // 代办类型 0 全部, 1 我的党课, 2 我的会议
		map.put("status", sanhuistatus); // 状态 0代办 1 已办

		// setAdapterParams(keys, ids, R.layout.mytodo_item, map);
		search();
		// txtKeyword = (EditText) this.findViewById(R.id.txtKeyword);
		// imgSearch = (ImageView) this.findViewById(R.id.imgSearch);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.leftImage:
			finish();
			break;
		case R.id.layout_daiban:
			// Toast.makeText(this, "点击了代办", 1).show();
			sanhuistatus = "0";
			if (type.equals("1")) {
//				popupWindow.showAsDropDown(v); 
				popupWindow.showAtLocation(contentView, Gravity.TOP, 0, 0);
			} else {
				sanhuisearch(); // 搜索三会一课代办
			}

			break;
		case R.id.bt_daiban:
			// Toast.makeText(this, "点击了代办", 1).show();
			sanhuistatus = "0";
			if (type.equals("1")) {
//				popupWindow.showAsDropDown(v); 
				popupWindow.showAtLocation(contentView, Gravity.TOP, 0, 0);
			} else {
				sanhuisearch();
			}

			break;
		case R.id.layout_yiban:
			// Toast.makeText(this, "点击了已办", 1).show();
			sanhuistatus = "1";
			sanhuisearch();
		case R.id.bt_yiban:
			// Toast.makeText(this, "点击了已办", 1).show();
			sanhuistatus = "1";
			sanhuisearch();
			break;
		case R.id.all:
			// Toast.makeText(this, "点击了全部", 1).show();
			popupWindow.dismiss();
			break;
		case R.id.mymetting:
			// Toast.makeText(this, "我的会议", 1).show();
			sanhuitype = "1";
			popupWindow.dismiss();
			aq.find(R.id.bt_daiban).getButton().setText("我的会议");
			sanhuisearch();
			break;
		case R.id.dangke:
			// Toast.makeText(this, "我的党课", 1).show();
			sanhuitype = "2";
			popupWindow.dismiss();
			aq.find(R.id.bt_daiban).getButton().setText("我的党课");
			sanhuisearch();
			break;
		}

	}

	public void initpop() {
		// contentView = getLayoutInflater().inflate(this,R.layout.popwindow,
		// null); // 泡泡窗体要显示的内容
		contentView = View.inflate(this, R.layout.popwindow, null);
		popupWindow = new PopupWindow(contentView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		popupWindow.setBackgroundDrawable(new BitmapDrawable());// 点击空白的地方关掉泡泡窗口

		ColorDrawable ColorDrawablecd = new ColorDrawable(-0000);
		popupWindow.setBackgroundDrawable(ColorDrawablecd);// 点击空白的地方关掉泡泡窗口

		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
	}

	public void search() {
		map.put("keyword", txtKeyword.getText().toString());
		setAdapterParams(keys, ids, R.layout.mytodo_item, map);
		doSearch();
	}
	
	

	@Override
	public void handleItemView(BaseAdapter adapter, int position,
			View itemView, ViewGroup parent) {
		ImageView img = (ImageView) itemView.findViewById(R.id.arraw);
		// ImageViewTool.getAsyncImageBg(mSlpControll.getmListData().get(position).get(key),
		// img, R.drawable.nostart);
		String isstart = mSlpControll.getmListData().get(position)
				.get("carryOutStatus").toString();
		if ("0".equals(isstart)) {
			img.setBackgroundResource(R.drawable.nostart);
		} else if ("1".equals(isstart)) {
			img.setBackgroundResource(R.drawable.jinxing);
		} else if ("2".equals(isstart)) {
			img.setBackgroundResource(R.drawable.jieshu);
		}

	}

	@Override
	protected void itemClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// if (type.equals("4")) {
		// intent = new Intent(MyToDoListActivity.this, GuanaiActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// } else {
		intent = new Intent(MyToDoListActivity.this, QianDaoActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		System.out.println("listmap: " + mSlpControll.getmListData().size());
		System.out.println("status: "
				+ mSlpControll.getmListData().get(position)
						.get("carryOutStatus") + "");
		isstart = mSlpControll.getmListData().get(position)
				.get("carryOutStatus")
				+ "";
		System.out.println("isstart: " + isstart);
		intent.putExtra("isstart", isstart);
		intent.putExtra("id",
				mSlpControll.getmListData().get(position).get("id") + "");
		intent.putExtra("title",
				mSlpControll.getmListData().get(position).get("title") + "");
		intent.putExtra("type", type);
		System.out.println("signUpCount: "
				+ mSlpControll.getmListData().get(position).get("signUpCount")
				+ "");
		// if(type.equals("4"))
		// intent.putExtra("renshu",
		// mSlpControll.getmListData().get(position).get("signUpCount")+"");

		// }
		// intent.putExtra("type", type);
		// intent.putExtra("todo_id", listMap.get(position).get("todo_id")
		// .toString());
		// intent.putExtra("rel_id", listMap.get(position).get("rel_id")
		// .toString());
		startActivity(intent);
	}

	@Override
	public void onExecuteSuccess(int requestId, ResultObject resultObject) {
	}

	@Override
	public void setHeadMethod() {
		
//		String count=getIntent().getStringExtra("count");
		setHeadView(findViewById(R.id.headLayout),
				R.drawable.btn_back_thzhd, "", title, 0, "", null, null);
	
	}

	@Override
	public void setRequestMethod() {
		type = getIntent().getStringExtra("type");
		System.out.println("type: " + type);
		switch (Integer.parseInt(type)) {
		case 1:
			method = getString(R.string.my_todo_list1);
			break;
		case 2:
			method = getString(R.string.my_todo_list2);
			break;
		case 3:
			method = getString(R.string.my_todo_list3);
			break;
		case 4:
			method = getString(R.string.my_todo_list4);
			break;
		}
		parentResId = R.layout.mytodo_list;
	}
}
