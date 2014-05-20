<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看人员信息</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<table class="tableCommon mt5" dataSrc='${baseURL}/person/person!viewPerson.action?personId=${param.personId}'
       width="100%" border="0" cellspacing="0" cellpadding="0">
    <thead>
    <tr>
        <th colspan="4">人员信息</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td width="120" class="tdBlue">人员名称：</td>
        <td width="280" name="personVO.personName"></td>
        <td width="120" class="tdBlue">性别：</td>
        <td width="280" name="personVO.sexDesc"></td>
    </tr>
    <tr>
        <td width="120" class="tdBlue">年龄：</td>
        <td width="280" name="personVO.age"></td>
        <td width="120" class="tdBlue"></td>
        <td width="280"></td>
    </tr>
    </tbody>
</table>

<div id="user_list"></div>
<div class="pageDown" id="userPage"><a href="#">上一页</a>|<a href="#">下一页</a>&nbsp;共30页<span class="font048">第
<input class="form24px border999" type="text" value="1"/>
页</span><input class="btnQuery" type="button" value="确定" onclick="location.href='#'"/></div>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function() {
        listUser(1);
    });

    function listUser($pageIndx) {
        $.ajax({
            type:'post',
            url:'${baseURL}/person/person!listUserByPersonView.action',
            dataType:'json',
            data:{'personId':'${param.personId}'},
            success:function(result){
                if (result.code == '0') {
                    var userList = new ListTable({
                        title:[
                            {width:"30%", name:"userName", showName:"用户名称"}
                        ],
                        data:result.data.userPagerData,
                        operations:[],
                        trStyle:["trWhite"]
                    });
                    userList.createList("user_list");
                    var rolePager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listUser"});
                    rolePager.createPageBar("userPage");
                }
            }
        });
    }

</script>

</body>
</html>
