    function initPageBar(){
    	 var totalRows=document.getElementById("totalRows").value;
        if(totalRows==0){
        	var phtml="<span  style=\"color:red\">您请求的内容为空！</span>";
        	$("#pagerBar").removeClass("pagewrap");
        	$("#pagerBar").css("text-align","center");
        	$("#pagerBar").html(phtml);
        }else{
	    var maxPage=document.getElementById("maxPage").value;
	    var currPage=document.getElementById("currPage").value;
		var phtml = "";
		var firstShow=1;
		var lastShow=maxPage;
		var nextPage=parseInt(currPage)+parseInt("1");
		if(currPage>6){
			firstShow = currPage-5;
		}
		if(currPage<maxPage-6){
			lastShow = firstShow+9;
		}
		if(currPage>6){
			phtml = phtml+"<span onclick=\"javascript:doSearch(1)\" class=\"num\">1 ...</span>";
		}
		for(var i=firstShow;i<=lastShow;i++){
		if(i==currPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch("+i+")\" class=\"num\">"+i+"</span>";
		}else{
			phtml = phtml+"<span onclick=\"javascript:doSearch("+i+")\" class=\"num\">"+i+"</span>";
		}
		}
		if(lastShow<maxPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch("+maxPage+")\" class=\"\">... "+maxPage+"</span>";
		}
		//other
		phtml = phtml+ "<span onclick=\"javascript:doSearch("+nextPage+")\" class=\"nextPage\"></span>"
	                +"<label >共"+maxPage+"页</label>&nbsp;"
	                +"<label >到第</label>"
	                +"<input type=\"text\" class=\"\" style=\"width\:25px\" value=\""+currPage+"\" id=\"input_page\">"
	                +"<label >页</label>"
	                +"<input type=\"button\" value=\"GO\" class=\"redBtn\"  onclick=\"_pageGo("+maxPage+",$('#input_page').val(),'doSearch')\"></input>";

		$("#pagerBar").html(phtml);
		}
		}
        function initPageBar1(){
        var totalRows=document.getElementById("totalRows").value;
        if(totalRows==0){
        	var phtml="<span  style=\"color:red\">您请求的内容为空！</span>";
        	$("#pagerBar1").removeClass("pagewrap");
        	$("#pagerBar1").css("text-align","center");
        	$("#pagerBar1").html(phtml);
        }else{
	    var maxPage=document.getElementById("maxPage").value;
	    var currPage=document.getElementById("currPage").value;
		var phtml = "";
		var firstShow=1;
		var lastShow=maxPage;
		var nextPage=parseInt(currPage)+parseInt("1");
		if(currPage>6){
			firstShow = currPage-5;
		}
		if(currPage<maxPage-6){
			lastShow = firstShow+9;
		}
		if(currPage>6){
			phtml = phtml+"<span onclick=\"javascript:doSearch1(1)\" class=\"num\">1 ...</span>";
		}
		for(var i=firstShow;i<=lastShow;i++){
		if(i==currPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch1("+i+")\" class=\"num\">"+i+"</span>";
		}else{
			phtml = phtml+"<span onclick=\"javascript:doSearch1("+i+")\" class=\"num\">"+i+"</span>";
		}
		}
		if(lastShow<maxPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch1("+maxPage+")\" class=\"\">... "+maxPage+"</span>";
		}
		//other
		phtml = phtml+ "<span onclick=\"javascript:doSearch1("+nextPage+")\" class=\"nextPage\"></span>"
	                +"<label >共"+maxPage+"页</label>&nbsp;"
	                +"<label >到第</label>"
	                +"<input type=\"text\" class=\"\" style=\"width\:25px\" value=\""+currPage+"\" id=\"input_page\">"
	                +"<label >页</label>"
	                +"<input type=\"button\" value=\"GO\" class=\"redBtn\"  onclick=\"_pageGo("+maxPage+",$('#input_page').val(),'doSearch1')\"></input>";

		$("#pagerBar1").html(phtml);
		}
		}
        function initPageBar2(){
        var totalRows=document.getElementById("totalRows").value;
        if(totalRows==0){
        	var phtml="<span  style=\"color:red\">您请求的内容为空！</span>";
        	$("#pagerBar2").removeClass("pagewrap");
        	$("#pagerBar2").css("text-align","center");
        	$("#pagerBar2").html(phtml);
        }else{
	    var maxPage=document.getElementById("maxPage").value;
	    var currPage=document.getElementById("currPage").value;
		var phtml = "";
		var firstShow=1;
		var lastShow=maxPage;
		var nextPage=parseInt(currPage)+parseInt("1");
		if(currPage>6){
			firstShow = currPage-5;
		}
		if(currPage<maxPage-6){
			lastShow = firstShow+9;
		}
		if(currPage>6){
			phtml = phtml+"<span onclick=\"javascript:doSearch2(1)\" class=\"num\">1 ...</span>";
		}
		for(var i=firstShow;i<=lastShow;i++){
		if(i==currPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch2("+i+")\" class=\"num\">"+i+"</span>";
		}else{
			phtml = phtml+"<span onclick=\"javascript:doSearch2("+i+")\" class=\"num\">"+i+"</span>";
		}
		}
		if(lastShow<maxPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch2("+maxPage+")\" class=\"\">... "+maxPage+"</span>";
		}
		//other
		phtml = phtml+ "<span onclick=\"javascript:doSearch2("+nextPage+")\" class=\"nextPage\"></span>"
	                +"<label >共"+maxPage+"页</label>&nbsp;"
	                +"<label >到第</label>"
	                +"<input type=\"text\" class=\"\" style=\"width\:25px\" value=\""+currPage+"\" id=\"input_page\">"
	                +"<label >页</label>"
	                +"<input type=\"button\" value=\"GO\" class=\"redBtn\"  onclick=\"_pageGo("+maxPage+",$('#input_page').val(),'doSearch2')\"></input>";

		$("#pagerBar2").html(phtml);
		}
		}
        function initPageBar3(){
        var totalRows=document.getElementById("totalRows").value;
        if(totalRows==0){
        	var phtml="<span  style=\"color:red\">您请求的内容为空！</span>";
        	$("#pagerBar3").removeClass("pagewrap");
        	$("#pagerBar3").css("text-align","center");
        	$("#pagerBar3").html(phtml);
        }else{
	    var maxPage=document.getElementById("maxPage").value;
	    var currPage=document.getElementById("currPage").value;
		var phtml = "";
		var firstShow=1;
		var lastShow=maxPage;
		var nextPage=parseInt(currPage)+parseInt("1");
		if(currPage>6){
			firstShow = currPage-5;
		}
		if(currPage<maxPage-6){
			lastShow = firstShow+9;
		}
		if(currPage>6){
			phtml = phtml+"<span onclick=\"javascript:doSearch3(1)\" class=\"num\">1 ...</span>";
		}
		for(var i=firstShow;i<=lastShow;i++){
		if(i==currPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch3("+i+")\" class=\"num\">"+i+"</span>";
		}else{
			phtml = phtml+"<span onclick=\"javascript:doSearch3("+i+")\" class=\"num\">"+i+"</span>";
		}
		}
		if(lastShow<maxPage){
			phtml = phtml+"<span onclick=\"javascript:doSearch3("+maxPage+")\" class=\"\">... "+maxPage+"</span>";
		}
		//other
		phtml = phtml+ "<span onclick=\"javascript:doSearch3("+nextPage+")\" class=\"nextPage\"></span>"
	                +"<label >共"+maxPage+"页</label>&nbsp;"
	                +"<label >到第</label>"
	                +"<input type=\"text\" class=\"\" style=\"width\:25px\" value=\""+currPage+"\" id=\"input_page\">"
	                +"<label >页</label>"
	                +"<input type=\"button\" value=\"GO\" class=\"redBtn\"  onclick=\"_pageGo("+maxPage+",$('#input_page').val(),'doSearch3')\"></input>";

		$("#pagerBar3").html(phtml);
		}
		}