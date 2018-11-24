<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12 sui-btn" style="background-color: #D3D3D3">
			<h2>${ blog.blogtittle }</h2>
		</div>
	</div>
</div>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12">
			<div class="sui-navbar">
				<div class="navbar-inner">
					<ul class="sui-nav">
						<li><a>文章号：${ blog.id }</a></li>
						<li><a>作者： ${ author }</a></li>
						<li><a>编辑日期：${ blog.createdate }</a></li>
						<li><a>标签分类： ${ blog.tag }</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12 sui-btn" style="border-radius: 5px; border: 1px solid gray;">
			<!-- 这里的富文本编辑器是可以防止XSS攻击的 所以直接写了 -->
			${ blog.blogmain }
		</div>
	</div>
</div>
<br>
<br>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span5">
			<!-- 占位 -->
		</div>
		<div class="span2">
			<button id="upbtn" class="sui-btn btn-xlarge btn-success"
				onclick="zan('${ pageContext.request.contextPath }','${blog.id}')">
				<i class="sui-icon icon-touch-arrow-up-circle"></i>赞！${ blog.upcount }
			</button>
			<button id="downbtn" class="sui-btn btn-xlarge btn-warning"
				onclick="cai('${ pageContext.request.contextPath }','${ blog.id }')">
				<i class="sui-icon icon-touch-arrow-down-circle"></i>踩！ ${ blog.downcount }
			</button>
		</div>
		<div class="span5">
			<!-- 占位 -->
		</div>
	</div>
</div>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12 sui-form">
			<div class="control-group">
				<label for="common" class="control-label">评论：</label>
				<div class="controls">
					<textarea class="span12" rows="10" id="common"></textarea>
				</div>
				<br>
				<button class="sui-btn btn-xlarge btn-primary" onclick="sentcommon('${ pageContext.request.contextPath }','${ blog.id }')">发表评论</button>
			</div>
		</div>
	</div>
</div>

<div class="sui-container">
	<c:if test="${ empty commons }">
		<div class="sui-row-fuild">
			<div class="span12">
				<div class="sui-msg msg-large msg-block msg-tips">
					<div class="msg-con">该文章暂时还没有评论！！快成为第一个评论者吧！</div>
					<s class="msg-icon"></s>
				</div>
			</div>
		</div>
	</c:if>
</div>
<c:if test="${ !empty commons }">

	<c:forEach items="${ commons }" var="com" varStatus="vas">
		<br>
		<div class="sui-container">
			<div class="sui-row-fuild">
				<div class="span12 disabled" style="background-color: #F0FFFF">
					<p>${ com.common }</p>
					<div class="pull-right">
						评论日期：${ com.date } &nbsp;&nbsp; 作者：${ com.authorname }
						&nbsp;&nbsp;
						<button id="zancom${ vas.count }" class="sui-btn btn-xlarge btn-success" onclick="zancommon('${ pageContext.request.contextPath }','${ com.id }','${ vas.count }')">赞！${ com.upcount }</button>
						<button id="caicom${ vas.count }" class="sui-btn btn-xlarge btn-warning" onclick="caicommon('${ pageContext.request.contextPath }','${ com.id }','${ vas.count }')">踩！${ com.downcount }</button>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

</c:if>

<script>
	function zan(url, tid) {
		$.post(url + "/upcount.action", {
			id : tid
		}, function(data) {
			if (data.result) {
				$("#upbtn").html(
						"<i class=\"sui-icon icon-touch-arrow-up-circle\"></i>赞！"
								+ data.count);
				$("#upbtn").attr("disabled", "disabled");
				$("#downbtn").attr("disabled", "disabled");
			}
		});
	}
	function cai(url, tid) {
		$.post(url + "/downcount.action", {
			id : tid
		}, function(data) {
			if (data.result) {
				$("#downbtn").html(
						"<i class=\"sui-icon icon-touch-arrow-down-circle\"></i>踩！"
								+ data.count);
				$("#downbtn").attr("disabled", "disabled");
				$("#upbtn").attr("disabled", "disabled");
			}
		});
	}
	function zancommon(url,id,ids){
		$.post(url+"/zancommon.action",{
			id:id
		},function(data){
			if(data.result){
				$("#zancom"+ids).html("赞"+data.count);
				$("#zancom"+ids).attr("disabled","disabled");
				$("#caicom"+ids).attr("disabled","disabled");
			}
		});
	}
	function caicommon(url,id,ids){
		$.post(url+"/caicommon.action",{
			id:id
		},function(data){
			if(data.result){
				$("#caicom"+ids).html("踩"+data.count);
				$("#zancom"+ids).attr("disabled","disabled");
				$("#caicom"+ids).attr("disabled","disabled");
			}
		})
	}
	
	function sentcommon(url,id){
		alert(id);
	}
</script>
<%@include file="footer.jsp"%>
