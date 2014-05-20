$(function()
{
//    首页图片滚动

    $('.slide').slide_1({textDiv:'.picText',slideDiv:'.slideDiv'});

//   首页带左右箭头的图片滚动

    $('.scrolDiv').slide_2({leftbtn:'.prev',rightbtn:'.next',scrollDiv:'.scrollBox ul',showNum:6});
//    下拉列表
    var list = (function(){
        $('.selectValue').on('click',function()
        {
          $('.list').toggle().find('li').on('click',function()
          {
             $('.selectValue label').html($(this).text());
              $('.list').hide();
          });

        });
    })();

//tab切换

    $('.login_tab').tab_1({hd:'.tab_hd',bd:'.tab_bd',cl:'on'});


//    点击菜单，弹出二级菜单

    $('#innovate').on('click',function()
    {
      $(this).addClass('on').siblings('li').removeClass('on');
      $(this).find('.sub').toggle();
    });
})