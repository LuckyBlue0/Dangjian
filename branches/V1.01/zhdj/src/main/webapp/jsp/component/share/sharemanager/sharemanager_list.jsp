<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    
            </head>

<body templateId="SQLPager">

<form action="${baseURL}/sharemanager/sharemanagerAction!ajaxSearch.action" method="post" id="026763b269c64b84b374864dd6f725ef" onsubmit="return false;" templateId="default" dqdpCheckPoint="query_form">
    <div class="searchWrap">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="searchLeft"></td>
                <td class="searchBg">
                    <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                        <tr dqdpCheckPoint="query_field_title">
                                        <td width="60" height="30">标题：</td>
                                        <td width="140">
                                        	                                            <input class="form120px" name="searchValue.title" type="text"/>
                                                                                                                                                                                                                            							                							                                                        </td>
                                        <td><input class="btnQuery" name="input" type="button" onclick="func_026763b269c64b84b374864dd6f725ef(1);_redraw();" value="搜索"/></td>
                                    </tr>
                                                                        <tr dqdpCheckPoint="query_field_type">
                                        <td width="60" height="30">类型：</td>
                                        <td width="140">
                                        	                                                                                                                                                                                <select name="searchValue.type" ></select>
                                                                                        							                							                                                        </td>
                                        <td><input class="btnQuery" name="input" type="button" onclick="func_026763b269c64b84b374864dd6f725ef(1);_redraw();" value="搜索"/></td>
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
<script type="text/javascript">
    var var_026763b269c64b84b374864dd6f725ef;
    
    var refreshEvent = {ok:function(){
    						func_026763b269c64b84b374864dd6f725ef(1);
    						_redraw();
    					}
    				}; //,fail:,error:
    
    function func_026763b269c64b84b374864dd6f725ef(pageIndex) {
        $('#026763b269c64b84b374864dd6f725ef').ajaxSubmit({
            dataType:'json',
            data:{page:pageIndex,
                'dqdp_csrf_token': dqdp_csrf_token},
            async:false,
            success:function (result) {
                if ("0" == result.code) {
                    var_026763b269c64b84b374864dd6f725ef = result.data;
                } else {
                    $("#tip").error({title:"信息提示层", content:result.desc, button:[
                        {text:"确定", events:"test"},
                        {text:"取消", events:"test"}
                    ]});
                }
            },
            error:function () {
                _alert("信息提示层", "网络异常");
            }
        });
    }
    func_026763b269c64b84b374864dd6f725ef(1);
</script>
<div class="pageDown" datasrc="local:var_026763b269c64b84b374864dd6f725ef" id="2ed24ee895244fbdaa21b87be1914cdd"  templateId="default">
    <div showOn="@{totalRows}>0" template="" >
        <a href="javascript:func_026763b269c64b84b374864dd6f725ef(@{currPage}-1); _redraw('2ed24ee895244fbdaa21b87be1914cdd');  _redraw('c744fb93de454433bb19ad7cd3ae74e5');  _redraw('b4fc91f8e665482ba7cc1ef137fad730'); ">上一页</a>|
        <a href="javascript:func_026763b269c64b84b374864dd6f725ef(@{currPage}+1); _redraw('2ed24ee895244fbdaa21b87be1914cdd');  _redraw('c744fb93de454433bb19ad7cd3ae74e5');  _redraw('b4fc91f8e665482ba7cc1ef137fad730'); ">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currPage}" id="2ed24ee895244fbdaa21b87be1914cdd_page" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo('@{maxPage}',$('#'+'2ed24ee895244fbdaa21b87be1914cdd_page').val(),'func_026763b269c64b84b374864dd6f725ef');_redraw();" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}<1">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
</div>
<div class="operation" templateId="default">
        <input class="btnS4" type="button" id="a5844dd89f4c4caf9f15ae1b877908f1" onclick="_doDel('c744fb93de454433bb19ad7cd3ae74e5', refreshEvent)" value="删除"/>
        <input class="btnS4" type="button" id="d356e27bec73429ebb43a53b3037c297" onclick="_doAdd('c744fb93de454433bb19ad7cd3ae74e5')" value="新增"/>
    </div>
<div datasrc="local:var_026763b269c64b84b374864dd6f725ef" id="c744fb93de454433bb19ad7cd3ae74e5" delUrl="${baseURL}/sharemanager/sharemanagerAction!batchDeleteTbShareInfoPO.action" editUrl="${baseURL}/jsp/component/share/sharemanager/sharemanager_edit.jsp" addUrl="${baseURL}/jsp/component/share/sharemanager/sharemanager_add.jsp" viewUrl="${baseURL}/jsp/component/share/sharemanager/sharemanager_detail.jsp" templateId="default" >
    <table width="100%" cellspacing="0" cellpadding="0" border="0" class="tableCommon">
        <thead>
            <tr templateId="default" dqdpCheckPoint="list_title">
                                <th width="10%"><input type="checkbox" onclick="_doCheck(this,'ids')"></th>
                                                <th width="20%" dqdpCheckPoint="list_field_title_title">标题</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_status">状态</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_createTime">创建时间</th>
                                <th width="20%" dqdpCheckPoint="list_field_title__type_desc">类型</th>
                                                <th width="20%">操作</th>
                            </tr>
        </thead>
        <tbody>
            <tr class="trWhite|trBlack" name="pageData" templateId="default" dqdpCheckPoint="list_value">
                                <td width="10%" class="tc"><input type="checkbox" value="@{id}" name="ids" ></td>
                                                <td class="tc" name="title" dqdpCheckPoint="list_field_value_title"></td>
                                <td class="tc" name="status" dqdpCheckPoint="list_field_value_status"></td>
                                <td class="tc" name="createTime" dqdpCheckPoint="list_field_value_createTime"></td>
                                <td class="tc" name="_type_desc" dqdpCheckPoint="list_field_value__type_desc"></td>
                                                <td width="20%" class="tdOpera  tc">
                                        <a href="javascript:_doSignlEdit('c744fb93de454433bb19ad7cd3ae74e5','@{id}', refreshEvent)">编辑</a>&nbsp;&nbsp;
                                        <a href="javascript:_doSignlView('c744fb93de454433bb19ad7cd3ae74e5','@{id}', refreshEvent)">查看</a>&nbsp;&nbsp;
                                        <a href="javascript:_doSignlDel('c744fb93de454433bb19ad7cd3ae74e5','@{id}', refreshEvent)">删除</a>&nbsp;&nbsp;
                                    </td>
                            </tr>

        </tbody>
    </table>
</div>

<div class="pageDown" datasrc="local:var_026763b269c64b84b374864dd6f725ef" id="b4fc91f8e665482ba7cc1ef137fad730"  templateId="default">
    <div showOn="@{totalRows}>0" template="" >
        <a href="javascript:func_026763b269c64b84b374864dd6f725ef(@{currPage}-1); _redraw('2ed24ee895244fbdaa21b87be1914cdd');  _redraw('c744fb93de454433bb19ad7cd3ae74e5');  _redraw('b4fc91f8e665482ba7cc1ef137fad730'); ">上一页</a>|
        <a href="javascript:func_026763b269c64b84b374864dd6f725ef(@{currPage}+1); _redraw('2ed24ee895244fbdaa21b87be1914cdd');  _redraw('c744fb93de454433bb19ad7cd3ae74e5');  _redraw('b4fc91f8e665482ba7cc1ef137fad730'); ">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currPage}" id="b4fc91f8e665482ba7cc1ef137fad730_page" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo('@{maxPage}',$('#'+'b4fc91f8e665482ba7cc1ef137fad730_page').val(),'func_026763b269c64b84b374864dd6f725ef');_redraw();" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}<1">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
</div>

</body>
</html>
