<div class="form-group">
    <form:label path="userId" cssClass="col-sm-2 control-label">ユーザーID</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.userId)}</span>
        <form:hidden path="userId"/>
    </div>
</div>
<div class="form-group">
    <form:label path="name" cssClass="col-sm-2 control-label">氏名</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.name)}</span>
        <form:hidden path="name"/>
    </div>
</div>
<div class="form-group">
    <form:label path="dateOfBirth" cssClass="col-sm-2 control-label">誕生日</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.dateOfBirth)}</span>
        <form:hidden path="dateOfBirth"/>
    </div>
</div>
<div class="form-group">
    <form:label path="address" cssClass="col-sm-2 control-label">住所</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.address)}</span>
        <form:hidden cssClass="form-control" path="address"/>
    </div>
</div>
<div class="form-group">
    <form:label path="tel" cssClass="col-sm-2 control-label">電話番号</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.tel)}</span>
        <form:hidden path="tel"/>
    </div>
</div>
<div class="form-group">
    <form:label path="email" cssClass="col-sm-2 control-label">メールアドレス</form:label>
    <div class="col-sm-8">
        <span class="form-control">${f:h(userForm.email)}</span>
        <form:hidden path="email"/>
    </div>
</div>
<div class="form-group">
    <form:label path="roles" cssClass="col-sm-2 control-label">ロール</form:label>
    <div class="col-sm-8">
        <c:forEach var="roleCodeListElement" items="${CL_ROLE}">
            <spring:eval expression="T(com.github.kazuki43zoo.domain.model.Role).valueOf(roleCodeListElement.key)"
                         var="role"/>
            <span class="form-control">
                <c:choose>
                    <c:when test="${userForm.roles.contains(role)}">
                        <span class="glyphicon glyphicon-check"></span>
                    </c:when>
                    <c:otherwise>
                        <span class="glyphicon glyphicon-unchecked"></span>
                    </c:otherwise>
                </c:choose>
                ${f:h(roleCodeListElement.value)}
            </span>
        </c:forEach>
        <form:hidden path="roles"/>
    </div>
</div>
