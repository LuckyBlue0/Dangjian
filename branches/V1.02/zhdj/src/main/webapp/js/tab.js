;(function($){
    $.fn.extend({
        tab : function(options)
        {
            var defaults = {
              
				tab_head:'.tab_hd',
				tab_body:'.bd',
				current_css:'on'
				
            };
            var opt= $.extend(defaults,options);
            var tab_head = opt.tab_head || 0,tab_body = opt.tab_body || 0,current_css = opt.current_css || 'on';
			if(!tab_head || !tab_body)
				return ;
			var $tab_head = $(tab_head),$tab_body = $(tab_body),$this = $(this);
			if(!$tab_head.length || !$tab_body.length)
				return ;
			
			var cur = 0,$tab_head_subitem = $(tab_head,$(this)).children();
			
			$tab_head_subitem.on('click',function()
			{
				var i = $tab_head_subitem.index(this);
				$(this).addClass(current_css).siblings().removeClass(current_css);
				$($tab_body,$this).eq(i).css('display','block').siblings(tab_body).css('display','none');
			});
			
        }
    })
})(jQuery);

