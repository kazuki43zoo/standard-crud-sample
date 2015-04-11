<jsp:include page="inc/legend.jsp"/>

<t:messagesPanel/>

<c:url value="/users" var="creatingUrl"/>
<form:form action="${creatingUrl}" cssClass="form-horizontal" modelAttribute="userForm">

    <jsp:include page="inc/userForm.jsp">
        <jsp:param name="operation" value="CREATE"/>
    </jsp:include>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="createConfirm">
                <span class="glyphicon glyphicon-ok"></span> 入力完了</form:button>
            <a href="<c:url value="/users?createForm"/>" class="btn btn-default">
                <span class="glyphicon glyphicon-erase"></span> クリア</a>
        </div>
    </div>

</form:form>

<c:choose>
    <c:when test="${backwardQueryString != null}">
        <a href="<c:url value="/users?${f:h(backwardQueryString)}" />">
            <span class="glyphicon glyphicon-list"></span>
            <spring:message code="title.user.searchResult"/>
        </a>
    </c:when>
    <c:otherwise>
        <a href="<c:url value="/"/>">
            <span class="glyphicon glyphicon-home"></span>
            <spring:message code="title.welcome.home"/></a>
    </c:otherwise>
</c:choose>

