<c:url value="/users" var="creatingUrl"/>
<form:form action="${creatingUrl}" cssClass="form-horizontal" modelAttribute="userForm">

    <jsp:include page="inc/confirm.jsp"/>

    <form:hidden path="confirmationPassword"/>
    <form:hidden path="password"/>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="createRedo">
                <span class="glyphicon glyphicon-backward"></span> 入力し直す</form:button>
            <form:button class="btn btn-default">
                <span class="glyphicon glyphicon-save"></span> 登録</form:button>
        </div>
    </div>

</form:form>
