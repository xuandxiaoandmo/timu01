<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- UserAction --- register -->
	<form action="${pageContext.request.contextPath }/user_register" method="post">
		用户名： <input type="text" name="user_code"/><br>
		用户密码： <input type="password" name="user_password"/><br>
		用户昵称： <input type="text" name="user_name"/><br>
		<input type="submit"  value="注册"/>
	</form>

</body>
</html>