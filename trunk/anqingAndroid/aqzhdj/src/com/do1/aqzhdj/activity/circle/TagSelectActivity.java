package com.do1.aqzhdj.activity.circle;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import cn.com.do1.dqdp.android.component.DqdpEditText;
import cn.com.do1.dqdp.android.control.DataReqListActivity;
import cn.com.do1.dqdp.android.data.dqdp.DataReqListAdapter;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.circle.parser.TagSelectHolder;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-20
 * Time: 下午4:38
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TagSelectActivity extends DataReqListActivity {
    DataReqListAdapter listAdapter = new DataReqListAdapter();
    public String tagId;
    private View preView = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagId = getIntent().getStringExtra("tagId");
        setContentView(R.layout.circle_tag_select);
        setHead();
        super.getListView(R.id.listView).setAdapter(listAdapter);
        initBind();
        listAdapter.updatePageData("getTagList",TagSelectHolder.class,"page_no");
        
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constants.NAGIVATEBAR_ID_START:
                finish();
                break;
            case Constants.NAGIVATEBAR_ID_START + 1:
                finish();
                break;
            case R.id.image_search_btn:
                listAdapter.clearDataList();
                listAdapter.updatePageData("getTagList",TagSelectHolder.class,"page_no");
        }
    }


    private void initBind() {
        super.bindAdapter(listAdapter, this);
        ((DqdpEditText) findViewById(R.id.searchText)).getDqdpAttrs().addBindDS("getTagList", "keyword");
        getDataSourceById("getTagList").batchBindParams(createStrArry("type","page_count"), createStrArry("4","10"));
    }

    @Override
    public void defaultChoose(View view, Object data) {
        if (((JSONObject) data).optString("label_id").equals(tagId)) {
        	view.findViewById(R.id.imageView).setVisibility(view.VISIBLE);
        	preView = view;
        }
        else {
        	view.findViewById(R.id.imageView).setVisibility(View.GONE);
        }
            
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        view.findViewById(R.id.imageView).setVisibility(view.getVisibility());
        Intent _intent = new Intent();
        _intent.putExtra("tagId", ((JSONObject) listAdapter.getDataList().get(i)).optString("label_id"));
        _intent.putExtra("tagName", ((JSONObject) listAdapter.getDataList().get(i)).optString("name"));
        setResult(100, _intent);
        // 把原有的勾
        if(null != preView) {
        	preView.findViewById(R.id.imageView).setVisibility(View.GONE);
        }
        finish();
    }

    private void setHead() {
        Object[] left = {R.drawable.btn_back_thzhd};
        Object[] right = null;
        NagivateBar.initBarById(R.id.include_head, this, "选择标签", left, right);
    }
}