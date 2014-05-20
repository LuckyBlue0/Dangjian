<%@ page import="cn.com.do1.common.dictionary.DictOperater" %>
<%@ page import="cn.com.do1.common.dictionary.vo.ExItemObj" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>增加/修改字典项</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/css.css" type="text/css">
  <script Language="JavaScript" src="js/style.js"></script>
  <script Language="JavaScript" src="js/comm.js"></script>
  <script Language="JavaScript" src="js/check.js"></script>
  <script Language="JavaScript" src="js/wait.js"></script>
  <script Language="JavaScript" src="js/hrjs.js"></script>
  <script language="JavaScript" src="js/disablePop.js"></script>
  <!--<script Language="JavaScript" src=""></script>-->
</head>

<body class="scroll">
<script>
  function doSubmit() {
    if (checkByFormName("form_1"))
      {
          showSubmitWait();
          form_1.submit();
      }
  }

</script>
<%
  String type=request.getParameter("type");
  String item=request.getParameter("item");
  String action=request.getParameter("action");
  boolean modify = "modify".equals(action);
  ExItemObj obj = null;
  if(modify)
    obj = DictOperater.getItem(type, item);
%>
<form name="form_1" method="post" action="dict_save.jsp">
  <input type="hidden" name="type" value="<%=type%>">
  <input type="hidden" name="item" value="<%=item%>">
  <input type="hidden" name="action" value="<%=action%>">
  <table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="#CCCCCC">
    <tr bgcolor="#DDDEE6">
      <td valign="top" background="images/main_top_bg.gif">
        <a style="cursor:hand" onclick="doSubmit()" onmouseover="doMouseOverButton(this)" onmouseout="doMouseUnButton(this)">
          <img src="images/save.gif" width="14" align="absmiddle"/>
          保存</a>
        <a style="cursor:hand" onclick="window.close()" onmouseover="doMouseOverButton(this)" onmouseout="doMouseUnButton(this)">
          <img src="images/back.gif" width="16"  align="absmiddle"/>
          关闭</a>
      </td>
    </tr>
  </table>
  <table width="99.17%" border="0" cellspacing="0" cellpadding="2" align="center">
    <tr style="padding-top:15px;">
      <td width="30%" align="right">字典项的描述：</td>
      <td width="70%" align="left">
        <input type="text" showName="字典项的描述" checkNull="true" class="notnull" value="<%=modify?obj.getFsItemDesc():""%>" name="fsItemDesc" size="30">
      </td>
    </tr>
    <tr>
      <td width="30%" align="right">字典项的代码：</td>
      <td width="70%" align="left">
        <%if(modify){%>
          <input type="hidden" name="fsItemCode" value="<%=obj.getFsItemCode()%>"><%=obj.getFsItemCode()%>
          <% }else{%>
        <input type="text" showName="字典项的代码" checkNull="true" class="notnull" name="fsItemCode" size="30">
        <%}%>
      </td>
    </tr>
  </table>
</form>
</body>
</html>