<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <%--<script type="text/javascript" src="${baseURL}/js/3rd-plug/ease-ui/easyui.min.js"></script>--%>
        <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/ajaxfileupload.js"></script>
    <%--<script src="${baseURL}/js/3rd-plug/jsmart/smart-2.9.min.js"></script>--%>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <style type="text/css">
        <!--
        .ziti {
            font-size: 16px;
        }

        -->
    </style>
</head>

<body>
<form action="${baseURL}/demomodel/demomodel!add.action" method="post" id="id_form_search">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" id="id_body"
           dataSrc="local:v1">
        <thead>
            <tr>
                <th colspan="4">新增报备</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">报备类型：</td>
                <td width="280">
                    请休假报备 &nbsp;&nbsp;
                    <input type="radio" name="testVB.radio1" dictType="test" />
                    <%--<select name="testVB.radio1" dictType="test" id="ra3"></select>--%>
                    </td>

                <td width="120" class="tdBlue">考勤卡号：</td>
                <td width="280"><input class="form120px" name="testVB.carId" type="text" valid="{length:10,must:true,fieldType:'pattern',regex:'.+',tip:'测试'}"/></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">报备类型：</td>
                <td width="280">
                    <select id="ade" dictType="test" dictItem="" defaultValue="1" >
                        </select>
                        <select id="edf" parentDict="ade">
                        </select>
                        <select id="xd" parentDict="edf" >
                        </select>
                    </td>

                <td width="120" class="tdBlue">考勤卡号：</td>
                <td width="280" name="testVB.carId"></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue"><span id="qxj">请休假</span>人员姓名：</td>
                <td width="280"><input permission="xxx" readonlyWhenNoPermission="true"  class="form120px" name="testVB.userName" length="2" type="text" valid="{must:true,fieldType:'pattern',regex:'.*',tip:'测试'}"/></td>
                <td width="120" class="tdBlue">所属部门：</td>
                <td width="280"><input class="form120px" name="testVB.deptName" charlength="1" type="text" valid="{fieldType:'func',regex:'test1(\'2\')',tip:'测试'}"/>
                    <a href="#" onclick="javascript:window.open('所属部门.html', 'newwindow', 'height=450, width=300');">
                        <img src="../../../themes/default/images/@{testVB.img}"/>
                        <img src="" name="testVB.img"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">报备原因：</td>
                <td width="280"><select id="xj" class="form120px" name="testVB.bb" dict="test" valid="{must:true,tip:'报备原因'}">


                </select>
                    <select id="gw" class="form120px" style="display:none">
                        <OPTION selected value="0"> 请选择</OPTION>
                        <OPTION value="1"> 外出学习培训</OPTION>
                        <OPTION value="2"> 外出开会</OPTION>
                        <OPTION value="3"> 外出参加活动</OPTION>
                        <OPTION value="1"> 业务工作外出</OPTION>
                        <OPTION value="2"> 干部疗养</OPTION>
                        <OPTION value="3"> 其他</OPTION>
                    </select>
                </td>
                <td width="120" class="tdBlue">报备日期：</td>
                <td width="280"><span name="testVB.strList"><a href="xxx?abc=@{account}" name="account"></a></span></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue"><span id="qj">请假</span>起止日期：</td>
                <td width="280" colspan="3"><input class="form120px" name="" type="text"/>&nbsp;至&nbsp;<input class="form120px" name="" type="text"/></td>
            </tr>

            <tr>
                <td width="100" class="tdBlue">
                    备注：
                </td>
                <td colspan="3">
                    <textarea style="width: 230px;height:50px" onclick="this.select()"></textarea>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">报备人：</td>
                <td width="280"><input type="text" name="userPO.account"></td>
                <td width="120" class="tdBlue">报备时间：</td>
                <td width="280">2011-09-03</td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">备注：</td>
                <td colspan="3"><textarea rows="3" cols="120" name="testVB.remark" id="xxx21" editor="{'soft':'kindeditor','param':''}" ></textarea></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">文件上传：</td>
                <td colspan="3" ><input type="file" name="newsFile" id="newsFile"><input type="button" class="btn4" value="gg" onclick="uploadFile()"></td>
            </tr>
        </tbody>
    </table>
</form>

<h1 ID="test_tpl">{$HAHA}</h1>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" id="save" onclick="doSave();" value="保存"/>
        <input class="btn4" type="button" onclick="doFresh();" value="刷新"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">

    var v1=_doGetDataSrc("${baseURL}/demomodel/demomodel!testAddGet.action?id=${param.personId}",null);
//    _regEventBeforDraw(function(){alert("a");});
//    var v1 = {"userPO":{"remark":"中国<span style=\"background-color:#ff9900;\">右人世间<\/span>"}, "testVB":{"bb":"2", "carId":"<script>alert('a')<\/script>", "deptName":"部门1", "img":"big_btn_left.gif", "radio1":"2", "strList":[
//        {"account":"account0", "createTime":null, "creatorId":"", "deleteTime":null, "department":"", "departmentId":"", "email":"", "id":"id0", "isMerchantAccountLocked":0, "isModifyPasswordForce":0, "lastModifyPasswordTime":null, "lastSignin":null, "merchantDesc":"", "merchantId":"", "password":"", "remark":"", "ssoId":"", "status":0, "telephone":"", "userDesc":"", "userFlag":0, "userFrom":0, "userType":0},
//        {"account":"account1", "createTime":null, "creatorId":"", "deleteTime":null, "department":"", "departmentId":"", "email":"", "id":"id1", "isMerchantAccountLocked":0, "isModifyPasswordForce":0, "lastModifyPasswordTime":null, "lastSignin":null, "merchantDesc":"", "merchantId":"", "password":"", "remark":"", "ssoId":"", "status":0, "telephone":"", "userDesc":"", "userFlag":0, "userFrom":0, "userType":0},
//        {"account":"account2", "createTime":null, "creatorId":"", "deleteTime":null, "department":"", "departmentId":"", "email":"", "id":"id2", "isMerchantAccountLocked":0, "isModifyPasswordForce":0, "lastModifyPasswordTime":null, "lastSignin":null, "merchantDesc":"", "merchantId":"", "password":"", "remark":"", "ssoId":"", "status":0, "telephone":"", "userDesc":"", "userFlag":0, "userFrom":0, "userType":0},
//        {"account":"account3", "createTime":null, "creatorId":"", "deleteTime":null, "department":"", "departmentId":"", "email":"", "id":"id3", "isMerchantAccountLocked":0, "isModifyPasswordForce":0, "lastModifyPasswordTime":null, "lastSignin":null, "merchantDesc":"", "merchantId":"", "password":"", "remark":"", "ssoId":"", "status":0, "telephone":"", "userDesc":"", "userFlag":0, "userFrom":0, "userType":0}
//    ], "userName":"user1"}};
//    function doSave() {
//        alert(_doCommonSubmit("id_form_search")) ;
//    }
    var v2={};
    $('#save').click(function (){(_doCommonSubmit("id_form_search","",{ok:function(){},fail:function(){}})) ;});
    function doFresh() {
//        _initElementValue("id_form_search", true);
        _redraw();
    }
    $(document).ready(function () {
        doTest();
    });
    function doTest() {
        var tplText = document.getElementById('test_tpl').innerHTML;
        var data = {HAHA:"CCCC" };
        var tpl = new jSmart(tplText);
        var res = tpl.fetch(data);
    }
    function test1($p1){
        return $p1=="1";
    }
    function uploadFile(){
            $.ajaxFileUpload({
                url:'${baseURL}/demomodel/demomodel!uploadFile.action?type=2',//需要链接到服务器地址
                secureuri:false,
                fileElementId:'newsFile',                        //文件选择框的id属性
                dataType: 'json',                                              //服务器返回的格式，可以是json
                success: function (data) {
                    if('0'==data.code){
                    	_alert("上传文件成功！",data.desc,function(){alert(data.code)});
                    }else{
                    	alert(data.desc);
                    }
                },
                error: function () { alert("上传文件失败！"); }
            })
    	}
    _resetFrameHeight();
</script>
</body>
</html>
