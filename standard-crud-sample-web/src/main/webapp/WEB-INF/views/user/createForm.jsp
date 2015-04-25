<jsp:include page="inc/legend.jsp"/>

<t:messagesPanel/>

<c:url value="/users" var="creatingUrl"/>
<form:form action="${creatingUrl}" cssClass="form-horizontal" modelAttribute="userForm">

    <jsp:include page="inc/userForm.jsp">
        <jsp:param name="operation" value="create"/>
    </jsp:include>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default" name="createConfirm">
                <span class="glyphicon glyphicon-ok"></span> 入力完了</form:button>
            <form:button class="btn btn-default" name="clearCreateForm">
                <span class="glyphicon glyphicon-refresh"></span> クリア</form:button>
        </div>
    </div>

</form:form>

<a href="<c:url value="${_flow.cancelPath}" />">
    <span class="glyphicon glyphicon-backward"></span> 戻る</a>
</a>

