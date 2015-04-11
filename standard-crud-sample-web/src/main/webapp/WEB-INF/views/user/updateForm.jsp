<jsp:include page="inc/legend.jsp"/>

<t:messagesPanel/>

<c:url value="/users/${user.userUuid}" var="updateUrl"/>
<form:form action="${updateUrl}" cssClass="form-horizontal" modelAttribute="userForm">

    <jsp:include page="inc/userForm.jsp">
        <jsp:param name="operation" value="UPDATE"/>
    </jsp:include>

    <div class="form-group">
        <form:label path="status" cssClass="col-sm-3 control-label required">ステータス</form:label>
        <div class="col-sm-4">
            <form:select path="status" cssClass="form-control" items="${CL_USERSTATUS}"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="status"/>
        </div>
    </div>

    <form:hidden path="version"/>
    <form:hidden path="credentialVersion"/>
    <form:hidden path="userUuid"/>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="updateConfirm">
                <span class="glyphicon glyphicon-ok"></span> 入力完了</form:button>
            <a href="<c:url value="/users/${user.userUuid}?updateForm&backwardQueryString=${f:h(f:u(backwardQueryString))}"/>"
               class="btn btn-default">
                <span class="glyphicon glyphicon-refresh"></span> 最新化
            </a>
        </div>
    </div>

</form:form>

<a href="<c:url value="/users?${f:h(backwardQueryString)}"/>">
    <span class="glyphicon glyphicon-list"></span>
    <spring:message code="title.user.searchResult"/></a>
