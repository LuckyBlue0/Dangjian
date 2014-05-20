<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../../common/dqdp_common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>frame</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${baseURL}/js/do1/common/showLeft.js"></script>
    <script type="text/javascript" src="${baseURL}/js/do1/common/menu.js"></script>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/frame1.0.js"></script>
    <script src="${baseURL}/js/do1/common/head1.0.js"></script>
    <script src="${baseURL}/js/do1/common/tree2.0.js"></script>
<body>
<div id="main"></div>

</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        var framehead = head("田馥甄", "http://www.baidu.com", "帮助", "http://www.baidu.com", "退出", "2012-01-17 17:34:49");
        $("#main").frame({framehead:framehead, frameleft:"<div id='id_div_left' />", frameright:"", framebottom:""});
        doMenuLoad();
    });

    function doMenuLoad() {
        $.ajax({
            type:"post",
            dataType:"json",
            url:baseURL+"/mainpage/mainpage!getMainPageInfo.action",
            beforeSend:function (XMLHttpRequest) {
                //ShowLoading();
            },
            success:function (data, textStatus) {
                if (data.code == "0")
                    $("#id_div_left").tree({
                        nodes:data.data.tree,
                        showall:false
                    });
                else
                    alert(data.code);
            },
            complete:function (XMLHttpRequest, textStatus) {
                //HideLoading();
            },
            error:function () {
                //请求出错处理
            }
        });
    }
</script>