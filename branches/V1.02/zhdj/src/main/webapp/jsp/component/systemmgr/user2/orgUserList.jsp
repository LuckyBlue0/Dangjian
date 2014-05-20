<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="userAdd,userEdit,userDel,userView,userChangePassword,userList"></jsp:param>
    <jsp:param name="mustPermission" value="userList"></jsp:param>
    <jsp:param name="dict" value="userStatus,personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户管理</title>

    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
    <style type="text/css">
    </style>
    <script type="text/javascript">
    </script>
</head>

<body >
<!--头部 end-->

<div>
    <form action="" method="post" id="user_search">
        <div class="text_bg">
        <div class="searchWrap">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="searchLeft"></td>
                    <td class="searchBg">
                        <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="60" height="30">账号名称：</td>
                                            <td width="140"><input class="form120px" id="searchUserName" type="text" keySearch="keySearchFunc()"/></td>
                                            <td width="60" height="30">人员名称：</td>
                                            <td width="140"><input class="form120px" id="searchPersonName" type="text" keySearch="keySearchFunc()"/></td>
                                            <td><input class="btnQuery" type="button" value="查询" permission="userList"
                                                                     onclick="javascript:listUser(1, currOrgId);"/>
                                                <input class="btnQuery" type="button" value="新增用户" permission="userAdd" onclick="javascript:doPersonAdd()"/>
                                                                     </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td class="searchRight"></td>
                </tr>
                <tr>
                    <td class="searchButtomLeft"></td>
                    <td class="searchButtom"></td>
                    <td class="searchButtoRight"></td>
                </tr>
            </table>
        </div>
        <div id="user_list" delUrl="${baseURL}/person/person!delBaseUser.action"></div>
        <div class="page" id="topIdPage"></div>
        </div>
    </form>

        
    
</div>

<script type="text/javascript">
    function doSearch() {
        window.location.href = 'orgUserList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
    }

    var currOrgId;

    $(document).ready(function () {
        currOrgId = window.parent.getCurrOrgId();
        if (!_isNullValue(currOrgId)) {
            listUser(1, currOrgId);
        }
    });

    function doPersonAdd() {
        currOrgId = window.parent.getCurrOrgId();
        window.location.href = 'userAdd.jsp?orgId=' + currOrgId+ '&dqdp_csrf_token='+dqdp_csrf_token;
    }

    function keySearchFunc() {
        listUser(1, currOrgId);
    }

    function cleanForm() {
        $("#user_search :input").not(":button,:reset,:submit,:hidden").val("");
    }

    function listUser(pageIndex, orgId) {
        // 设置当前机构id
        if (orgId != undefined) {
            currOrgId = orgId;
        }
        $("#id_user_toolbar").css("display", "block");
        $.ajax({
            url:'${baseURL}/person/person!listPersonByOrgId.action',
            type:'post',
            data:{
                'page':pageIndex,
                'searchValue.orgId':currOrgId,
                'searchValue.userName':$('#searchUserName').val(),
                'searchValue.personName':$('#searchPersonName').val()
            },
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var userList = new ListTable({
                        checkableColumn:"personId",
                        title:[
                            {width:"2%", name:"", showName:"", isCheckColunm:true},
                            {width:"15%", name:"userName", showName:"账户名称"},
                            {width:"15%", name:"personName", showName:"用户名称"},
                            {width:"40%", name:"orgName", showName:"所属机构"},
                            {width:"20%", name:"operation", showName:"操作", isOperationColumn:true}
                        ],
                        data:result.data.userPager,
                        operations:[
                            {name:"查看", permission:"userView", event:function (index, content) {
                                window.location.href = 'userView.jsp?personId=' + content.personId + '&userId=' + content.userId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                            }},
                            {name:"启用", permission:"userEdit", condition:function (index, content) {
                                return content.status != "0"
                            }, event:function (index, content) {
                                disableUser(content.userId, 'false');
                            }},
                            {name:"禁用", permission:"userEdit", condition:function (index, content) {
                                return content.status == "0"
                            }, event:function (index, content) {
                                disableUser(content.userId, 'true');
                            }}
                        ],
                        trStyle:["trWhite"]
                    });
                    userList.createList("user_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"listUser"});
                    pager.createPageBar("topIdPage");
                    pager.createPageBar("downIdPage");
                    window.parent.parent._resetFrameHeight(1);
                }
            }
        });
    }

    /**
     * 启用或禁用用户
     * @param userId
     * @param disabled
     */
    function disableUser(userId, disabled) {
        $.ajax({
            type:'post',
            url:'${baseURL}/user/user!disableUser.action',
            data:{
                'userId':userId,
                'disabled':disabled,
                'dqdp_csrf_token': dqdp_csrf_token
            },
            dataType:'json',
            success:function (result) {
                if (result.code == '0') {
                    alert("操作成功");
                } else {
                    alert(result.desc);
                }
                window.location.href = 'orgUserList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
            },
            error:function () {
                alert('操作失败，请稍候再试');
            }
        });
    }

</script>
</body>

</html>
