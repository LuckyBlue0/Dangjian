<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="userStatus,personSex"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户管理</title>

    <link rel="stylesheet" href="${baseURL}/js/3rd-plug/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.core-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.exedit-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
    <style type="text/css">
        div.ztree {
            border: 1px solid #E0E0E0;
            background: #FFFEFC;
            width: 220px;
            height: 440px;
            overflow-y: auto;
            overflow-x: auto;
        }

        div.content_wrap {
            width: 1100px;
        }

        div.content_wrap div.left {
            float: left;
            width: 250px;
            margin-top: 7px;
            padding-left: 20px;
        }

        div.content_wrap div.right {
            float: left;
            width: 750px;
        }

        div.zTreeDemoBackground {
            width: 250px;
            height: 362px;
            text-align: left;
        }
    </style>
</head>

<body style="background: #f6ebe5;">
<!--头部 end-->

<!--公告 end-->
<div class="searchWrap">
            <div class="title clearfix">
                <h2 class="icon1">用户管理</h2>
            </div><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td class="searchLeft"></td>
            </tr>
            </tbody></table>
 </div>
 <div class="text_bg" id="text">
<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <div id="orgTree" class="ztree"></div>
    </div>
    <div >
        <iframe name="orgUserList" id="orgUser" src="orgUserList.jsp" width="750"  scrolling="no" frameborder="0"></iframe>
    </div>
</div>
</div>
<script type="text/javascript">

    var currOrgId;

    var setting = {
        data:{
            simpleData:{
                enable:true,
                idKey:"organizationId",
                pIdKey:"parentId",
                root:0
            },
            key:{
                name:"organizationName"
            }
        },
        view:{
            showIcon:false
        },
        async:{
            enable:true,
            url:"${baseURL}/org/org!listOrgNode.action",
            autoParam:["organizationId"],
            dataFilter:ajaxDataFilter
        },
        callback:{
            onClick:viewOrgNode
        },
        check:{
            enable:false
        }
    };

    $(document).ready(function () {
        init();
    });

    /**
     * 初始化组织机构树
     */
    function init() {
        $('#org_info').hide();
        $('#org_view').hide();
        $('#add_root').hide();
        $.ajax({
            url:'${baseURL}/org/org!listOrgUnderCurrentUserOrg.action',
            type:'post',
            data:{'dqdp_csrf_token': dqdp_csrf_token},
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var orgNodes = result.data.orgList;
                    if (orgNodes.length > 0) {
                        $.fn.zTree.init($('#orgTree'), setting, orgNodes);
                        // 展开机构树的根节点
                        var orgTree = $.fn.zTree.getZTreeObj("orgTree");
                        var rootNodes = orgTree.getNodes();
                        if (rootNodes.length > 0)
                            orgTree.expandNode(rootNodes[0], true, false, true);
                        currOrgId = rootNodes[0].organizationId;
                        window.frames["orgUserList"].window.listUser(1, currOrgId);
                       _resetFrameHeight();
                    } else {
                        $('#add_root').show();
                    }
                } else {
                    alert(result.desc);
                }
            }
        });
    }

    /**
     * 异步加载节点数据后的处理函数
     * @param treeId
     * @param parent
     * @param result
     */
    function ajaxDataFilter(treeId, parent, result) {
        if ('0' == result.code) {
            return result.data.orgList;
        }
    }

    /**
     * 查看组织机构人员信息
     * @param event
     * @param treeId
     * @param treeNode
     */
    function viewOrgNode(event, treeId, treeNode) {
        currOrgId = treeNode.organizationId;
        window.frames["orgUserList"].window.cleanForm();
        window.frames["orgUserList"].window.listUser(1, currOrgId);
    }

    /**
     * 强制刷新节点
     * @param treeId
     * @param treeNode
     */
    function refreshNode(treeId, treeNode) {
        var orgTree = $.fn.zTree.getZTreeObj(treeId);
        orgTree.reAsyncChildNodes(treeNode, "refresh");
    }

    function expandTreeByOrgId(orgId) {
        $.ajax({
            url:"${baseURL}/org/org!listOrgByOrgId.action",
            type:"post",
            dataType:"json",
            data:{"organizationId":orgId,'dqdp_csrf_token': dqdp_csrf_token},
            success:function (result) {
                if ("0" == result.code) {
                    var orgNodes = result.data.orgList;
                    if (orgNodes.length > 0) {
                        $.fn.zTree.init($('#orgTree'), setting, orgNodes);
                    }
                    var orgTree = $.fn.zTree.getZTreeObj("orgTree");
                    var node = orgTree.getNodeByParam("organizationId",orgId, null);
                    orgTree.selectNode(node);
                }
            }
        });
    }

    function getCurrOrgId() {
        return currOrgId;
    }

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
                window.location.href = 'userList.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token;
            },
            error:function () {
                alert('操作失败，请稍候再试');
            }
        });
    }


</script>
</body>

</html>
