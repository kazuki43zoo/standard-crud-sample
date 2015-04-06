<c:url value="/profile" var="editUrl"/>
<form:form action="${editUrl}" cssClass="form-horizontal" modelAttribute="profileForm">

    <c:set var="userForm" value="${profileForm}" scope="request"/>
    <jsp:include page="inc/profileConfirm.jsp"/>

    <form:hidden path="confirmPassword"/>
    <form:hidden path="password"/>
    <form:hidden path="version"/>
    <form:hidden path="credentialVersion"/>
    <form:hidden path="userUuid"/>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="editRedo">
                <span class="glyphicon glyphicon-backward"></span> 入力し直す</form:button>
            <form:button class="btn btn-default" name="_method" value="PUT">
                <span class="glyphicon glyphicon-save"></span> 更新</form:button>
        </div>
    </div>

</form:form>
