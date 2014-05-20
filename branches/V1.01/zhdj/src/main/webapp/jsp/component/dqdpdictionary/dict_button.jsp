<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../../common/dqdp_common.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <title>干部任免审批表</title>
  <link href="css/css.css" rel="stylesheet" type="text/css">
  <script language="JavaScript" src="js/disablePop.js"></script>
  <script language="JavaScript" src="js/style.js"></script>
</head>
<script>
  var typeID = "";
  var itemID = "";
  function setType($type) {
    typeID = $type;
  }
  function setItem($item) {
    itemID = $item;
  }

  function itemCanUse($canUse) {
    if ("false" == $canUse) {
      document.getElementById("btn_add").style.visibility = "hidden";
      document.getElementById("btn_disable").style.visibility = "hidden";
      document.getElementById("btn_edit").style.visibility = "hidden";
      document.getElementById("btn_enable").style.visibility = "visible";
    }
    else {
      document.getElementById("btn_add").style.visibility = "visible";
      document.getElementById("btn_edit").style.visibility = "visible";
      document.getElementById("btn_disable").style.visibility = "visible";
      document.getElementById("btn_enable").style.visibility = "hidden";
    }
  }
  var winParm = "top=235,left=35,width=400,height=100";
  function doAdd() {
    if (typeID == "" && itemID == "") {
      alert("请确认已选择了字典项，并且字典项不是大类");
      return;
    }
    window.open('DictAdd.jsp?type=' + typeID + '&item=' + itemID, '', winParm);
  }

  function doEdit(){
     if (typeID == "" && itemID == "") {
      alert("请确认已选择了字典项，并且字典项不是大类");
      return;
    }
    if (typeID != "" && itemID == "") {
      alert("不能修改分类，请选择具体的字典项");
      return;
    }
    if (confirm("确认要修改当前选中字典项吗?"))
      window.open('DictAdd.jsp?action=modify&type=' + typeID + '&item=' + itemID, '', winParm);
  }

  function doDisable() {
    if (typeID == "" && itemID == "") {
      alert("请确认已选择了字典项，并且字典项不是大类");
      return;
    }
    if (typeID != "" && itemID == "") {
      alert("不能禁用分类，请选择具体的字典项");
      return;
    }
    if (confirm("确认要禁用当前选中字典项吗?"))
      window.open('dict_disable.jsp?action=disable&type=' + typeID + '&item=' + itemID, '', winParm);
  }
  function doEnable() {
    if (typeID == "" && itemID == "") {
      alert("请确认已选择了字典项，并且字典项不是大类");
      return;
    }
    if (typeID != "" && itemID == "") {
      alert("请选择具体的字典项");
      return;
    }
    if (confirm("确认要启用当前选中字典项吗?"))
      window.open('dict_disable.jsp?action=enable&type=' + typeID + '&item=' + itemID, '', winParm);
  }
</script>

<body class="body_comm">
<form name="name_form">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">

    <tr bgcolor="#DDDEE6">
      <td height="25" >
          <img src="images/info.gif"  align="absbottom">
        &nbsp;
        <a id="btn_add"  onclick="doAdd()" onmouseover="doMouseOverButton(this)" onmouseout="doMouseUnButton(this)">
            <img src="images/add.gif" border="0"  align="absmiddle">新建</a>
        <a width="16" height="16" id="btn_edit"  onclick="doEdit()" onmouseover="doMouseOverButton(this)" onmouseout="doMouseUnButton(this)">
            <img src="images/editdoc.gif" border="0"  align="absmiddle">编辑</a>
        <a width="16" height="16" id="btn_disable"  onclick="doDisable()" onmouseover="doMouseOverButton(this)" onmouseout="doMouseUnButton(this)">
            <img src="images/delete.gif" border="0"  align="absmiddle">禁用</a>
        <a width="16" height="16" id="btn_enable"  onclick="doEnable()" onmouseover="doMouseOverButton(this)" onmouseout="doMouseUnButton(this)">
            <img src="images/info.gif" border="0"  align="absmiddle">启用</a>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
