<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>josn 测试</title>
<script  src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
//请求json 输出json
function requestJson(){
	 $.ajax({
		 type:"post",
		 url:'${pageContext.request.contextPath}/requestJson.action',
		 contentType:'application/json;charset=utf-8',
		 data:'{"name":"手机","price":"998"}',
		 success:function(result){
			 alert(result);
			 alert(result.name);
		 }
	 });
 }
 //请求key/value  输出 json
 function responseJson(){
	 $.ajax({
		 type:"post",
		 url:'${pageContext.request.contextPath}/responseJson.action',
		 //contentType:'application/json;charset=utf-8',//key-value 默认类型
		 data:'name=手机&price=999',
		 success:function(result){
			// alert(result);
			alert(result.name);
		 }
	 });
 }
</script>
</head>

<body>
<input type="button" onclick="requestJson()" value="请求json 输出json"/>
<input type="button" onclick="responseJson()" value="请求key/value  输出 json"/>
</body>
</html>