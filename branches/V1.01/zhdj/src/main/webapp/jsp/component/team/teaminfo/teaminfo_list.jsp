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

<form action="${baseURL}/teaminfo/teaminfoAction!ajaxSearch.action" method="post" id="04bdf44a272e4a09aad95f25f44b5a1e" onsubmit="return false;" templateId="default" dqdpCheckPoint="query_form">
    <div class="searchWrap">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="searchLeft"></td>
                <td class="searchBg">
                    <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                        <tr dqdpCheckPoint="query_field_teamTitle">
                                        <td width="60" height="30">集体主题名称：</td>
                                        <td width="140">
                                        	                                            <input class="form120px" name="searchValue.teamTitle" type="text"/>
                                                                                                                                                                                                                            							                							                                                        </td>
                                        <td><input class="btnQuery" name="input" type="button" onclick="func_04bdf44a272e4a09aad95f25f44b5a1e(1);_redraw();" value="搜索"/></td>
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
    var var_04bdf44a272e4a09aad95f25f44b5a1e;
    
    var refreshEvent = {ok:function(){
    						func_04bdf44a272e4a09aad95f25f44b5a1e(1);
    						_redraw();
    					}
    				}; //,fail:,error:
    
    function func_04bdf44a272e4a09aad95f25f44b5a1e(pageIndex) {
        $('#04bdf44a272e4a09aad95f25f44b5a1e').ajaxSubmit({
            dataType:'json',
            data:{page:pageIndex,
                'dqdp_csrf_token': dqdp_csrf_token},
            async:false,
            success:function (result) {
                if ("0" == result.code) {
                    var_04bdf44a272e4a09aad95f25f44b5a1e = result.data;
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
    func_04bdf44a272e4a09aad95f25f44b5a1e(1);
</script>
<div class="pageDown" datasrc="local:var_04bdf44a272e4a09aad95f25f44b5a1e" id="85566195b4824e96a2191f2f0fea833d"  templateId="default">
    <div showOn="@{totalRows}>0" template="" >
        <a href="javascript:func_04bdf44a272e4a09aad95f25f44b5a1e(@{currPage}-1); _redraw('85566195b4824e96a2191f2f0fea833d');  _redraw('039f672cad654d18a7559204eb585888');  _redraw('5b4ae323f1e74f2b8af5dfd6de66b2ae'); ">上一页</a>|
        <a href="javascript:func_04bdf44a272e4a09aad95f25f44b5a1e(@{currPage}+1); _redraw('85566195b4824e96a2191f2f0fea833d');  _redraw('039f672cad654d18a7559204eb585888');  _redraw('5b4ae323f1e74f2b8af5dfd6de66b2ae'); ">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currPage}" id="85566195b4824e96a2191f2f0fea833d_page" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo('@{maxPage}',$('#'+'85566195b4824e96a2191f2f0fea833d_page').val(),'func_04bdf44a272e4a09aad95f25f44b5a1e');_redraw();" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}<1">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
</div>
<div class="operation" templateId="default">
        <input class="btnS4" type="button" id="d2530640ef084fe3ab75ac90e6e2a2c2" onclick="_doDel('039f672cad654d18a7559204eb585888', refreshEvent)" value="删除"/>
        <input class="btnS4" type="button" id="92c7dd0c5355452da30c611c59f9161e" onclick="_doAdd('039f672cad654d18a7559204eb585888')" value="新增"/>
    </div>
<div datasrc="local:var_04bdf44a272e4a09aad95f25f44b5a1e" id="039f672cad654d18a7559204eb585888" delUrl="${baseURL}/teaminfo/teaminfoAction!batchDeleteTbTeamInfoPO.action" editUrl="${baseURL}/jsp/component/team/teaminfo/teaminfo_edit.jsp" addUrl="${baseURL}/jsp/component/team/teaminfo/teaminfo_add.jsp" viewUrl="${baseURL}/jsp/component/team/teaminfo/teaminfo_detail.jsp" templateId="default" >
    <table width="100%" cellspacing="0" cellpadding="0" border="0" class="tableCommon">
        <thead>
            <tr templateId="default" dqdpCheckPoint="list_title">
                                <th width="10%"><input type="checkbox" onclick="_doCheck(this,'ids')"></th>
                                                <th width="20%" dqdpCheckPoint="list_field_title_teamTypeId">集体类别</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_teamTitle">集体主题名称</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_context">集体内容</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_image">集体图片</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_author">发布人</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_releaseDate">发布时间</th>
                                <th width="20%" dqdpCheckPoint="list_field_title_createTime">创建时间</th>
                                <th width="20%" dqdpCheckPoint="list_field_title__status_desc">状态</th>
                                                <th width="20%">操作</th>
                            </tr>
        </thead>
        <tbody>
            <tr class="trWhite|trBlack" name="pageData" templateId="default" dqdpCheckPoint="list_value">
                                <td width="10%" class="tc"><input type="checkbox" value="@{id}" name="ids" ></td>
                                                <td class="tc" name="teamTypeId" dqdpCheckPoint="list_field_value_teamTypeId"></td>
                                <td class="tc" name="teamTitle" dqdpCheckPoint="list_field_value_teamTitle"></td>
                                <td class="tc" name="context" dqdpCheckPoint="list_field_value_context"></td>
                                <td class="tc" name="image" dqdpCheckPoint="list_field_value_image"></td>
                                <td class="tc" name="author" dqdpCheckPoint="list_field_value_author"></td>
                                <td class="tc" name="releaseDate" dqdpCheckPoint="list_field_value_releaseDate"></td>
                                <td class="tc" name="createTime" dqdpCheckPoint="list_field_value_createTime"></td>
                                <td class="tc" name="_status_desc" dqdpCheckPoint="list_field_value__status_desc"></td>
                                                <td width="20%" class="tdOpera  tc">
                                        <a href="javascript:_doSignlEdit('039f672cad654d18a7559204eb585888','@{id}', refreshEvent)">编辑</a>&nbsp;&nbsp;
                                        <a href="javascript:_doSignlView('039f672cad654d18a7559204eb585888','@{id}', refreshEvent)">查看</a>&nbsp;&nbsp;
                                        <a href="javascript:_doSignlDel('039f672cad654d18a7559204eb585888','@{id}', refreshEvent)">删除</a>&nbsp;&nbsp;
                                    </td>
                            </tr>

        </tbody>
    </table>
</div>

<div class="pageDown" datasrc="local:var_04bdf44a272e4a09aad95f25f44b5a1e" id="5b4ae323f1e74f2b8af5dfd6de66b2ae"  templateId="default">
    <div showOn="@{totalRows}>0" template="" >
        <a href="javascript:func_04bdf44a272e4a09aad95f25f44b5a1e(@{currPage}-1); _redraw('85566195b4824e96a2191f2f0fea833d');  _redraw('039f672cad654d18a7559204eb585888');  _redraw('5b4ae323f1e74f2b8af5dfd6de66b2ae'); ">上一页</a>|
        <a href="javascript:func_04bdf44a272e4a09aad95f25f44b5a1e(@{currPage}+1); _redraw('85566195b4824e96a2191f2f0fea833d');  _redraw('039f672cad654d18a7559204eb585888');  _redraw('5b4ae323f1e74f2b8af5dfd6de66b2ae'); ">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currPage}" id="5b4ae323f1e74f2b8af5dfd6de66b2ae_page" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo('@{maxPage}',$('#'+'5b4ae323f1e74f2b8af5dfd6de66b2ae_page').val(),'func_04bdf44a272e4a09aad95f25f44b5a1e');_redraw();" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}<1">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
</div>

</body>
</html>
