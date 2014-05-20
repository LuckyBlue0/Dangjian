(function($)
{
    $.fn.extend({
//        不可点击的图片滚动
        slide_1 : function(options)
        {
            var defaults ={
                textDiv:'',
                slideDiv:''
            }

            var opt = $.extend(defaults,options),
                textDiv = opt.textDiv,
                slideDiv = opt.slideDiv,
                $t = $(textDiv),
                $s = $(slideDiv),
                $this = $(this);
                if(!textDiv || !slideDiv || !$t || !$s)
                    return;
			
           var childnodes = $s.children('li'),childNum = childnodes.length,frag=$('<div class="points"></div>'),cur=0;
		   console.log(childNum);
            (function()//初始化界面
            {
                var tmp='',
                    picText=childnodes.eq(0).attr('text');
                for(var i=0;i<childNum;i++)
                {
                    if(!i)
                        tmp+='<i class="on"></i>';
                   else
                       tmp+='<i></i>';
                }
                frag.append(tmp);
                $this.append(frag);

                $t.html(picText);


            })();



            var scroll = function()
            {
               cur = cur+1 <= childNum-1? cur+1:0;
                childnodes.eq(cur).css('display','block').siblings('li').css('display','none');
                $('.points').children().eq(cur).addClass('on').siblings().removeClass('on');
                $t.html(childnodes.eq(cur).find('img').attr('text'));
            }

            $(this).bind('finished',function()
            {
                var interval = setInterval(function(){scroll();},3000);
            }).trigger('finished');
        },


//        有左右箭头的图片滚动
       slide_2:function(options)
       {
           var defaults ={
               leftbtn:'',
               rightbtn:'',
               scrollDiv:'',
//               imgWrap:'',
               showNum:''
           }

           var opt = $.extend(defaults,options),
               lbtn = opt.leftbtn,
               rbtn = opt.rightbtn,
//               wrap = opt.imgWrap,
               s = opt.scrollDiv,
               $lbtn = $(lbtn),
               $rbtn = $(rbtn),
//               $w = $(wrap),
               showNum = opt.showNum || 4,
               $s = $(s);
           if(!lbtn || !$rbtn || !$s )
                return;
           var total = $s.find('img').length,$this=$(this);
           var group = Math.ceil(total/showNum),curIndex= 0,i;
           (function()
           {
                $s.css('width',group*100+'%');

               $lbtn.on('click',function() //点击左按钮
               {
                   clearInterval(i);
                   curIndex = curIndex-1>0?curIndex-1:0;
                    scroll(curIndex);
                   i = setInterval(function()
                   {
                       curIndex = curIndex+1<group?curIndex+1:0;
                      scroll(curIndex);
                   },3000)
               });


               $rbtn.on('click',function() //点击吉按钮
               {
                   clearInterval(i);
                   curIndex = curIndex+1<group?curIndex+1:0;
                   scroll(curIndex);
                   i = setInterval(function()
                   {
                       curIndex = curIndex+1<group?curIndex+1:0;
                       scroll(curIndex);
                   },3000)
               });

              $this.on('loaded',function()
              {
                  i = setInterval(function()
                  {
                      curIndex = curIndex+1 < group?curIndex+1:0;
                     scroll(curIndex);
                  },3000)
              }).trigger('loaded');
           })();

           var scroll = function(index)
           {
              $s.animate({'marginLeft':-index*100+'%'},300);
           }


       },

//        tab切换

        tab_1:function(options)
        {
           var defaults = {
               hd:'',
               bd:'',
               cl:'',
               childNode:'li'
           }

            var opt = $.extend(defaults,options),
                hd=opt.hd,
                bd=opt.bd,
                $hd=$(hd),
                $bd=$(bd),
                cl = opt.cl,
                child = opt.childNode;
            if(!$hd || !$bd)
                return;

            $this = $(this);
            $this.each(function(index)
            {
                var ch = $hd.find(child);
               ch.click(function()
               {
                   var self = $(this);
                   var cur = self.index();
                   ch.eq(cur).addClass(cl).siblings().removeClass(cl);
                   $bd.eq(cur).show().siblings(bd).hide();
               })
            });

        }

    });
})(jQuery);
