<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="header.jsp"%>
<!-- 由于引入了header所以每一个都是从这里开始写  -->
<div class="sui-container">
	<div class="sui-row-fluid">
		<div class="span12">
			<table class="sui-table table-bordered-simple">
				<thead>
					<tr>
						<th>序号</th>
						<th>TAG</th>
						<th>Tittle</th>
						<th>Main</th>
						<th>Date</th>
					</tr>
				</thead>
				<c:forEach items="${ blogs }" var="blog" varStatus="blogcou">
					<tr>
						<td>${ blogcou.count }</td>
						<td>${ blog.tag }</td>
						<td>${ blog.blogtittle }</td>
						<td>${ blog.blogmain }</td>
						<td>${ blog.createdate }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>