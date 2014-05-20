<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value=""></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-增加1</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
</head>

<body>


<form action="${baseURL}/demomodel/demomodel!testList.action" method="post" id="61c99212056c49208158e76929ce0e17" onsubmit="return false;">
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
                                        <td width="60" height="30">测试字段1：</td>
                                        <td width="140"><select name="searchValue.xxx"></select>
                                        </td>
                                        <td><input class="btnQuery" name="input" type="button" onclick="func_61c99212056c49208158e76929ce0e17(1)" value="搜索"/></td>
                                    </tr>
                                    <tr>
                                        <td width="60" height="30">测试字段2：</td>
                                        <td width="140"><input class="form120px" name="searchValue.xxx2" type="text"/>
                                        </td>
                                        <td><input class="btnQuery" name="input" type="button" onclick="func_61c99212056c49208158e76929ce0e17(1)" value="搜索"/></td>
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
    var var_61c99212056c49208158e76929ce0e17={cv:"1,3",
                "maxPage":13, "currPage":2, "totalRows":12,"test1":{"test2":"a"},
                "pageData":[
                    {"haha1":"hhh1", "haha2":"hhh1", "haha3":"hhh1"},
                    {"haha1":"hhh2", "haha2":"<script><\/script>", "haha3":"hhh2"},
                    {"haha1":"hhh3", "haha2":"hhh3abcdefgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "haha3":"hhh3"}
                ]};
    function func_61c99212056c49208158e76929ce0e17(pageIndex) {
        $('#61c99212056c49208158e76929ce0e17').ajaxSubmit({
            dataType:'json',
            data:{page:pageIndex},
            async:false,
            success:function (result) {
                if ("0" == result.code) {
                    var_61c99212056c49208158e76929ce0e17 = result.data;
                    _redraw();
                } else {
                    $("#tip").error({title:"信息提示层", content:result.desc, button:[
                        {text:"确定", events:"test"},
                        {text:"取消", events:"test"}
                    ]});
                }
            },
            error:function () {
                $("#tip").error({title:"信息提示层", content:"网络异常", button:[
                    {text:"确定", events:"test"},
                    {text:"取消", events:"test"}
                ]});
            }
        });
    }
</script>
<div class="pageDown" datasrc="local:var_61c99212056c49208158e76929ce0e17" id="7a4b0e75c763468f90c254f2ed31abb6">
    <div showOn="@{totalRows}>0" template="">
        <a href="javascript:func_61c99212056c49208158e76929ce0e17(@{currPage}-1)">上一页</a>|
        <a href="javascript:func_61c99212056c49208158e76929ce0e17(@{currPage}+1)">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currentPage}" id="7a4b0e75c763468f90c254f2ed31abb6_page" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo(@{maxPage},$('#'+'7a4b0e75c763468f90c254f2ed31abb6_page').val(),'func_61c99212056c49208158e76929ce0e17')" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}<1">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
</div>
<div class="operation">
    <input class="btnS4" type="button" id="6462a21b47a949e296e2ca3847d4aac0" onclick="xxx" value="新增"/>
    <input class="btnS4" type="button" id="f275723ab9d24d2abb7d4c048a40ca3f" onclick="xxx" value="删除"/>
</div>
<div datasrc="local:var_61c99212056c49208158e76929ce0e17" id="9a70d43bfc94494ab26fff9bb40e6a44" delUrl="${baseURL}/" editUrl="${baseURL}/">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" class="tableCommon">
        <thead>
            <tr>
                <th width="10%"><input type="checkbox" onclick="_doCheck(this,'ids')"></th>
                <th width="20%">字段1</th>
                <th width="20%">字段2</th>
                <th width="20%">字段3</th>
                <th width="20%"> 操作</th>
            </tr>
        </thead>
        <tbody>
            <tr class="trWhite|trBlack" name="pageData" >
                <td width="10%" class="tc"><input type="checkbox" value="@{haha1}" name="ids"></td>
                <td class="tc" name="haha2"></td>
                <td class="tc" name="haha2"></td>
                <td class="tc" name="haha3"></td>
                <td width="20%" class="tdOpera  tc">
                    <a href="javascript:_doSignlEdit('9a70d43bfc94494ab26fff9bb40e6a44','@{haha1}')">编辑</a>&nbsp;&nbsp;
                    <a href="javascript:_doSignlDel('9a70d43bfc94494ab26fff9bb40e6a44','@{haha1}')">删除</a>&nbsp;&nbsp;
                </td>
            </tr>
        </tbody>
    </table>
</div>

<div class="pageDown" datasrc="local:var_61c99212056c49208158e76929ce0e17" id="b6ddd8f657dd4c73b7394264f6a67788">
    <div showOn="@{totalRows}>0" template="">
        <a href="javascript:func_61c99212056c49208158e76929ce0e17(@{currPage}-1)">上一页</a>|
        <a href="javascript:func_61c99212056c49208158e76929ce0e17(@{currPage}+1)">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currentPage}" id="b6ddd8f657dd4c73b7394264f6a67788_page" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo(@{maxPage},$('#'+'b6ddd8f657dd4c73b7394264f6a67788_page').val(),'func_61c99212056c49208158e76929ce0e17')" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}<1">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
</div>

</body>
</html>
