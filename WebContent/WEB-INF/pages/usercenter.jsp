<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span3"></div>
		<div class="span6 sui-form ">
			<div class="control-group">
				<label for="username" class="control-label">用户名:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="text" id="username" name="username" disabled="disabled" value="${ user.username }">
				</div>
			</div>
			<div class="control-group">
				<label for="sex" class="control-label">性别:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="text" id="sex" name="sex" disabled="disabled" value="${ user.sex }">
				</div>
			</div>
			<div class="control-group">
				<label for="pass" class="control-label">密码:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="password" id="pass" name="pass" placeholder="密码">
				</div>
			</div>
			<div class="control-group">
				<label for="passconi" class="control-label">确认密码:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="password" id="passconi" name="passconi" placeholder="确认密码">
				</div>
			</div>
			<div class="control-group">
				<label for="address" class="control-label">地址:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="text" id="address" name="address" placeholder="地址" value="${ user.address }">
				</div>
			</div>
			<div class="control-group">
				<label for="tel" class="control-label">电话:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="tel" id="tel" name="tel" placeholder="电话" value="${ user.tel }">
				</div>
			</div>
			<div class="control-group">
				<label for="Email" class="control-label">Email:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="text" id="Email" name="Email" placeholder="Eamil" value="${ user.email }">
				</div>
			</div>
			<div class="control-group">
				<label for="regdate" class="control-label">注册日期:</label>
				<div class="controls">
					<input class="input-xfat input-block-level" type="text" id="regdate" name="regdate" disabled="disabled" value="${ user.registerdate }">
				</div>
			</div>
			<button type="button" class="sui-btn btn-success btn-xlarge" onclick="updatepro('${ pageContext.request.contextPath}')">更新信息</button>
		</div>
		
		<div class="span3"></div>
	</div>
</div>
<script type="text/javascript">
	function updatepro(url){
		let pass = $("#pass").val();
		let pass2 = $("#passconi").val();
		let address = $("#address").val();
		let email = $("#Email").val();
		let tel = $("#tel").val();
		if(pass.trim()==""||pass2.trim()==""||address.trim()==""||email.trim()==""||tel.trim()==""){
			$("#messagetittle").text("填写错误！！");
			$("#messagebody").text("您必须完全填写所有项才可以修改！");
			$("#errmessage").modal('show');
		}
		else if(pass.trim()!=pass2.trim()){
			$("#messagetittle").text("请检查！！");
			$("#messagebody").text("两次输入的密码不一致");
			$("#errmessage").modal('show');
		}
		else{
			$.post(url+"/own/updateprofile.action",{
				pass:pass,
				address:address,
				email:email,
				tel:tel
			},function(data){
				if(data.result){
					$("#messagetittle").text("成功提示！！");
					$("#messagebody").text("更新用户信息成功！");
					$("#errmessage").modal('show');
				}
			})
		}
	}
</script>
<%@include file="footer.jsp"%>