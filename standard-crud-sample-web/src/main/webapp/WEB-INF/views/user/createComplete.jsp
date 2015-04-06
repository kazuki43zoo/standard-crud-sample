<spring:message code="i.sc.um.1004" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<a href="<c:url value="/users?createForm"/>" class="btn btn-default">
    <span class="glyphicon glyphicon-plus"></span>
    <spring:message code="title.user.createForm"/></a>
<a href="<c:url value="/users?initSearchForm"/>" class="btn btn-default">
    <span class="glyphicon glyphicon-step-backward"></span>
    <spring:message code="title.user.searchForm"/></a>
