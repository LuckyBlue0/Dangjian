package cn.com.do1.dqdp.creater;

import org.junit.Test;

import cn.com.do1.common.framebase.dqdp.PageViewField;
import cn.com.do1.component.dqdploger.log.model.TbDqdpLogPO;
import cn.com.do1.dqdp.creater.jsp.JspCreater;
import cn.com.do1.dqdp.creater.jsp.JspType;
import cn.com.do1.dqdp.creater.jsp.model.Button;
import cn.com.do1.dqdp.creater.jsp.model.Link;
import cn.com.do1.dqdp.creater.jsp.model.ListField;
import cn.com.do1.dqdp.creater.jsp.viewer.element.CenterToolsbar;
import cn.com.do1.dqdp.creater.jsp.viewer.element.Form;
import cn.com.do1.dqdp.creater.jsp.viewer.element.InfoCard;
import cn.com.do1.dqdp.creater.jsp.viewer.element.List;
import cn.com.do1.dqdp.creater.jsp.viewer.element.OperationToolsBar;
import cn.com.do1.dqdp.creater.jsp.viewer.element.PageBar;
import cn.com.do1.dqdp.creater.jsp.viewer.element.Query;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-8-14
 * Time: 下午5:37
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class JspCreaterTest {
    @Test
    public void testCreate() throws Exception {

        JspCreater creater = new JspCreater("D:\\MyProject\\DO1\\dqdp\\models\\dqdp-web\\src\\main\\webapp", "creater", "default");
        Form form = new Form("creater", "default");
        form.setActionURL("xxx.action");
        form.setMethod("post");
        creater.setJspType(JspType.ADD);
        form.setViewObj("test", new TbDqdpLogPO());
        creater.addPageElement(form);

        CenterToolsbar toolsbar = new CenterToolsbar("creater", "default");
        Button button = new Button();
        button.setOnclick("func_" + form.getId() + "()");
        button.setValue("测试按钮");
        toolsbar.addButton(button);

        creater.addPageElement(toolsbar);
        creater.create();
    }

    @Test
    public void testCreate1() throws Exception {

        JspCreater creater = new JspCreater("D:\\MyProject\\DO1\\dqdp\\models\\dqdp-web\\src\\main\\webapp", "creater", "default");
        creater.setJspType(JspType.QUERYLIST);
        Query query = new Query("creater", "default");
        PageViewField queryField1 = new PageViewField("xxx", "测试字段1", "select");
        PageViewField queryField2 = new PageViewField("xxx2", "测试字段2", "input");
        query.addQueryField(queryField1);
        query.addQueryField(queryField2);
        query.setSearchURL("demomodel/demomodel!testList.action");
        creater.addPageElement(query);

        OperationToolsBar toolsBar1 = new OperationToolsBar("creater", "default");
                toolsBar1.addButton("新增","xxx");
                toolsBar1.addButton("删除","xxx");


        List list = new List("creater", "default");
        ListField field1 = new ListField();
        field1.setName("abc1");
        field1.setLinkURL("abc.action1");
        field1.setShowName("字段1");
        field1.setShowSize(100);
        field1.setTdWidth("20%");
        list.addField(field1);
        ListField field2 = new ListField();
        field2.setName("abc2");
        field2.setLinkURL("abc.action2");
        field2.setShowName("字段2");
        field2.setShowSize(100);
        field2.setTdWidth("20%");
        list.addField(field2);
        ListField field3 = new ListField();
        field3.setName("abc3");
        field3.setLinkURL("abc.action3");
        field3.setShowName("字段3");
        field3.setShowSize(100);
        field3.setTdWidth("20%");
        list.addField(field3);
        Link link1 = new Link("编辑", "javascript:_doSignlEdit('"+list.getId()+"','@{id}')");
        Link link2 = new Link("删除", "javascript:_doSignlDel('"+list.getId()+"','@{id}')");
        list.addOperation(link1);
        list.addOperation(link2);
        list.setDataSrcId(query.getId());
//        list.setDataSrc("xxx.action");
        list.setCheckId("checked_field");

        PageBar pageBar = new PageBar("creater", "default");
        pageBar.setQuery(query);
        creater.addPageElement(pageBar);
        creater.addPageElement(toolsBar1);
        creater.addPageElement(list);
        PageBar pageBar1 = new PageBar("creater", "default");
        pageBar1.setQuery(query);
        creater.addPageElement(pageBar1);
        creater.create();
    }

    @Test
        public void testCreate2() throws Exception {
            JspCreater creater = new JspCreater("D:\\MyProject\\DO1\\dqdp\\models\\dqdp-web\\src\\main\\webapp", "creater", "default");
            InfoCard form = new InfoCard("creater", "default");
            form.setDataSrc("xxx.action");
            creater.setJspType(JspType.DETAIL);
            form.setViewObj("test", new TbDqdpLogPO());
            creater.addPageElement(form);

            CenterToolsbar toolsbar = new CenterToolsbar("creater", "default");
            Button button = new Button();
            button.setOnclick("func_" + form.getId() + "()");
            button.setValue("测试按钮");
            toolsbar.addButton(button);

            creater.addPageElement(toolsbar);
            creater.create();
        }
}
