(function(jQuery) {  
	jQuery.fn.textlimit=function(counter_el, thelimit, speed) {  
         var charDelSpeed = speed || 15;  
         var toggleCharDel = speed != -1;  
         var toggleTrim = true;  
         var that = this[0];  
         var isCtrl = false;   
         updateCounter();  
           
         function updateCounter(){  
             if(typeof that == "object")  
                 jQuery(counter_el).html("("+thelimit+"字数之内,还可以输入:"+(thelimit-that.value.length)+")");  
         };  
           
         this.keydown (function(e){   
             if(e.which == 17) isCtrl = true;  
             var ctrl_a = (e.which == 65 && isCtrl == true) ? true : false; // detect and allow CTRL + A selects all.  
             var ctrl_v = (e.which == 86 && isCtrl == true) ? true : false; // detect and allow CTRL + V paste.  
             // 8 is 'backspace' and 46 is 'delete'  
             if( this.value.length >= thelimit && e.which != '8' && e.which != '46' && ctrl_a == false && ctrl_v == false)  
                 e.preventDefault();  
         })  
         .keyup (function(e){  
             updateCounter();  
             if(e.which == 17)  
                 isCtrl=false;  
   
             if( this.value.length >= thelimit && toggleTrim ){  
                 if(toggleCharDel){  
                     // first, trim the text a bit so the char trimming won't take forever  
                     // Also check if there are more than 10 extra chars, then trim. just in case.  
                     if ( (this.value.length - thelimit) > 10 )  
                         that.value = that.value.substr(0,thelimit+100);  
                     var init = setInterval  
                         (   
                             function(){   
                                 if( that.value.length <= thelimit ){  
                                     init = clearInterval(init); updateCounter()   
                                 }  
                                 else{  
                                     // deleting extra chars (one by one)  
                                     that.value = that.value.substring(0,that.value.length-1); jQuery(counter_el).text('自动截取字数限制内的长度'+(thelimit - that.value.length));  
                                 }  
                             } ,charDelSpeed   
                         );  
                 }  
                 else this.value = that.value.substr(0,thelimit);  
             }  
         });  
           
     };  
 })(jQuery);