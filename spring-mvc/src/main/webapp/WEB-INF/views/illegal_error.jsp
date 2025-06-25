<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>오류</title>
</head>
<body>
<h2>🚫 예외가 발생했습니다</h2>

<p><strong>오류 메시지:</strong></p>
<p style="color: red;">
  <c:out value="${errorMessage}" default="알 수 없는 오류가 발생했습니다."/>
</p>

<p>
  입력 값을 다시 확인하고 시도해 주세요.
</p>

<a href="<c:url value='/' />">홈으로 돌아가기</a>
</body>
</html>