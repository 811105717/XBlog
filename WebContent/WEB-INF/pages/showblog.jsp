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
			<button class="sui-btn btn-xxlarge btn-success" onclick="zan('${blog.id}')">赞！${ blog.upcount }</button>
			<button class="sui-btn btn-xxlarge btn-warning" onclick="cai('${ blog.id }')">踩！ ${ blog.downcount }</button>
		</div>
		<div class="span5"><!-- 占位 --></div>
	</div>
</div>
<script>
function zan(){
	alert("zan");
}
function cai(){
	alert("cai");
}
</script>
<%@include file="footer.jsp"%>
