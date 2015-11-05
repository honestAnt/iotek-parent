<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.iotekclass.persist.pagination.Page" required="true"%>
<%@ attribute name="isPost" type="java.lang.Boolean" required="false"%>
<%@ attribute name="formName" type="java.lang.String" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty page.result}">
	  <c:choose>
	  	<c:when test="${isPost}">
	  		<script type="text/javascript">
		  	$(document).ready(function() {
		  		var current=$("#current");
		  		var $form=$("#${formName}");
		  		var last="${page.maxPage}";
		  		var formAction=$form.attr("action");
		  		//首页
		  		$("#first").click(function(){
		  			$form.attr("action",formAction+"?pageNo=1");
		  			$form.submit();
		  		});
		  		//上一页
		  		$("#prev").click(function(){
		  			var prev=parseInt(current.text())-1;
		  			$form.attr("action",formAction+"?pageNo="+prev);
		  			$form.submit();
		  		});
		  		$("a[id^=num]").each(function(){
		  			$(this).click(function(){
		  				var num=$(this).text();
		  				$form.attr("action",formAction+"?pageNo="+num);
			  			$form.submit();
		  			});
		  		});
		  		//下一页
		  		$("#next").click(function(){
		  			var next=parseInt(current.text())+1;
		  			$form.attr("action",formAction+"?pageNo="+next);
		  			$form.submit();
		  		});
		  		//末页
		  		$("#last").click(function(){
		  			$form.attr("action",formAction+"?pageNo="+last);
		  			$form.submit();
		  		});
		  		
		  	});
		  	</script>
	  		<div class="sabpages">
			  	<c:choose>
			  		<c:when test="${page.prev}">			  					
			  			<a id="prev" href="javascript:void(0);" title="上一页">
			  				<em class="Em_prev"></em>
			  			</a>
			  		</c:when>
			  		<c:otherwise>
			  			<span class="disabled" title="上一页">
			  				<em class="Em_prev"></em>
			  			</span>
			  		</c:otherwise>
			  	</c:choose>
			  	<c:choose>
			  		<c:when test="${page.maxPage>5}">
			  			<c:choose>
						<c:when test="${page.currentPage>3}">
							<a id="first" href="javascript:void(0);">1</a>
							<c:if test="${page.currentPage!=4}">
								<span>…</span>
							</c:if>
							<c:choose>
								<c:when test="${page.maxPage-page.currentPage<3}">
									<c:forEach begin="${page.maxPage-4}" end="${page.currentPage-1}" var="pageNumber">
									<a id="num${pageNumber}" href="javascript:void(0);">${pageNumber}</a>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<a id="num${page.currentPage-2}" href="javascript:void(0);">${page.currentPage-2}</a>
									<a id="num${page.currentPage-1}" href="javascript:void(0);">${page.currentPage-1}</a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:forEach begin="1" end="${page.currentPage-1}" var="pageNumber">
								<a id="num${pageNumber}" href="javascript:void(0);">${pageNumber}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<span id="current" class="current">${page.currentPage}</span>						
					<c:choose>
						<c:when test="${page.maxPage-page.currentPage>=3}">
							<c:choose>
								<c:when test="${page.currentPage<3}">
									<c:forEach begin="${page.currentPage+1 }" end="5" var="pageNumber">
										<a id="num${pageNumber}" href="javascript:void(0);">${pageNumber}</a>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<a id="num${page.currentPage+1}" href="javascript:void(0);">${page.currentPage+1}</a>
									<a id="num${page.currentPage+2}" href="javascript:void(0);">${page.currentPage+2}</a>
								</c:otherwise>
							</c:choose>
							<c:if test="${page.maxPage-page.currentPage!=3}">
								<span>…</span>
							</c:if>
							<a id="last" href="javascript:void(0);">${page.maxPage}</a>
						</c:when>
						<c:otherwise>
							<c:forEach begin="${page.currentPage+1}" end="${page.maxPage}" var="pageNumber">
								<a id="num${pageNumber}" href="javascript:void(0);">${pageNumber}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			  		</c:when>
			  		<c:otherwise>
			  			<c:forEach var="pageNumber" begin="1" end="${page.maxPage}" step="1">
					  		<c:choose>
					  			<c:when test="${pageNumber==page.currentPage}">
					  				<span id="current" class="current">${page.currentPage}</span>
					  			</c:when>
					  			<c:otherwise>
					  				<a id="num${pageNumber}" href="javascript:void(0);">${pageNumber}</a>
					  			</c:otherwise>
					  		</c:choose>
			  			</c:forEach>
			  		</c:otherwise>
			  	</c:choose>
			    <c:choose>
			  		<c:when test="${page.next}">
			  			<a id="next" href="javascript:void(0);" title="下一页">
			  				<em class="Em_next"></em>
			  			</a>
			  		</c:when>
			  		<c:otherwise>
			  			<span class="disabled" title="下一页">
			  				<em class="Em_next"></em>
			  			</span>
			  		</c:otherwise>
			  	</c:choose>
		   		</div>
	  	</c:when>
	  	<c:otherwise>
	  		 <c:if test="${not empty page.pageSort}">
		  		<c:set var="sortParam" value="&sortType=${page.pageSort.sortItem}"/>
		     </c:if>
	  		<div class="sabpages">
			  	<c:choose>
			  		<c:when test="${page.prev}">
			  			<a href="?pageNo=${page.currentPage-1}${sortParam}" title="上一页">
			  				<em class="Em_prev"></em>
			  			</a>
			  		</c:when>
			  		<c:otherwise>
			  			<span class="disabled" title="上一页">
			  				<em class="Em_prev"></em>
			  			</span>
			  		</c:otherwise>
			  	</c:choose>
			  	<c:forEach var="pageNumber" begin="1" end="${page.maxPage}" step="1">
			  		<c:choose>
			  			<c:when test="${pageNumber==page.currentPage}">
			  				<span class="current">${page.currentPage}</span>
			  			</c:when>
			  			<c:otherwise>
			  				<a href="?pageNo=${pageNumber}${sortParam}">${pageNumber}</a>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			    <c:choose>
			  		<c:when test="${page.next}">
			  			<a href="?pageNo=${page.currentPage+1}${sortParam}" title="下一页">
			  				<em class="Em_next"></em>
			  			</a>
			  		</c:when>
			  		<c:otherwise>
			  			<span class="disabled" title="下一页">
			  				<em class="Em_next"></em>
			  			</span>
			  		</c:otherwise>
			  	</c:choose>
		   		</div>
	  	</c:otherwise>
	  </c:choose>
   </c:if>

