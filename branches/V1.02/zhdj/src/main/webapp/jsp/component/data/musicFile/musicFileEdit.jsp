<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
    <jsp:param name="dict" value="musicType"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="${baseURL}/css/progressBar.css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/progressBar.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/musicfile/musicfileAction!ajaxUpdate.action" method="post" id="org_add" enctype="multipart/form-data">
<div class="searchWrap">
            <div class="title">
                <h2 class="icon1">修改</h2>
            </div>
        </div>
    <div class="text_bg" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" datasrc="${baseURL}/musicfile/musicfileAction!ajaxView.action?id=${param.id}">
       <input type="hidden" name="tbMusicfilePO.id"/>
        <input type="hidden" name="tbMusicfilePO.fileSize" id="fileSize"/>

        <tbody>
            <tr>
                <td class="tdBlue"><span><font color="red">*</font></span>文件类型</td>
                <td>
                    <select class="form120px"  name="tbMusicfilePO.type" dictType="musicType"
                           valid="{must:true,tip:'文件类型',fieldType:'pattern',length:100}"/>
                    
                </td>
            </tr>
            <tr>
                <td class="tdBlue"><span><font color="red">*</font></span>文件名</td>
                <td>
                    <input class="form120px"  name="tbMusicfilePO.fileName" id="fileName"  type="text" 
                           valid="{must:true,tip:'文件名',fieldType:'pattern',length:100}"/>
                    
                </td>
            </tr>
            <tr>
                <td class="tdBlue"><span><font color="red">*</font></span>文件上传</td>
                <td>
                   <input class="form120px" name="file"  id="file" type="file"  style="width: 81%;"/>
                   <input name="tbMusicfilePO.filePath" id="imgPath" class="form120px"  type="hidden"   />&nbsp; 
                   <input type="button" value="上 传" onClick="uploadFile();" class="greenbtn mr10" id="sbutton"/>   
                    
                 </td>
            </tr>
            <tr>
                <td class="tdBlue">文件说明</td>
                <td>
                <textarea rows="3" name="tbMusicfilePO.remark"  valid="{must:false,tip:'文件说明',length:2000}" ></textarea>
                </td>
            </tr>
          
         
        </tbody>
    </table>
    </div>
</form>
<div id="progress">
   <div id="title"><span id="text">上传进度</span><div id="close">X</div></div>
   <div id="progressBar">
   	<div id="uploaded"></div>
   	</div>
   	<div id="info"></div>
   	<br/>
   	<div align="center"><input class="btn4" type="button" id="closeBt" value="确定"/></div>
   </div>
<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" id="save" type="button" value="保存"/>
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->

<script type="text/javascript">

   

    $('#save').click(function () {
    	if(""==$("#imgPath").val()){
    		alert("请上传文件！");
    		return;
    	}
    	var parentId=$("#parentId").val();
        var dqdp = new Dqdp();
        if (dqdp.submitCheck("org_add")) {
        
                    $("#file").attr("disabled","disabled");
                	$('#org_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('修改成功'); 
                            if(""!=$("#file").val()){
                                 window.location.href=("musicFileList.jsp?filePath="+$("#imgPath").val()+"&fileName="+$("#fileName").val()); 
                            }else{
                            	 window.location.href=("musicFileList.jsp");
                            }
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


   function getFiletype(filename)
    {
    var extStart  = filename.lastIndexOf(".")+1;
     return filename.substring(extStart,filename.length).toUpperCase();
    } 

   function uploadFile(){
	  // $('#org_add').attr("action","${baseURL}/videofile/videofileAction!uploadFile.action?dir=videoFile"); 
	
	   if(""==$("#file").val()){
		   alert("请选择文件！");
		   return;
	   }
	   	var file = $("#file").val();   
        var fileext = getFiletype(file);   
        var allowtype =  ["MP3"];
        if ($.inArray(fileext,allowtype) == -1)
          {
            alert("视频文件上传只支持'mp3'类型");  
            return;
          }
	   $('#org_add').attr("action","${baseURL}/progressUpload?dir=musicFile"); 
	   var myDate = new Date();
		startTime = myDate.getTime();
		$("#sbutton").attr("disabled", true);
		//$('#org_add').submit();
		$('#org_add').ajaxSubmit({
                  dataType:'json',
                    success:function (result) {  
			                
			                if("true"==result.result){ 
                        	    $("#imgPath").val(result.url);
                        	     
                        	}else{
                        		 $("#progress").hide();
                        		 $("#sbutton").attr("disabled", false);
                        		alert(result.tip);
                        	}
                        
                    },   
                    error:function () {
                        alert('上传失败，请稍候再试');  
                    }
                });
		$("#progress").show();
	     window.setTimeout("getProgressBar()", 500);
	     $('#org_add').attr("action","${baseURL}/musicfile/musicfileAction!ajaxUpdate.action"); 
   }
</script>

</body>
</html>
