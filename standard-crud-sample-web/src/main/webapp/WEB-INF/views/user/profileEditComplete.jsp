<spring:message code="i.sc.um.1009" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<ul class="list-inline">
    <li>
        <a href="<c:url value="${_flow.finishPath}"/>">
            <span class="glyphicon glyphicon-home"></span>
            <spring:message code="title.welcome.home"/>
        </a>
    </li>
</ul>