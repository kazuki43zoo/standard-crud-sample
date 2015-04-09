<spring:message code="i.sc.um.1006" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<a href="<c:url value="/users?${f:h(backwardQueryString)}"/>" class="btn btn-default">
    <span class="glyphicon glyphicon-step-backward"></span>
    <spring:message code="title.user.searchResult"/></a>
