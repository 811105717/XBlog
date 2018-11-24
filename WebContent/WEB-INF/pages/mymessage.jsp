<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12">
			<div class="sui-msg msg-large msg-block msg-success">
				<div class="msg-con">您的所有消息列表</div>
				<s class="msg-icon"></s>
			</div>
		</div>
	</div>
</div>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span12">
			<table class="sui-table table-bordered-simple">
				<tr>
					<th>序号</th>
					<th>消息类别</th>
					<th>状态</th>
				</tr>
				<c:forEach items="${ messages }" var="mes" varStatus="mv">
					<tr>
						<td>${ mv.count }</td>
						<c:if test="${ mes.isread==0 }">
							<td><a  onclick="yidu('${ pageContext.request.contextPath }','${ mes.id }')" href="${ pageContext.request.contextPath }/blog.action?id=${ mes.blogid }">${ mes.mess }</a></td>
							<td style="color: green;">未读</td>
						</c:if>
						<c:if test="${ mes.isread!=0 }">
							<td><a style="color: gray" onclick="yidu('${ pageContext.request.contextPath }','${ mes.id }')" href="${ pageContext.request.contextPath }/blog.action?id=${ mes.blogid }">${ mes.mess }</a></td>
							<td style="color: gray;">已读</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<script>
	function yidu(url,id){
		$.post(url+"/own/isread.action",{
			id:id
		},function(){});
	}
</script>
<%@include file="footer.jsp"%>