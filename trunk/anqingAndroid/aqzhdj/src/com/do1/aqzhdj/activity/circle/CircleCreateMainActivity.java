package com.do1.aqzhdj.activity.circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.component.widget.BaseAdapterWrapper;
import cn.com.do1.dqdp.android.control.BaseActivity;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-19
 * Time: 下午4:41
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleCreateMainActivity extends BaseActivity implements BaseAdapterWrapper.ItemViewHandler, AdapterView.OnItemClickListener {
    private Constants constants;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constants = (Constants) getApplication();
        setContentView(R.layout.circle_create_main);
        setHead();
        loadDefautContent();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constants.NAGIVATEBAR_ID_START:
                // startActivity(IndexActivity.class, createStrArry("tabId"), createStrArry("群组"));
                finish();
                break;
        }
    }

    private void loadDefautContent() {
        ListView circleList = (ListView) findViewById(R.id.list_circle);
        circleList.setOnItemClickListener(this);
        int[] imgs = {R.drawable.circle_logo_createcircle, R.drawable.circle_logo_joincircle};
        String[] titles = {"创建群", "加入群"};
        String[] from = {"img", "title"};
        int[] to = {R.id.item_circle_logo, R.id.item_circle_text};
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < titles.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", titles[i]);
            map.put("img", imgs[i]);
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.circle_create_item, from, to);
        circleList.setAdapter(new BaseAdapterWrapper(adapter, this));
        ListView tagList = (ListView) findViewById(R.id.list_title);
        tagList.setOnItemClickListener(this);
        imgs = new int[]{R.drawable.circle_logo_createtag, R.drawable.circle_logo_tagarea};
        titles = new String[]{"创建标签", "标签区"};
        from = new String[]{"img", "title"};
        to = new int[]{R.id.item_circle_logo, R.id.item_circle_text};
        data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < titles.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", titles[i]);
            map.put("img", imgs[i]);
            data.add(map);
        }
        adapter = new SimpleAdapter(this, data, R.layout.circle_create_item, from, to);
        tagList.setAdapter(new BaseAdapterWrapper(adapter, this));
    }

    @Override
    public void handleItemView(BaseAdapter adapter, int position, View itemView, ViewGroup parent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = ((TextView) view.findViewById(R.id.item_circle_text)).getText().toString();
        if ("创建群".equals(text)) {
        	//党员账户，有权限使用论坛，群众帐号没有使用论坛的权限
			if("1".equals(constants.userInfo.getUser_type())){
				Intent intent = new Intent(this, CircleCreateActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
			}else{
				ToastUtil.showLongMsg(CircleCreateMainActivity.this, "您不是党员用户，暂时无法访问！");
			}
        }
        if ("加入群".equals(text)) {
            Intent intent = new Intent(this, CircleJoinListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if ("创建标签".equals(text)) {
            Intent intent = new Intent(this, CircleTagCreateActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if ("标签区".equals(text)) {
            Intent intent = new Intent(this, TagHostActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
//       switch (i){
//           case 0:
//       }
    }

    private void setHead() {
        String[] left = {"群组"};
        String[] right = null;
        NagivateBar.initBarById(R.id.include_head, this, "添加与创建", left, right);
    }
}