<c:url value="/share/streetAddresses" var="searchPath"/>
<form:form action="${searchPath}" method="get" cssClass="form-horizontal" modelAttribute="streetAddressSearchForm">
    <div class="form-group">
        <form:label path="zipCode" cssClass="col-sm-3 control-label">郵便番号</form:label>
        <div class="col-sm-2">
            <form:input cssClass="form-control" path="zipCode"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="address" cssClass="col-sm-3 control-label">住所</form:label>
        <div class="col-sm-4">
            <form:input cssClass="form-control" path="address"/>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="address"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="size" cssClass="col-sm-3 control-label">ページサイズ</form:label>
        <div class="col-sm-1">
            <form:input type="number" cssClass="form-control" path="size"/>
        </div>
        <div class="col-sm-3">
            <span class="form-control">デフォルト: 20件 最大: 200件</span>
        </div>
        <div class="col-sm-4">
            <form:errors cssClass="control-label" path="size"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-8">
            <form:button class="btn btn-default">検索</form:button>
            <form:button class="btn btn-default" name="searchForm">クリア</form:button>
        </div>
    </div>
</form:form>
<a href="<c:url value="${_flow.cancelPath}"/>">
    <span class="glyphicon glyphicon-backward"></span>
    戻る
</a>
