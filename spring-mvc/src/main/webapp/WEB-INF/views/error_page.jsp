<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>오류 발생</title>
</head>
<body>
<h2>⚠ 예외가 발생했습니다</h2>
<p><strong>예외 메시지:</strong> ${exception.message}</p>

<c:if test="${not empty exception}">
    <h4>자세한 내용 (StackTrace):</h4>
    <pre>
            <c:forEach var="stack" items="${exception.stackTrace}">
                ${stack}<br/>
            </c:forEach>
        </pre>
</c:if>

<a href="<c:url value='/' />">홈으로 돌아가기</a>
</body>
</html>