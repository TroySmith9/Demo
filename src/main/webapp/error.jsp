<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常信息</title>
<style type="text/css">
	/*解决调整窗口宽度小于1002像素时图片没有居中的问题*/
	body{min-width:1002px;background:#f2f2f2;} /*firefox*/
	#header{height:45px;position:relative;z-index:100;}
	.minWidthDiv{width:1002px;margin:0 auto;min-height:700px;} /*IE6.0*/
	#footer{height:45px;position:relative;z-index:100;}
	span.error {color:#F00;background-color:#F2F2F2;overflow:hidden;width:430px;display:block;cursor:default;}
</style>
<script type="text/javascript">
	function checkShowErrorDetailInfo(){
		if (errorInfo.style.display == 'none') {
			errorInfo.style.display = 'inline';
		} else {
			errorInfo.style.display = 'none';
		}
	}
</script>
</head>
<body>
	<div id="header">
	<!-- 页头 -->	
	</div>

	<!-- 正文 -->
	<div id="content" class="minWidthDiv">
		<%
			StringBuffer errorDetailInfo = new StringBuffer();
			if (request.getAttribute("javax.servlet.error.request_uri") != null) {
				errorDetailInfo.append("\n当前操作URL：[" + request.getAttribute("javax.servlet.error.request_uri") + "]\n\n");
			}
			if (exception != null) {
				errorDetailInfo.append("错误信息：<span class='error' ondblclick='checkShowErrorDetailInfo();'>").append(exception.getMessage()).append("</span>\n");
				ByteArrayOutputStream byteOStream = new ByteArrayOutputStream();
				exception.printStackTrace(new PrintStream(byteOStream));
				errorDetailInfo.append("<span id='errorInfo' style='display:none;'>发生错误，具体信息如下：\n").append(byteOStream);
			}
			
			/*if (errorDetailInfo.length() > 3800) {
				errorDetailInfo.delete(3800, errorDetailInfo.length()).append("</span>");
			}*/
			int index = errorDetailInfo.lastIndexOf("; SQL state ");
			if(index > 0){
				errorDetailInfo.replace(index, index+12, "; \nSQL state ");
			}
			errorDetailInfo.append("</span>");
		%>
		
		<pre class="error_1">
		   <b>服务器内部异常</b>
			<span>
			 Web 服务器不能执行此请求。请稍后重试此请求。
			 如果问题依然存在，请与Web服务器的管理员联系。
			</span>
		</pre>
		<table class="table_1" width="92%" align="center" border="0" cellspacing="0" cellpadding="3" style="table-layout:fixed;word-break:break-all;">
		  <tbody style="display:inline;">
		  <tr>
			<td valign="top">
			  <pre style="font-size:12px;width:100%;overflow:visible">
				<%
				if (errorDetailInfo != null) {
					out.print(errorDetailInfo.toString().replaceAll("h3>", "h4>"));
				}
				%>
			  </pre>
			</td>
		  </tr>
		  </tbody>
		</table>
		<p align="center">
			<a href="javascript:history.back();">返回</a>
		</p>
	</div>
	<div id="footer">
	<!-- 页脚 -->
	</div>
</body>
</html>