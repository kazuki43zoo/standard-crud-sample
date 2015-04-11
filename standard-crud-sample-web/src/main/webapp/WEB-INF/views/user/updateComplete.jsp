<spring:message code="i.sc.um.1006" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<a href="<c:url value="/users?${f:h(backwardQueryString)}"/>">
    <span class="glyphicon glyphicon-list"></span>
    <spring:message code="title.user.searchResult"/></a>
