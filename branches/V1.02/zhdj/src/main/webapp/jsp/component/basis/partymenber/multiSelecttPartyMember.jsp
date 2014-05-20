<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选择党员</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${baseURL}/js/3rd-plug/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.core-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.exedit-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/HashMap.js"></script>
	<script type="text/javascript" src="${baseURL}/js/do1/common/DataTable.js"></script>
    <style type="text/css">
        .ztree li button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            vertical-align: middle
        }

        div.ztree {
            margin-top: 10px;
            border: 1px solid #E0E0E0;
            background: #f0f6e4;
            width: 220px;
            height: 360px;
            overflow-y: auto;
            overflow-x: auto;
        }

        div.content_wrap {
            width: 1100px;
            height: 800px;
        }

        div.content_wrap div.left {
            float: left;
            width: 250px;
            padding-left: 20px;
        }

        div.content_wrap div.right {
            float: left;
            width: 650px;
            margin-top: 10px;
        }

        div.zTreeDemoBackground {
            width: 250px;
            height: 362px;
            text-align: left;
        }
        
        div.content_wrap div.bottom {
            float: none;
            width: 900px;
            margin-top: 10px;
            margin-left: 20px;
            border:  1px solid #CCCCCC;
            
        }
        
		
		.xzyh_normal {
		    background-image: url("../themes/default/images/u116_normal.png");
		}
    </style>
</head>

<body style="background: #f6ebe5;">
<!--头部 end-->
   
<!--公告 end--> 
<div class="searchWrap">
			<div class="title clearfix">
				<h2 class="icon1">
					选择党员
				</h2>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td class="searchLeft"></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="text_bg">
<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <div id="orgTree" class="ztree"></div>
    </div>
    <div class="right">
        <div>
		    <form action="${baseURL}/partymenber/partymenberAction!ajaxSearch.action" method="post" id="user_search">
		        <div class="searchWrap">
		            <table width="100%" border="0" cellspacing="0" cellpadding="0">
		            <input type="hidden" name="searchValue.isManagement" value="${param.isManagement}"/>
		            <input type="hidden" name="searchValue.state" value="1"/>
		                <tr>
		                    <td class="searchLeft"></td>
		                    <td class="searchBg">
		                        <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
		                            <tr>
		                                <td>
		                                    <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
		                                    <input type="hidden" name="searchValue.organizationId" id="orgId"/>
		                                        <tr>
		                                            <td>姓名：</td>
		                                        <td ><input name="searchValue.name" onkeypress="if(event.keyCode==13||event.which==13){return false;}"  /></td>
		                                        <td>党员类别：</td>
		                                        <td>
		                                        	<select name="searchValue.userType">
		                                        		<option value="">全部</option>
		                                        		<option value="1">在职党员</option>
		                                        		<option value="2">流动党员</option>
		                                        	</select>
		                                        </td>
		                                        <td>
		                                        	<input class="btnQuery" type="button" value="查询" permission="" onclick="javascript:doSearch(1);"/></td>
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
		    </form>

		    <div id="user_list" ></div>
		    <div class="pageDown" id="topIdPage"></div>
		</div>
	</div>
	<div style="clear:both"></div>
	<div class="bottom">
		<div id="xzyh" class="xzyh_normal">
			<p style="text-align:left;background-color: #EEEEEE">
				<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">已选用户</span>
			</p>
		</div>
		<div id="choose_user_list">
		</div>
	</div>
	<div class="toolBar">
		<div align="center">
		<input id="confirmBtn" class="btnQuery" type="button" value="确定选择">
		<input id="cancelBtn" class="btnQuery" type="button" value="取消">
		</div>
	</div>
</div>
</div>

<script type="text/javascript">

var selUserMap = new HashMap();
 var setting = {
        data:{
            simpleData:{
                enable:true,
                idKey:"id",
                pIdKey:"parentId",
                root:0
            },
            key:{
                name:"organizationName"
            }
        },
        view:{
            showIcon:false,
            removeHoverDom:removeHoverDom
        },
        edit:{
            enable:false, 
            renameTitle:'修改机构信息',
            removeTitle:'删除机构',
            drag:{
                autoExpandTrigger:true
            }
        },
        async:{
            enable:false,
            url:"",
            autoParam:["id"],
            dataFilter:ajaxDataFilter
        },
        callback:{
//            onExpand:expandParentOrg,
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
        doSearch(1);
        
    });

    /**
     * 初始化组织机构树
     */
    function init() {
        $('#org_info').hide();
        $('#org_view').hide();
        $('#add_root').hide();
        $.ajax({
            url:'${baseURL}/orgManage/orgManageAction!listOrgRoot.action',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    orgNodes = result.data.orgList;
                    if (orgNodes.length > 0) {
                        $.fn.zTree.init($('#orgTree'), setting, orgNodes);
                        // 展开机构树的根节点
                        var orgTree = $.fn.zTree.getZTreeObj("orgTree");
                        var rootNodes = orgTree.getNodes();
                        if (rootNodes.length > 0)
                            orgTree.expandNode(rootNodes[0], true, false, true);
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
     * 展开父节点
     * @param event
     * @param orgTreeId
     * @param orgTreeNode
     */
    function expandParentOrg(event, orgTreeId, orgTreeNode) {
        var orgTree = $.fn.zTree.getZTreeObj("orgTree");
//    orgTree.reAsyncChildNodes(orgTreeNode, "refresh");
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
     * 在节点后增加添加按钮
     * @param treeId
     * @param treeNode
     */
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0) return;
        // 添加新增机构节点按钮
        var addStr = "<button type='button' permission='orgAdd' class='add' id='addBtn_" + treeNode.id
                + "' title='添加子机构' onfocus='this.blur();'></button>";
        sObj.append(addStr);
        var btn = $("#addBtn_" + treeNode.id);
        if (btn) btn.bind("click", function () {
            beforeAddOrgNode(treeNode);
        });
        // 添加编辑机构节点按钮
        var editStr = "<button type='button' permission='orgEdit' class='add' id='addBtn_" + treeNode.id
                + "' title='添加子机构' onfocus='this.blur();'></button>";
    }

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.id).unbind().remove();
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
     * 查看组织机构具体信息
     * @param event
     * @param treeId
     * @param treeNode
     */
    function viewOrgNode(event, treeId, treeNode) {
    	$("#orgId").val(treeNode.id);
    	doSearch(1);
        _resetFrameHeight();
    }

    /**
     * 新增机构预处理
     */
    function beforeAddOrgNode(treeNode) {
        // 清空表单
        cleanOrgInfo();
        // 设置组织机构的父id
        $('#orgPid').val(treeNode.id);
        // 设置新增节点后要刷新的节点
        expanNode = treeNode;
        $('#save').unbind("click");
        $('#save').bind("click", addOrgNode);
        // 显示新增表单
        $('#org_info').show();
        $('#org_view').hide();
        allowClick = false; //把allowClick设为false，屏蔽点击事件
        _resetFrameHeight();
    }

    /**
     * 新增机构节点
     */
    function addOrgNode() {
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("org_edit") && checkDescLength()) {
            $('#org_edit').attr('action', '${baseURL}/org/org!addOrganization.action');
            $('#org_edit').ajaxSubmit({
                dataType:'json',
                success:function (result) {
                    if ('0' == result.code) {
                        alert(result.desc);
                        refreshNode('orgTree', expanNode);
                        $('#org_info').hide();
                    } else {
                        alert(result.desc);
                    }
                },
                error:function () {
                    alert('通讯错误，请稍候再试');
                }
            });
        }
    }

    /**
     * 更新机构节点信息
     */
    function updateOrgNode() {
        var dqdp = new Dqdp();
        if (dqdp.submitCheck('org_edit') && checkDescLength()) {
            $('#org_edit').attr('action', '${baseURL}/org/org!updateOrganization.action');
            $('#org_edit').ajaxSubmit({
                dataType:'json',
                success:function (result) {
                    if ('0' == result.code) {
                        alert(result.desc);
                        $('#org_info').hide();
                        refreshNode('orgTree', expanNode);
                    } else {
                        alert(result.desc);
                    }
                },
                error:function () {
                    alert('通讯错误，请稍候再试');
                }
            });
        }
    }

    /**
     * 预处理修改组织机构信息
     * @param treeId
     * @param treeNode
     */
    function beforeEditOrg(treeId, treeNode) {
        //设置更改后要刷新的节点
        if (treeNode.getParentNode() != null) {
            expanNode = treeNode.getParentNode();
        } else {
            expanNode = treeNode;
        }
        $('#save').unbind("click");
        $('#save').bind("click", updateOrgNode); //绑定表单提交方法为update
        $('#org_view').remove('dataSrc');
        $('#org_info').attr('dataSrc', '${baseURL}/org/org!viewOrganization.action?id=' + treeNode.id);
        _initElementValue();
        $('#org_info').show();
        $('#org_view').hide();
        _resetFrameHeight();
        allowClick = false; //把allowClick设为false，屏蔽点击事件
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
                data:{'id':treeNode.id},
                type:'post',
                dataType:'json',
                success:function (result) {
                    if ('0' == result.code) {
                        alert(result.desc);
                        $('#org_info').hide();
                        $('#org_view').hide();
                        return true;
                    } else {
                        alert(result.desc);
                        return false;
                    }
                },
                error:function () {
                    alert('通讯错误，请稍候再试');
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
            alert('不能移动成根机构节点');
            return false;
        }
        // 目标节点与父节点相同，不用移动
        if (targetNode.id == treeNodes[0].parentNode.id) return false;
        $.ajax({
            type:'post',
            url:'${baseURL}/org/org!dragOrgNode.action',
            data:{
                'orgVO.id':treeNodes[0].id,
                'orgVO.parentId':treeNodes[0].parentId,
                'newParentId':targetNode.id
            },
            dataType:'json',
            success:function (result) {
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
        cleanOrgInfo();
        $('#org_info').show();
        $('#org_view').hide();
        $('#save').unbind("click");
        $('#save').bind("click", addRoot);
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
    function refreshNode(treeId, treeNode) {
        var orgTree = $.fn.zTree.getZTreeObj(treeId);
        orgTree.reAsyncChildNodes(treeNode, "refresh", true);
    }

    /**
     * 清除表单信息
     */
    function cleanOrgInfo() {
        $(':input', '#org_edit').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
    }

    function checkDescLength() {
        if ($('#orgDesc').val().length > 150 || $('#orgAddress').val().length > 50) {
            alert('机构描述信息或联系地址超出字数限制！');
            return false;
        }
        return true;
    }


    function checkDescLength() {
        if ($('#orgDesc').val().length > 150 || $('#orgAddress').val().length > 50) {
            alert('机构描述信息或联系地址超出字数限制！');
            return false;
        }
        return true;
    }

    function doSearch($pageIndex) {
        $('#user_search').ajaxSubmit({
            data:{
                'page':$pageIndex
            },
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"id",
                                title:[
                                    {width:"10%", name:"name", showName:"姓名"},
                                    {width:"10%", name:"organizationName", showName:"所属组织"},
                                    {width:"5%", name:"", showName:"选择", isCheckColunm:true}
                                ],
                                data:result.data.pageData,
                                trStyle:["trWhite"]
                            });
                    list1.createList("user_list");
                    
					 // 添加选择事件
					 initCheckEvent();
					 // 选择已选择的用户
					 var userMap = window.opener.getUserMap();
					 userIdArray = userMap.keys();
					 $.each($('[name=ids]'), function () {
					     for (var i = 0; i < userIdArray.length; i++) {
					         if ($(this).val() == userIdArray[i]) {
					             $(this).prop("checked", true);
					             break;
					         }
					     }
					 });
					 initCheckEvent();
					 
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("topIdPage");
                    pager.createPageBar("downIdPage");

                } else {
                    $("#tip").error({title:"错误", content:result.desc, button:[
                        {name:"确定", event:"_hideMsg()"}
                    ]});
                }
            },
            error:function () {
            }
        });
    }


    function cleanForm() {
        $("#user_search :input").not(":button,:reset,:submit,:hidden").val("");
    }
     
    
	 $('#confirmBtn').click(function () {
		window.opener.updateUser();
		window.close();
	 }); 
	 
	 $('#cancelBtn').click(function () {
		window.close();
	 }); 
	 
	 
	 function initCheckEvent() { 
		 
	     $('[name=ids]').click(function () {
	         if ($(this).prop('checked')) {
	             window.opener.getUserMap().put($(this).val(), $(this).parent().parent().find("td").eq(0).html());
	             initSelUserEvent();
	         } else {
	             window.opener.getUserMap().remove($(this).val());
	             initSelUserEvent();
	         }
	     });
	     $(':checkbox:eq(0)').click(function () {
	         checkAll();
	     });
	     initSelUserEvent();
	 }
	
	 function checkAll() {
	     $("[name=ids]").each(function () {
	         if ($(this).prop('checked')) {
	             window.opener.getUserMap().put($(this).val(), $(this).parent().parent().find("td").eq(0).html());
	             initSelUserEvent();
	         } else {
	             window.opener.getUserMap().remove($(this).val());
	             initSelUserEvent();
	         }
	     });
	 }

	 
	 function initSelUserEvent(){
		 selUserMap = window.opener.getUserMap();
	     var userTable = new DataTable({
	         data: selUserMap,
	         colsCount:10
	     });
	     userTable.createUserList("choose_user_list");
	 }
	 
	 function delUser(obj){
		 var objValue = $(obj).attr("id");
		 window.opener.getUserMap().remove(objValue);
		 
       	 $.each($('[name=ids]'), function () {
			if($(this).val() == objValue){
	    		 $(this).prop("checked", "");
	    	}   
  		});
		 initCheckEvent();
	 }
	 
 function selectParent(){
	window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp","_blank","width=800,height=400");
 } 
 
 function selectOrg(id,name){
	document.getElementById("parentId").value = id;
	document.getElementById("parentName").value = name ;
 } 
</script>
</body>

</html>
