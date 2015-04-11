<c:url value="/users/${user.userUuid}" var="updateUrl"/>
<form:form action="${updateUrl}" cssClass="form-horizontal" modelAttribute="userForm">

    <jsp:include page="inc/userConfirm.jsp"/>

    <div class="form-group">
        <form:label path="status" cssClass="col-sm-3 control-label">ステータス</form:label>
        <div class="col-sm-8">
            <span class="form-control">${f:h(CL_USERSTATUS[userForm.status.name()])}</span>
            <form:hidden path="status"/>
        </div>
    </div>

    <form:hidden path="confirmPassword"/>
    <form:hidden path="password"/>
    <form:hidden path="version"/>
    <form:hidden path="credentialVersion"/>
    <form:hidden path="userUuid"/>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="updateRedo">
                <span class="glyphicon glyphicon-backward"></span> 入力し直す</form:button>
            <form:button class="btn btn-default" name="update">
                <span class="glyphicon glyphicon-save"></span> 更新</form:button>
        </div>
    </div>

</form:form>
