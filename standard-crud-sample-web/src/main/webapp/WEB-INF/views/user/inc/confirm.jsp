<jsp:include page="profileConfirm.jsp"/>
<div class="form-group">
    <form:label path="roles" cssClass="col-sm-3 control-label">ロール</form:label>
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
