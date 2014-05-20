package com.do1.zhdj.activity.common;

import org.json.JSONObject;

import android.util.Log;
import cn.com.do1.dqdp.android.control.ResultType;
import cn.com.do1.dqdp.android.data.dqdp.DqdpDataParser;

import com.do1.zhdj.util.Entryption;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-4-22
 * Time: 上午10:42
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class ZhdjBaseDataParser extends DqdpDataParser {
    @Override
    public void setData(Object respObject) {
        if (respObject != null)
            try {
                Entryption.encode("");
                String jsonStr = Entryption.decode(respObject.toString());
                Log.d("zhdj",jsonStr);
                super.setData(new JSONObject(jsonStr));
            } catch (Exception e) {
                errorMsg = (e.getMessage());
                Log.e("zhdj", e.getMessage(), e);
            }
        else
            errorMsg = "网络异常，未能正确返回数据";
    }

    @Override
    public <T> T getAvalibelData() throws Exception {
        if(data==null)return null;
        JSONObject jsonObject=data.optJSONObject("data");
        return jsonObject==null?(T)new JSONObject():(T)jsonObject;
    }
    
    public JSONObject getData() throws Exception {
        if(data==null)return null;
        return data;
    }

    @Override
    public boolean isOK() throws Exception {
        return data != null && data.getInt("code") == 1;
    }

    @Override
    public ResultType getDataType() {
        return ResultType.CONTENT;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
