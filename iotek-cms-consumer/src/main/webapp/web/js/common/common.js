/**
 * 通用js 
 */

/**
 * 根据id加载验证码 
 */
function reloadImage(imgId) {
	$('#'+imgId).attr("src", ctx+'/captcha/generateCaptcha?ts= ' + new Date().getTime().toString());
}

/**
 * 显示加载效果图片
 * @param div
 */
function showLoading(div) {
	var loading = "<img src='" + ctx
			+ "/web/images/common/loading_1.gif'></img>";
	$("#" + div).html(loading);
}
//目录选择
function checkMenu(menuId) {
	$("#" + menuId).addClass("current");
}

/**
 * 校验验证码
 * @param capCode
 */
function validCaptch(capCode) {
	
	var validCode = false;
	$.ajax({
		url : ctx + "/validCaptchaAjax/validCaptcha",
		type : "post",
		data: {
			captchaCode:capCode
		},
		async: false,
		dataType : 'json',
		success : function(data) {
			validCode = data.validCaptcha;
		}
	});
	return validCode;
}

/**
 * 计算字符的真实长度（包含中英文）
 */
var getStrRealLength = function(str) { 
	///<summary>获得字符串实际长度，中文2，英文1</summary> ///<param name="str">要获得长度的字符串</param> 
	var realLength = 0, len = str.length, charCode = -1; 
	for (var i = 0; i < len; i++) { 
		charCode = str.charCodeAt(i); 
	if (charCode >= 0 && charCode <= 128) realLength += 1; 
	else realLength += 2; } 
	return realLength;
}

/**
 * 使用百度api,获取ip所在的地址信息(ip为空时,使用当前浏览器所在的ip)
 */
function getAddressByIp(ipStr) {
	
	var ip = ipStr;
	if (ip == undefined || ip == null || ip == 'null') {
		ip = '';
	}

	$.ajax({
	    type : "get",
	    async:false,
	    url : "http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+ip+"&coor=bd09ll",
	    dataType : "jsonp",
	    success : function(json){
	    	ipAddress = json;
	    },
	    error:function(a,b,c){
	        alert('fail');
	    }
	});
}

/**
 * 显示弹出框 
 * @param confirm 确认回调 传空值代表关闭
 * @param cancel  取消回调
 * @param xclose  关闭回调
 * @param msg     对应信息
 */
function showMessage(confirm,cancel,xclose,msg){
	// 确认回调
	if(confirm == null){
		$("#p_confirm_btn").hide();
		/*$("#p_confirm_btn").click(function(){
			publicbox_close();
		});*/
	}else{
		$("#p_confirm_btn").show();
		$("#p_confirm_btn").unbind("click").click(function(){
			confirm();
		});
	}
	
	// 取消回调
	if(cancel == null){
		$("#p_cancel_btn").hide();
		/*$("#p_cancel_btn").unbind("click").click(function(){
			publicbox_close();
		});*/
	}else{
		$("#p_cancel_btn").show();
		$("#p_cancel_btn").unbind("click").click(function(){
			cancel();
		});
	}
	
	//关闭回调
	if(xclose == null){
		$("#pmp_close").hide();
		/*$("#pmp_close").click(function(){
			publicbox_close();
		});*/
	}else{
		$("#pmp_close").show();
		$("#pmp_close").click(function(){
			xclose();
		});
	}
	$("#public_bg").show();
	$("#public_pmp").show();
	$("#rel_problem_span").html(msg);
	
}

/**
 * 
 * @param email
 * @returns 验证邮箱
 */
function checkEmail(email) {
	var emailRegExp = new RegExp(
			"[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9A-Z!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		return false;
	} else {
		return true;
	}
}

/**
 * 
 * @param qq
 * @returns 验证qq
 */
function isQq(qq) {
	var reg = /^[1-9]\d{4,14}$/;
	qq_Flag = reg.test(qq);
	return qq_Flag;
}

/**
 * 
 * @param value
 * @returns 验证手机号码
 */
function checkMobile(value) {
	/*if(/^13\d{9}$/g.test(value)||(/^15[0-35-9]\d{8}$/g.test(value))||(/^147\d{8}$/g.test(value))||(/^18[0-9]\d{8}$/g.test(value))){
		return true;  
	}else{  
		return false;  
	} */
	/*按照目前最新的号段校验*/
	if(/^13\d{9}$/g.test(value)
		|| (/^14[5|7]\d{8}$/g.test(value))
		|| (/^15[0-35-9]\d{8}$/g.test(value))
		|| (/^17[6-8]\d{8}$/g.test(value))
		|| (/^170[0|5|9]\d{7}$/g.test(value))
		|| (/^18[0-9]\d{8}$/g.test(value))
		|| (/^18[6|9]0\d{7}$/g.test(value))){
		return true;
	}else{
		return false;
	}
}

/**
 * 
 * @param name
 * @returns 验证真实姓名
 */
function checkRealname(name) {
	var reg = /^([\u4E00-\u9FA5])*$/;
	return name.match(reg);
}

/**
 * 验证URL
 * @param str_url URL字符串
 * @returns {Boolean} 是否合法
 */
function isURL(str_url) {
		var strRegex =  "^((https|http|ftp|rtsp|mms)?://)" 
		   + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@ 
           + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184 
           + "|" // 允许IP和DOMAIN（域名）
           + "([0-9a-z_!~*'()-]+\.)*" // 域名- www. 
           + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名 
           + "[a-z]{2,6})" // first level domain- .com or .museum 
           + "(:[0-9]{1,4})?" // 端口- :80 
           + "((/?)|" // a slash isn't required if there is no file name 
           + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
		var re=new RegExp(strRegex);
		if (re.test(str_url)) {
			return true;
		} else {
			return false;
		}
	}


//设置省市级联下拉框
var provinces = '上海,北京,天津,河北,山西,内蒙古,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,广西,海南,重庆,四川,贵州,云南,西藏,陕西,甘肃,青海,宁夏,新疆,台湾,香港,澳门'.split(',');
var cities =  '黄浦区,卢湾区,徐汇区,长宁区,静安区,普陀区,闸北区,虹口区,杨浦区,闵行区,宝山区,嘉定区,浦东新区,金山区,松江区,青浦区,南汇区,奉贤区,崇明县;东城区,西城区,海淀区,朝阳区,丰台区,石景山区,门头沟区,房山区,通州区,顺义区,昌平区,大兴区,怀柔区,平谷区,密云县,延庆县;和平区,河东区,河西区,南开区,河北区,红桥区,塘沽区,汉沽区,大港区,东丽区,西青区,津南区,北辰区,武清区,宝坻区,宁河县,静海县,蓟县;石家庄,唐山,秦皇岛,邯郸,邢台,保定,张家口,承德,沧州,廊坊,衡水,太原,大同,阳泉;长治,晋城,朔州,晋中,运城,忻州,临汾,吕梁;呼和浩特,包头,乌海,赤峰,通辽,鄂尔多斯,呼伦贝尔,兴安盟,锡林郭勒盟,乌兰察布盟,巴彦淖尔盟,阿拉善盟;沈阳,大连,鞍山,抚顺,本溪,丹东,锦州,营口,阜新,辽阳,盘锦,铁岭,朝阳,葫芦岛;长春,吉林,四平,辽源,通化,白山,松原,白城,延边朝鲜族自治州;哈尔滨,齐齐哈尔,鸡西,鹤岗,双鸭山,大庆,伊春,佳木斯,七台河,牡丹江,黑河,绥化,大兴安岭;南京,无锡,徐州,常州,苏州,南通,连云港,淮安,盐城,扬州,镇江,泰州,宿迁;杭州,宁波.温州,嘉兴,湖州,绍兴,金华,衢州,舟山,台州,丽水;合肥,芜湖,蚌埠,淮南,马鞍山,淮北,铜陵,安庆,黄山,滁州,阜阳,宿州,巢湖,六安,亳州,池州,宣城;福州,厦门,莆田,三明,泉州,漳州,南平,龙岩,宁德;南昌,景德镇,萍乡,九江,新余,鹰潭,赣州,吉安,宜春,抚州,上饶;济南,青岛,淄博,枣庄,东营,烟台,潍坊,济宁,泰安,威海,日照,莱芜,临沂,德州,聊城,滨州,菏泽;郑州,开封,洛阳,平顶山,安阳,鹤壁,新乡,焦作,濮阳,许昌,漯河,三门峡,南阳,商丘,信阳,周口,驻马店,济源;武汉,黄石,十堰,宜昌,襄樊,鄂州,荆门,孝感,荆州,黄冈,咸宁,随州,恩施土家族苗族自治州;长沙,株洲,湘潭,衡阳,邵阳,岳阳,常德,张家界,益阳,郴州,永州,怀化,娄底,湘西土家族苗族自治州;广州,韶关,深圳,珠海,汕头,佛山,江门,湛江,茂名,肇庆,惠州,梅州,汕尾,河源,阳江,清远,东莞,中山,潮州,揭阳,云浮;南宁,柳州,桂林,梧州,北海,防城港,钦州,贵港,玉林,百色,贺州,河池,南宁,柳州;海口,三亚,其他;万州区,涪陵区,渝中区,大渡口区,江北区,沙坪坝区,九龙坡区,南岸区,北碚区,万盛区,双桥区,渝北区,巴南区,黔江区,长寿区,綦江县,潼南县,铜梁县,大足县,荣昌县,璧山县,梁平县,城口县,丰都县,垫江县,武隆县,忠县,开县,云阳县,奉节县,巫山县,巫溪县,石柱土家族自治县,秀山土家族苗族自治县,酉阳土家族苗族自治县,彭水苗族土家族自治县,江津市,合川市,永川区,南川市;成都,自贡,攀枝花,泸州,德阳,绵阳,广元,遂宁,内江,乐山,南充,眉山,宜宾,广安,达州,雅安,巴中,资阳,阿坝,甘孜,凉山;贵阳,六盘水,遵义,安顺,铜仁,黔西南,毕节,黔东南,黔南;昆明,曲靖,玉溪,保山,昭通,楚雄,红河,文山,思茅,西双版纳,大理,德宏,丽江,怒江,迪庆,临沧;拉萨,昌都,山南,日喀则,那曲,阿里,林芝;西安,铜川,宝鸡,咸阳,渭南,延安,汉中,榆林,安康,商洛;兰州,嘉峪关,金昌,白银,天水,武威,张掖,平凉,酒泉,庆阳,定西,陇南,临夏,甘南;西宁,海东,海北,黄南,海南,果洛,玉树,海西;银川,石嘴山,吴忠,固原;乌鲁木齐,克拉玛依,吐鲁番,哈密,昌吉,博尔塔拉,巴音郭楞,阿克苏,克孜勒苏,喀什,和田,伊犁,塔城,阿勒泰;台北,高雄,其他;香港;澳门'.split(';'); 
function initAddrControl(provinceId, cityId){
	$.each(provinces,function(index,value){
	    //回调函数有两个参数,第一个是元素索引,第二个为当前值
		if (value == u_province){
			$("#"+provinceId).append("<option selected>"+value+"</option>");
		}else{
			$("#"+provinceId).append("<option>"+value+"</option>");
		}
		
		var data = cities[index].split(",");
		$.each(data,function(c_index,c_value){
			if (c_value == u_city){
				$("#"+cityId).append("<option class=\""+value+"\" selected>"+c_value+"</option>");
			}else{
				$("#"+cityId).append("<option class=\""+value+"\">"+c_value+"</option>");
			}
		});
	});

	$("#"+cityId).chained("#"+provinceId);
}

//返回顶部
function b() {
	h = $(window).height(),
	t = $(document).scrollTop(),
	t > h ? $("#moquu_top").show() : $("#moquu_top").hide()
}
$(document).ready(function() {
	b(),
	$("#moquu_top").click(function() {
		$(document).scrollTop(0)
	})
}),
$(window).scroll(function() {
	b();
});

//点击需求反馈
function psninfor(){
document.getElementById('psinforbg').style.display='block';
document.getElementById('psninforbox').style.display='block';
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
function publicbox(){
document.getElementById('public_bg').style.display='block';
document.getElementById('public_pmp').style.display='block';
}
function publicbox_close(){
document.getElementById('public_bg').style.display='none';
document.getElementById('public_pmp').style.display='none';
}
function logo_in(){alert()
//验证
//转向...
//myform.action=""
//myform.submit()
guanbi();
}

/**
 * 校验用户资源权限(直接给url。不要带权限标志：xxxx:0x00)
 * @param url
 * @returns {Boolean}
 */
function validPermission(url) {
	
	var validCode = false;
	$.ajax({
		url : ctx + "/systemManage/validAccountPermission",
		type : "post",
		data: {
			url: url
		},
		async: false,
		dataType : 'json',
		success : function(data) {
			validCode = data.valid;
		}
	});
	return validCode;
}

/**
 * 简单检查url有效性
 * @param str_url
 * @returns {Boolean}
 */
function checkUrl (str_url) {
	/*var strRegex = '^(https|http){1}://'
	+ '([A-Za-z0-9-_#$%&*!/\+-`~]){1,280}'
	+ '.([a-z]{2,3}(.[a-z]{2,3})?)$';*/
	//var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{1,280}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
	var strRegex = "^((https|http|ftp|rtsp|mms)://)?([A-Za-z0-9-_#$%&*!/\+-`~]){1,280}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
	var re=new RegExp(strRegex);
	//re.test()
	if (re.test(str_url)) {
		return true;
	} else {
		return false;
	}
} 