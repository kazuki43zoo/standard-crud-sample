<spring:message code="i.sc.um.1004" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<ul class="list-inline">
    <li>
        <a href="<c:url value="/users?createForm"/>">
            <span class="glyphicon glyphicon-plus"></span>
            <spring:message code="title.user.createForm"/></a>
    </li>
    <li>
        <a href="<c:url value="/users?${f:h(backwardQueryString)}" />">
            <span class="glyphicon glyphicon-list"></span>
            <spring:message code="title.user.searchResult"/>
        </a>
    </li>
</ul>

