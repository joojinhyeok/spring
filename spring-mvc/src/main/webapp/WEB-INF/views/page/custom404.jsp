<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>페이지를 찾을 수 없습니다</title>
</head>
<body>
<h2>🚫 요청하신 페이지를 찾을 수 없습니다 (404)</h2>

<c:if test="${not empty uri}">
    <p>요청된 URI: <strong>${uri}</strong></p>
</c:if>

<p>요청하신 페이지가 존재하지 않거나, 주소가 변경되었을 수 있습니다.</p>
<a href="<c:url value='/' />">홈으로 돌아가기</a>
</body>
</html>