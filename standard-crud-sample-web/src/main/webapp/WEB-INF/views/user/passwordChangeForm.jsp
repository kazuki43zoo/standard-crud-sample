<t:messagesPanel/>

<c:url value="/password" var="changeUrl"/>
<form:form action="${changeUrl}" cssClass="form-horizontal" modelAttribute="passwordChangeForm">

    <div class="form-group">
        <form:label path="userId" cssClass="col-sm-3 control-label">ユーザーID</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="userId"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="userId"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="password" cssClass="col-sm-3 control-label">現在のパスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="password" showPassword="true"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="password"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="newPassword" cssClass="col-sm-3 control-label">新しいパスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="newPassword" showPassword="true"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="newPassword"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="confirmNewPassword" cssClass="col-sm-3 control-label">確認パスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="confirmNewPassword"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="confirmNewPassword"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default">
                <span class="glyphicon glyphicon-save"></span> 変更</form:button>
        </div>
    </div>

</form:form>

