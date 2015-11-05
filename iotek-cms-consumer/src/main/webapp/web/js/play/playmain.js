// JavaScript Document

/*右侧菜单点击搜索*/
function Hide_div() { 
		document.getElementById("frmTitle").style.display = "none";
        document.getElementById("main_right").style.marginRight = "76px";
        document.getElementById("switchPoint").style.display = "block";
    }
function Show_div() {
		document.getElementById("frmTitle").style.display = "";
        document.getElementById("main_right").style.marginRight = "";
        document.getElementById("switchPoint").style.display = "";
    }



//右边滑动门


//function setTab(name,cursel,n){
//	
//	if (cursel == 1) {
////		$("#one1").addClass("hover");
////		$("#one1").addClass("directory_box");
//		$("#con_one_1").removeAttr("style");
//		
//		//显示视频obj最近一层的div	（插件生成）
//		$("#container_wrapper").css("width","958px");
//		$("#container_wrapper").css("height","540px");
//		$("#con_one_1 div.Video_introd").show();
//	} else {
//		
////		$("#one1").removeClass("hover");
////		$("#one1").removeClass("directory_box");
//		$("#con_one_1").css("width","0");
//		$("#con_one_1").css("height","0");
//		
//		//隐藏视频obj最近一层的div	（插件生成）
//		$("#container_wrapper").css("width","0");
//		$("#container_wrapper").css("height","0");
//		$("#con_one_1 div.Video_introd").hide();
//		
//		if(playType == "mobile"){
//			$("#example_video_1").pause();
//		}else{
//			//thePlayer.stop();
//			thePlayer.play(false);
//		}
//	}
//	
//	for(var i=2;i<=n;i++){
//		var menu=document.getElementById(name+i);
//		if (menu != undefined && menu != null && menu != "") {
//			var con=document.getElementById("con_"+name+"_"+i);
//			menu.className=i==cursel?"hover":"";
//			con.style.display=i==cursel?"block":"none";
//		}
//	}
//}
//-->

//树效果
function getObject(objectId){
	if(document.getElementById && document.getElementById(objectId)){
		return document.getElementById(objectId);
	}else if(document.all && document.all(objectId)){
		return document.all(objectId);
	}else if(document.layers && document.layers[objectId]){
		return document.layers[objectId];
	}else{
		return false;
	}
}

function showHide(e,objname){
	var obj = getObject(objname);
	if(obj.style.display == "none"){
		obj.style.display = "block";
		e.className="minus";
	}else{
		obj.style.display = "none";
		e.className="plus";
	}
}

//编程题Tab切换

//<!CDATA[
function g(o) { return document.getElementById(o); }
function hover_zzjs_net(n) {
//如果有N个标签,就将i<=N;
for (var i = 1; i <= 4; i++) { g('all_letter_' + i).className = 'nor_mywork'; g('all_letter_0' + i).className = 'unall_letter_net'; } g('all_letter_0' + n).className = 'all_letter_con'; g('all_letter_' + n).className = 'hovertab_mywork';}
//如果要做成点击后再转到请将<li>中的onmouseover 改成 onclick;
//]]>