<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width"/>
    <meta name="contextPath" content="${pageContext.request.contextPath}"/>
    <sec:csrfMetaTags/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        var contextPath = $("meta[name='contextPath']").attr("content");
    </script>

    <c:set var="titleKey" scope="request">
        <tiles:insertAttribute name="title" ignore="true"/>
    </c:set>
    <title><spring:message code="${titleKey}"
                           text="standard-crud-sample"/></title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet"
          href="<c:url value="/resources/app/css/styles.css"/>">
</head>
<body>

<div class="container">

    <header class="navbar navbar-default navbar-fixed-top">
        <tiles:insertAttribute name="header"/>
    </header>

    <div id="wrapper">
        <tiles:insertAttribute name="body"/>
    </div>

    <footer>
        <tiles:insertAttribute name="footer"/>
    </footer>

</div>

<jsp:include page="inc/logoutDialog.jsp"/>

</body>
</html>