package com.do1.aqzhdj.activity.circle;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.dqdp.android.common.HttpHelper;
import cn.com.do1.dqdp.android.common.JSONHelper;
import cn.com.do1.dqdp.android.component.MutexVisibility;
import cn.com.do1.dqdp.android.control.PageListActivity;
import cn.com.do1.dqdp.android.data.dqdp.DataReqListAdapter;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.circle.parser.ActivityHolder;
import com.do1.aqzhdj.activity.circle.parser.CircleItemHolder;
import com.do1.aqzhdj.activity.common.CircleMidleSwitcher;
import com.do1.aqzhdj.activity.parent.AppManager;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright ? 2013 广州市道一信息技术有限公司 All rights reserved. User: saron Date: 13-4-19
 * Time: 下午3:31 ★★★★★★★★★★★★★★★★★★★★★★★★★★ ★ Saron出品 ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleListActivity extends PageListActivity {
    DataReqListAdapter circleListAdapter = new DataReqListAdapter();
    DataReqListAdapter activityListAdapter = new DataReqListAdapter();
    String circleListId = "getMyCircleList";
    String myActivityListId = "myActivityList";
    MutexVisibility mutuxVisibility;
    CircleMidleSwitcher midleSwitcher;
    boolean isCircle = true;
    Constants constants;


    public void onCreate(Bundle savedInstanceState) {
    	constants = (Constants) getApplication();
        HttpHelper.setHttpPort(80);
        HttpHelper.setGlobalParam(
                getResources().getString(R.string.server_url), "user_id",
                constants.userInfo.getUserId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_list);
        setHead();
        midleSwitcher = new CircleMidleSwitcher(this, R.id.include_midle);
        midleSwitcher.setText("我的群组", "我的活动");
        super.getListView(R.id.listView_circle).setAdapter(circleListAdapter);
        super.getListView(R.id.listView_activity).setAdapter(activityListAdapter);
        mutuxVisibility = new MutexVisibility(createIntArry(R.id.listView_activity), createIntArry(R.id.listView_circle), createIntArry(R.id.lay_join));
        mutuxVisibility.addMutexVisibility(createIntArry(R.id.imgFinish), createIntArry(R.id.imgUnstart), createIntArry(R.id.imgStart));
        loadCircle();
    }

    private void initCircleBind() {
        super.bindAdapter(circleListAdapter, this);
        getDataSourceById(circleListId).bindParam("page_count", "10");
    }

    private void initActivityBind() {
        if (isCircle) {
            super.bindAdapter(activityListAdapter, this);
            getDataSourceById(myActivityListId).bindParam("page_count", "10");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isCircle) {
        	if(AppManager.needFreshForCircleClass){
	        	AppManager.needFreshForCircleClass = false;
	            if (circleListAdapter != null && circleListAdapter.getDataList() != null)
	                circleListAdapter.clearDataList();
	            loadCircle();
        	}
        } else {
        	if(AppManager.needFreshForCircleActivity){
        		AppManager.needFreshForCircleActivity = false;
	            if (activityListAdapter != null && activityListAdapter.getDataList() != null)
	                activityListAdapter.clearDataList();
	            loadActivity();
        	}
        }
    }

    public void loadCircle() {
        initCircleBind();
        isCircle = true;
        if (AssertUtil.isEmpty(circleListAdapter.getDataList()))
            circleListAdapter.updatePageData(circleListId, CircleItemHolder.class, "page_no");
    }

    public void loadActivity() {
        initActivityBind();
        isCircle = false;
        if (AssertUtil.isEmpty(activityListAdapter.getDataList()))
            activityListAdapter.updatePageData(myActivityListId, ActivityHolder.class, "page_no");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constants.NAGIVATEBAR_ID_START: {
                startActivity(CircleCreateMainActivity.class, null, null);
                break;
            }
            case R.id.button1:
                if (midleSwitcher.switchMidle(R.id.button1)) {
                    mutuxVisibility.setViewVisbility(getRootView(), R.id.listView_circle, View.VISIBLE);
                    loadCircle();
                }
                break;
            case R.id.button2:
                if (midleSwitcher.switchMidle(R.id.button2)) {
                    mutuxVisibility.setViewVisbility(getRootView(), R.id.listView_activity, View.VISIBLE);
                    loadActivity();
                }
                break;
            case R.id.button_join:
                startActivity(CircleJoinListActivity.class, null, null);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        i=i-1;
        try {
            if (isCircle) {
                List<JSONObject> objects = (List<JSONObject>) circleListAdapter.getDataList();
                String id = JSONHelper.getValueByPath("community_id", objects.get(i)).toString();
                String circleName = JSONHelper.getValueByPath("name", objects.get(i)).toString();
                String circleType = JSONHelper.getValueByPath("circletype", objects.get(i)).toString();
                
                Constants.circleInfo.setId(id);
                Constants.circleInfo.setName(circleName);
                Constants.circleInfo.setNumbers(JSONHelper.getValueByPath("numbers", objects.get(i)).toString());
                Constants.circleInfo.setLabels(JSONHelper.getValueByPath("labels", objects.get(i)).toString());
                Constants.circleInfo.setInfo(JSONHelper.getValueByPath("info", objects.get(i)).toString());
                Constants.circleInfo.setApplyNum(JSONHelper.getValueByPath("applyNum", objects.get(i)).toString());
                Constants.circleInfo.setCircletype(JSONHelper.getValueByPath("circletype", objects.get(i)).toString());
                Constants.circleInfo.setCreateUserId(JSONHelper.getValueByPath("create_userid", objects.get(i)).toString());
                Constants.circleInfo.setCreateUserName(JSONHelper.getValueByPath("createName", objects.get(i)).toString());

                Intent intent = new Intent(CircleListActivity.this, MyCircleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id", id);
                intent.putExtra("circleName", circleName);
                startActivity(intent);
                Log.d("dqdp", id);
            } else {
                List<JSONObject> objects = (List<JSONObject>) activityListAdapter.getDataList();
                String id = JSONHelper.getValueByPath("community_id", objects.get(i)).toString();
            }
        } catch (Exception e) {
            Log.e("dqdp", e.getMessage(), e);
        }
    }

    @Override
    public void defaultChoose(View view, Object data) {
        if (!isCircle) {
            String[] t = ((JSONObject) data).optString("time").split(" - ");
            Date activityStart = DateUtil.parse(t[0], "yyyy/M/d H:mm:ss");
            Date activityFinish = DateUtil.parse(t[1], "yyyy/M/d H:mm:ss");
            Date now = new Date();
            if (now.before(activityStart)) {
                mutuxVisibility.setViewVisbility(view, R.id.imgUnstart, View.VISIBLE);
            } else if (now.after(activityFinish)) {
                mutuxVisibility.setViewVisbility(view, R.id.imgFinish, View.VISIBLE);
            } else {
                mutuxVisibility.setViewVisbility(view, R.id.imgStart, View.VISIBLE);
            }
        }
    }

    @Override
    public void onEmptyResult() {
        if (isCircle) {
            mutuxVisibility.setViewVisbility(getRootView(), R.id.lay_join, View.VISIBLE);
        }
    }

    private void setHead() {
        String[] left = null;
        String[] right = {"添加与创建"};
        NagivateBar.initBarById(R.id.include_head, this, "群组", left, right);
    }


}