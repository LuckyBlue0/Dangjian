<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>组织机构管理</title>

    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${baseURL}/js/3rd-plug/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.core-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.exedit-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
    <style type="text/css">
        .ztree li button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>

<body style="background: #f6ebe5;">
<!--头部 end-->

<!--公告 end-->
<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">静态字典管理</h2>
            </div>
        </tr>
    </table>
</div>
<form action="${baseURL}/dictmgr/dictmgr!listDictRoot.action" method="post" id="id_form_search">
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
                                        <td width="60" height="30">分类代码：</td>
                                        <td width="140"><input class="form120px" name="fsTypeId" type="text" value=""/></td>
                                        <td width="60" height="30">分类描述：</td>
                                        <td width="140"><input class="form120px" name="fsTypeDesc" type="text" value=""/></td>
                                        <td><input class="btnQuery" name="input" type="button" value="搜索" onclick="doSearch()"/>
                                        <input type="button" class="btnQuery" value="新增字典" id="create-user" onclick="javascript:clearDictDiv();beforeAddRoot();"/></td>
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
  


<div>
    <div id="orgTree" class="ztree"></div>
    <div id="nodeDetail">

    </div>
    
</div>
  </div>
</form>
<div id="dialog-form" title="新建字典">

        <form id="id_form_save" action="${baseURL}/dictmgr/dictmgr!saveDict.action" method="post">
            <input type="hidden" id="saveType" name="saveType"/>
            <fieldset style="margin-top: 5px;">
                <label for="fsTypeId" >分类&nbsp;&nbsp;&nbsp;代码</label>
                <input type="text" name="fsTypeId" id="fsTypeId" class="form120px"/>
                <br/>
                <br/>
                <label for="fsTypeDesc" >分类&nbsp;&nbsp;&nbsp;描述</label>
                <input type="text" name="fsTypeDesc" id="fsTypeDesc" class="form120px"/>
                <br/>
                <br/>
                <label for="fsParentType" >父字典代码</label>
                <input type="text" name="fsParentType" id="fsParentType" class="form120px"/>
                <br/>
                <br/>
                <label for="fsItemCode" >字典&nbsp;&nbsp;&nbsp;代码</label>
                <input type="text" name="fsItemCode" id="fsItemCode" class="form120px"/>
                <br/>
                <br/>
                <label for="fsItemDesc" >字典&nbsp;&nbsp;&nbsp;描述</label>
                <input type="text" name="fsItemDesc" id="fsItemDesc" value="" class="form120px"/>
            </fieldset>
        </form>
    </div>
<script type="text/javascript">

    $("#dialog-form").dialog({
        autoOpen:false,
        height:300,
        width:300,
        modal:true,
        buttons:{
            "保存":function () {
                $('#id_form_save').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        _alert("操作结果",result.desc);
//                        result.data.itemObj.fsItemCode="";
//                        result.data.itemObj.fsParentType=result.data.itemObj.fsTypeId;
                        refreshNode(result.data.itemObj);
                        $("#dialog-form").dialog("close");
                    },
                    error:function () {
                        _alert("操作结果","保存字典过程中发生网络错误");
                    }
                });
            },
            "取消":function () {
                $(this).dialog("close");
            }
        },
        close:function () {

        }
    });

    $("#create-user").click(function () {
        $("#dialog-form").dialog("open");
    });
    /**
     * Created by IntelliJ IDEA.
     * User: hyf
     * Date: 12-3-24
     * Time: 下午6:10
     * To change this template use File | Settings | File Templates.
     */


    var setting = {
        data:{
            simpleData:{
                enable:true,
                idKey:"fsDictItemId",
                pIdKey:"fsParentType",
                root:0
            },
            key:{
                name:"fsItemDesc1"
            }
        },
        view:{
            showIcon:false,
            addHoverDom:addHoverDom,
            removeHoverDom:removeHoverDom
        },
        edit:{
            enable:false,
            renameTitle:'修改字典',
            removeTitle:'删除字典',
            drag:{
                autoExpandTrigger:true
            }
        },
        async:{
            enable:true,
            url:"${baseURL}/dictmgr/dictmgr!listDictItem.action",
            autoParam:["fsItemCode", "layer", "fsTypeId"],
            dataFilter:ajaxDataFilter
        },
        callback:{
            onExpand:expandParentOrg,
            onClick:viewOrgNode,
            beforeClick:beforNodeClick,
            beforeEditName:beforeEditOrg,
            beforeRemove:beforeDelOrg,
            beforeDrop:beforeDropOrgNode
        },
        check:{
            enable:false
        }
    };

    var orgNodes;
    var expanNode;
    var allowClick = true; //标记节点是否能被点击

    $(document).ready(function () {
        init();
    });

    /**
     * 初始化组织机构树
     */
    function init() {
        $('#org_info').hide();
        doSearch();
    }
    var orgTree;
    function doSearch() {
        $("#orgTree").html("");
        $("#id_form_search").ajaxSubmit({
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    orgNodes = result.data.dictList;
                    if (orgNodes.length > 0) {
                        $.fn.zTree.init($('#orgTree'), setting, orgNodes);
                        // 展开机构树的根节点
                        orgTree = $.fn.zTree.getZTreeObj("orgTree");
                        var rootNodes = orgTree.getNodes();
                        _resetFrameHeight(document.documentElement.scrollHeight);
                        //                    if (rootNodes.length > 0)
                        //                        orgTree.expandNode(rootNodes[0], true, false, true);
                    }
                    //                    $('#add_root').show();
                } else {
                    _alert("操作结果",result.desc);
                }
            }
        });

    }

    /**
     * 展开父节点
     * @param event
     * @param orgTreeId
     * @param orgTreeNode
     */
    function expandParentOrg(event, orgTreeId, orgTreeNode) {
        var orgTree = $.fn.zTree.getZTreeObj("orgTree");
        _resetFrameHeight(document.documentElement.scrollHeight);
//        orgTree.reAsyncChildNodes(orgTreeNode, "refresh");
    }

    /**
     * 异步加载节点数据后的处理函数
     * @param treeId
     * @param parent
     * @param result
     */
    function ajaxDataFilter(treeId, parent, result) {
        if ('0' == result.code) {
            return result.data.dictList;
        }
    }

    /**
     * 在节点后增加添加按钮
     * @param treeId
     * @param treeNode
     */
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.fsDictItemId).length > 0) return;
        // 添加新增机构节点按钮
        var addStr = "        <a type='button' class='add' id='addBtn_" + treeNode.fsDictItemId
                + "' title='添加字典' onfocus='this.blur();'>添加字典</a>";
        sObj.append(addStr);
        var btn = $("#addBtn_" + treeNode.fsDictItemId);
        if (btn) btn.bind("click", function () {
            beforeAddOrgNode(treeNode);
        });
        if ("type" != treeNode.layerType) {
            // 添加编辑机构节点按钮
            var editStr = "  <a type='button' class='edit' id='editBtn_" + treeNode.fsDictItemId
                    + "' title='编辑字典' onfocus='this.blur();'>编辑字典</a>";
            sObj.append(editStr);
            var btnEdit = $("#editBtn_" + treeNode.fsDictItemId);
            if (btnEdit) btnEdit.bind("click", function () {
                beforeEditOrg(treeNode.fsDictItemId, treeNode);
            });
        }
    }

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.fsDictItemId).unbind().remove();
        $("#editBtn_" + treeNode.fsDictItemId).unbind().remove();
    }

    /**
     * 控制节点是否能被选中
     * @param treeId
     * @param treeNode
     * @param clickFlag
     */
    function beforNodeClick(treeId, treeNode, clickFlag) {
        if (!allowClick) {
            allowClick = true;
            return false;
        } else {
            return true;
        }
    }

    /**
     * 查看设置字典信息
     * @param event
     * @param treeId
     * @param treeNode
     */
        //todo@yom:
    function viewOrgNode(event, treeId, treeNode) {
        clearDictDiv();
        if (treeNode.fsTypeId) {
            $("#fsTypeId").val(treeNode.fsTypeId);
            $("#fsTypeId").attr("readonly", "readonly");
        }
        if (treeNode.fsTypeDesc) {
            $("#fsTypeDesc").val(treeNode.fsTypeDesc);
            $("#fsTypeDesc").attr("readonly", "readonly");
        }
        if ("type" != treeNode.layerType) {
            $("#fsParentType").val(treeNode.fsItemCode);
            $("#fsParentType").attr("readonly", "readonly");
        } else {
//            $("#fsItemCode").attr("readonly", "readonly");
//            $("#fsItemDesc").attr("readonly", "readonly");
            $("#fsParentType").attr("readonly", "readonly");
        }
    }
    function editOrgNode(event, treeId, treeNode) {
        clearDictDiv();
        $("#saveType").val("edit");
        $("#fsTypeId").val(treeNode.fsTypeId);
        $("#fsTypeDesc").val(treeNode.fsTypeDesc);
        if ("type" != treeNode.layerType) {
            $("#fsItemCode").val(treeNode.fsItemCode);
            $("#fsItemDesc").val(treeNode.fsItemDesc);
            $("#fsTypeId").attr("readonly", "readonly");
            $("#fsTypeDesc").attr("readonly", "readonly");
        } else {
            $("#fsTypeId").attr("readonly", "readonly");
//            $("#fsItemCode").attr("readonly", "readonly");
            $("#fsItemCode").attr("readonly", "readonly");
            $("#fsItemDesc").attr("readonly", "readonly");
            $("#fsParentType").attr("readonly", "readonly");

        }
        if ("type" != treeNode.layerType) {
            $("#fsParentType").val(treeNode.fsParentType);
        } else {

        }
    }

    function clearDictDiv() {
        $("#fsTypeId").val("");
        $("#fsTypeDesc").val("");
        $("#fsItemCode").val("");
        $("#fsItemDesc").val("");
        $("#fsParentType").val("");
        $("#fsTypeId").removeAttr("readonly");
        $("#fsTypeDesc").removeAttr("readonly");
        $("#fsItemCode").removeAttr("readonly");
        $("#fsItemDesc").removeAttr("readonly");
        $("#fsParentType").removeAttr("readonly");
    }

    /**
     * 新增机构预处理
     */
    function beforeAddOrgNode(treeNode) {
        viewOrgNode(null, null, treeNode);
        $("#saveType").val("add");
        beforeAddRoot();
        // 清空表单
//        cleanOrgInfo();
        // 设置组织机构的父id
//        $('#orgPid').val(treeNode.fsDictItemId);
        // 设置新增节点后要刷新的节点
        expanNode = treeNode;
//        $('#save').unbind("click");
//        $('#save').bind("click", addOrgNode);
//        // 显示新增表单
//        $('#org_info').show();
//        $('#org_view').hide();
        allowClick = false; //把allowClick设为false，屏蔽点击事件
    }

    /**
     * 预处理修改组织机构信息
     * @param treeId
     * @param treeNode
     */
    function beforeEditOrg(treeId, treeNode) {
        expanNode = treeNode.getParentNode(); //设置更改后要刷新的节点
        editOrgNode(null, null, treeNode);
        $("#saveType").val("edit");
        allowClick = false; //把allowClick设为false，屏蔽点击事件
        beforeAddRoot();
        return false;
    }

    /**
     * 删除机构信息
     * @param treeId
     * @param treeNode
     */
    function beforeDelOrg(treeId, treeNode) {
        allowClick = false;
        if (confirm('确定删除该机构')) {
            $.ajax({
                url:'${baseURL}/org/org!delOrganization.action',
                data:{'fsDictItemId':treeNode.fsDictItemId},
                type:'post',
                dataType:'json',
                success:function (result) {
                    if ('0' == result.code) {
                        _alert("操作结果",result.desc);
                        return true;
                    } else {
                        return false;
                    }
                },
                error:function () {
                    _alert("操作结果",'通讯错误，请稍候再试');
                }
            });
        } else {
            return false;
        }
    }

    /**
     * 移动机构节点
     * @param treeId
     * @param treeNodes
     * @param targetNode
     * @param moveType
     */
    function beforeDropOrgNode(treeId, treeNodes, targetNode, moveType) {
        if (targetNode == null || (moveType != "inner" && !targetNode.parentTId)) {
            _alert('不能移动成根机构节点');
            return false;
        }
        alert('test');
        $.ajax({
            type:'post',
            url:'${baseURL}/org/org!dragOrgNode.action',
            data:{
                'orgVO.organizationId':treeNodes[0].organizationId,
                'orgVO.parentId':treeNodes[0].parentId,
                'newParentId':targetNode.organizationId
            },
            dataType:'json',
            success:function () {
                if ('0' == result.code) {
                    return true;
                } else {
                    return false;
                }
            },
            error:function () {
                alert('通讯错误，请稍候再试');
                return false;
            }
        });
    }

    /**
     * 新增机构根节点预处理
     */
    function beforeAddRoot() {

        $("#dialog-form").dialog("open");
//        cleanOrgInfo();
//        $('#org_info').show();
//        $('#org_view').hide();
//        $('#save').unbind("click");
//        $('#save').bind("click", addRoot);
    }

    function addRoot() {
        $('#org_edit').attr('action', '${baseURL}/org/org!addOrganization.action');
        $('#org_edit').ajaxSubmit({
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    alert("新增机构根节点成功");
                    init();
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert('通讯错误，请稍候再试');
            }
        });
    }

    /**
     * 强制刷新节点
     * @param treeId
     * @param treeNode
     */
    function refreshNode(treeNode) {
//        var orgTree = $.fn.zTree.getZTreeObj(treeId);
//        orgTree.updateNode(treeNode);
        if (undefined == expanNode) init();
        else {
            expanNode.isParent = true;
            orgTree.reAsyncChildNodes(expanNode, "refresh", true);
        }
    }

    /**
     * 清除表单信息
     */
    function cleanOrgInfo() {
        $(':input', '#org_edit').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
    }


</script>
</body>

</html>
