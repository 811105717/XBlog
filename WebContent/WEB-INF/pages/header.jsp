<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css" rel="stylesheet">
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script>
<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui-append.min.css" rel="stylesheet">
<!-- 处理登陆及登陆后的一些操作！ -->
<script>
	function logout(basepath) {
		$
				.post(
						basepath + "/logout.action",
						function(data) {
							if (data.result) {
								var log = "用户名：<input type=\"text\" name=\"un\" id=\"un\">"
										+ "密  码：<input type=\"password\" name=\"pwd\" id=\"pwd\">"
										+ "<button class=\"sui-btn\" onclick=\"login('${ pageContext.request.contextPath }')\">"
										+ "<i class=\"sui-icon icon-touch-user3\">"
										+ "</i>登陆</button><button class=\"sui-btn\" data-toggle=\"modal\" data-target=\"#regwindow\">"
										+ "<i class=\"sui-icon icon-tb-share\"></i>注册账户</button>";
								$("#logincontrol").html(log);
							}
						},"json")
	}
	function login(basepath) {
		var un = $("#un").val();
		var pwd = $("#pwd").val();
		if (un.trim() == "" || pwd.trim() == "") {
			$("#messagetittle").text("非法输入！！");
			$("#messagebody").text("您必须将用户名或密码填写完整才能登陆！！");
			$("#errmessage").modal('show');
			return;
		}
		$
				.post(
						basepath + "/login.action",
						{
							un : un,
							pwd : pwd
						},
						function(data) {
							if (data.result) {
								var inner = "<button  id=\"user\" class=\"sui-btn btn-success\"></button>"
									+"<a class=\"sui-btn btn-success\" href=\"${ pageContext.request.contextPath }/own/newblog.action\">"	
									+"<i class=\"sui-icon icon-touch-edit\"></i>写文章</a>"
										+ "<button class=\"sui-btn btn-success\" onclick=\"logout('${ pageContext.request.contextPath }')\">"
										+ "<i class=\"sui-icon icon-tb-close\"></i>退出</button>";
								var ico = "<i class=\"sui-icon icon-tb-emoji\"></i>"
								$("#logincontrol").html(inner);
								$("#user").html(
										ico + "用户 [ " + data.un + " ] ，欢迎您！");
							} else {
								$("#messagetittle").text("登陆失败提示！");
								$("#messagebody").text("登陆失败，用户名或密码错误！");
								$("#errmessage").modal('show');
							}
						},"json");
	}

	function register(basepath) {

		var username = $("#reg_un").val();
		var password = $("#reg_pass").val();
		var conf = $("#reg_pass_confirm").val();
		var sex = $("#reg_sex").val();
		var address = $("#reg_address").val();
		var email = $("#reg_email").val();
		var tel = $("#reg_tel").val();
		if (username.trim() == "" || password.trim() == "" || conf.trim() == "") {
			$("#messagetittle").text("非法输入！！");
			$("#messagebody").text("请完善您的信息！！");
			$("#errmessage").modal('show');
			return false;
		} else if (password.trim() != conf.trim()) {
			$("#messagetittle").text("请检查！！");
			$("#messagebody").text("两次输入的密码不一致");
			$("#errmessage").modal('show');
			return false;
		} else {
			$
					.post(
							basepath + "/register.action",
							{
								username : username,
								password : password,
								sex : sex,
								address : address,
								email : email,
								tel : tel
							},
							function(data) {
								if (data.result) {
									$("#regwindow").modal('hide');
									var inner = "<button  id=\"user\" class=\"sui-btn btn-success\"></button>"
											+"<a class=\"sui-btn btn-success\" href=\"${ pageContext.request.contextPath }/own/newblog.action\">"
											+"<i class=\"sui-icon icon-touch-edit\"></i>写文章</a>"
											+ "<button class=\"sui-btn btn-success\" onclick=\"logout('${ pageContext.request.contextPath }')\">"
											+ "<i class=\"sui-icon icon-tb-close\"></i>退出</button>";
									var ico = "<i class=\"sui-icon icon-tb-emoji\"></i>"
									$("#logincontrol").html(inner);
									$("#user").html(
											ico + "用户 [ " + data.un
													+ " ] ，欢迎您！");
								}
							},"json");
		}
	}
</script>
</head>
	<!-- 消息框集合 -->

	<!-- 注册框 -->
	<div id="regwindow" tabindex="-1" role="dialog" data-hasfoot="false"
		class="sui-modal hide fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-hidden="true"
						class="sui-close">×</button>
					<h4 class="modal-title">注册用户</h4>
				</div>
				<div class="modal-body">
					<div class="sui-form form-horizontal sui-validate">
						<div class="control-group">
							<label for="reg_un" class="control-label">账户名：</label>
							<div class="controls">
								<input type="text" class="input-xfat" id="reg_un"
									data-rules="required">
							</div>
						</div>
						<div class="control-group">
							<label for="reg_pass" class="control-label">密码：</label>
							<div class="controls">
								<input type="password" class="input-xfat" id="reg_pass"
									data-rules="required">
							</div>
						</div>
						<div class="control-group">
							<label for="reg_pass_confirm" class="control-label">确认密码：</label>
							<div class="controls">
								<input type="password" class="input-xfat" id="reg_pass_confirm"
									data-rules="required">
								<div class="sui-msg msg-tips msg-naked">
									<div class="msg-con">两次密码必须相同</div>
									<s class="msg-icon"></s>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label for="reg_sex" class="control-label ">性别：</label>
							<div class="controls">
								<input type="radio" name="reg_sex" value="男" id="reg_sex">男
								<input type="radio" name="reg_sex" id="reg_sex" value="女">女
							</div>
						</div>
						<div class="control-group">
							<label for="reg_address" class="control-label">地址：</label>
							<div class="controls">
								<input class="input-xfat" type="text" id="reg_address">
							</div>
						</div>
						<div class="control-group">
							<label for="reg_email" class="control-label">Email：</label>
							<div class="controls">
								<input type="email" class="input-xfat" id="reg_email">
							</div>
						</div>
						<div class="control-group">
							<label for="reg_tel" class="control-label">电话：</label>
							<div class="controls">
								<input type="tel" class="input-xfat" id="reg_tel">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"
						onclick="register('${ pageContext.request.contextPath}')"
						class="sui-btn btn-primary btn-large">注册</button>
					<button type="button" data-dismiss="modal"
						class="sui-btn btn-default btn-large">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 错误提示 -->
	<div id="errmessage" tabindex="-1" role="dialog" data-hasfoot="false"
		class="sui-modal hide fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-hidden="true"
						class="sui-close">×</button>
					<h4 id="messagetittle" class="modal-title">失败提示</h4>
				</div>
				<div class="modal-body sui-text-xxlarge" id="messagebody">失败，请检查</div>
				<div class="modal-footer">
					<button type="button" data-ok="modal"
						class="sui-btn btn-primary btn-large">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 消息框结束 -->

	<div class="sui-container">
		<!-- 导航条 -->
		<div class="sui-row-fluid">
			<div class="span12">
				<div class="sui-navbar">
					<div class="navbar-inner">
						<a href="${ pageContext.request.contextPath }/index.action"
							class="sui-brand">
							<div class="sui-loading loading-xxsmall loading-inline">
								<i class="sui-icon icon-pc-loading"></i>
							</div>XBlog
						</a>
						<ul class="sui-nav">
							<li><a
								href="${ pageContext.request.contextPath }/own/center.action">个人中心</a></li>
							<li><a
								href="${ pageContext.request.contextPath }/own/message.action">消息中心</a></li>
							<li><a
								href="${ pageContext.request.contextPath }/own/piccontrol.action">图片管理</a></li>
							<li class="sui-dropdown"><a href="javascript:void(0);"
								data-toggle="dropdown" class="dropdown-toggle">快捷链接</a>
								<ul role="menu" class="sui-dropdown-menu">
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="https://www.csdn.net/">CSDN</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="https://www.baidu.com/">百度</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="https://www.google.com/">GOOGLE(科学)</a></li>
								</ul></li>
						</ul>
						<!-- id方便登陆后局部刷新 -->
						<c:if test="${ empty sessionScope._LOGIN_USER_ }">
							<div id="logincontrol" class="sui-form sui-form pull-right">
								用户名：<input style="height: 20px" type="text" name="un" id="un" data-rules="required">
								密 码：<input style="height: 20px" type="password" name="pwd" id="pwd"
									data-rules="required">
								<button class="sui-btn"
									onclick="login('${ pageContext.request.contextPath }')">
									<i class="sui-icon icon-touch-user3"></i>登陆
								</button>
								<button class="sui-btn" data-toggle="modal"
									data-target="#regwindow">
									<i class="sui-icon icon-tb-share"></i>注册账户
								</button>
							</div>
						</c:if>
						<c:if test="${ ! empty sessionScope._LOGIN_USER_ }">
							<div id="logincontrol" class="sui-form sui-form pull-right">
								<button id="user" class="sui-btn btn-success">
									<i class="sui-icon icon-tb-emoji"></i>用户 [ ${ sessionScope._LOGIN_USER_ }
									],欢迎您！
								</button>
								<a class="sui-btn btn-success" href="${ pageContext.request.contextPath }/own/newblog.action">
									<i class="sui-icon icon-touch-edit"></i>写文章
								</a>
								<button class="sui-btn btn-success"
									onclick="logout('${ pageContext.request.contextPath }')">
									<i class="sui-icon icon-tb-close"></i>退出
								</button>
							</div>
						</c:if>
						<form class="sui-form sui-form"
							action="${ pageContext.request.contextPath }/search.action"
							method="post">
							<input type="text" style="height: 20px" name="kwd" placeholder="标签/文章标题">
							<button class="sui-btn">搜索</button>
						</form>


					</div>
				</div>
			</div>
		</div>
		<!-- end of 导航条 -->
</div>
	<!-- 下面的东西在每个页的页内  -->