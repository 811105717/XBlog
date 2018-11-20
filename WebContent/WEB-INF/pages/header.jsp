<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>

<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css"
	rel="stylesheet">
<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui-append.min.css" rel="stylesheet">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript"
	src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script>
	<!-- 处理登陆及登陆后的一些操作！ -->
	<script>
		function login(basepath){
			var un = $("#un").val();
			var pwd = $("#pwd").val();
			if(un.trim()==""||pwd.trim()==""){
				$("#messagetittle").text("非法输入！！");
				$("#messagebody").text("您必须将用户名或密码填写完整才能登陆！！");
				$("#errmessage").modal('show');
				return ;
			}
			$.post(basepath+"/login.action",{un:un,pwd:pwd},function(data){
					if(data.result){
						var inner = "<button  id=\"user\" class=\"sui-btn btn-success\"></button>";
						var ico ="<i class=\"sui-icon icon-tb-emoji\"></i>"
						$("#logincontrol").html(inner);
						$("#user").html(ico+data.un+" ，欢迎您！");
					}
					else{
						$("#messagetittle").text("登陆失败提示！");
						$("#messagebody").text("登陆失败，用户名或密码错误！");
						$("#errmessage").modal('show');
					}
				});
		}
		
		function register(basepath){
		
				var username = $("#reg_un").val();
				var password = $("#reg_pass").val();
				var conf = $("#reg_pass_confirm").val();
				var sex = $("#reg_sex").val();
				var address= $("#reg_address").val();
				var email = $("#reg_email").val();
				var tel = $("#reg_tel").val();
				if(username.trim()==""||password.trim()==""||conf.trim()==""){
					$("#messagetittle").text("非法输入！！");
					$("#messagebody").text("请完善您的信息！！");
					$("#errmessage").modal('show');
					return false;
				}
				else if (password.trim()!=conf.trim()){
					$("#messagetittle").text("请检查！！");
					$("#messagebody").text("两次输入的密码不一致");
					$("#errmessage").modal('show');
					return false;
				}
				else{
					$.post(basepath+"/register.action",
						{username:username,password:password,sex:sex,address:address,email:email,tel:tel},
						function(data){
							if(data.result){
								$("#regwindow").modal('hide');
								var inner = "<button  id=\"user\" class=\"sui-btn btn-success\"></button>";
								var ico ="<i class=\"sui-icon icon-tb-emoji\"></i>"
								$("#logincontrol").html(inner);
								$("#user").html(ico+data.un+" ，欢迎您！");
							}
						});
				}
		}
	</script>
</head>
<body>
<!-- 消息框集合 -->

<!-- 注册框 -->
<div id="regwindow" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×</button>
        <h4 class="modal-title">注册用户</h4>
      </div>
      <div class="modal-body">
      	<div class="sui-form sui-form">
      		&emsp;账户名：<input type="text" class="input-xfat" id="reg_un" required="required"><br><br>
      		密&emsp;&emsp;码：<input type="password" class="input-xfat"  id="reg_pass" required="required"><br><br>
      		确认密码：<input type="password" class="input-xfat"  id="reg_pass_confirm" required="required"><br><br>
      		性&emsp;&emsp;别：<input type="radio" class="input-xfat"  name="reg_sex" value="男" id="reg_sex">男 
      				<input type="radio" name="reg_sex" id="reg_sex" value="女">女<br><br>
      		地&emsp;&emsp;址：<input class="input-xfat"  type="text" id="reg_address"><br><br>
      		 &emsp;Email:&emsp;<input type="email" class="input-xfat" id="reg_email"><br><br>
      		电&emsp;&emsp;话：<input type="tel"  class="input-xfat" id="reg_tel"><br><br>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" onclick="register('${ pageContext.request.contextPath}')" class="sui-btn btn-primary btn-large">注册</button>
        <button type="button" data-dismiss="modal" class="sui-btn btn-default btn-large">取消</button>
      </div>
    </div>
  </div>
</div>
<!-- 错误提示 -->
<div id="errmessage" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×</button>
        <h4 id="messagetittle" class="modal-title">失败提示</h4>
      </div>
      <div class="modal-body sui-text-xxlarge" id="messagebody">失败，请检查</div>
      <div class="modal-footer">
        <button type="button" data-ok="modal" class="sui-btn btn-primary btn-large">确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 消息框结束 -->

	<div class="sui-container">
		<!-- 导航条 -->
		<div class="sui-row">
			<div class="span8">
				<div class="sui-navbar navbar-fixed-top">
					<div class="navbar-inner">
						<a href="${ pageContext.request.contextPath }/index.action"
							class="sui-brand">XBlog</a>
						<ul class="sui-nav">
							<li><a
								href="${ pageContext.request.contextPath }/own/center.action">个人中心</a></li>
							<li><a
								href="${ pageContext.request.contextPath }/own/message.action">消息中心</a></li>
							<li class="sui-dropdown"><a href="javascript:void(0);"
								data-toggle="dropdown" class="dropdown-toggle">快捷链接<i
									class="caret"></i></a>
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
							<div id="logincontrol" class="sui-form sui-form pull-right">
								用户名：<input type="text" name="un" id="un">
								密  码：<input type="password" name="pwd" id="pwd">
								<button class="sui-btn" onclick="login('${ pageContext.request.contextPath }')"><i class="sui-icon icon-touch-user3"></i>登陆</button>
								<button class="sui-btn" data-toggle="modal" data-target="#regwindow"><i class="sui-icon icon-tb-share"></i>注册账户</button>
							</div>
						<form class="sui-form sui-form"
							action="${ pageContext.request.contextPath }/search.action"
							method="post">
							<input type="text" name="kwd" placeholder="标签/文章标题">
							<button class="sui-btn">搜索</button>
						</form>
						
							
					</div>
				</div>
			</div>
		</div>
		<!-- end of 导航条 -->
	</div>
	<!-- 下面的东西在每个页的页内  -->