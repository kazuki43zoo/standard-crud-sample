<div id="wrapper">

    <c:choose>
        <c:when test="${param.containsKey('encourage')}">
            <spring:message code="i.sc.se.1002" var="message"/>
            <t:messagesPanel messagesType="info" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('logoutSuccess')}">
            <spring:message code="i.sc.se.1001" var="message"/>
            <t:messagesPanel messagesType="success" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('invalidSession')}">
            <spring:message code="w.sc.se.2000" var="message"/>
            <t:messagesPanel messagesType="warning" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('deleted')}">
            <spring:message code="i.sc.se.8003" var="message"/>
            <t:messagesPanel messagesType="danger" messagesAttributeName="message"/>
        </c:when>
        <c:when test="${param.containsKey('error')}">
            <t:messagesPanel messagesType="danger" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION"/>
        </c:when>
        <c:when test="${param.containsKey('systemError')}">
            <spring:message code="e.sc.fw.9001" arguments="${requestScope['X-Track']}" var="message"/>
            <t:messagesPanel messagesType="danger" messagesAttributeName="message"/>
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
            </div>
            <div class="col-sm-4">
                <form:errors path="userId"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="password" cssClass="col-sm-3 control-label">パスワード</form:label>

            <div class="col-sm-4">
                <form:password path="password" cssClass="form-control"/>
            </div>
            <div class="col-sm-4">
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
