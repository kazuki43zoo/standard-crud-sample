<t:messagesPanel/>

<c:url value="/users" var="searchUrl"/>
<form:form action="${searchUrl}" method="get" cssClass="form-horizontal" modelAttribute="userSearchForm">

    <div class="form-group">
        <form:label path="userId" cssClass="col-sm-3 control-label">ユーザーID</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="userId"/>
            <form:errors cssClass="control-label" path="userId"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="name" cssClass="col-sm-3 control-label">氏名</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="name"/>
            <form:errors cssClass="control-label" path="name"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="dateOfBirth" cssClass="col-sm-3 control-label">誕生日</form:label>
        <div class="col-sm-4">
            <form:input type="date" cssClass="form-control" path="dateOfBirth" placeholder="(yyyy-M-d形式)"/>
            <form:errors cssClass="control-label" path="dateOfBirth"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="address" cssClass="col-sm-3 control-label">住所</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="address"/>
            <form:errors cssClass="control-label" path="address"/>
        </div>
        <div class="col-sm-1">
            <form:button name="gotoAddressSearch" class="btn btn-default">検索</form:button>
        </div>
    </div>
    <div class="form-group">
        <form:label path="tel" cssClass="col-sm-3 control-label">電話番号</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="tel" placeholder="(ハイフンなし)"/>
            <form:errors cssClass="control-label" path="tel"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="email" cssClass="col-sm-3 control-label">メールアドレス</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="email"/>
            <form:errors cssClass="control-label" path="email"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="statusTargets" cssClass="col-sm-3 control-label">ステータス</form:label>
        <div class="col-sm-4">
            <c:forEach var="statusCodeListElement" items="${CL_USERSTATUS}">
                <div class="checkbox-inline">
                    <form:checkbox
                            path="statusTargets"
                            value="${statusCodeListElement.key}"
                            label="${statusCodeListElement.value}"/>
                </div>
            </c:forEach>
            <br>
            <form:errors cssClass="control-label" path="statusTargets"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
            <form:button class="btn btn-default"><span class="glyphicon glyphicon-search"></span> 検索</form:button>
            <form:button class="btn btn-default" name="clearSearchForm"><span
                    class="glyphicon glyphicon-erase"></span> クリア</form:button>
        </div>
    </div>

</form:form>

<a href="<c:url value="${_flow.cancelPath}"/>">
    <span class="glyphicon glyphicon-backward"></span>
    戻る
</a>

