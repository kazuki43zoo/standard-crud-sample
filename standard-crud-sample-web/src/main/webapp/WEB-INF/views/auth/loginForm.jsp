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
        <c:when test="${param.containsKey('error')}">
            <t:messagesPanel messagesType="danger" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION"/>
        </c:when>
    </c:choose>
    <t:messagesPanel/>

    <c:url value="/authenticate" var="searchUrl"/>
    <form action="${searchUrl}" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="username" class="col-sm-3 control-label">ユーザーID</label>

            <div class="col-sm-4">
                <input class="form-control" id="username" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-3 control-label">パスワード</label>

            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-4">
                <button class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span>ログイン</button>
            </div>
        </div>
        <sec:csrfInput/>
    </form>

</div>
