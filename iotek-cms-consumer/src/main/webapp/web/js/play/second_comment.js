
var commentId = '';
//获取页码信息，并初始化第一层评论分页组件
function showSecondComent(id){
	
	commentId= id;
	
	if (!clearSecondDiv()){
		return;
	}
	
	$.ajax({
		url : ctx + "/play/comment/showCommentReply",
		type : "post",
		data: {
			commentId: id,
			commentType: 2
		}, 
		dataType : 'json',
		success : function(data) {
			// 初始化页数和条数
			initCommentReplyPatination('second_comment_page_'+commentId,data.innerPage.dataCount,data.innerPage.pageSize);
		}
	});
}

/**
 * 清空回复div
 */
function clearSecondDiv(){ 
	
	//是否加载
	var load = true;
	$(".second_comment_div").each(function (index,elem) {
		if (elem.id != ("second_comment_div_"+ commentId)) {
			$("#"+elem.id).hide();
		} else {
			
			if ($("#"+elem.id).html() != '') {
				load = false;
				$("#"+elem.id).hide();
			} else {
				load = true;
				$("#"+elem.id).show();
			}
		}
		$("#"+elem.id).html("");
	});

	//隐藏其他的分页组件
	$(".sabpages").each(function (index,elem) {
		//找到回复的分页
		if ((elem.id).indexOf("second_comment_page_") > -1 ) {
			var ele = elem.id;
			var pageDiv = "#second_page_div_" + ele.substring(ele.lastIndexOf("_") + 1,ele.length);
			if (ele != ("second_comment_page_"+ commentId)) { 
				$(pageDiv).hide();
				$("#"+ele).hide();
				$("#"+ele).html("");
			}else {
				
				var outHtml = $("#second_comment_page_" + commentId)[0].outerHTML;
				if (outHtml.indexOf("Em_prev") > -1) {
					$(pageDiv).hide();
					$("#"+ele).hide();
					load = false;
				} else {
					load = true;
					$(pageDiv).show();
					$("#"+ele).show();
				}
				$("#second_comment_page_" + commentId).html('');
			}
		} 
	});
	
	return load;
}

//分页方法，参数showPage为显示页面的容器ID，callbackfunction为回调函数名,totleCount为数据总条数，pageNum为每页显示条数	
function initCommentReplyPatination(showPage,dataCount,pagesize){

	$("#"+showPage).pagination(dataCount, {
		num_edge_entries: 2, //边缘页数
		num_display_entries: 4, //主体页数
		callback: commentReplyPageselectCallback,
		items_per_page:pagesize, //每页显示项
		prev_text:"<em class='Em_prev'></em>",
		next_text:"<em class='Em_next'></em>",
		link_to:"javascript:void(0)"
	});
}

//分页回调函数
function commentReplyPageselectCallback(page_index, jq){
	showLoading("second_comment_div_"+commentId);
	$.ajax({
		url : ctx + "/play/comment/showCommentReply",
		type : "post",
		data: {
			pageNo : page_index+1,
			commentId: commentId,
			commentType: 2
		}, 
		dataType : 'json',
		success : function(data) {
			//获取成功，处理数据的回调函数
			var pageDive = "#second_page_div_"+ commentId;
			var commentPage = "#second_comment_page_" + commentId;
			if (data.innerPage.maxPage == 1){
				$(pageDive).hide();
				$(commentPage).hide();
			} else {
				$(pageDive).show();
				$(commentPage).show();
			}
			resolveCommentReplyData(data, data.innerPage.result);
		}
	});
}

//数据解析方法
function resolveCommentReplyData(data, list){
	
	var content = "";
	if (list.length > 0) {
		content += "<div class='cmt_repeat_in'>";
		content += "<i class='S_bg2_br'></i>";
		$.each(list, function(i, commentReply){
			//回复内容
			content += "<div class='cmt_list_li cmt_line02 clearfix' id='second_comment_"+commentReply.id+"'>";
			content += "<div class='cmt_face fl'>";
			//判断是否是登录用户
			var icon = '';
			if (commentReply.visitUrl != null && commentReply.visitUrl != '') {
				//使用默认头像
				icon = noLoginIcon;
			} else {
				icon = commentReply.userInfo.iconUrl;
			}
			content += "<img src='"+img_suffix+"/"+icon+"' width='40' height='40' />";
			content += "</div>";
			content += "<div class='cmt_list_con'>";
			content += "<div class='cmt_text'>";
			content += "<h3>";
			//判断是否是登录用户
			var realName = '';
			if (commentReply.visitUrl != null && commentReply.visitUrl != '') {
				if (commentReply.visitUrl.indexOf(",") > -1) {
					realName = commentReply.visitUrl.split(",")[1]+"网友";
            	  } else {
            		  realName = commentReply.visitUrl;
            	  }
			} else {
				if (commentReply.userInfo != null || commentReply.userInfo != 'null') {
					if (commentReply.userInfo.nickName != null && commentReply.userInfo.nickName != '') {
						realName = commentReply.userInfo.nickName;
					} else if (commentReply.userInfo.realName != null && commentReply.userInfo.realName != '') {
						realName = commentReply.userInfo.realName;
					}
				}
				
				if (realName == ''){
          		  realName = commentReply.user.username;
          	  	}
			}
			content += "<a href='javascript:void(0);' style='cursor:default'>"+realName+"</a>";
			//判断是否是回复中的回复
			if (commentReply.repliedNickName != null && commentReply.repliedNickName != ''
				&& commentReply.repliedNickName != null) {
				content += "回复: <a href='javascript:void(0);' style='cursor:default'>"+commentReply.repliedNickName+"</a>";
			} else if (commentReply.userInfoReply != null && commentReply.userInfoReply.realName != ''
				 && commentReply.userInfoReply.realName != null) {
				content += "回复: <a href='javascript:void(0);' style='cursor:default'>"+commentReply.userInfoReply.realName+"</a>";
			} else if (commentReply.replyUserName != null && commentReply.replyUserName != ''
				 && commentReply.replyUserName != null){
				content += "回复: <a href='javascript:void(0);' style='cursor:default'>"+commentReply.replyUserName+"</a>";
			}
			
			if (commentReply.repliedVisitUrl != null) {
				var repRelName = "";
				if (commentReply.repliedVisitUrl.indexOf(",") > -1) {
					repRelName = commentReply.repliedVisitUrl.split(",")[1]+"网友";
            	  } else {
            		  repRelName = commentReply.repliedVisitUrl;
            	  }
				content += "回复:  <a href='javascript:void(0);' style='cursor:default'>"+repRelName+"</a>";
			}
			content += "<span>";
//			content += "<a href='javascript:void(0);' style='color:gray;'>";
			content += commentReply.content;
//			content += "</a>";
			content += "</span>";
			var validStr = "second_comment_reply_"+ commentReply.id;
			
			content += "</h3>";
			content += "</div>";
			content += "<div class='cmt_func clearfix'>";
			content += "<div class='fr cmt_date'>";
			content += "<a href='javascript:clickShowReply("+"second_comment_reply_div_"+commentReply.id +","+validStr+");'>回复</a>";
			content += "</div>";
			content += "<div class='cmt_time'><fmt:formatDate value='"+dealServerDate(commentReply.createTime)+"' pattern='yyyy-MM-dd: HH:mm:ss'>"+dealServerDate(commentReply.createTime)+"</fmt:formatDate>";
			content += "</div>";
			
			//二级回复的载体
			content += "<div class='cmt_publish' id ='second_comment_reply_div_"+ commentReply.id +"' style='display:none;'>";
			content += "<div id='fade_comment_reply_"+commentReply.id+"' class='comme_succ' style='display: none;'><p></p></div>";
			content += "<p class='p_textarea'>";
			//拼接校验对象
			
			var lengthStr = "second_input_length_" + commentReply.id;
			
			content += "<textarea id='second_comment_reply_"+commentReply.id+"' maxlength='150' rows='' cols=''";
			content += "class='C_txtar' ";
			content += " oninput='validFirstInput("+validStr + ","+lengthStr +")' ";
			content += " onpropertychange='validFirstInput("+validStr + ","+lengthStr +")' ";
			content += " onfocus='validFirstInput("+ validStr +","+lengthStr+")' ";
			content += " ></textarea>";
			content += "</p>";
			content += "<p class='p_textbtn clearfix'>";
			//回复的用户
			content += "<span class='fl cmt_pub_face'><a href='javascript:void(0);'>";
			var loginIcon = '';
			var loginName = '';
			if (ctx_user_id != 'undefine' && ctx_user_id != 0 && ctx_user_id != '') {
				loginIcon = img_suffix+ "/" + ctx_user_info.iconUrl;
				if (ctx_user_info.nickName != null && ctx_user_info.nickName != '') {
					loginName = ctx_user_info.nickName;
				} else if (ctx_user_info.realName != null && ctx_user_info.realName != '') {
					loginName = ctx_user_info.realName;
				} else { 
					loginName = ctx_user.username;
				}
			} else {
				loginIcon =  img_suffix+ "/" + noLoginIcon;
				loginName = ipAddress.content.address_detail.city+"网友";
			}
			content += "<img src='"+loginIcon+"' width='40' height='40'/>";
			content += "</a><a class='user_name' href='javascript:void(0);'>"+loginName+"</a></span>";
			//被回复的楼层用户
			content += "<span class='fl cmt_pub_face'>";
			content += "&nbsp;&nbsp;回复:&nbsp;&nbsp;";
			content += "</span>";
			content += "<span class='fl cmt_pub_face'><a href='#'>";
			content += "<img src='"+img_suffix+"/"+icon+"' width='40' height='40'/>";
			content += "</a><a class='user_name' href='javascript:void(0);'>"+realName+"</a></span>";
			
			content += "<span class='fr'>还可以输入<i id='second_input_length_"+commentReply.id+"'>";
			content += "150</i>字<input class='C_input'";
			content += "type='button' value='评论' onclick='saveCommentReply("+commentReply.commentId + ","+validStr+","+commentReply.id +",2" +")'></span>";
			content += "</p>";
			content += "</div>";
			
			
			content += "</div>";
			content += "</div>";
			content += "</div>";
		});
		
		content += "</div>";
	}
	$("#second_comment_div_"+commentId).html(content);
}

/**
 * 显示楼层间的回复
 */
function clickCommentReply(commId,replyId) {
	
	var content = "";
	
	
	$("#second_comment_reply_div_"+replyId).html();
}

/*
	<div class="cmt_publish">
	<p class="p_textarea">
		<input id="ip_addr" value="${request.remoteAddr }" type="hidden"/>
		<textarea id="first_comment" maxlength="150" rows="" cols=""
			class="C_txtar" onblur="valid_input_comment('first_comment','first_input_length')" oninput="valid_input_comment('first_comment','first_input_length')" ></textarea>
	</p>
	<p class="p_textbtn clearfix">
		<span class="fr">还可以输入<i id="first_input_length">150</i>字<input class="C_input"
			type="button" name="" value="评论" onclick="saveComment()"></span>
	</p>
</div>
</div>
<!--二级回复 end-->
<!--评论 列表 end-->
 * */

