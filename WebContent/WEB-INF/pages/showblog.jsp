<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12 sui-btn">
			<h2>${ blog.blogtittle }</h2>
		</div>
	</div>
</div>
<br>
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
		<div class="span12 sui-btn">
		<!-- 这里是不防止XSS攻击的，因为富文本编辑器的问题 以后会改进 -->
			${ blog.blogmain }
		</div>
	</div>
</div>
<br>
<br>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span5"><!-- 占位 --></div>
		<div class="span2">
			<button id="upbtn" class="sui-btn btn-xxlarge btn-success" onclick="zan('${ pageContext.request.contextPath }','${blog.id}')">赞！${ blog.upcount }</button>
			<button id="downbtn" class="sui-btn btn-xxlarge btn-warning" onclick="cai('${ pageContext.request.contextPath }','${ blog.id }')">踩！ ${ blog.downcount }</button>
		</div>
		<div class="span5"><!-- 占位 --></div>
	</div>
</div>
<script>
function zan(url,tid){
	$.post(url+"/upcount.action",{id:tid},function(data){
		if(data.result){
			$("#upbtn").html("赞！"+data.count);
			$("#upbtn").attr("disabled","disabled");
		}
	});
}
function cai(url,tid){
	$.post(url+"/downcount.action",{id:tid},function(data){
		if(data.result){
			$("#downbtn").html("踩！"+data.count);
			$("#downbtn").attr("disabled","disabled");
		}
	});
}
</script>
<%@include file="footer.jsp"%>
