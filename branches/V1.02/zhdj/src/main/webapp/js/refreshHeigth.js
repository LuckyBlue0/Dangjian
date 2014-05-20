//调整text_bg的高度
$(function(){
		var screenH = $(window).height();
		$('.text_bg').css({'height':screenH-140+'px','overflow':'auto'});
});