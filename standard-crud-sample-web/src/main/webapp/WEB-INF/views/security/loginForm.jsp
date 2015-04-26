<div id="wrapper">

    <c:choose>
        <c:when test="${param.containsKey('encourage')}">
            <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).LOGIN_ENCOURAGE" var="messageEnum"/>
            <spring:message code="${messageEnum.code}" var="message"/>
            <t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('logoutSuccess')}">
            <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).USER_WAS_LOGOUT" var="messageEnum"/>
            <spring:message code="${messageEnum.code}" var="message"/>
            <t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('invalidSession')}">
            <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).VALID_SESSION_NOT_EXIST" var="messageEnum"/>
            <spring:message code="${messageEnum.code}" var="message"/>
            <t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('deleted')}">
            <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).ACCOUNT_WAS_DELETED" var="messageEnum"/>
            <spring:message code="${messageEnum.code}" var="message"/>
            <t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('error')}">
            <t:messagesPanel messagesType="danger" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION"/>
        </c:when>
        <c:when test="${param.containsKey('systemError')}">
            <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).SYSTEM_ERROR" var="messageEnum"/>
            <spring:message code="${messageEnum.code}" arguments="${requestScope['X-Track']}" var="message"/>
            <t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>
        </c:when>
        <c:otherwise>
            <t:messagesPanel/>
        </c:otherwise>
    </c:choose>

    <c:url value="/login" var="loginUrl"/>
    <form:form action="${loginUrl}" cssClass="form-horizontal" modelAttribute="loginForm">
        <div class="form-group">
            <form:label path="userId" cssClass="col-sm-3 control-label">ユーザーID</form:label>

            <div class="col-sm-4">
                <form:input path="userId" cssClass="form-control"/>
                <form:errors path="userId"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="password" cssClass="col-sm-3 control-label">パスワード</form:label>

            <div class="col-sm-4">
                <form:password path="password" cssClass="form-control"/>
                <form:errors path="password"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-4">
                <form:button class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> ログイン</form:button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-4">
                <a href="<c:url value="/password"/>"><span class="glyphicon glyphicon-edit"></span> パスワード変更フォームへ</a>
            </div>
        </div>
        <sec:csrfInput/>
    </form:form>


</div>
