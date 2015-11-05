/* 智能机浏览器版本信息:*/
  var browser={
    versions:function(){ 
           var u = navigator.userAgent, app = navigator.appVersion; 
           return {//移动终端浏览器版本信息 
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !u.match(/AppleWebKit.*Mobile.*/)||!u.match(/AppleWebKit/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
         }(),
         language:(navigator.browserLanguage || navigator.language).toLowerCase()
} 


$(window).bind('beforeunload',function (){
	var now_time =  parseInt(record());
	var all_time = parseInt(getAllTime());
	if(isNaN(all_time)==true){
		all_time = 0;
	}
	$.ajax({
		url : ctx + "/play/userCoursewarePlayLog",
	type : "post",
	data : {coursewareId:$("#coursewareId").val(), videoTime:all_time, time:now_time}, 
	dataType : 'json',
  		success : function(data) {}
  	});
  });
  
//加载手机端播放器功能
function LoadMobilePlay()
{
	
	video_url = video_url.replace('_player.html','.mp4').replace('.flv','.mp4');
	var strww ="<video id='example_video_1'  controls oncontextmenu='return false;' class='video-js vjs-default-skin' controls preload='none' width='660px' height='485px'";
	strww+="autoplay='autoplay' poster='' data-setup='{\"example_option\":true}'>";
	strww+="<source src='"+video_url+"' type='video/mp4; codecs=\"avc1.42E01E, mp4a.40.2\"'/></video>";
	window.onload = function() {
	var testdiv = document.getElementById("container");
	testdiv.innerHTML=strww;
	}
}

///加载PC端手机播放器功能
function LoadPCPlay()
{
	video_url = video_url.replace('_player.html','.flv').replace('.flv','.mp4');
	$(document).ready(function() {
		getFilesPage();
		thePlayer = jwplayer('container').setup({
	    flashplayer: ctx+'/web/js/jwplayer/player.swf',
	    playlist: [
			{file: $.trim(video_url), title:'' }
	  ],
	    width: 958,  
	    height: 540,  
	    dock: false,
		modes: [  
		{ type: "html5" },  
		{ type: "flash",src: ctx+'/web/js/jwplayer/player.swf'},  
		{ type: "download"}  
		],
	    autostart:true
		});  
	}); 
}

//获取当前播放时长
function record(){
	var beginTime;
	if(GetIEVersion()){
		var Media;
		if(playType == "mobile"){
			//获取手机端播放器对象
			Media  =document.getElementById("example_video_1");
		}else if(playType == "pc"){
			//获取pc播放器对象
			Media  =document.getElementById("container_video");
	
		}
	    if(Media != "Null")
	    {
	      beginTime = Media.currentTime;
		}
	}else{
		beginTime = thePlayer.getPosition();
	}
	return beginTime;
}
//获取视频总时长
function getAllTime(){
	var allTime;
	if(GetIEVersion()){
		var Media;
		if(playType == "mobile"){
			//获取手机端播放器对象
			Media  =document.getElementById("example_video_1");
		}else if(playType == "pc"){
			//获取pc播放器对象
			Media  =document.getElementById("container_video");
	
		}
	    if(Media != "Null"){
	    	allTime = Media.duration;
		}
	}else{
		allTime = thePlayer.getDuration();
	}
	return allTime;
}

//设置当前播放时长HTML5,参数单位为秒
function  setrecord(voidtime){
	var Media;
	if(GetIEVersion()){
		if(playType == "mobile"){
			//获取手机端播放器对象
			Media  =document.getElementById("example_video_1");
		}else if(playType == "pc"){
			//获取pc播放器对象
			Media  =document.getElementById("container_video");
	
		}
	    if(Media != "Null"){
	    	Media.currentTime = voidtime;
	    }  
	}
}
//控制视频播放/暂停
function playPause()
{
	var Media;
	if(GetIEVersion()){
		if(playType == "mobile"){
			//获取手机端播放器对象
			Media  =document.getElementById("example_video_1");
		}else if(playType == "pc"){
			//获取pc播放器对象
			Media  =document.getElementById("container_video");
	
		}
	    if (Media.paused)
	    	Media.play();
	    else
	    	Media.pause();
	}
}
///获取浏览器是否支持HTML5
function  GetIEVersion()
{
	var getIEVersionTrue=false;
    if (window.applicationCache) {
        //浏览器支持HTML5
    	getIEVersionTrue = true; 
    } else {
        //浏览器不支持HTML5
    	getIEVersionTrue=false;
    }
	return  getIEVersionTrue;
}