$(document).ready(function(){
	if($("#coursewareId").val() != null && $("#coursewareId").val() != ""){
		getData($("#syllabusId").val(), $("#index").val(), $("#itemindex").val());
	}
	$("#online_first"+$("#index").val()).addClass("current_play");
	$("#online_second"+$("#index").val()).addClass("A_current");
	$("#online_sfirst"+$("#index").val()+$("#itemindex").val()).addClass("current_play");
	$("#online_ssecond"+$("#index").val()+$("#itemindex").val()).addClass("A_current");	
});

function getData(syllabusId, index, itemindex){
	$.ajax({
		url : ctx + "/play/coursewareData",
		type : "post",
		data : {syllabusId:syllabusId, courseId:$("#courseId").val(), goodsId:$("#goodsId").val()}, 
		dataType : 'json',
		success : function(data) {
			if(data.coursewareList.length>0){
				resolveCourseData(data.goodsId, data.courseId, data.syllabusId, data.coursewareList, index, itemindex, data.ifAuth, data.syllabus);
			}
		}
	});
}

function resolveCourseData(goodsId, courseId, syllabusId, list, index, itemindex, ifAuth, syllabus){
	var content = "";	
	if(ctx_user_id != null){
		var para = "authInfo";
	}else{
		var para = "info";
	}
	var free = $("#free").val();
	$.each(list, function(i, object){
		if(free == 1){
			if(syllabus.ifFree == 0){
				var detailUrl = "javascript:info()";
			}else{
				var detailUrl = ctx + "/play/"+para+"?goodsId=" + goodsId + "&courseId=" + courseId + "&syllabusId=" + syllabusId + "&coursewareId=" + object.id + "&index=" + index + "&itemindex=" + itemindex + "&free=" + free;
			}
		}else{
			if(!ifAuth){
				var detailUrl = "javascript:info()";
			}else{
				var detailUrl = ctx + "/play/"+para+"?goodsId=" + goodsId + "&courseId=" + courseId + "&syllabusId=" + syllabusId + "&coursewareId=" + object.id + "&index=" + index + "&itemindex=" + itemindex;
			}
		}			
		if($("#coursewareId").val() == object.id){
			content += "<li class='current'><i class='icon_li vd_icon'></i>";
		}else{
			content += "<li>";
		}
		content += "<a href='";
		content += detailUrl;
		content += "'>";
		content += object.coursewareName;
		content += "</a></li>";
	});
	if(itemindex == -1){
		if($("#online_sl"+index).html() == null || $("#online_sl"+index).html() == ""){		
			$("#online_sl"+index).html(content);
			$("#online_sl"+index).show();
			$("#online_sn"+index).removeClass("py_section");
		}else{		
			$("#online_sl"+index+" li").remove();
			$("#online_sl"+index).hide();
			$("#online_sn"+index).addClass("py_section");
		}
	}else{
		if($("#online_sl"+index+itemindex).html() == null || $("#online_sl"+index+itemindex).html() == ""){		
			$("#online_sl"+index+itemindex).html(content);
			$("#online_sl"+index+itemindex).show();
			$("#online_sn"+index+itemindex).removeClass("py_section");
		}else{		
			$("#online_sl"+index+itemindex+" li").remove();
			$("#online_sl"+index+itemindex).hide();
			$("#online_sn"+index+itemindex).addClass("py_section");
		}
	}
	
}


function changeCourseware(flag){
	if(ctx_user_id != null){
		var para = "authInfo";
	}else{
		var para = "info";
	}
	
	$("form").attr("action",ctx + "/play/"+para+"?goodsId=" +
			$("#goodsId").val() +
			"&courseId=" +
			$("#courseId").val() +
			"&syllabusId=" +
			$("#syllabusId").val() +
			"&coursewareId=" +
			$("#coursewareId").val() +
			"&index=" +
			$("#index").val() +
			"&itemindex=" +
			$("#itemindex").val() +
			"&flag=" +
			flag);
	$("form").submit();
}

function info(){
	showMessage(publicbox_close,null,null,"抱歉,您还未购买此课程!</br>现在就去购买！<a href='"+ctx+"/goods/index'><font color='#0000FF'>课程中心</font></a>")
}