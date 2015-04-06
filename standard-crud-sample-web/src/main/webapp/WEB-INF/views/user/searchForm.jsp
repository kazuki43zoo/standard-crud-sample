<div id="wrapper">
    <h1><spring:message code="${titleKey}"/></h1>

    <t:messagesPanel/>

    <c:url value="/users" var="searchUrl"/>
    <form:form action="${searchUrl}" method="get" cssClass="form-horizontal" modelAttribute="userSearchForm">

        <div class="form-group">
            <form:label path="userId" cssClass="col-sm-2 control-label">ユーザーID</form:label>
            <div class="col-sm-3">
                <form:input cssClass="form-control" path="userId"/>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="userId"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="name" cssClass="col-sm-2 control-label">氏名</form:label>
            <div class="col-sm-3">
                <form:input cssClass="form-control" path="name"/>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="name"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="dateOfBirth" cssClass="col-sm-2 control-label">誕生日</form:label>
            <div class="col-sm-3">
                <form:input type="date" cssClass="form-control" path="dateOfBirth"/>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="dateOfBirth"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="address" cssClass="col-sm-2 control-label">住所</form:label>
            <div class="col-sm-3">
                <form:input cssClass="form-control" path="address"/>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="address"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="tel" cssClass="col-sm-2 control-label">電話番号</form:label>
            <div class="col-sm-3">
                <form:input cssClass="form-control" path="tel"/>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="tel"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="email" cssClass="col-sm-2 control-label">メールアドレス</form:label>
            <div class="col-sm-3">
                <form:input cssClass="form-control" path="email"/>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="email"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="statusTargets" cssClass="col-sm-2 control-label">ステータス</form:label>
            <div class="col-sm-3">
                <c:forEach var="statusCodeListElement" items="${CL_USERSTATUS}">
                    <div class="checkbox-inline">
                        <form:checkbox
                                path="statusTargets"
                                value="${statusCodeListElement.key}"
                                label="${statusCodeListElement.value}"/>
                    </div>
                </c:forEach>
            </div>
            <div class="col-sm-3">
                <form:errors cssClass="control-label" path="statusTargets"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-3">
                <form:button class="btn btn-default"><span class="glyphicon glyphicon-search"></span> 検索</form:button>
                <form:button class="btn btn-default" name="initSearchForm">
                    <span class="glyphicon glyphicon-erase"></span> クリア</form:button>
            </div>
        </div>

    </form:form>

    <a href="<c:url value="/"/>" class="btn btn-default">
        <span class="glyphicon glyphicon-step-backward"></span>
        <spring:message code="title.welcome.home"/></a>

</div>
