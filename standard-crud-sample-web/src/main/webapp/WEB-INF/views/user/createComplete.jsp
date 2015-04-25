<spring:message code="i.sc.um.1004" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<ul class="list-inline">
    <li>
        <a href="<c:url value="/users?createForm&${f:query(_flow.asIdMap())}"/>">
            <span class="glyphicon glyphicon-plus"></span>
            <spring:message code="title.user.createForm"/></a>
    </li>
    <li>
        <a href="<c:url value="${_flow.finishPath}" />">
            <span class="glyphicon glyphicon-backward"></span> 戻る</a>
        </a>
    </li>
</ul>

