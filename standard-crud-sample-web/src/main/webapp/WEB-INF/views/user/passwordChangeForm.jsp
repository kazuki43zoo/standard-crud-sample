
<jsp:include page="parts/legend.jsp"/>

<c:choose>
    <c:when test="${param.containsKey('encourage')}">
        <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).PASSWORD_CHANGE_ENCOURAGE" var="messageEnum"/>
        <spring:message code="${messageEnum.code}" var="message"/>
        <t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>
    </c:when>
</c:choose>

<t:messagesPanel/>

<c:url value="/password" var="changeUrl"/>
<form:form action="${changeUrl}" cssClass="form-horizontal" modelAttribute="passwordForm">

    <div class="form-group">
        <form:label path="userId" cssClass="col-sm-3 control-label required">ユーザーID</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="userId"/>
            <form:errors cssClass="control-label" path="userId"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="currentPassword" cssClass="col-sm-3 control-label required">現在のパスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="currentPassword" showPassword="true"/>
            <form:errors cssClass="control-label" path="currentPassword"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="password" cssClass="col-sm-3 control-label required">新しいパスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="password" showPassword="true"/>
            <form:errors cssClass="control-label" path="password"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="confirmPassword" cssClass="col-sm-3 control-label required">確認パスワード</form:label>
        <div class="col-sm-4">
            <form:password cssClass="form-control" path="confirmPassword"/>
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

<c:if test="${not param.containsKey('encourage')}">
    <a href="<c:url value="/login"/>"><span class="glyphicon glyphicon-log-in"></span> ログインフォームへ</a>
</c:if>
