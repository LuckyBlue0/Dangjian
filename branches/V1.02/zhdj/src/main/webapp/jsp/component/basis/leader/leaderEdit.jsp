<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="partyPosition"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="../js/CheckboxTable.js"></script>
    <style type="text/css">
    </style>
</head>

<body>
<form action="${baseURL}/leaderinfo/leaderinfoAction!ajaxUpdate.action" method="post" id="org_add">
 <div class="searchWrap" style="display: none;">
            <div class="title">
                <h2 class="icon1">修改领导班子</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/leaderinfo/leaderinfoAction!ajaxView.action?id=${param.id}">
        <input type="hidden" name="tbLearderVO.id"/>

        <tbody>
            <tr>
                <td  class="tdBlue"><span><font color="red">*</font></span>领导姓名</td>
                <td >
                    <input class="form120px"  id="memberName" name="tbLearderVO.userName" type="text" disabled="disabled" onclick="selectMember()"
                           valid="{must:true,tip:'组织名称',fieldType:'pattern',length:100}"/>
                </td>
            </tr>
            <tr>
                <td  class="tdBlue"><span><font color="red">*</font></span>所属组织</td>
                <td >
                    <input class="form120px" id="parentName" name="tbLearderVO.organizationName"   type="text" disabled="disabled" onclick="selectParent()"
                           valid="{must:false,tip:'所属组织',fieldType:'pattern',length:100}"/>
                           <input type="hidden"  id="parentId" value="${param.orgId}"/>
                    
                    
                </td>
            </tr>
            <tr>
                <td  class="tdBlue"><span><font color="red">*</font></span>党内职务</td>
                <td >
                   <select name="tbLearderVO.duty" dictType="partyPosition" valid="{must:true,length:50,tip:'党内职务'}" />
                </td>
            </tr>

          
         
        </tbody>
    </table>
    </div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" id="save" type="button" value="保存"/>
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

   

    $('#save').click(function () {
    	var parentId=$("#parentId").val();
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("org_add")) {
        
            
                	$('#org_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('修改成功'); 
                            window.location.href=("leaderPageList.jsp?id="+parentId); 
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('修改失败，请稍候再试'); 
                    }
                });
                

        }
    });

   function selectParent(){
	   window.open("${baseURL}/jsp/component/systemmgr/org/selectOrgList.jsp","_blank","height=600, width=800, top=100,left=200");
   }
   function selectMember(){
	   var orgId=$("#parentId").val();
	   window.open("${baseURL}/jsp/component/basis/partymenber/selectPartyMember.jsp?orgId="+orgId,"_blank","height=600, width=800, top=100,left=200");
   }
   function showType($this){
	   var type=$($this).val();
	   if(type=='3'){
		   $("#newType").css("display","");
	   }else{
		   $("#newType").css("display","none");
	   }
   }
   function uploadFile(){
	   $('#org_add').attr("action","${baseURL}/orgManage/orgManageAction!fileUpload.action");
	    $('#org_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('文件上传成功！');  
                            $("#img").attr("src","${baseURL}/"+result.desc);
                            $("#img").css("display","");
                            $("#imgPath").val(result.desc);
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('文件上传失败，请稍候再试');  
                    }
                });
	     $('#org_add').attr("action","${baseURL}/orgManage/orgManageAction!ajaxAdd.action");
   }
</script>

</body>
</html>
