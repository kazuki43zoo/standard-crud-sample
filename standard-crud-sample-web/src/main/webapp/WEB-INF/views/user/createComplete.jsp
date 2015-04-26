<spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).USER_WAS_ADDED" var="messageEnum"/>
<spring:message code="${messageEnum.code}" var="message"/>
<t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>

<ul class="list-inline">
    <li>
        <a href="<c:url value="${_flow.finishPath}" />">
            <span class="glyphicon glyphicon-backward"></span> 戻る</a>
        </a>
    </li>
    <li>
        <a href="<c:url value="/users?createForm&${f:query(_flow.asIdMap())}"/>">
            <span class="glyphicon glyphicon-plus"></span>
            <spring:message code="title.user.createForm"/></a>
    </li>
</ul>

