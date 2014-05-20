<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="newsColumnAttManager"></jsp:param>
    <jsp:param name="mustPermission" value="newsColumnAttManager"></jsp:param>
    <jsp:param name="dict" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新闻栏目属性-编辑</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
	<script src="${baseURL}/js/do1/common/Math_uuid.js"></script>
	<script src="${baseURL}/js/do1/common/json2.js"></script>
</head>

<body>
<!--头部 end-->

<!--公告 end-->
<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">新闻栏目属性-编辑</h2>
            </div>
        </tr>
    </table>
</div>

<form method="post" id="id_form_add">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="8">新闻栏目属性-编辑 </th>
        </tr>
        </thead>
        <tbody id="modelBodyID">
        </tbody>
    </table>
</form>
<div align="right"><br/><input class="btn4" type="button" onclick="addModel();" value="添加"/>&nbsp;&nbsp;</div>
<div class="toolBar">
    <div align="center">     
    	</br> 
    	<input class="btn4" id="saveButtonId" permission="newsColumnAttManager" type="button" onclick="save()" value="保存" style="display:none"/>
    </div>
</div>  

<script type="text/javascript">
	/**
	*模板数据
	**/
	var modelList;
	
    $(document).ready(function () {
       init();
    });

    /**
     * 初始化模板
     */
    function init() {
  
    }
    function refreshUI(){
    	var html="";
    	if(modelList!=null){
    		for(var i=0;i<modelList.length;i++){
    			html=html+getItemHtml(modelList[i]);
    		}
    	}
		$("#modelBodyID").html(html);
		flashIframe_GDZC();
    }
    function getModelById(id){
    	if(modelList!=null){
    		for(var i=0;i<modelList.length;i++){
    			var model=modelList[i];
    			if(model.labelModelId==id){
    				return model;
    			}
    		}
    	}
    	return null;
    }
    function doItemTypeClick(id,index){
    	//设置UI

    	$("#itemType_"+id+"_"+index).attr("checked","checked");
    	
    	if(index==0){
			$("#itemType_"+id+"_1").removeAttr("checked");
			$("#itemType_"+id+"_2").removeAttr("checked");
			$("#itemType_"+id+"_3").removeAttr("checked");
    		$("#selectListUI_"+id).hide();
    		$("#expireAlertUI_"+id).hide();
    	}else if(index==1){
    		$("#itemType_"+id+"_0").removeAttr("checked");
			$("#itemType_"+id+"_2").removeAttr("checked");
			$("#itemType_"+id+"_3").removeAttr("checked");
			
    		$("#selectListUI_"+id).show();
    		$("#expireAlertUI_"+id).hide();
    	}else if(index==2){
    		$("#itemType_"+id+"_0").removeAttr("checked");
			$("#itemType_"+id+"_1").removeAttr("checked");
			$("#itemType_"+id+"_3").removeAttr("checked");
			
    		$("#selectListUI_"+id).hide();
    		$("#expireAlertUI_"+id).show();
    	}else if(index==3){
    		$("#itemType_"+id+"_0").removeAttr("checked");
			$("#itemType_"+id+"_1").removeAttr("checked");
			$("#itemType_"+id+"_2").removeAttr("checked");
			
    		$("#selectListUI_"+id).hide();
    		$("#expireAlertUI_"+id).show();
    	}
    	
    	//修改数据
    	var model=getModelById(id);
    	if(model!=null){
    		model.itemType=index+"";
    	}
    }
    function doAddSelectClick(id){
    	var inputValue=$("#selectListText_"+id).val();
    	if(inputValue==""){
    		return;
    	}
    	//修改数据
    	var model=getModelById(id);
    	if(model!=null){
    		var t=model.selectList;
    		if(t!=null&&t!=""){
    			var isExist=false;
    			var tList=t.split(",");
    			for(var j=0;j<tList.length;j++){
    				if(tList[j]==inputValue){
    					isExist=true;
    					break;
    				}
    			}
	    		if(isExist==false)
				{
	    			model.selectList=model.selectList+","+inputValue;
	    			//更新UI
	    			$("#selectList_"+id).append("<option value=\""+inputValue+"\" title=\""+inputValue+"\">"+inputValue+"</option>"); 
	    		}
    		}else{
	    			model.selectList=inputValue;
	    			//更新UI
	    			$("#selectList_"+id).append("<option value=\""+inputValue+"\" title=\""+inputValue+"\">"+inputValue+"</option>"); 
    		}
    	}
    }
    function doClearSelectClick(id){
    	if (confirm("确认要清空列表吗？")) {
	    	//修改数据
	    	var model=getModelById(id);
	    	if(model!=null){
	    		model.selectList="";
	    		//更新UI
	    		$("#selectList_"+id).empty();
	    	}
    	}
    }
    
    function doExpireAlertOnClick(id){
        var model=getModelById(id);
		if($("#expireAlert_"+id).attr('checked')==undefined){
			//没选中
    		if(model!=null){
    			model.expireAlert=0;
    		}
		}else{
			//选中
    		if(model!=null){
    			model.expireAlert=1;
    		}
		}
    }
    function doUpClick(id){
    	//更新数据
    	if(modelList!=null){
    		for(var i=0;i<modelList.length;i++){
    			var model=modelList[i];
    			if(model.labelModelId==id){
    				if(i==0){
    					alert('已经是第一行,不能上移');
    					return;
    				}else{
    					modelList[i]=modelList[i-1];
    					modelList[i-1]=model;
    				}
    				break;
    			}
    		}
    		refreshUI();
    	}
    }
    
    function doDownClick(id){
    	//更新数据
    	if(modelList!=null){
    		for(var i=0;i<modelList.length;i++){
    			var model=modelList[i];
    			if(model.labelModelId==id){
    				if(i==(modelList.length-1)){
    					alert('已经是最后一行,不能下移');
    					return;
    				}else{
    					modelList[i]=modelList[i+1];
    					modelList[i+1]=model;
    				}
    				break;
    			}
    		}
    		refreshUI();
    	}
    }
	Array.prototype.remove=function(dx)
	　{
	　　if(isNaN(dx)||dx>this.length){return false;}
	　　for(var i=0,n=0;i<this.length;i++)
	　　{
	　　　　if(this[i]!=this[dx])
	　　　　{
	　　　　　　this[n++]=this[i]
	　　　　}
	　　}
	　　this.length-=1
	　}

    function doDeleteClick(id){
		if (confirm("确认要删除？")) {
	    	//更新数据
	    	if(modelList!=null){
	    		for(var i=0;i<modelList.length;i++){
	    			var model=modelList[i];
	    			if(model.labelModelId==id){
						modelList.remove(i);
						break;
	    			}
	    		}
	    		refreshUI();
	    	}
		}
    }
    function doLabelNameChange(id){
    	var model=getModelById(id);
    	if(model!=null){
    		var labelName=$("#labelName_"+id).val();
    		model.labelName=labelName;
    	}
    }
    function addModel(){
    	//修改数据
    	var id=Math.uuid();
    	var labelName="";
    	var itemType="0";
    	var selectList="";
    	var sortValue=0;
    	var expireAlert=0;
    	var model= { 
			labelModelId : id, 
			labelName : labelName, 
			itemType : itemType,
			selectList : selectList,
			sortValue : sortValue
			//expireAlert : expireAlert
		};
		modelList.push(model);
		
		//刷新UI
		refreshUI();
    }
	function getItemHtml(model){
		
		//日期类型的，判断是否勾选【到期提醒】
		//var expireAlert;
		//if(model.itemType=='2'&&model.expireAlert==1){
		//	expireAlert="checked";
		//}else{
		//	expireAlert="";
		//}-->
		var expireAlertUI="";
		var selectListUI="";
		if(model.itemType=='0'){
			itemType_0="checked";
			itemType_1="";
			itemType_2="";
			itemType_3="";
			selectListUI="none";
			expireAlertUI="none";
		}else if(model.itemType=='1'){
			itemType_0="";
			itemType_1="checked";
			itemType_2="";
			itemType_3="";
			selectListUI="block";
			expireAlertUI="none";
		}else if(model.itemType=='2'){
			itemType_0="";
			itemType_1="";
			itemType_2="checked";
			itemType_3="";
			selectListUI="none";
			expireAlertUI="block";
		}else if(model.itemType=='3'){
			itemType_0="";
			itemType_1="";
			itemType_2="";
			itemType_3="checked";
			selectListUI="none";
			expireAlertUI="block";
		}
		var selectList="";
		if(model.selectList!=null&&model.selectList!=""){
			var selectObjList=model.selectList.split(",");
			for(var i=0;i<selectObjList.length;i++){
				selectList=selectList+"<option value=\""+selectObjList[i]+"\" title=\""+selectObjList[i]+"\">"+selectObjList[i]+"</option>";
			}
		}
		
		
        var html="<tr id='id_"+model.labelModelId+"'>"+
            "<td width=\"70\" class=\"tdBlue\">标签名称:</td>"+
            "<td width=\"70\"><input type=\"text\" id=\"labelName_"+model.labelModelId+"\" value=\""+model.labelName+"\" onChange=\"doLabelNameChange('"+model.labelModelId+"');\" valid=\"{must:true,tip:'标签名称'}\"/></td>"+
            "<td width=\"70\" class=\"tdBlue\">标签类型:</td>"+
            "<td width=\"300\">" +
            	"<input id=\"itemType_"+model.labelModelId+"_0\" type=\"radio\" "+itemType_0+" onClick=\"doItemTypeClick('"+model.labelModelId+"',0);\"/>:文本&nbsp;&nbsp;" +
            	"<input id=\"itemType_"+model.labelModelId+"_1\" type=\"radio\" "+itemType_1+" onClick=\"doItemTypeClick('"+model.labelModelId+"',1);\"/>:单选下拉框 &nbsp;&nbsp;" +
            	"<input id=\"itemType_"+model.labelModelId+"_2\" type=\"radio\" "+itemType_2+" onClick=\"doItemTypeClick('"+model.labelModelId+"',2);\"/>:文本域&nbsp;&nbsp;"+
           		"<input id=\"itemType_"+model.labelModelId+"_3\" type=\"radio\" "+itemType_3+" onClick=\"doItemTypeClick('"+model.labelModelId+"',3);\"/>:图片</td>"+
            "<td>"+
            	"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"selectListUI_"+model.labelModelId+"\" style=\"display:"+selectListUI+"\">"+
            	"<tr>"+
            		"<td width=\"110\"><select style=\"width:100px;\" id=\"selectList_"+model.labelModelId+"\">"+selectList+"</select></td>"+
            		"<td><input type=\"text\" id=\"selectListText_"+model.labelModelId+"\"/></td>"+
            		"<td width=\"40\"><input type=\"button\" value=\"新增\" onClick=\"doAddSelectClick('"+model.labelModelId+"');\" /></td>"+
            		"<td width=\"40\"><input type=\"button\" value=\"清空列表\" onClick=\"doClearSelectClick('"+model.labelModelId+"');\" /></td>"+
            	"</tr>"+
            	"</table>"+
            	//
            	//"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"expireAlertUI_"+model.labelModelId+"\" style=\"display:"+expireAlertUI+"\">"+
            	//"<tr>"+
            		//"<td width=\"110\">是否到期提醒:</td>"+
            		//"<td><input type=\"checkbox\" id=\"expireAlert_"+model.labelModelId+"\" "+expireAlert+" onClick=\"doExpireAlertOnClick('"+model.labelModelId+"');\"/></td>"+
            	//"</tr>"+
            	//"</table>"+
            "</td>"+
            "<td width=\"40\"><input type=\"button\" value=\"上移\" onClick=\"doUpClick('"+model.labelModelId+"');\"/></td>"+
            "<td width=\"40\"><input type=\"button\" value=\"下移\" onClick=\"doDownClick('"+model.labelModelId+"');\"/></td>"+
            //"<td width=\"40\"><input type=\"button\" value=\"删除\" onClick=\"doDeleteClick('"+model.labelModelId+"');\"/></td>"+
        "</tr>";
	    return html;
	}
	function checkData(){
    	if(modelList!=null){
    		for(var i=0;i<modelList.length;i++){
    			var model=modelList[i];
					if(model.labelName==null||model.labelName==""){
						alert("[标签名称]不能为空");
						$("#labelName_"+model.labelModelId).focus();
						return false;
					}
					if(model.itemType=="1"){
						if(model.selectList==null||model.selectList==""){
							alert("[下拉选择框]不能为空");
							$("#selectListText_"+model.labelModelId).focus();
							return false;
						}
					}
    		}
    	}
    	return true;
	}
	function save(){
		//数据检验
		if(checkData()==false){
			return;
		}
		var modelListStr=JSON.stringify(modelList);
        $.ajax({
            url:'${baseURL}/basis/label/labelAction!saveModel.action',
            type:'post',
            data:{
                'modelListStr':modelListStr
            },
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                	alert("保存成功");
                }else{
                	alert("保存失败,错误提示:"+result.desc);
                }
            }
        });
	}

    function flashIframe_GDZC(){
    	var i=parent.document.getElementById("ifm");
    	if(i!=null)
    	{	
    		if(document.body.scrollHeight<700)
    		{
    			i.style.height ="730px";
    		}else
    		{
    			i.style.height = (document.body.scrollHeight+30)+"px";
    		}
    	}
    }
</script>
</body>

</html>
