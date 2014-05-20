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
    <title>新增机构</title>
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
<form action="${baseURL}/orgManage/orgManageAction!ajaxAdd.action" method="post" id="org_add">
   <div class="searchWrap" style="display: none;">
            <div class="title">
                <h2 class="icon1">新增机构</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">

        <tbody>
            <tr>
                <td class="tdBlue"><span><font color="red">*</font></span>组织名称</td>
                <td >
                    <input class="form120px"  name="tbOrganizationPO.organizationName" type="text"
                           valid="{must:true,tip:'组织名称',fieldType:'pattern',length:100}"/>
                    
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">父级组织</td>
                <td >
                    <input class="form120px" id="parentName" name="parentName" value="${param.name}" type="text" readonly="readonly" onclick="selectParent()"
                           valid="{must:false,tip:'父级组织',fieldType:'pattern',length:100}"/>
                    <input class="greenbtn mr10" type="button"  onclick="selectParent()" value="选择组织" />
                    <input type="hidden" name="tbOrganizationPO.parentId" id="parentId" value="${param.parentId}"/>
                    
                </td>
            </tr>
            <tr>
                <td class="tdBlue">组织标识</td>
                <td >
                    <select name="tbOrganizationPO.organizationSign" dictType="organizationSign" defaultValue="0" ></select>
                </td>
            </tr>
            <tr>
                <td  class="tdBlue"><span><font color="red">*</font></span>组织类型</td>
                <td >
                    <select name="tbOrganizationPO.organizationType"  onchange="showType(this)" dictType="organizationType" defaultValue="0" valid="{must:true,tip:'组织类型',fieldType:'pattern',length:100}"></select>
                    
                </td>
            </tr>
            <tr id="newType" style="display: none">
                <td  class="tdBlue">“两新”类型</td>
                <td >
                    <select name="tbOrganizationPO.newType" dictType="newType" defaultValue="0" ></select>
                </td>
            </tr>
            <tr>
                <td class="tdBlue">组织图片</td>
                <td >
                    <input class="form120px" name="file" id="file" type="file" style="width: 81.5%;"/>
                   <input name="tbOrganizationPO.imgPath" id="imgPath" class="form120px" value="" type="hidden"  valid="{must:false,tip:'图片'}" /> 
                   <input type="button" value="上 传" onClick="uploadFile();" class="greenbtn mr10">
			       <img id="img" src="" height="100px" width="100px" style="display:none;" />
						
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">组织坐标</td>
                <td >
                    <input class="form120px" style="width: 37.5%;" name="tbOrganizationPO.longitude" id="num1" type="text"/>--
                    <input class="form120px" style="width: 37.5%;" name="tbOrganizationPO.latitude" id="num2" type="text"/><span id="tnum" style="color:red"></span>
                    <span><font color="red">(经度，纬度)</font></span>
                </td>
            </tr>
            <tr>
                <td  class="tdBlue">组织管理员</td>
                <td>
                    <input class="form120px" type="text" id="memberName"  readonly="readonly" onclick="selectMember()"/>
                    <input class="greenbtn mr10" type="button"  onclick="selectMember()" value="选择管理员" />
                    <input type="hidden" name="tbOrganizationPO.administrator" id="memberId"/>
                </td>
            </tr>
            <tr>
                <td class="tdBlue">联系电话</td>
                <td>
                    <input class="form120px" id="mobile" name="tbOrganizationPO.telephone" type="text"  valid="{must:false,tip:'联系电话',length:22}"/><span id="tmobile" style="color:red"></span>
                </td>
            </tr>
            <tr>
                <td class="tdBlue">备注</td>
                <td >
                <textarea rows="3" name="tbOrganizationPO.remark"  valid="{must:false,tip:'备注',length:2000}" ></textarea>
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
$(document).ready(function() {
	flashIframe_GDZC();
});
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

    $('#save').click(function () {
    	var parentId=$("#parentId").val();
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("org_add")) {
         var mobile= $("#mobile").val();
         var reMobile=/^[0-9]*$/;  
         var reNum=/^[0-9]+.?[0-9]*$/;
         var isMobile=reMobile.test(mobile);
         
         if(""!=mobile&&!isMobile){
        	 $("#tmobile").text("请输入正确的手机号码");
        	 return; 
        	 
         }else{
        	 $("#tmobile").text("");
        	 
         }
         if(""!=mobile&&mobile.length!=11){
        		 $("#tmobile").text("请输入正确的手机号码");
        	 return; 
        	 }else{
        		 $("#tmobile").text("");
        	 }
         var num1=$("#num1").val();
         var isNum1= reNum.test(num1);
         if(""!=num1&&!isNum1){
             $("#tnum").text("请输入正确的坐标");
             return;
         }else{
        	 $("#tnum").text("");
         }
         var num2=$("#num2").val();
         var isNum2= reNum.test(num2);
         if(""!=num2&&!isNum2){
             $("#tnum").text("请输入正确的坐标");
             return;
         }else{
        	 $("#tnum").text("");
         }
         document.getElementById("save").disabled = true; 
                // 提交数据
                if($("#parentId").val()==''){
                if(confirm("由于父级组织为空，是否建立根机构？")){    
                $('#org_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('建立根机构成功');  
                             parent.location.reload();
                            window.location.href=("orgPageList.jsp?id="+parentId); 
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('建立根机构失败，请稍候再试'); 
                    }
                });
                }
                }else{
                	$('#org_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('新增机构成功'); 
                             parent.location.reload();
                            window.location.href=("orgPageList.jsp?id="+parentId); 
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('新增机构失败，请稍候再试'); 
                    }
                });
                }

        }
    });

   function selectParent(){
	   window.open("selectOrgList.jsp","_blank","height=600, width=800, top=100,left=200");
   }
   function selectMember(){
	   window.open("${baseURL}/jsp/component/basis/partymenber/selectPartyMember.jsp?isManagement=1","_blank","height=600, width=800, top=100,left=200");
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
