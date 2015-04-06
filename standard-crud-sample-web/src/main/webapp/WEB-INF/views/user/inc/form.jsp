<div class="form-group">
    <form:label path="userId" cssClass="col-sm-2 control-label">ユーザーID</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="userId"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="userId"/>
    </div>
</div>
<div class="form-group">
    <form:label path="name" cssClass="col-sm-2 control-label">氏名</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="name"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="name"/>
    </div>
</div>
<div class="form-group">
    <form:label path="dateOfBirth" cssClass="col-sm-2 control-label">誕生日</form:label>
    <div class="col-sm-4">
        <form:input type="date" cssClass="form-control" path="dateOfBirth"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="dateOfBirth"/>
    </div>
</div>
<div class="form-group">
    <form:label path="address" cssClass="col-sm-2 control-label">住所</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="address"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="address"/>
    </div>
</div>
<div class="form-group">
    <form:label path="tel" cssClass="col-sm-2 control-label">電話番号</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="tel"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="tel"/>
    </div>
</div>
<div class="form-group">
    <form:label path="email" cssClass="col-sm-2 control-label">メールアドレス</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="email"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="email"/>
    </div>
</div>
<div class="form-group">
    <form:label path="password" cssClass="col-sm-2 control-label">パスワード</form:label>
    <div class="col-sm-4">
        <form:password cssClass="form-control" path="password" showPassword="true"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="password"/>
    </div>
</div>
<div class="form-group">
    <form:label path="confirmationPassword" cssClass="col-sm-2 control-label">確認パスワード</form:label>
    <div class="col-sm-4">
        <form:password cssClass="form-control" path="confirmationPassword"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="confirmationPassword"/>
    </div>
</div>
<div class="form-group">
    <form:label path="roles" cssClass="col-sm-2 control-label">ロール</form:label>
    <div class="col-sm-4">
        <c:forEach var="roleCodeListElement" items="${CL_ROLE}">
            <div class="checkbox-inline">
                <form:checkbox
                        path="roles"
                        value="${roleCodeListElement.key}"
                        label="${roleCodeListElement.value}"/>
            </div>
        </c:forEach>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="roles"/>
    </div>
</div>
