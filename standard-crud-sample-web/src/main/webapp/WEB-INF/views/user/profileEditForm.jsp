<jsp:include page="inc/legend.jsp"/>

<t:messagesPanel/>

<c:url value="/profile" var="editUrl"/>
<form:form action="${editUrl}" cssClass="form-horizontal" modelAttribute="profileForm">

    <jsp:include page="inc/profileForm.jsp">
        <jsp:param name="operation" value="UPDATE"/>
    </jsp:include>

    <form:hidden path="version"/>
    <form:hidden path="credentialVersion"/>
    <form:hidden path="userUuid"/>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="confirm">
                <span class="glyphicon glyphicon-ok"></span> 入力完了</form:button>
            <a href="<c:url value="/profile?editForm"/>" class="btn btn-default">
                <span class="glyphicon glyphicon-refresh"></span> 元に戻す</a>
        </div>
    </div>

</form:form>

<a href="<c:url value="/"/>" class="btn btn-default">
    <span class="glyphicon glyphicon-step-backward"></span>
    <spring:message code="title.welcome.home"/></a>
