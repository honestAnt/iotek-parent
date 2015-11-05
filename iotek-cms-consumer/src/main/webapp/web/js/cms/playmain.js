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



//点击需求反馈
function psninfor(){
document.getElementById('psinforbg').style.display='block';
document.getElementById('psninforbox').style.display='block';
}
function payforbox(){
document.getElementById('payforpbbg').style.display='block';
document.getElementById('payforpbbox').style.display='block';
}
function statebox(){
document.getElementById('statebg').style.display='block';
document.getElementById('statemain').style.display='block';
}
function statebox(){
document.getElementById('statebg').style.display='block';
document.getElementById('statemain').style.display='block';
}
function inforclose(){
document.getElementById('psinforbg').style.display='none';
document.getElementById('psninforbox').style.display='none';
}
function closebg(){
document.getElementById('statebg').style.display='none';
document.getElementById('statemain').style.display='none';
}
function payfordata(){
document.getElementById('payforpbbg1').style.display='block';
document.getElementById('payforpbbox1').style.display='block';
}
function payforclose(){
document.getElementById('payforpbbg').style.display='none';
document.getElementById('payforpbbox').style.display='none';
}
function payforclose1(){
document.getElementById('payforpbbg1').style.display='none';
document.getElementById('payforpbbox1').style.display='none';
}
function publicbox(){
document.getElementById('public_bg').style.display='block';
document.getElementById('public_pmp').style.display='block';
}
function publicbox_close(){
document.getElementById('public_bg').style.display='none';
document.getElementById('public_pmp').style.display='none';
}

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
for (var i = 1; i <= 3; i++) { g('all_letter_' + i).className = 'nor_mywork'; g('all_letter_0' + i).className = 'unall_letter_net'; } g('all_letter_0' + n).className = 'all_letter_con'; g('all_letter_' + n).className = 'hovertab_mywork';}
//如果要做成点击后再转到请将<li>中的onmouseover 改成 onclick;
//]]>

$(function (){ 
		$("#topic_type1").click(function(){ 
		$("#pubfoe1").show();
		$("#pubfoe2").hide();
		$("#pubfoe3").hide();
	});
		$("#topic_type2").click(function(){ 
		$("#pubfoe2").show();
		$("#pubfoe1").hide();
		$("#pubfoe3").hide();
	});
		$("#topic_type3").click(function(){
		$("#pubfoe3").show();
		$("#pubfoe2").hide();
		$("#pubfoe1").hide();
	});
			
	
})




