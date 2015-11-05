var csJs = {};

/**
 * 初始化章节
 * @param obj
 * @param flag	1:课程找章	2:章找节
 */
csJs.changeChapter = function(val,flag) {
	$.ajax({
		url : ctx + "/review/findSyllabus",
		type : "post",
		data : {
			pid : val,
			flag : flag
		},
		dataType : 'json',
		success : function(data) {
			csJs.initSelect(flag,data.listSyllabus);
		}
	});
}

/**
 * 初始化下拉框
 * @param id
 * @param list
 */
csJs.initSelect = function(flag,list) {
	if(flag == 1) {
		var start = -1;
		if(list.length>0) {
			start = list[0].id;
		}
		csJs.changeChapter(start,2);
	}
	var obj = flag == 1?$("#chapterId"):$("#sectionId");
	var type = flag == 1?"章":"节";
	var oHtml = "<option value='-1'>请选择</option>";
	if(list.length>0) {
		$.each(list,function(index,o){
			oHtml += "<option value='" + o.id + "'>第" + o.sortNumber +type+" "+ o.itemName + "</option>";
		});
	}
	obj.html(oHtml);
}