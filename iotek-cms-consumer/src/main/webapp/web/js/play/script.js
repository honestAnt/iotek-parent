


$(function() {

 $('#scrollbar2').tinyscrollbar();

 $('.prc_qid p a').click(function() {
  $(this).addClass('current').siblings().removeClass('current');
   $('.prc_main dl:eq(' + $(this).index() + ')').show().siblings().hide();
  })
 $('.prc_qid p a').click(function() {
   $('.prc_main02 dl:eq(' + $(this).index() + ')').show().siblings().hide();
  })

 // 选项卡
 $(".pl_lists ul li").click(function(){
	
   $(this).find("a").addClass("hover").parent("li").siblings("li").find("a").removeClass("hover");
   var index = $(".pl_lists ul li").index(this);
   $(".lists").eq(index).show().siblings(".lists").hide();

 });
 
 function kcHide(){   
     $("#rig_show").hide();
     $(".li").removeClass("hover");
}


$(".li").click(function(){  
	$("#one2").addClass("li");
  
	if($(this).hasClass("hover"))
	  { 
		$("#rig_show").animate({
			right:"-270px"
		});
	      $(".li").removeClass("hover");
		}else{ 
		  $(this).addClass("hover").siblings(".li").removeClass("hover");
		  $(".mulu").show().animate({
			  right:"45px"
		  },"slow");
		  var ind = $(".li").index(this);
		  $(".kc_ti").eq(ind).show().siblings(".kc_ti").hide();
		  $(".Scroller-Container").eq(ind).show().siblings(".Scroller-Container").hide();
		  $('#scrollbar1').tinyscrollbar(); //滚动栏 
		}
})

$(".shouqi").click(function(){
	$("#rig_show").animate({
		right:"-270px"
	});
    $(".li").removeClass("hover");	
})


//
$(".p_zhang").click(function(){
	$(this).jQuery.next(".p_jie").toggle(); 
})

// 
$("#one3").click(function(){
  $(".Learn_bd_box").show();
  $(".psinforbg").show(); 
})

$(".img_close").click(function(){
  $(".Learn_bd_box").hide();
  $(".psinforbg").hide()
})

$(".ques_hide").click(function(){
  $("#question").hide();
})
  

})
