<t:messagesPanel/>
<spring:message code="i.sc.um.1010" var="message"/>
<t:messagesPanel messagesType="info" messagesAttributeName="message"/>

<c:url value="/password" var="changeUrl"/>
<form:form action="${changeUrl}" cssClass="form-horizontal" modelAttribute="passwordForm">

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
        <form:label path="currentPassword" cssClass="col-sm-3 control-label">現在のパスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="currentPassword" showPassword="true"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="currentPassword"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="password" cssClass="col-sm-3 control-label">新しいパスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="password" showPassword="true"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="password"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="confirmPassword" cssClass="col-sm-3 control-label">確認パスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="confirmPassword"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="confirmPassword"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default">
                <span class="glyphicon glyphicon-save"></span> 変更</form:button>
        </div>
    </div>

</form:form>

