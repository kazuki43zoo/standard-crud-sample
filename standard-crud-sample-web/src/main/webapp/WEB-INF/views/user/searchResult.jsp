<c:set var="backwardQueryString" value="${f:query(userSearchForm)}&page=${usersPage.number}&size=${usersPage.size}" />

<script type="text/javascript">
    $(function () {
        $("#userTable .deleteBtn").on("click", function () {
            var $this = $(this);
            var dialog = $("#deletingConfirmationDialog");
            var action = contextPath + "users/" + $this.data("user-uuid");
            dialog.find("#deleteForm").attr("action", action);
            dialog.modal('show');
        });
    });
</script>

<h1><fmt:formatNumber value="${usersPage.totalElements}"/> 件
    (${f:h(usersPage.number + 1) } / ${f:h(usersPage.totalPages)} ページ)</h1>

<t:messagesPanel/>

<table id="userTable" class="table table-hover">
    <tr>
        <th>#</th>
        <th>ユーザーID</th>
        <th>氏名</th>
        <th>誕生日</th>
        <th>メールアドレス</th>
        <th>ステータス</th>
        <th>操作</th>
    </tr>
    <c:forEach var="user" items="${usersPage.content}" varStatus="rowStatus">
        <tr>
            <td>${f:h((usersPage.number * usersPage.size) + rowStatus.count)}</td>
            <td>${f:h(user.credential.userId)}</td>
            <td>${f:h(user.name)}</td>
            <td>${f:h(user.dateOfBirth)}</td>
            <td>${f:h(user.email)}</td>
            <td>${f:h(CL_USERSTATUS[user.status.name()])}</td>
            <td>
                <a href="<c:url value="/users/${f:h(user.userUuid)}?updateForm&backwardQueryString=${f:h(f:u(backwardQueryString))}" />" class="btn btn-default"
                   title="編集">
                    <span class="glyphicon glyphicon-edit"></span></a>
                <c:if test="${user.status.name() != 'DELETED'}">
                    <button class="btn btn-default deleteBtn"
                            data-user-uuid="${f:h(user.userUuid)}" title="削除">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${1 < usersPage.totalPages}">
    <div class="paginationContainer">
        <t:pagination page="${usersPage}" criteriaQuery="${f:query(userSearchForm)}" outerElementClass="pagination"/>
    </div>
</c:if>

<div>
    <a href="<c:url value="/users?searchForm&${f:h(f:query(userSearchForm))}"/>" class="btn btn-default">
        <span class="glyphicon glyphicon-step-backward"></span>
        <spring:message code="title.user.searchForm"/></a>
</div>

<div class="modal fade" id="deletingConfirmationDialog" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalLabel">ユーザー削除</h4>
            </div>
            <div class="modal-body">
                <span class="glyphicon glyphicon-warning-sign" style="color: orange; font-size: x-large;"></span>
                ユーザーを削除します。よろしいですか？
            </div>
            <div class="modal-footer">
                <form:form id="deleteForm">
                    <input type="hidden" name="backwardQueryString" value="${f:h(backwardQueryString)}">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        いいえ
                    </button>
                    <button name="delete" class="btn btn-default" >
                        はい
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>
