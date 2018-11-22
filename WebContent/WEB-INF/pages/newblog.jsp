<%@ page pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="sui-container">
	<div class="sui-row-fuild">
		<div class="span8">
			<!-- 开始 -->
			<%@include file="textedit.jsp" %>
			<!-- 结束 -->
		</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div class="sui-row-fuild">
		<div class="span2">
		<button class="sui-btn" onclick="getContent()">sdfdsf</button>
		</div>
	</div>
</div>
<script type="text/javascript">
function getContent() {
    alert($editor.editor('getContent'))
}
</script>
<%@include file="footer.jsp"%>