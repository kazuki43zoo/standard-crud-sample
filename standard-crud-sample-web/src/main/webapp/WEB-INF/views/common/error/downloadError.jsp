<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Download Error!</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
<div id="wrapper">
    <h1>Download Error!</h1>

    <div class="error">
        <c:if test="${!empty exceptionCode}">[${f:h(exceptionCode)}]</c:if>
        <spring:message code="e.sc.fw.7005"/>
        <t:messagesPanel/>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>
</body>
</html>