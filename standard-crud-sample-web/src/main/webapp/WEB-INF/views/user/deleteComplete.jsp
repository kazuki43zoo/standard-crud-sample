<div id="wrapper">

    <h1><spring:message code="${titleKey}"/></h1>

    <spring:message code="i.sc.um.1002" var="message"/>
    <t:messagesPanel messagesType="success" messagesAttributeName="message" />

    <a href="<c:url value="/users?searchResult"/>" class="btn btn-default">
        <span class="glyphicon glyphicon-step-backward"></span>
        <spring:message code="title.user.searchResult"/></a>

</div>
