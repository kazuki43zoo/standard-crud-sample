

<div class="form-group">
    <form:label path="userId" cssClass="col-sm-3 control-label required">ユーザーID</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="userId"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="userId"/>
    </div>
</div>
<div class="form-group">
    <form:label path="name" cssClass="col-sm-3 control-label required">氏名</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="name"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="name"/>
    </div>
</div>
<div class="form-group">
    <form:label path="dateOfBirth" cssClass="col-sm-3 control-label">誕生日</form:label>
    <div class="col-sm-4">
        <form:input type="date" cssClass="form-control" path="dateOfBirth" placeholder="(yyyy-M-d形式)"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="dateOfBirth"/>
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
    <form:label path="tel" cssClass="col-sm-3 control-label">電話番号</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="tel" placeholder="(ハイフンなし)"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="tel"/>
    </div>
</div>
<div class="form-group">
    <form:label path="email" cssClass="col-sm-3 control-label required">メールアドレス</form:label>
    <div class="col-sm-4">
        <form:input cssClass="form-control" path="email"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="email"/>
    </div>
</div>
<div class="form-group">
    <form:label path="password" cssClass="col-sm-3 control-label ${param.operation == 'CREATE' ? 'required' : ''}">パスワード</form:label>
    <div class="col-sm-4">
        <form:password cssClass="form-control" path="password" showPassword="true"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="password"/>
    </div>
</div>
<div class="form-group">
    <form:label path="confirmPassword" cssClass="col-sm-3 control-label ${param.operation == 'CREATE' ? 'required' : 'conditionalRequired'}">確認パスワード</form:label>
    <div class="col-sm-4">
        <form:password cssClass="form-control" path="confirmPassword"/>
    </div>
    <div class="col-sm-4">
        <form:errors cssClass="control-label" path="confirmPassword"/>
    </div>
</div>
