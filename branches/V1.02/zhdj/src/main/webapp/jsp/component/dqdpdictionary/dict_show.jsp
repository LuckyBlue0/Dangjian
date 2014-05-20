<%@ page import="cn.com.do1.common.dictionary.DictOperater" %>
<%@ page import="cn.com.do1.common.dictionary.DictUtil" %>
<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<%@include file="../../common/dqdp_common.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>无标题文档</title>
    <link href="css/css.css" rel="stylesheet" type="text/css">
    <link href="css/stree.css" rel="stylesheet" type="text/css">
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script language="JavaScript" src="js/disablePop.js"></script>
    <script language="JavaScript" src="js/STree.js"></script>
    <script language="JavaScript" src="js/check.js"></script>
    <script language="JavaScript" src="js/comm.js"></script>
    <style>
        td {
            height: 18px;
            font-size: 12px;
        }
    </style>
</head>
<script>

    function doChoose($typeID, $itemID, $canUse) {
        parent.buttonFrame.window.setType($typeID);
        parent.buttonFrame.window.setItem($itemID);
        parent.buttonFrame.window.itemCanUse($canUse);
    }
</script>
<%
    String loadNode = request.getParameter("loadNode");
    String realNode = "";
    if (!WebUtil.ParmIsNull(loadNode)) {
        realNode = DictUtil.getDictPath(loadNode);
    }
%>
<body style="border:0;padding:0;margin:0;background-color:#F6F3F7">
<form>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0;border-style:none" align="center">
        <%
            ArrayList list = DictOperater.getAllClass();
            list.remove("人事系统专用字典项");
            list.add("人事系统专用字典项");
            for (int i = 0; i < list.size(); i++) {
        %>
        <tr>
            <td class="tree_td" align="left">
                <img id="click_<%=list.get(i)%>" onclick="doExpand(this,'type=class&classID=<%=list.get(i)%>',1)"
                     posion="<%=(i==list.size()-1)?"2":(i==0)?"0":"1"%>" align='absmiddle' border="0"
                     src="htmltree/img/<%=(i==0)?"P0":(i==list.size()-1)?"P2":"P1"%>.gif" alt="">
                <img  id="img_<%=list.get(i)%>" expandIcon="folderOpen.gif" icon="folder.gif" align='absmiddle' border="0" src="htmltree/img/folder.gif"
                     alt="">
                <%=list.get(i)%>
                <table id="table_<%=list.get(i)%>" opened="uninit" width="100%" border="0" cellspacing="0" cellpadding="0"
                       style="margin:0;border-style:none;display: '" align="center"></table>
            </td>
        </tr>
        <%}%>
    </table>
    <script>
        setUseWait(true);
        setDataSource(baseURL+"/Dict_Source");
        loadDefault("<%=realNode%>");
    </script>
</form>
</body>
</html>
