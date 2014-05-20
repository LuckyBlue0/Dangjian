
var startTime;
$(document).ready(function () {
	$("#subButton").click(function () {
		var myDate = new Date();
		startTime = myDate.getTime();
		$(this).attr("disabled", true);
		$("#uploadForm").submit();
		$("#progress").show();
		window.setTimeout("getProgressBar()", 1000);
	});
	$("#close").click(function(){$("#progress").hide();});
	$("#closeBt").click(function(){$("#progress").hide();});
});
function getProgressBar() {
	var timestamp = (new Date()).valueOf();
	var bytesReadToShow = 0;
	var contentLengthToShow = 0;
	var bytesReadGtMB = 0;
	var contentLengthGtMB = 0;
	$.getJSON("/zhdj/getBar", {"t":timestamp}, function (json) {
		if(""==json.pContentLength||0==json.pContentLength){
			contentLengthGtMB=2;
		}
		var bytesRead = (json.pBytesRead / 1024).toString();
		if (bytesRead > 1024) {
			bytesReadToShow = (bytesRead / 1024).toString();
			bytesReadGtMB = 1;
		}else{
			bytesReadToShow = (bytesRead / 1024).toString();
		}
		var contentLength = (json.pContentLength / 1024).toString();
		if (contentLength > 1024) {
			contentLengthToShow = (contentLength / 1024).toString();
			contentLengthGtMB = 1;
		}else{

			contentLengthToShow= (contentLength / 1024).toString();
			
		}
		bytesReadToShow = bytesReadToShow.substring(0, bytesReadToShow.lastIndexOf(".") + 4);
		contentLengthToShow = contentLengthToShow.substring(0, contentLengthToShow.lastIndexOf(".") + 4);
		if (bytesRead == contentLength) {
			$("#close").show();
			$("#uploaded").css("width", "300px");
			if (contentLengthGtMB == 0) {
				$("div#info").html("\u4e0a\u4f20\u5b8c\u6210\uff01\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "MB.\u5b8c\u6210100%");
				$("#fileSize").val(contentLengthToShow);
			} else if(contentLengthGtMB == 1){
				$("div#info").html("\u4e0a\u4f20\u5b8c\u6210\uff01\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "MB.\u5b8c\u6210100%");
				$("#fileSize").val(contentLengthToShow);
			}else{
				$("div#info").html("\u4e0a\u4f20\u5931\u8d25\uff01\u4e0a\u4f20\u6587\u4ef6\u4e3a\u7a7a\uff01");
			}
			window.clearTimeout(interval);
			$("#subButton").attr("disabled", false);
		} else {
			var pastTimeBySec = (new Date().getTime() - startTime) / 1000;
			var sp = (bytesRead / pastTimeBySec).toString();
			var speed = sp.substring(0, sp.lastIndexOf(".") + 3);
			var percent = Math.floor((bytesRead / contentLength) * 100) + "%";
			$("#uploaded").css("width", percent);
			if (bytesReadGtMB == 0 && contentLengthGtMB == 0) {
				$("div#info").html("\u4e0a\u4f20\u901f\u5ea6:" + speed + "KB/Sec,\u5df2\u7ecf\u8bfb\u53d6" + bytesReadToShow + "KB,\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "KB.\u5b8c\u6210" + percent);
			} else {
				if (bytesReadGtMB == 0 && contentLengthGtMB == 1) {
					$("div#info").html("\u4e0a\u4f20\u901f\u5ea6:" + speed + "KB/Sec,\u5df2\u7ecf\u8bfb\u53d6" + bytesReadToShow + "KB,\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "MB.\u5b8c\u6210" + percent);
				} else {
					if (bytesReadGtMB == 1 && contentLengthGtMB == 1) {
						$("div#info").html("\u4e0a\u4f20\u901f\u5ea6:" + speed + "KB/Sec,\u5df2\u7ecf\u8bfb\u53d6" + bytesReadToShow + "MB,\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "MB.\u5b8c\u6210" + percent);
					}
				}
			}
			if("100%"==percent){
				$("#fileSize").val(contentLengthToShow);
			}
		}
	});
	var interval = window.setTimeout("getProgressBar()", 500);
}

