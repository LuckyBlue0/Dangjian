<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="roleList"></jsp:param>
    <jsp:param name="mustPermission" value="userAdd"></jsp:param>
    <jsp:param name="dict" value="organizationSign,organizationType,newType"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看机构</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.core-3.1.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/ztree/js/jquery.ztree.exedit-3.1.js"></script>
    <script src="../js/CheckboxTable.js"></script>
    <style type="text/css">
    </style>
</head>

<body id="mybody">
<form action="${baseURL}/orgManage/orgManageAction!ajaxUpdate.action" method="post" id="org_edit">
    <div class="searchWrap" style="display: none;">
            <div class="title">
                <h2 class="icon1">查看机构</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" dataSrc="${baseURL}/orgManage/orgManageAction!ajaxView.action?id=${param.id}" cellspacing="0" cellpadding="0">
        <input type="hidden" name="tbOrganizationPO.id"/>

        <tbody>
            <tr>
                <td  class="tdBlue">组织名称</td>
                <td>
                    <input class="form120px"  name="tbOrganizationPO.organizationName" type="text"
                           valid="{must:true,tip:'组织名称',fieldType:'pattern',length:100}"/>
                    
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">父级组织</td>
                <td >
                    <input class="form120px" id="parentName" name="parentName" value="${param.name}"  type="text" readonly="readonly" onclick="selectParent()"
                           valid="{must:false,tip:'父级组织',fieldType:'pattern',length:100}"/>
                    <input type="hidden" name="tbOrganizationPO.parentId" id="parentId" value="${param.parentId}"/>
                    
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">组织标识</td>
                <td >
                    <select name="tbOrganizationPO.organizationSign" dictType="organizationSign" defaultValue="0" ></select>
                </td>
            </tr>
            <tr>
                <td class="tdBlue">组织类型</td>
                <td >
                    <select name="tbOrganizationPO.organizationType"   onchange="showType(this)" dictType="organizationType" defaultValue="0" valid="{must:true,tip:'组织类型',fieldType:'pattern',length:100}"></select>
                    
                </td>
            </tr>
            <tr id="newType" style="display: none">
                <td  class="tdBlue">“两新”类型</td>
                <td >
                    <select name="tbOrganizationPO.newType" id="type" dictType="newType" defaultValue="0" ></select>
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">组织图片</td>
                <td >
                    <input class="form120px" name="file" id="file" type="file" style="width: 81.5%;"/>
                   <input name="tbOrganizationPO.imgPath" id="imgPath" class="form120px" value="" type="hidden"  valid="{must:false,tip:'图片'}" />&nbsp;
				   <br/><img id="img" src="" height="100px" width="100px" style="display:none;" />
						
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">组织坐标</td>
                <td >
                    <input class="form120px" style="width: 37.5%;" id="num1" name="tbOrganizationPO.longitude" type="text"/>--
                    <input class="form120px" style="width: 37.5%;" id="num2" name="tbOrganizationPO.latitude" type="text"/><span id="tnum" style="color:red"></span>
                    <span><font color="red">(经度，纬度)</font></span>
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">组织管理员</td>
                <td >
                    <input class="form120px" id="memberName" name="memberName" type="text"  readonly="readonly" onclick="selectMember()"/>
                    <input type="hidden" name="tbOrganizationPO.administrator" id="memberId"/>
                </td>
            </tr>

            <tr>
                <td  class="tdBlue">联系电话</td>
                <td >
                    <input class="form120px" id="mobile" name="tbOrganizationPO.telephone" type="text"  valid="{must:false,tip:'联系电话',length:22}"/><span id="tmobile" style="color:red"></span>
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">备注</td>
                <td >
                <textarea rows="3" name="tbOrganizationPO.remark" valid="{must:false,tip:'备注',length:2000}" ></textarea>
                </td>
            </tr>
         
        </tbody>
    </table>
    </div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

 function flashIframe_GDZC() {
	var i = parent.document.getElementById("orgUser");
	if (i != null) {
		if (document.body.scrollHeight < 700) {
			i.style.height = "730px";
		} else {
			i.style.height = (document.body.scrollHeight + 30) + "px";
		}
	}
}
     var expanNode;

   
  
   function showType($this){
	   var type=$($this).val();
	   if(type=='3'){
		   $("#newType").css("display","");
	   }else{
		   $("#newType").css("display","none");
	   }
   }
   
    $(document).ready(function () {
      if($("#type").val()==''||$("#type").val()=='0'){
    	   $("#newType").css("display","none");
      }else{
    	  $("#newType").css("display","");
      }
      var imgPath=$("#imgPath").val();
      $("#img").attr("src","${baseURL}/"+imgPath);
       $("#img").css("display","");
       flashIframe_GDZC();
    });
     
   
</script>

</body>
</html>
