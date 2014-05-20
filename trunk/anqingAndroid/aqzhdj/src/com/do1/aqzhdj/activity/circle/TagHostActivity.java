package com.do1.aqzhdj.activity.circle;

import java.util.Collection;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.reflation.ConvertUtil;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.dqdp.android.common.Pager;
import cn.com.do1.dqdp.android.component.MutexVisibility;
import cn.com.do1.dqdp.android.control.DataReqListActivity;
import cn.com.do1.dqdp.android.data.IDataBundler;
import cn.com.do1.dqdp.android.data.IDataParser;
import cn.com.do1.dqdp.android.data.IDataSource;
import cn.com.do1.dqdp.android.data.dqdp.DataReqListAdapter;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.circle.parser.TagVotesHolder;
import com.do1.aqzhdj.activity.common.CircleMidleSwitcher;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-23
 * Time: 下午3:21
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TagHostActivity extends DataReqListActivity {
    DataReqListAdapter listAdapter = new DataReqListAdapter();
    boolean isHot = false;
    CircleMidleSwitcher midleSwitcher;
    MutexVisibility mutexVisibility;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_tag_hotlist);
        super.getListView(R.id.listView).setAdapter(listAdapter);
        setHead();
        midleSwitcher = new CircleMidleSwitcher(this, R.id.include_midle);
        midleSwitcher.setText("热门标签", "标签投票区");
        mutexVisibility = new MutexVisibility(createIntArry(R.id.listView,R.id.textTip),createIntArry(R.id.taghot_lay));
        loadHotTag();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constants.NAGIVATEBAR_ID_START:
                //startActivity(CircleCreateMainActivity.class, null, null);
            	finish();
                break;
            case R.id.button1:
                if (midleSwitcher.switchMidle(R.id.button1))
                    mutexVisibility.setViewVisbility(getRootView(),R.id.taghot_lay,View.VISIBLE);
                break;
            case R.id.button2:
                if (midleSwitcher.switchMidle(R.id.button2)) {
                    mutexVisibility.setViewVisbility(getRootView(),R.id.listView,View.VISIBLE);
                    loadTagList();
                }
                break;
        }
    }

    private void loadTagList() {
        isHot = false;
        if (AssertUtil.isEmpty(listAdapter.getDataList())) {
            initBind();
            listAdapter.updatePageData("getTagList",TagVotesHolder.class,"page_no");
        }
    }

    @Override
    public void onHttpOK(IDataBundler dataBundler, IDataParser dataParser) throws Exception {
        if ("getTagList".equals(dataParser.getId()) && isHot) {
            Collection<JSONObject> tagList = ((Pager) dataParser.getAvalibelData()).getPageData();
            int i = 0;
            ViewGroup layViews = ((ViewGroup) findViewById(R.id.taghot_lay));
            for (JSONObject jsonObject : tagList) {
                TextView view = (TextView) layViews.getChildAt(i);
                view.setText(jsonObject.optString("name"));
                view.setVisibility(View.VISIBLE);
                i++;
            }
            
            return;
        }
        if ("doVote".equals(dataParser.getId())) {
            votedView.setText((ConvertUtil.getInt(votedView.getText().toString()) + 1) + "");
            Toast.makeText(this, "投票成功", Toast.LENGTH_LONG).show();
            return;
        }
        
        super.onHttpOK(dataBundler, dataParser);
    }
    public void onHttpFail(IDataBundler dataBundler, IDataParser dataParser) throws Exception{
    	ToastUtil.showShortMsg(this, dataParser.getErrorMsg());
    	return;
    }
    private IDataSource getDS() {
        return getDataSourceById("getTagList");
    }

    private void loadHotTag() {
        isHot = true;
        getDS().batchBindParams(new String[]{"type","page_no","page_count"}, new String[]{"3","1","12"});
        runHttpReq("getTagList");
    }

    private void initBind() {
        if (listAdapter.getDataList() != null && listAdapter.getDataList().size() > 0)
            return;
        super.bindAdapter(listAdapter, this);
        getDS().batchBindParams(createStrArry("type","page_count"), createStrArry("2","10"));
    }

    TextView votedView = null;

    @Override
    public void defaultChoose(final View view, final Object data) {
        if (!isHot)
            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    getDataSourceById("doVote").bindParam("label_id", ((JSONObject) data).optString("label_id"));
                    runHttpReq("doVote");
                    votedView = (TextView) view.findViewById(R.id.text_tagVotes);
                }
            });
    }

    private void setHead() {
        String[] left = {"添加与创建"};
        String[] right = null;
        NagivateBar.initBarById(R.id.include_head, this, "标签区", left, right);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}