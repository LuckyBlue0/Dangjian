<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<jsp:include page="/jsp/web/common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script type="text/javascript" src="js/plug.js"></script>
<script type="text/javascript" src="js/fun.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>

<title>智慧党建平台</title>
</head>

<body>
<!--头部-->
	<s:include value="/jsp/web/common/head.jsp"></s:include>
<!--头部-->
<!--正文-->
<div id="main">
    <div class="loginWrap">
        <div class="wrapper">
            <div class="loginBox clearfix">
                <div class="fl" id="div1" style="display: none">
                <form action="${baseURL}/index/indexAction!login.action" id="formId">
                <input type="hidden" name="type" id="type" value="3"/>
                    <span>
                        <label for="">用户名：</label>
                        <input type="text" class="inputstyle w120" id="username" name="uservo.username"/><span id="tip" style="color:red"></span>
                    </span>

                    <span>
                        <label for="">密码：</label>
                        <input type="password" class="inputstyle w120" id="pwd" name="uservo.userPwd"/><span id="tip1" style="color:red"></span>
                    </span>

                    <input type="button" class="btnLogin" onclick="login();" value="登 录"/>
                    </form>
                </div>

                <div class="fr">
                    <div class="selectBox">
                        <div class="selectValue">
                            <label for="">组织导航</label>
                            <i></i>
                        </div>
                        <div class="list">
                            <ul>
                                <li><a href="${baseURL}/jsp/web/tip.jsp?type=6">政党组织</a></li>
                                <li><a href="${baseURL}/jsp/web/tip.jsp?type=6">区党委会</a></li>
                                <li><a href="${baseURL}/jsp/web/tip.jsp?type=6">政党组织</a></li>
                                <li><a href="${baseURL}/jsp/web/tip.jsp?type=6">区党委会</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
             </div>
        </div>
    </div>
    <div class="wrapper">
    <div class="col1 clearfix mt20">
        <div class="slide fl" datasrc="${baseURL}/index/indexAction!ajaxSearchTopPic.action">
            <div  class="slideDiv" style="width: 380px;height: 300px">
                <li name="listPic">
                <img src="${baseURL}/@{imgPath}" alt="" text="@{title}" width="380px" onclick="window.location.href='${baseURL}/jsp/web/hotNewsDetail.jsp?id=@{newsInfoId}'" height="300px" style="float: left;cursor: pointer;"/>
                </li>
            </div>
            <div class="picText">
                <p class="ml10"></p>
            </div>
        </div>

        <div class="gzzt box fl ml15" datasrc="${baseURL}/index/indexAction!ajaxSearchNews.action?newInfoType=1">
            <h3 class="ml10"><span name="newsType"></span></h3>
            <div style="height: 235px">
            <ul class="p20" style="padding-bottom: 10px;height: 10px" name="listNews">
                <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>
            </ul>
            </div>
            <p class="tr mr20">
                <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more">更多>></a>
            </p>
        </div>

        <div class="annou box fl ml15" datasrc="${baseURL}/index/indexAction!ajaxSearchNews.action?newInfoType=2">
            <h3 class="ml10"><span name="newsType"></span></h3>
            <div style="height: 235px">
            <ul class="p20" style="padding-bottom: 10px;height: 10px" name="listNews">
               <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>
            </ul>
            </div>
            <p class="tr mr20">
                <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more">更多>></a>
            </p>
        </div>

    </div>
    <div class="col2 clearfix mt10"  >
        <div class="col2_box fl"  id="url1" datasrc="">
            <h3 id="name1">两新党建</h3>
            <div class="colText" style="height: 175px">
                <div style="height: 150px">
                <ul class="p10" name="listNews" style="height: 10px">
               <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>
                </ul>
                </div>
                <p class="tr mr20">
                    <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                </p>
            </div>
        </div>

        <div class="col2_box fl ml10" id="url2" datasrc="">
            <h3 id="name2">社区党建</h3>
            <div class="colText" style="height: 175px">
                <div style="height: 150px">
                <ul class="p10" name="listNews" style="height: 10px">
                     <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>

                </ul>
                </div>
                <p class="tr mr20">
                    <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                </p>
            </div>
        </div>


        <div class="col3_box fl ml10" id="url3" datasrc="">
            <h3 id="name3">农村党建</h3>
            <div class="colText" style="height: 175px">
                <div style="height: 150px">
                <ul class="p10" name="listNews" style="height: 10px">
                      <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>

                </ul>
                </div>
                <p class="tr mr20">
                    <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                </p>
            </div>
        </div>

    </div>


    <div class="col3 clearfix mt10">
        <div class="col3_left fl">
            <div class="clearfix">
                <div class="col2_box fl" id="url4"  datasrc="">
                    <h3 id="name4">机关党建</h3>
                    <div class="colText" style="height: 175px">
                      <div style="height: 150px">
                        <ul class="p10" name="listNews" style="height: 10px">
                           <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>
                        </ul>
                        </div>
                        <p class="tr mr20">
                            <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                        </p>
                    </div>
                </div>

                <div class="col2_box fl ml10" id="url5" datasrc="">
                    <h3 id="name5">国企党建</h3>
                    <div class="colText" style="height: 175px">
                        <div style="height: 150px">
                        <ul class="p10" name="listNews" style="height: 10px">
                          <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>
                        </ul>
                        </div>
                        <p class="tr mr20">
                            <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="adBox mt10">
                <a href="#"><img src="images/ad33.png" alt=""/></a>
            </div>
        </div>

        <div class="col3_right fr">
            <div class="services ">
                <h3>服务指引</h3>
                <div class="colIcon">
                    <ul class="pt10 clearfix">
                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon1.png" alt=""/>
                                <p>我要入党</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon2.png" alt=""/>
                                <p>找组织</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon3.png" alt=""/>
                                <p>找党代表</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon4.png" alt=""/>
                                <p>组织关系转移</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon5.png" alt=""/>
                                <p>政策文件</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon6.png" alt=""/>
                                <p>党史之窗</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon7.png" alt=""/>
                                <p>党务公文模板</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon8.png" alt=""/>
                                <p>经典好歌</p>
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}/jsp/web/tip.jsp?type=5">
                                <img src="images/icon9.png" alt=""/>
                                <p>办事指南</p>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>



    <div class="col2 clearfix mt10">
        <div class="col2_box fl" id="url6" datasrc="">
            <h3 id="name6">干部工作</h3>
            <div class="colText" style="height: 175px">
                <div style="height: 150px">
                <ul class="p10" name="listNews" style="height: 10px">
                      <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>

                </ul>
                </div>
                <p class="tr mr20">
                    <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                </p>
            </div>
        </div>

        <div class="col2_box fl ml10" id="url7" datasrc="">
            <h3 id="name7">人才工作</h3>
            <div class="colText" style="height: 175px">
                <div style="height: 150px">
                <ul class="p10" name="listNews" style="height: 10px">
                      <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>

                </ul>
                </div>
                <p class="tr mr20">
                    <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                </p>
            </div>
        </div>


        <div class="col3_box fl ml10" id="url8" datasrc="">
            <h3 id="name8">老干部工作</h3>
            <div class="colText" style="height: 175px">
                <div style="height: 150px">
                <ul class="p10" name="listNews" style="height: 10px">
                      <li><a href="${baseURL}/jsp/web/newsDetail.jsp?id=@{newsInfoId}&newsTypeId=@{typeId}"><span name="title"></span></a></li>

                </ul>
                </div>
                <p class="tr mr20">
                    <a href="${baseURL}/jsp/web/newsList.jsp?id=@{newsTypeId}" class="more  ">更多>></a>
                </p>
            </div>
        </div>

    </div>


    <div class="picScroll p10 mt10">
        <h3>创先争优</h3>
        <div class="scrolDiv mt10">
            <span class="prev"></span>
            <span class="next"></span>
            <div class="scrollBox">
                <ul class="clearfix">
                    <li>
                        <a href="#">
                            <img src="images/pic1.png" alt=""/>
                            <p>成都义工酷暑中走上街头</p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic2.png" alt=""/>
                            <p>《党建》杂志送文化进老区 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic3.png" alt=""/>
                            <p>“神枪手”这样练就 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic4.png" alt=""/>
                            <p>梦在蓝天——空军英雄 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic5.png" alt=""/>
                            <p>扬州社区志愿者超12万 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic1.png" alt=""/>
                            <p>成都义工酷暑中走上街头 </p>
                        </a>
                    </li>


                    <li>
                        <a href="#">
                            <img src="images/pic3.png" alt=""/>
                            <p>“神枪手”这样练就 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic4.png" alt=""/>
                            <p>梦在蓝天——空军英雄 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic5.png" alt=""/>
                            <p>扬州社区志愿者超12万 </p>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <img src="images/pic1.png" alt=""/>
                            <p>成都义工酷暑中走上街头 </p>
                        </a>
                    </li>


                </ul>
            </div>
        </div>
    </div>

    </div>
</div>
<!--正文 end-->

<!--页脚-->
<s:include value="/jsp/web/common/foot.jsp"></s:include>
<!--页脚 end-->
<script type="text/javascript">
  $(document).ready(function(){
    	var show="${sessionScope.isMemberLogin}";
    	if(show=="true"){
    		$("#div1").css("display","none");
    	}else{
    		$("#div1").css("display","");
    	}
    	for(var i=1;i<=8;i++){
    	var name=$("#name"+i).text();
    	$("#url"+i).attr("datasrc","${baseURL}/index/indexAction!ajaxSearchNewsBySort.action?typeName="+encodeURI(encodeURI(name)));
    	_redraw("url"+i);
    	}
    	
    });
 function login(){
	    if($("#username").val()==''){
	    	$("#tip").text("用户帐号不能为空！");
	    	return;
	    }else{
	    	$("#tip").text("");
	    }
	    if($("#pwd").val()==''){
	    	$("#tip1").text("密码不能为空！"); 
	    	return;
	    }else{
	    	$("#tip1").text("");
	    }
	    $('#formId').ajaxSubmit({
      
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    window.location.href="${baseURL}/jsp/web/index.jsp"
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("登录出错，请稍后再试!");
            }
        });
 }
</script>
</body>
</html>
