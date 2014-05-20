package com.do1.aqzhdj.activity.mine.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import cn.com.do1.component.util.JsonUtil;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.mine.DuesTestActivity;

public class DuesTestAdapter extends BaseAdapter {

	private Context ctx;
	private List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> afterListMap = new ArrayList<Map<String, Object>>();
	private String itm;
	private List<Map<String, Object>> itemListMap = new ArrayList<Map<String, Object>>();
	private boolean flag = false;
	protected LayoutInflater mInflater;
	private int itemTemplateId;
	private int clickble = 0;

	// RadioButton mRadioButton1;
	// RadioButton mRadioButton2;
	// RadioButton mRadioButton3;
	// RadioButton mRadioButton4;

	CheckBox mRadioButton1;
	CheckBox mRadioButton2;
	CheckBox mRadioButton3;
	CheckBox mRadioButton4;
	CheckBox mRadioButton5;
	CheckBox mRadioButton6;
	CheckBox mRadioButton7;
	CheckBox mRadioButton8;

//	private List<Map<String, Object>> mlistMap = new ArrayList<Map<String, Object>>();

	public DuesTestAdapter(Context ctx, List<Map<String, Object>> lsmap,
			int itemTemplateId,int clickble) {
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.listMap = lsmap;
		mInflater = LayoutInflater.from(ctx);
		this.clickble = clickble;
	}

	public DuesTestAdapter(Context ctx, List<Map<String, Object>> lsmap,
			List<Map<String, Object>> afterListMap,
			String itemListMap, boolean flag,
			int itemTemplateId,int clickble) {
		this.itemTemplateId = itemTemplateId;
		this.ctx = ctx;
		this.listMap = lsmap;
		this.afterListMap = afterListMap;
		this.itm = itemListMap;
		this.flag = flag;
		this.clickble = clickble;
		mInflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return listMap.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(itemTemplateId, null);
		if (!DuesTestActivity.ids.isEmpty() && position == 0) {
			DuesTestActivity.ids.clear();
		}
		final Map<String, Object> map = new HashMap<String, Object>();
		AQuery mAq = new AQuery(convertView);
		
		final TextView view = (TextView) convertView.findViewById(R.id.saveIds);

		mAq.id(R.id.content).text((position + 1)+ "."+ listMap.get(position).get("content").toString().trim());

		mRadioButton1 = (CheckBox) convertView.findViewById(R.id.checkbox1);
		mRadioButton2 = (CheckBox) convertView.findViewById(R.id.checkbox2);
		mRadioButton3 = (CheckBox) convertView.findViewById(R.id.checkbox3);
		mRadioButton4 = (CheckBox) convertView.findViewById(R.id.checkbox4);
		mRadioButton5 = (CheckBox) convertView.findViewById(R.id.checkbox5);
		mRadioButton6 = (CheckBox) convertView.findViewById(R.id.checkbox6);
		mRadioButton7 = (CheckBox) convertView.findViewById(R.id.checkbox7);
		mRadioButton8 = (CheckBox) convertView.findViewById(R.id.checkbox8);
		
		if(clickble == 1){
			mRadioButton1.setClickable(false);
			mRadioButton2.setClickable(false);
			mRadioButton3.setClickable(false);
			mRadioButton4.setClickable(false);
			mRadioButton5.setClickable(false);
			mRadioButton6.setClickable(false);
			mRadioButton7.setClickable(false);
			mRadioButton8.setClickable(false);
		}else{
			mRadioButton1.setClickable(true);
			mRadioButton2.setClickable(true);
			mRadioButton3.setClickable(true);
			mRadioButton4.setClickable(true);
			mRadioButton5.setClickable(true);
			mRadioButton6.setClickable(true);
			mRadioButton7.setClickable(true);
			mRadioButton8.setClickable(true);
		}

		String str = listMap.get(position).get("options").toString();
		final List<Map<String, Object>> mlistMap = JsonUtil.jsonArray2List(str);
		final int len = mlistMap.size();
		DuesTestActivity.exam_num = len;

//		mRadioButton1.setText(mlistMap.get(0).get("option_content").toString());
//		mRadioButton2.setText(mlistMap.get(1).get("option_content").toString());
//		mRadioButton3.setText(mlistMap.get(2).get("option_content").toString());
//		if (len == 3) {
			mRadioButton1.setVisibility(View.GONE);
			mRadioButton2.setVisibility(View.GONE);
			mRadioButton3.setVisibility(View.GONE);
			mRadioButton4.setVisibility(View.GONE);
			mRadioButton5.setVisibility(View.GONE);
			mRadioButton6.setVisibility(View.GONE);
			mRadioButton7.setVisibility(View.GONE);
			mRadioButton8.setVisibility(View.GONE);
//		}
		if (len >= 1) {
			mRadioButton1.setText(mlistMap.get(0).get("option_content").toString());
			mRadioButton1.setVisibility(View.VISIBLE);
		} 
		if (len >= 2) {
			mRadioButton2.setText(mlistMap.get(1).get("option_content").toString());
			mRadioButton2.setVisibility(View.VISIBLE);
		} 
		if (len >= 3) {
			mRadioButton3.setText(mlistMap.get(2).get("option_content").toString());
			mRadioButton3.setVisibility(View.VISIBLE);
		} 
		if (len >= 4) {
			mRadioButton4.setText(mlistMap.get(3).get("option_content").toString());
			mRadioButton4.setVisibility(View.VISIBLE);
		} 
		if(len >= 5){
			mRadioButton5.setText(mlistMap.get(4).get("option_content").toString());
			mRadioButton5.setVisibility(View.VISIBLE);
		}
		if(len >= 6){
			mRadioButton6.setText(mlistMap.get(5).get("option_content").toString());
			mRadioButton6.setVisibility(View.VISIBLE);
		}
		if(len >= 7){
			mRadioButton6.setText(mlistMap.get(6).get("option_content").toString());
			mRadioButton6.setVisibility(View.VISIBLE);
		}
		if(len >= 8){
			mRadioButton8.setText(mlistMap.get(7).get("option_content").toString());
			mRadioButton8.setVisibility(View.VISIBLE);
		}

		map.put("exam_id", listMap.get(position).get("exam_id").toString());
		
		if(!flag){
			mRadioButton1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (isChecked) {
						view.setText(view.getText()+mlistMap.get(0).get("option_id").toString()+",");
						map.put("option_id", view.getText());
					}else{
						view.setText(view.getText().toString().replace(mlistMap.get(0).get("option_id")+",", ""));
						map.put("option_id", view.getText());
					}
				}
			});

			mRadioButton2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (isChecked) {
						view.setText(view.getText()+mlistMap.get(1).get("option_id").toString()+",");
						map.put("option_id", view.getText());
					}else{
						view.setText(view.getText().toString().replace(mlistMap.get(1).get("option_id")+",", ""));
						map.put("option_id", view.getText());
					}
				}
			});

			mRadioButton3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
						if (isChecked) {
							view.setText(view.getText()+mlistMap.get(2).get("option_id").toString()+",");
							map.put("option_id", view.getText());
						}else{
							view.setText(view.getText().toString().replace(mlistMap.get(2).get("option_id")+",", ""));
							map.put("option_id", view.getText());
						}
					}
				});

			mRadioButton4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
						if (isChecked) {
							view.setText(view.getText()+mlistMap.get(3).get("option_id").toString()+",");
							map.put("option_id", view.getText());
						}else{
							view.setText(view.getText().toString().replace(mlistMap.get(3).get("option_id")+",", ""));
							map.put("option_id", view.getText());
						}
					}
				});
			
			mRadioButton5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
						if (isChecked) {
							view.setText(view.getText()+mlistMap.get(4).get("option_id").toString()+",");
							map.put("option_id", view.getText());
						}else{
							view.setText(view.getText().toString().replace(mlistMap.get(4).get("option_id")+",", ""));
							map.put("option_id", view.getText());
						}
					}
				});
			mRadioButton6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
						if (isChecked) {
							view.setText(view.getText()+mlistMap.get(5).get("option_id").toString()+",");
							map.put("option_id", view.getText());
						}else{
							view.setText(view.getText().toString().replace(mlistMap.get(5).get("option_id")+",", ""));
							map.put("option_id", view.getText());
						}
					}
				});
			mRadioButton7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
						if (isChecked) {
							view.setText(view.getText()+mlistMap.get(6).get("option_id").toString()+",");
							map.put("option_id", view.getText());
						}else{
							view.setText(view.getText().toString().replace(mlistMap.get(6).get("option_id")+",", ""));
							map.put("option_id", view.getText());
						}
					}
				});
			mRadioButton8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
						if (isChecked) {
							view.setText(view.getText()+mlistMap.get(7).get("option_id").toString()+",");
							map.put("option_id", view.getText());
						}else{
							view.setText(view.getText().toString().replace(mlistMap.get(7).get("option_id")+",", ""));
							map.put("option_id", view.getText());
						}
					}
				});
			DuesTestActivity.ids.add(map);
		}
		
		if (flag) {
			itemListMap = JsonUtil.jsonArray2List(itm);
			for (int i = 0; i < itemListMap.size(); i++) {
				String[] str2 = itemListMap.get(i).get("option_id")
						.toString().split(",");
				for (int j = 0; j < str2.length; j++) {
					for (int k = 0; k < mlistMap.size(); k++) {
						if (str2[j].equals(mlistMap.get(k).get("option_id").toString())) {
							switch (k) {
							case 0:
								mRadioButton1.setChecked(true);
								break;
							case 1:
								mRadioButton2.setChecked(true);
								break;
							case 2:
								mRadioButton3.setChecked(true);
								break;
							case 3:
								mRadioButton4.setChecked(true);
								break;
							case 4:
								mRadioButton5.setChecked(true);
								break;
							case 5:
								mRadioButton6.setChecked(true);
								break;
							case 6:
								mRadioButton7.setChecked(true);
								break;
							case 7:
								mRadioButton8.setChecked(true);
								break;
							default:
								break;
							}
						}
					}
				}
			}
			
			
			for (int i = 0; i < afterListMap.size(); i++) {
				if (afterListMap.get(i).get("exam_id").toString()
						.equals(listMap.get(position).get("exam_id").toString())) {

					String[] str1 = afterListMap.get(i).get("option_id")
							.toString().split(",");
					for (int j = 0; j < str1.length; j++) {
						for (int k = 0; k < mlistMap.size(); k++) {
							if (str1[j].equals(mlistMap.get(k).get("option_id")
									.toString())) {
								switch (k) {
								case 0:
									mRadioButton1.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 1:
									mRadioButton2.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 2:
									mRadioButton3.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 3:
									mRadioButton4.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 4:
									mRadioButton5.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 5:
									mRadioButton6.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 6:
									mRadioButton7.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								case 7:
									mRadioButton8.setTextColor(ctx.getResources().getColor(R.color.red));
									break;
								default:
									break;
								}
							}
						}
					}
				}
			}
		}
		return convertView;
	}
	
	
}
