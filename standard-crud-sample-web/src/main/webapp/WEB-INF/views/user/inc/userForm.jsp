<jsp:include page="profileForm.jsp">
    <jsp:param name="operation" value="${param.operation}"/>
</jsp:include>

<div class="form-group">
    <form:label path="roles" cssClass="col-sm-3 control-label required">ロール</form:label>
    <div class="col-sm-4">
        <c:forEach var="roleCodeListElement" items="${CL_ROLE}">
            <div class="checkbox-inline">
                <form:checkbox
                        path="roles"
                        value="${roleCodeListElement.key}"
                        label="${roleCodeListElement.value}"/>
            </div>
        </c:forEach>
        <br>
        <form:errors cssClass="control-label" path="roles" />
    </div>
</div>
