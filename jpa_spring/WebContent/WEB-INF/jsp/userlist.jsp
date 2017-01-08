<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user list</title>
</head>
<body>
<c:forEach items="${userList }" var="user">
	<p><c:out value="${user.id }"></c:out></p>
	<p><c:out value="${user.age }"></c:out></p>
	<p>${user.name }</p>
</c:forEach>
</body>
</html>