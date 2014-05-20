<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="roleList"></jsp:param>
    <jsp:param name="mustPermission" value="userAdd"></jsp:param>
    <jsp:param name="dict" value="personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增人员</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="../js/CheckboxTable.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/person/person!addPerson.action" method="post" id="person_add">
    <input type="hidden" name="personVO.userIds" id="userIds" value=""/>
    <input type="hidden" name="personVO.orgId" value="${param.orgId}"/>
    <input type="hidden" name="userVO.isInternal" value="1"/>
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
            <tr>
                <th colspan="4">新增人员</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="120" class="tdBlue">人员名称：</td>
                <td width="280">
                    <input class="form120px" name="personVO.personName" type="text"
                           valid="{must:true,tip:'人员名称',fieldType:'pattern',length:20}"/>
                    <span><font color="red">*</font></span>
                </td>
                <td width="120" class="tdBlue">登陆账号：</td>
                <td width="280">
                    <input class="form120px" name="userVO.userName" type="text" id="userName"
                           valid="{must:true,tip:'登陆账号',fieldType:'pattern',regex:'^\\w+$',length:20}"/>
                    <span><font color="red">*</font></span>
                </td>

            </tr>
            <tr>
                <td width="120" class="tdBlue">密码：</td>
                <td width="280">
                    <input style="width: 134px;" name="userVO.password" id="password" type="password"
                           valid="{must:true, fieldType:'pattern', regex:'^.{6,20}$', tip:'密码'}"/>
                    <span><font color="red">*</font>6到20个字符</span>
                </td>
                <td width="120" class="tdBlue">确认密码：</td>
                <td width="280">
                    <input style="width: 134px;" name="userVO.confirmPassword" id="confirmPassword" type="password"
                           valid="{must:true, fieldType:'pattern', regex:'^.{6,20}$', tip:'确认密码'}"/>
                    <span><font color="red">*</font></span>
                </td>

            </tr>
            <tr>
                <td width="120" class="tdBlue">年龄：</td>
                <td width="280"><input class="form120px" name="personVO.age" type="text"
                                       valid="{must:false, fieldType:'pattern', regex:'^\\d{0,3}$', tip:'年龄'}"/></td>
                <td width="120" class="tdBlue">性别：</td>
                <td width="280"><select name="personVO.sex" dictType="personSex"/></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">boss工号：</td>
                <td width="280"><input class="form120px" name="myPersonMap.bossid" type="text"/></td>
                <td width="120" class="tdBlue">岗位：</td>
                <td width="280"><input class="form120px" name="myPersonMap.job" type="text"/></td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">手机号码：</td>
                <td width="280"><input class="form120px" name="myPersonMap.phone" type="text"
                                       valid="{must:false, fieldType:'pattern', regex:'^\\d{11}$', tip:'手机号码'}"/></td>
                <td width="120" class="tdBlue"></td>
                <td width="280"></td>
            </tr>
            <tr>
                <td width="120" height="70" class="tdBlue">角色：</td>
                <td colspan="3">
                    <div id="role_list"></div>
                </td>
                <input type="hidden" name="userVO.roleIds" id="roleIds"/>
            </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" id="save" type="button" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function () {
        listRole();
        $("#userName").blur(function () {
            $.ajax({
                url:"${baseURL}/user/user!isUserAlreadyExist.action",
                data:{"userName":$(this).val()},
                dataType:"json",
                type:"post",
                success:function (result) {
                    $("#userName").parent().children(".error").html("");
                    if ("1001" == result.code)
                        $("#userName").parent().append("<p class='error' id='userNameTip'><font color='red'>账户已存在！</font></p>");
                }
            });
        });
    });

    function updateRole() {
        var roleList = "";
        $.each($('[name$=Ids]'), function () {
            if ($(this).prop("checked")) {
                roleList += $(this).val() + ",";
            }
        });
        $("#roleIds").val(roleList);
    }

    function listRole() {
        $.ajax({
            url:"${baseURL}/role/role!listAllRole.action",
            data:{},
            type:"post",
            dataType:"json",
            success:function (result) {
                if ("0" == result.code) {
                    var roleCheckTable = new CheckboxTable({
                        data:result.data.roleList,
                        colsCount:4,
                        checkboxName:"roleName",
                        checkboxValue:"roleId"
                    });
                    roleCheckTable.createTable("role_list");
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert("通讯错误");
            }
        });
    }

    $('#save').click(function () {
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("person_add")) {
            if (checkPassword()) {
                updateRole();
                // 提交数据
                $('#person_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('新增人员信息成功');
                            window.location.href=("orgUserList.jsp"+ '?dqdp_csrf_token='+dqdp_csrf_token);
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('新增人员失败，请稍候再试');
                    }
                });
            } else {
                alert('两次输入的密码不一致！');
            }
        }
    });

    function listUser() {
        window.location.href = 'orgUserList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
    }

    function checkPassword() {
        return $('#password').val() == $('#confirmPassword').val();
    }
</script>

</body>
</html>
