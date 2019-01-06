<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<c:if test="${ !empty info }">
		<div class="sui-container">
			<div class="sui-row-confuild">
				<div class="span12">
					<div class="sui-msg msg-large msg-block msg-success">
						<div class="msg-con">${ info }</div>
						<s class="msg-icon"></s>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="sui-row-fuild">
		<div class="span3"></div>
		<div class="span6">
			<form action="${ pageContext.request.contextPath }/own/uploadimg.action"
				method="post" enctype="multipart/form-data" class="sui-form">
				上传新的图片：<input type="file" id="getimg" name="getimg" class="input">
				<input type="submit" class="sui-btn btn-primary" value="上传">
			</form>
		</div>
		<div class="span3"></div>
	</div>
</div>

<div class="sui-container">
	<c:if test="${ empty imglist }">
		<div class="sui-row-fuild">
			<div class="span12">
				<div class="sui-msg msg-block msg-error">
					<div class="msg-con">您还没有图片！</div>
					<s class="msg-icon"></s>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${ !empty imglist }">
	<div class="sui-row-fuild">
		<div class="span2"></div>
		<div class="span8">
			<table class="sui-table table-zebra">
				<thead>
					<tr>
						<td>图片</td>
						<td>链接地址</td>
					</tr>
				</thead>
				<c:forEach items="${ imglist }" var="imgs" >
					<tr>
						<td>
							<img alt="图片" src="${pageContext.request.contextPath }${ imgs.path }" height="50px" width="50px">
						</td>
						<td>
							<span>https://www.xiaobai1202.top${pageContext.request.contextPath }${ imgs.path }</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="span2"></div>
	</div>
	</c:if>
</div>
<%@include file="footer.jsp"%>