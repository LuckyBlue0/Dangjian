
$(function()
{
//    左侧菜单

    $('.menu2>ul li div').click(function()
    {
       $(this).parent('li').toggleClass('on');
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
})


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
