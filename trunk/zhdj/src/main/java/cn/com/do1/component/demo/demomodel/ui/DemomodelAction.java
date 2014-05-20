package cn.com.do1.component.demo.demomodel.ui;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.com.do1.common.annotation.struts.ActionRoles;
import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.annotation.struts.SignlAccess;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.demo.demomodel.model.TbRbacUserPO;
import cn.com.do1.component.demo.demomodel.model.TestVO;
import cn.com.do1.component.demo.demomodel.service.IDemomodelService;

/**
 * Copyright ? 2010 广州市道一信息技术有限公司
 * All rights reserved.
 * User: ${user}
 */

public class DemomodelAction extends BaseAction {
    Logger logger = Logger.getLogger(getClass());
    IDemomodelService demomodelService;
    TbRbacUserPO userPO;//用于和页面传递数据
    String ids[];
    String id;
    String[] tc;

    public String[] getTc() {
        return tc;
    }

    public void setTc(String[] tc) {
        this.tc = tc;
    }

    public IDemomodelService getDemomodelService() {
        return demomodelService;
    }

    @Resource
    public void setDemomodelService(IDemomodelService demomodelService) {
        this.demomodelService = demomodelService;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }


    public class Haha {
        String haha1;
        String haha2;
        String haha3;
Integer haha4;
        String _abc_desc;

        public String get_abc_desc() {
            return _abc_desc;
        }

        public void set_abc_desc(String _abc_desc) {
            this._abc_desc = _abc_desc;
        }

        public Integer getHaha4() {
            return haha4;
        }

        public void setHaha4(Integer haha4) {
            this.haha4 = haha4;
        }

        public String getHaha1() {
            return haha1;
        }

        public void setHaha1(String haha1) {
            this.haha1 = haha1;
        }

        public String getHaha2() {
            return haha2;
        }

        public void setHaha2(String haha2) {
            this.haha2 = haha2;
        }

        public String getHaha3() {
            return haha3;
        }

        public void setHaha3(String haha3) {
            this.haha3 = haha3;
        }
    }

    File newsFile;

    public File getNewsFile() {
        return newsFile;
    }

    public void setNewsFile(File newsFile) {
        this.newsFile = newsFile;
    }

    @JSONOut(catchException = @CatchException(errCode = "1001",faileMsg = "xxx",successMsg = "cccc"))
    public void uploadFile(){
        System.out.println(newsFile ==null);
    }
    public class TestAddObj {
        String radio1;
        String carId;
        String deptName;
        String userName;
        String bb;
        String remark;
        List<TbRbacUserPO> strList;
        private String img;

        public List<TbRbacUserPO> getStrList() {
            return strList;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setStrList(List<TbRbacUserPO> strList) {
            this.strList = strList;
        }

        public String getRadio1() {
            return radio1;
        }

        public void setRadio1(String radio1) {
            this.radio1 = radio1;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getBb() {
            return bb;
        }

        public void setBb(String bb) {
            this.bb = bb;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg() {
            return img;
        }
    }

    /**
     * 下面这个方法是用于将数据放入页面,相关联的页面请见/jsp/component/demo/add.jsp
     */
//    @ActionRoles({"ROLE_TEST1"})//访问此方法需要的权限
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void testAddGet() throws Exception, BaseException {
//        if(1==1)
//        throw new BaseException("ABC");
        TestAddObj obj = new TestAddObj();
        obj.setCarId("carId1");
        obj.setDeptName("部门1");
        obj.setRadio1("1");
        obj.setUserName("user1");
        obj.setImg("big_btn_left.gif");
        obj.setBb("2");
        List<TbRbacUserPO> stringList = new ArrayList<TbRbacUserPO>();
        for (int i = 0; i < 4; i++) {
            TbRbacUserPO po1 = new TbRbacUserPO();
            po1.setAccount("account" + i);
            po1.setId("id" + i);
            stringList.add(po1);
        }
        obj.setStrList(stringList);

        obj.setRemark("<div class=\"guide\">\n" +
                "                <h3>1.我是第一次使用这个功能，是否能够不注册就使用预约挂号?</h3>\n" +
                "                <p>答：首次使用预约挂号，需要进行注册才能够使用预约挂号功能，可点击右上角的“免费注册”按钮进行注册。</p>\n" +
                "             </div>\n" +
                "             <div class=\"guide\">\n" +
                "                <h3>2.办理预约挂号后，如何就诊？</h3>\n" +
                "                <p>答：您凭借收到的短信预约编号，到医院的门诊大厅总服务台或自助终端机上打印挂号单，然后到挂号窗口缴纳挂号费及诊金，随后凭借挂号单和病历本，到分诊台排队等候就可以了。</p>\n" +
                "             </div>   \n" +
                "             <div class=\"guide\">\n" +
                "                <h3>3.为什么要编写个人中心的基本信息？</h3>\n" +
                "                <p>答：这是为了方便您在填写预约资料时不用重复填写这些信息。</p>\n" +
                "             </div>   \n" +
                "             <div class=\"guide\">\n" +
                "                <h3>4.一天内对同一科室可预约几次？</h3>\n" +
                "                <p>答：一次。</p>\n" +
                "             </div>\n" +
                "             <div class=\"guide\">\n" +
                "                <h3>5.如果预约了下午的号，但下午没有去，请问这个预约还有效吗？能不能改成第二天再去？</h3>\n" +
                "                <p>答：如您没有按照预约时间到医院就诊，预约将视为作废。</p>\n" +
                "             </div>\n" +
                "             <div class=\"guide\">\n" +
                "                <h3>6.挂号的就医时间具有多长的有效期？</h3>\n" +
                "                <p>答：用户必须按预约的时间提前在医院门诊大厅办理有关手续，并在预约时间段内到分诊台就诊，逾时未到分诊台就诊，预约失效，做普通挂号处理。</p>\n" +
                "             </div>\n" +
                "             <div class=\"guide\">\n" +
                "                <h3>7.为何有些医生不能预约？</h3>\n" +
                "                <p>答：有些医生在某些时段或某个周期内没有时间坐诊，所以医院没有在此时间段提供医生号，因此在网站不能无法预约。但此医生有可能在将来安排坐诊时间，届时将可以通过网站预约。</p>\n" +
                "             </div>\n" +
                "             <div class=\"guide\">\n" +
                "                <h3>8.预约挂号收费是否与医院一致？</h3>\n" +
                "                <p>答：挂号费用完全一致。</p>\n" +
                "             </div>");
        addJsonObj("testVB", obj); //在JSON中增加一个testVB对象，在页面可以用 result.data.testVB来访问这个对象及其子属性
    }

    @SearchValueTypes(nameFormat = "true", value = {})
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void testList() throws Exception, BaseException {
        try {
            if (getSearchValue() != null) {
//                getSearchValue().put("key", "1");
                List list = demomodelService.customSearchDemomodel2(getSearchValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Pager pager = new Pager(ServletActionContext.getRequest(), 20); //调用框架方法生成一个PAGER对象，如果需要控制每页的大小，请在页面传入page参数或者将第二个参数设置成你想要的值
        pager.setTotalRows(13);
        pager.setTotalPages(13);
        pager.setCurrentPage(2);
        ArrayList list = new ArrayList();
        Haha h1 = new Haha();
        Haha h2 = new Haha();
        Haha h3 = new Haha();
//        h1.setHaha1("测试一下效果");
//        h1.setHaha2("hh1");
        h1.setHaha3("hh1");
        h1.set_abc_desc("abc1");
        h2.setHaha1("hh23<FONT color='red'>SSSS</FONT>");
        h2.setHaha2("hh2");
        h2.setHaha3("hh2");
        h2.set_abc_desc("abc2");
        h3.setHaha1("hh3");
        h3.setHaha2("hh3");
        h3.setHaha3("hh3");
        h3.set_abc_desc("abc3");
        list.add(h1);
        list.add(h2);
        list.add(h3);
        pager.setPageData(list);
        addJsonObj("xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<sync_resp><head><id>gssgz</id><pwd>gssgz2012</pwd></head><body><resultid>777<resultinfo>GIS平台不存在匹配的请求信息，请检查账号信息及请求ip是否正确</resultinfo></resultid></body></sync_resp>\n");
        addJsonPager("pageData", pager);   //分页醒询的返回调用addJsonPager方法，直接设置LIST可以调用 addJsonArray方法
    }

    @JSONOut(catchException = @CatchException(errCode = "", successMsg = "", faileMsg = ""))
    @SignlAccess
    public void testOut() throws BaseException, Exception {
        System.out.println("so,what");
//        throw new Exception("xxx");
        addJsonObj("avb", "haha");
        throw new BaseException("abc", "xxx");
    }


    @ActionRoles({"ROLE_TEST"})
    @SearchValueTypes(nameFormat = "true", value = {
            /**nameFormat参数表求页面所用的值是否需要转换成数据库的字段格式。一般情况下不是自己写SQL，调用框架原
             有默认的查询方法时，需要使用此属性。
             如：你自己写有SQL：select * from tb_test where testDate=:testDate,则不需要此参数，或参数值设置成false
             SearchValueType注解只在有需要时进行注解*/
            @SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"), //表示查询语句里的:testDate参数对应的是日期类型
            @SearchValueType(name = "testNumber", type = "number"), //表示查询语句里的testNumber参数是数字类型
            @SearchValueType(name = "testString", type = "string", format = "%s%%") //表示查询语句里的testString参数需要格式化，此例中如果页面传入abc，则实际值会变为abc%,一般用于模糊查询
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxPageSearch() throws BaseException, Exception {
        Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize()); //调用框架方法生成一个PAGER对象，如果需要控制每页的大小，请在页面传入page参数或者将第二个参数设置成你想要的值
        pager.setDelegeClass(TestVO.class);//为返回的列表设置一个代理映射类，这个方法必须在查询前设置才会生效。映射类与查询后原返回类的相同名称字段值将会进行拷贝
        /**
         *  将页面内传递过来的参数及分页信息传递至SERVICE进行查询
         *  getSearchValue(),用于获取页面传递过来的查询参数，页面传递查询参数时需要以searchValue为前缀加上.号。如testDate在页面上应为<input type="text" name="searchValue.testDate">
         */
        pager = demomodelService.searchDemomodel(getSearchValue(), pager);
        addJsonPager("pageData", pager);
    }

    @ActionRoles({"ROLE_TEST"})
    @SearchValueTypes(nameFormat = "true", value = {
            /**nameFormat参数表求页面所用的值是否需要转换成数据库的字段格式。一般情况下不是自己写SQL，调用框架原
             有默认的查询方法时，需要使用此属性。
             如：你自己写有SQL：select * from tb_test where testDate=:testDate,则不需要此参数，或参数值设置成false*/
            @SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"), //表示查询语句里的:testDate参数对应的是日期类型
            @SearchValueType(name = "testNumber", type = "number"), //表示查询语句里的testNumber参数是数字类型
            @SearchValueType(name = "testString", type = "string", format = "%s%%") //表示查询语句里的testString参数需要格式化，此例中如果页面传入abc，则实际值会变为abc%,一般用于模糊查询
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxCustomSearch1() throws BaseException, Exception {
        Pager pager = new Pager(ServletActionContext.getRequest(), 20); //调用框架方法生成一个PAGER对象，如果需要控制每页的大小，请在页面传入page参数或者将第二个参数设置成你想要的值
        pager.setDelegeClass(TestVO.class);//为返回的列表设置一个代理映射类，这个方法必须在查询前设置才会生效。映射类与查询后原返回类的相同名称字段值将会进行拷贝
        /**
         *  将页面内传递过来的参数及分页信息传递至SERVICE进行查询
         *  getSearchValue(),用于获取页面传递过来的查询参数，页面传递查询参数时需要以searchValue为前缀加上.号。如testDate在页面上应为<input type="text" name="searchValue.testDate">
         */
        pager = demomodelService.customSearchDemomodel(getSearchValue(), pager);
        addJsonPager("pageData", pager);
    }

    @ActionRoles({"ROLE_TEST"})
    @SearchValueTypes(nameFormat = "true", value = {
            /**nameFormat参数表求页面所用的值是否需要转换成数据库的字段格式。一般情况下不是自己写SQL，调用框架原
             有默认的查询方法时，需要使用此属性。
             如：你自己写有SQL：select * from tb_test where testDate=:testDate,则不需要此参数，或参数值设置成false*/
            @SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"), //表示查询语句里的:testDate参数对应的是日期类型
            @SearchValueType(name = "testNumber", type = "number"), //表示查询语句里的testNumber参数是数字类型
            @SearchValueType(name = "testString", type = "string", format = "%s%%") //表示查询语句里的testString参数需要格式化，此例中如果页面传入abc，则实际值会变为abc%,一般用于模糊查询
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxCustomSearch2() throws Exception {
        List returnList = demomodelService.customSearchDemomodel2(getSearchValue());
        addJsonArray("list", returnList);
    }

    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException {
        TbRbacUserPO rbacUserPO = demomodelService.searchByPk(TbRbacUserPO.class, id);
        addJsonFormateObj("rbacUserPO", rbacUserPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }

    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "测试成功", faileMsg = "测试 失败"))
//    public void testLocker() throws Exception {
//        String connectString = "localhost:3181";
//        Locker lk = new Locker(connectString, "/locks", 1);
//        logger.info(":get locker");
//        boolean result = lk.lock();
//        logger.info(":inside locker" + result);
//        Thread.currentThread().sleep(20 * 1000);
//        lk.unlock();
//        logger.info(":out locker" + result);
//
//    }


    public void add() {
        super.ajaxAdd(userPO);
    }

    public void update() {
        super.ajaxUpdate(userPO);
    }

    public void delete() {
        if (AssertUtil.isEmpty(id))
            id = ids[0];
        userPO._setPKValue(id);
        super.ajaxDelete(userPO);
    }

    public void batchDelete() {
        super.ajaxBatchDelete(TbRbacUserPO.class, ids);
    }

    public TbRbacUserPO getUserPO() {
        return userPO;
    }

    public void setUserPO(TbRbacUserPO userPO) {
        this.userPO = userPO;
    }
}
