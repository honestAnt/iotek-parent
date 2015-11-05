/**
 * 简单处理后台传来的日期
 */
function dealServerDate(date) {
	
	var dateTem = '';
	if (date.length > 0) {
		var index = date.indexOf("T");
		dateTem = date.substring(0, index) +" "+ date.substring(index + 1, date.length);
	}
	return dateTem;
}

/**
 * 校验用户输入的评论字数
 */
function valid_input_comment(ele,inputEle) {
	
//	jmz.GetLength($.trim($("#"+ele).val()));
//	var num = 150 - ($.trim($("#"+ele).val())).length;
	var num = (300 - getStrRealLength($.trim($("#"+ele).val())))/2;
	if (num < 0) { 
		num = 0;
	}
	
	$("#"+inputEle).html(parseInt(num));
}

/**
 * 获取焦点
 * @param ele
 */
function getFocus(ele) {
	ele.focus();
}

/**
 * 评论成功后，显示淡入淡出效果
 */
function fadeDive(obj,str) {
	
	$("#"+obj).show();
	$("#"+obj).html(str);
	$("#"+obj).fadeOut(3000);
}