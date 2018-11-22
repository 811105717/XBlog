<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<c:if test="${ empty results }">
		<div class="sui-row-fluid">
			<div class="span12">
				<div class="sui-msg msg-block msg-large  msg-error">
					<div class="msg-con">抱歉，没有找到与“ ${ kwd } ” 有关的内容！</div>
					<s class="msg-icon"></s>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${! empty results }">
		<div class="sui-row-fuild">
			<div class="span12">
				<div class="sui-msg msg-block msg-large   msg-success">
					<div class="msg-con">一共找到了${ fn:length(results) }个与 “ ${ kwd }
						” 有关的内容！</div>
					<s class="msg-icon"></s>
				</div>
			</div>
		</div>
		<div class="sui-row-fuild">
			<div class="span12">
				<table class="sui-table table-bordered">
					<thead>
						<tr>
							<th>
								<div class="msg-con sui-msg msg-large msg-success">序号</div>
							</th>
							<th>
								<div class="msg-con sui-msg msg-large msg-warning">标题</div>
							</th>
							<th>
								<div class="msg-con sui-msg msg-large msg-info">日期</div>
							</th>
							<th>
								<div class="msg-con sui-msg msg-large msg-question">点赞数</div>
							</th>
							<th>
								<div class="msg-con sui-msg msg-large msg-question">点踩数</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ results }" var="bl" varStatus="blcount">
							<tr>
								<td class="sui-text-xlarge"><a
									href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ blcount.count }</a></td>
								<td class="sui-text-xlarge"><a
									href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.blogtittle }</a></td>
								<td class="sui-text-xlarge"><a
									href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.createdate }</a></td>
								<td class="sui-text-xlarge"><a
									href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.upcount }</a></td>
								<td class="sui-text-xlarge"><a
									href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.downcount }</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</div>
<%@include file="footer.jsp"%>