package cn.com.do1.component.demo.demomodel.ui;

import cn.com.do1.common.framebase.spring.BaseCtrl;
import cn.com.do1.component.demo.demomodel.model.TbDqdpConfigPO;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-2-22
 * Time: 下午3:44
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
@Controller
@RequestMapping("/demo")
public class DemomodelCtrl extends BaseCtrl {
    @RequestMapping("/m1.sm")
    public @ResponseBody String demo(TbDqdpConfigPO bean){
        JSONObject obj = new JSONObject();
        obj.put("test",bean.getConfigName());
        return obj.toJSONString();
    }

    @RequestMapping("/m2.sm")

    public @ResponseBody Object demo2(TbDqdpConfigPO bean){
        Map map =new HashMap();
        map.put("abc","123");
        return map;
    }
}
