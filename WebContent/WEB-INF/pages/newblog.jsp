<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<div class="sui-container">
		<div class="sui-row-fuild">
			<div class="span12 sui-form">
				<span class="sui-text-xxlarge">文章标题：</span><input style="width: 1024px;height: 50px" type="text" id="tittle" class="sui-form sui-text-xxlarge">
			</div>
		</div>
	</div>
	<div class="sui-row-fuild">
		<div class="span8">
			<!-- 开始 -->
			<%@include file="textedit.jsp" %>
			<!-- 结束 -->
		</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div class="sui-row-fuild">
		<div class="span8 sui-form">
		Tag标签：
			<select id="tag">
				<option value="">请选择tag</option>
				<c:forEach items="${ tags }" var="ts">
					<option value="${ ts.id }">${ ts.tag }</option>
				</c:forEach>
			</select>
		<button class="sui-btn btn-bordered btn-xlarge btn-success" onclick="getContent('${ pageContext.request.contextPath }')">发表新文章</button>
		</div>
	</div>
</div>
<script type="text/javascript">
function getContent(url) {
	 let text = $editor.editor('getContent');
	 let tag = $("#tag").val();
	 let tittle = $("#tittle").val();
    if(text.trim()==""||tittle.trim()==""||tag.trim()==""){
    	$("#messagetittle").text("非法内容提示");
    	$("#messagebody").text("您输入的内容有缺失！请检查标题 文章和标签！，然后再提交！！");
    	$("#errmessage").modal('show');
    	return ;
    }
    else{
    	$.post(url+"/own/addblog.action",
    			{text:text,
    				tittle:tittle,
    				tag:tag
    			},function(data){
    		if(data.result){
    			window.location.href=url+"/blog.action?id="+data.id; //定位到发布后的文章
    		}
    		else{
    			$("#messagetittle").text("发布失败");
    	    	$("#messagebody").text("可能您已经掉线或账户存在异常，请刷新重试");
    	    	$("#errmessage").modal('show');
    		}
    	},"json");
    }
}
</script>
<%@include file="footer.jsp"%>