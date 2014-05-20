package com.do1.aqzhdj.activity.bbs.wall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import cn.com.do1.component.parse.ResultObject;

import com.androidquery.AQuery;
import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.activity.parent.BaseListActivity;


/**
 * 留影墙--首页
 * auth:YanFangqin
 * data:2013-4-25
 * thzhd
 */
public class WallIndexActivity extends BaseListActivity{

	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;
		aq = new AQuery(this);
		String[] keys = new String[]{"name"};
        int[] ids = new int[]{R.id.title};
        Map<String, Object> map = new HashMap<String, Object>();
        setAdapterParams(keys,ids,R.layout.wall_item,map);
	}

	@Override
	public void setHeadMethod() {
		setHeadView(findViewById(R.id.headLayout), R.drawable.btn_back_thzhd,"", "留影墙", 0,"", null, null);		
	}
	
	@Override
	public void success(ResultObject resultObject) {
		super.success(resultObject);

		WallUtil.listTypeId.clear();
		WallUtil.listTypeName.clear();
		List<Map<String, Object>> list = resultObject.getListMap();
		for(int i = 0; i < list.size(); i++){
			if(!"专题图片".equals(list.get(i).get("type_id")+"")){
				WallUtil.listTypeId.add(list.get(i).get("type_id")+"");
				WallUtil.listTypeName.add(list.get(i).get("name")+"");
			}
		}
	}

	@Override
	public void setRequestMethod() {
		method = getString(R.string.wall_type);
	}
	
	@Override
	protected void itemClick(AdapterView<?> parent, View view, int position,long id) {
		super.itemClick(parent, view, position, id);
		
		Intent intent = new Intent(context,WallImageListActivity.class);
//		intent.putExtra("typeId", mSlpControll.getmListData().get(position).get("typeId").toString());
//		intent.putExtra("title", mSlpControll.getmListData().get(position).get("name").toString());
		AppManager.typeId=mSlpControll.getmListData().get(position).get("typeId").toString();
		AppManager.title=mSlpControll.getmListData().get(position).get("name").toString();
		startActivity(intent);
	}
	
}
