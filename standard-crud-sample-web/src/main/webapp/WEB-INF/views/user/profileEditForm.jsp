<jsp:include page="inc/legend.jsp"/>

<t:messagesPanel/>

<c:url value="/profile" var="editUrl"/>
<form:form action="${editUrl}" cssClass="form-horizontal" modelAttribute="profileForm">

    <jsp:include page="inc/profileForm.jsp">
        <jsp:param name="operation" value="UPDATE"/>
    </jsp:include>

    <form:hidden path="version"/>
    <form:hidden path="credentialVersion"/>
    <form:hidden path="userUuid"/>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="confirm">
                <span class="glyphicon glyphicon-ok"></span> 入力完了</form:button>
            <form:button class="btn btn-default" name="reload">
                <span class="glyphicon glyphicon-refresh"></span> 元に戻す</form:button>
        </div>
    </div>

</form:form>

<a href="<c:url value="${_flow.cancelPath}"/>">
    <span class="glyphicon glyphicon-backward"></span>
    戻る
</a>