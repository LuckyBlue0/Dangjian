package com.do1.aqzhdj.activity.circle;

import android.os.Bundle;
import android.view.View;
import cn.com.do1.component.util.ToastUtil;
import cn.com.do1.dqdp.android.component.DqdpEditText;
import cn.com.do1.dqdp.android.control.DataReqActivity;
import cn.com.do1.dqdp.android.data.IDataBundler;
import cn.com.do1.dqdp.android.data.IDataParser;

import com.do1.aqzhdj.R;
import com.do1.aqzhdj.util.Constants;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-20
 * Time: 下午5:50
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class CircleTagCreateActivity extends DataReqActivity {
	
	DqdpEditText textname;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_tag_create);
        textname = (DqdpEditText) findViewById(R.id.text_tagName);
        setHead();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case Constants.NAGIVATEBAR_ID_START:
                finish();
                break;
//            case Constants.NAGIVATEBAR_ID_START + 1:
//                startActivity(CircleCreateMainActivity.class, null, null);
//                break;
            case R.id.button:
            	String content = textname.getText().toString().trim();
            	if(content.length() > 12){
            		ToastUtil.showShortMsg(this, "标签名称不能超过12个字，请重新输入");
            		textname.setText("");
            		return;
            	}
                runHttpReq("createTag");
                break;
        }
    }

    @Override
    public void onHttpOK(IDataBundler dataBundler, IDataParser dataParser) throws Exception {
        ToastUtil.showLongMsg(this, "操作成功");
        finish();
    }

    public void onTipClick() {
        this.finish();
    }

    private void setHead() {
        Object[] left = {"添加与创建"};
        Object[] right = null;
        NagivateBar.initBarById(R.id.include_head, this, "创建标签", left, right);
    }
}