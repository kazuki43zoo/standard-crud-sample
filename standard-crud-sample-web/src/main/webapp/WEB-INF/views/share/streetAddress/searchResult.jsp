<h1><fmt:formatNumber value="${page.totalElements}"/> 件 ( <fmt:formatNumber
        value="${page.number + 1}"/> / <fmt:formatNumber value="${page.totalPages}"/> ページ )</h1>
<c:if test="${1 < page.totalPages}">
    <div class="paginationArea">
        <t:pagination page="${page}"
                      criteriaQuery="${f:query(streetAddressSearchForm)}&${f:query(_flow.asIdMap())}"
                      outerElementClass="pagination"/>
    </div>
</c:if>
<table id="userTable" class="table table-hover">
    <tr>
        <th>#</th>
        <th>郵便番号</th>
        <th>住所</th>
        <th>住所(カナ)</th>
        <th>操作</th>
    </tr>
    <c:url var="selectPath" value="/share/streetAddresses"/>
    <c:forEach var="address" items="${page.content}" varStatus="rowStatus">
        <tr>
            <td><fmt:formatNumber value="${(page.number * page.size) + rowStatus.count}"/></td>
            <td>${f:h(address.zipCode)}</td>
            <td>${f:h(address.address)}</td>
            <td>${f:h(address.addressKana)}</td>
            <td>
                <c:set value="${address}" var="address" scope="request"/>
                <form:form action="${selectPath}" modelAttribute="address">
                    <form:hidden path="zipCode"/>
                    <form:hidden path="address"/>
                    <form:button class="btn btn-default">選択</form:button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${1 < page.totalPages}">
    <div class="paginationArea">
        <t:pagination page="${page}"
                      criteriaQuery="${f:query(streetAddressSearchForm)}&${f:query(_flow.asIdMap())}"
                      outerElementClass="pagination"/>
    </div>
</c:if>
<c:url value="/share/streetAddresses" var="redoPath"/>
<a href="<c:url value="/share/streetAddresses?searchRedo"/>&${f:h(f:query(streetAddressSearchForm))}&${f:h(f:query(_flow.asIdMap()))}"
   class="btn btn-default">検索条件の変更</a>
