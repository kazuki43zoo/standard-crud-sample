<div id="wrapper">

    <h1><spring:message code="${titleKey}"/></h1>

    <t:messagesPanel/>

    <c:url value="/users/${user.userUuid}" var="updateUrl"/>
    <form:form action="${updateUrl}" cssClass="form-horizontal" modelAttribute="userForm">

        <jsp:include page="inc/form.jsp"/>

        <div class="form-group">
            <form:label path="status" cssClass="col-sm-2 control-label">ステータス</form:label>
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
            <div class="col-sm-offset-2 col-sm-8">
                <form:button class="btn btn-default" name="updateConfirm">
                    <span class="glyphicon glyphicon-ok"></span> 入力完了</form:button>
                <a href="<c:url value="/users/${user.userUuid}?updateForm"/>" class="btn btn-default">
                    <span class="glyphicon glyphicon-refresh"></span> 最新化</a>
            </div>
        </div>

    </form:form>

    <a href="<c:url value="/users?searchResult"/>" class="btn btn-default">
        <span class="glyphicon glyphicon-step-backward"></span>
        <spring:message code="title.user.searchResult"/></a>

</div>
