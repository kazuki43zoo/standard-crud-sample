<jsp:include page="profileForm.jsp"/>
<div class="form-group">
    <form:label path="roles" cssClass="col-sm-3 control-label">ロール</form:label>
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
