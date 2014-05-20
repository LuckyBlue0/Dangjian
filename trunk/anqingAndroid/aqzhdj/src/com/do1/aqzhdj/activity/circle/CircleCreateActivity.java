package com.do1.aqzhdj.activity.circle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.dqdp.android.common.ViewHelper;
import cn.com.do1.dqdp.android.component.DqdpEditText;
import cn.com.do1.dqdp.android.component.DqdpValueText;
import cn.com.do1.dqdp.android.control.DataReqActivity;
import cn.com.do1.dqdp.android.data.IDataBundler;
import cn.com.do1.dqdp.android.data.IDataParser;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.activity.IndexActivity;
import com.do1.aqzhdj.activity.common.ZhdjBaseDataParser;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-20
 * Time: 下午3:26
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleCreateActivity extends DataReqActivity {
	
	DqdpEditText textname;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_create);
        setHead();
        findViewById(R.id.lay_tag).setOnClickListener(this);
        textname = (DqdpEditText) findViewById(R.id.circle_name);
    }

    private void setHead() {
        String[] left = {"添加与创建"};
        String[] right = null;
        NagivateBar.initBarById(R.id.include_head, this, "创建群", left, right);
    }

    public void onTipClick() {
        this.finish();
    }

    @Override
    public void onHttpOK(IDataBundler dataBundler, IDataParser dataParser)
    		throws Exception {
    	ToastUtil.showLongMsg(this, "操作成功");
    	Intent intent = new Intent(this,IndexActivity.class);
    	intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	intent.putExtra("tabId", "群组");
    	startActivity(intent);
    	finish();
    }
    
    @Override
    public void onHttpFaile(IDataBundler dataBundler, IDataParser dataParser)
    		throws Exception {
    	ZhdjBaseDataParser parser = (ZhdjBaseDataParser)dataParser;
    	String desc = parser.getData().optString("desc");
    	ToastUtil.showLongMsg(this, desc);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            DqdpValueText view = ((DqdpValueText) findViewById(R.id.textView2));
            view.setTextAndValue(data.getStringExtra("tagName"), data.getStringExtra("tagId"));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constants.NAGIVATEBAR_ID_START:
                //startActivity(CircleCreateMainActivity.class, null, null);
                finish();
                break;
            case R.id.lay_tag:
                startActivityForResult(100, TagSelectActivity.class, new String[]{"tagId"}, new String[]{ViewHelper.getStringValue(R.id.textView2,this)});
                break;
            case R.id.button:
            	String content = textname.getText().toString().trim();
            	if(content.length() > 12){
            		ToastUtil.showShortMsg(this, "群名称不能超过12个字，请重新输入");
            		textname.setText("");
            		return;
            	}
                runHttpReq("createCircle");
        }
    }
}