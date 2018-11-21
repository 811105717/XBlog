<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="header.jsp"%>
<!-- 由于引入了header所以每一个都是从这里开始写  -->
<div class="sui-container">
	<div class="sui-row-fluid">
		<div class="span2">
			<div class="sui-btn" style="width: 14rem;">
				<img src="${ pageContext.request.contextPath }/img/logo.jpg"
					alt="Card image cap">
				<div>
					<h5>XBlog</h5>
					<p>
						欢迎来到XBlog！ 这是一个全新的基于ssm+sui框架开发的简单博客系统! <br> powered by
						xiaobai <br> <br> <br> <br>
					</p>
				</div>
			</div>
		</div>
		<div class="span8">
			<div id="myCarousel" data-ride="carousel" data-interval="3000"
				class="sui-carousel slide">
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="active item">
						<img src="${ pageContext.request.contextPath }/img/1.jpg">
						<div class="carousel-caption">
							<p>美好的景色往往让人产生美好的心情</p>
						</div>
					</div>
					<div class="item">
						<img src="${ pageContext.request.contextPath }/img/2.jpg">
						<div class="carousel-caption">
							<p>在这里，你可以记录下你的一点一滴</p>
						</div>
					</div>
					<div class="item">
						<img src="${ pageContext.request.contextPath }/img/3.jpg">
						<div class="carousel-caption">
							<p>XBlog 是你值得拥有的应用！ 我们即将推出Android版！</p>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="span2">
			<div class="sui-btn" style="width: 14rem;">
				<img src="${ pageContext.request.contextPath }/img/logo.jpg"
					alt="Card image cap">
				<div>
					<h5>Version Control</h5>
					<p>
						V 1.0.0 初始版本 <br> <br> <br> <br> <br> <br>
					</p>
				</div>
			</div>
		</div>
		<div class="sui-row-fuild">
			<div class="span12">
				<div class="sui-msg msg-large   msg-block msg-success">
					<div class="msg-con">博客列表</div>
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
						<c:forEach items="${ blogs }" var="bl" varStatus="blcount">
							<tr>
								<td class="sui-text-xlarge"><a href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ blcount.count }</a></td>
								<td class="sui-text-xlarge"><a href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.blogtittle }</a></td>
								<td class="sui-text-xlarge"><a href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.createdate }</a></td>
								<td class="sui-text-xlarge"><a href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.upcount }</a></td>
								<td class="sui-text-xlarge"><a href="${ pageContext.request.contextPath }/blog.action?id=${ bl.id }">${ bl.downcount }</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>