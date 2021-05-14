<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>サニタイズサンプル</title>
</head>
<body>

<h1>サニタイズサンプル</h1>



	<form action="sanitize.jsp" method="POST">
        <pre><input type="text" name="input"></pre>
        <button type="submit">送信</button>
        <c:remove var="input" scope="request" />
    </form>

	<c:out value="<aa>b%'''' c" escapeXml="False "  />
	<c:out value="<aa>b%'''' c" escapeXml="False "  />


</body>
</html>