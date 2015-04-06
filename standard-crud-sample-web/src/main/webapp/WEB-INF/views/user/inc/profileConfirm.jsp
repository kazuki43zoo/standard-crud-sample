<div class="form-group">
    <form:label path="userId" cssClass="col-sm-3 control-label">ユーザーID</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.userId)}</span>
        <form:hidden path="userId"/>
    </div>
</div>
<div class="form-group">
    <form:label path="name" cssClass="col-sm-3 control-label">氏名</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.name)}</span>
        <form:hidden path="name"/>
    </div>
</div>
<div class="form-group">
    <form:label path="dateOfBirth" cssClass="col-sm-3 control-label">誕生日</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.dateOfBirth)}</span>
        <form:hidden path="dateOfBirth"/>
    </div>
</div>
<div class="form-group">
    <form:label path="address" cssClass="col-sm-3 control-label">住所</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.address)}</span>
        <form:hidden cssClass="form-control" path="address"/>
    </div>
</div>
<div class="form-group">
    <form:label path="tel" cssClass="col-sm-3 control-label">電話番号</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.tel)}</span>
        <form:hidden path="tel"/>
    </div>
</div>
<div class="form-group">
    <form:label path="email" cssClass="col-sm-3 control-label">メールアドレス</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.email)}</span>
        <form:hidden path="email"/>
    </div>
</div>
