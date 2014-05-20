(function ($) {
    $.fn.tree = function (options) {
        var defaults = {
            nodes:"",
            showall:true
        };
        var options = $.extend(defaults, options);
        this.each(function () {
            var nodes = options.nodes ? options.nodes : "",
                    tree_node = "",
                    detail = "",
                    tree = "";
            obj = $(this);

            for (var list in nodes) {
                detail = nodeList(nodes[list], list, options.showall);
                tree_node += "<li>" + detail + "</li>";
            }
            tree = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td valign=\" top \" bgcolor=\"#fffefb\" class=\"left \" id=\"left \"><div  class='menu2'><ul >" + tree_node + "</ul></div></td></tr></table>";
            $(this).html(tree);
        });

        function nodeList(node, list, showall) {
            var nodes = node,
                    tree_detail = "",
                    tree_common = "",
                    tree_node = "",
                    icon = nodes.icon ? ("<div><img src=\"" + baseURL + nodes.icon + "\"></img>" ): "",
                    title = nodes.title ? nodes.title : "",
                    titleurl = nodes.titleurl ? baseURL + nodes.titleurl : "javascript:void(0)",
                    nodelist = nodes.nodes ? nodes.nodes : "";

            var num = list;
            if(titleurl.indexOf("?")>0)titleurl+="&dqdp_csrf_token="+dqdp_csrf_token;
            else if( titleurl!="javascript:void(0)"&&titleurl!="javascript:"&&titleurl!="") titleurl+="?dqdp_csrf_token="+dqdp_csrf_token;
            var classnode = "node_" + num;
            if(nodes.icon!=""){
            var nodehref = "<a href='" + titleurl +(titleurl=="#"?"'":"' target='mainFrame' ")+">" + title + "</a><span class=\"arr2\"></span></div>";
            }else{
            	var nodehref = "<a href='" + titleurl +(titleurl=="#"?"'":"' target='mainFrame' ")+">" + title + "</a>";
            }
            if (nodelist) {
                for (var list in nodelist) {
                    var nums = num + "_" + list,
                            nodes = nodelist[list].node ? nodelist[list].node : "";
                    tree_common += nodeList(nodelist[list], nums, showall);
                    classnode = "node_" + nums;
                    if(classnode.split("_").length==4){
                    	 tree_common = showall ? "<ul class=" + classnode + "><li>" + tree_common + "</li></ul>" : "<ul class='hidden " + classnode + "'><li>" + tree_common + "</li></ul>";
                    }else{
                    tree_common = showall ? "<ul class=" + classnode + "><li>" + tree_common + "</li></ul>" : "<ul class=' " + classnode + "'><li>" + tree_common + "</li></ul>";
                    
                    }
                    tree_node += tree_common;
                    if (nodes) {
                        return nodeList(nodes, nums, showall);
                    }
                    tree_common = "";
                }
                tree_detail = icon ? icon + nodehref : nodehref;
            } else {
                tree_detail = icon ? icon + nodehref : nodehref;
            }
            tree_detail += tree_node;
            return tree_detail;
        }

        var lastClickedObj = null;
        var isInChild = false;
        $.each($("a", obj), function () {
            var cssObj = $(this).parent();
            cssObj.bind("mouseover", function () {
                cssObj.addClass("on");
            });
            cssObj.bind("mouseout", function () {
                if(!lastClickedObj||cssObj.html()!=lastClickedObj.parent().html())
                cssObj.removeClass("on");
            });
        });
       
        $("a", obj).click(function () {
                 
                if ($(this).parent().parent().parent().parent().parent().attr("class") == "menu2") {
                   
                if($(this).next().next().css("display")=="block"){
                	 $(this).nextAll('ul').addClass("hidden");
                }else{
                $(this).nextAll('ul').removeClass("hidden");
                }
                } 
               
                
              
        });
        
        $('.menu2>ul li div').click(function()
    {           
                if ($(this).parent().parent().parent().attr("class") == "menu2") {
                	
                    $(this).parent('li').toggleClass('on');
                } 

                
       
    });
    (function()
    {
		var isIE=!!window.ActiveXObject;
		var isIE6=isIE&&!window.XMLHttpRequest;	
		if(isIE6){
			
		   $(window).on('resize',function()
			{
				
				var w = $(window).height();				
				$('#footer').css({'position':'absolute','top':w-30+'px'});
			}).trigger('resize');
			
			$(window).on('scroll',function()
			{
				
				var w = $(window).height();				
				//console.log($(document).scrollTop());
				$('#footer').css({'position':'absolute','top':w-30+$(document).scrollTop()});
			});
			
		}
        
    })(); 
   
	$(function()
	{
		var screenH = $(window).height();
		$('.menu2').css({'height':screenH-100+'px','overflow':'auto'});
	});


    function showSubMenu(x){
	for(var i=0;i<3;i++){
		document.getElementById("subMenu"+i).style.display='none';
	}
		document.getElementById("subMenu"+x).style.display='block';
}
function showThirdMenu(y){
	for(var j=0;j<0;j++){
		document.getElementById("thirdMenu"+j).style.display='none';
	}
		document.getElementById("thirdMenu"+y).style.display='block';
}
    }
})(jQuery); 