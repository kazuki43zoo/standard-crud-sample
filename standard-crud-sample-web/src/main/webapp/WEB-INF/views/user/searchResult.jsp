<c:set var="paginationQueryString" value="${f:query(userSearchForm)}&page=${page.number}&size=${page.size}"/>

<script type="text/javascript">
    $(function () {
        $("#userTable .deleteBtn").on("click", function () {
            var $this = $(this);
            var $dialog = $("#deletingConfirmationDialog");
            var action = contextPath + "users?userUuid=" + $this.data("user-uuid") + "&" + "${paginationQueryString}";
            $dialog.find("#deleteForm").attr("action", action);
            $dialog.modal('show');
        });
    });
</script>

<h1>検索結果：<fmt:formatNumber value="${page.totalElements}"/> 件
    (${f:h(page.number + 1) } / ${f:h(page.totalPages)} ページ)</h1>

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
    <c:forEach var="user" items="${page.content}" varStatus="rowStatus">
        <tr>
            <td>${f:h((page.number * page.size) + rowStatus.count)}</td>
            <td>${f:h(user.credential.userId)}</td>
            <td>${f:h(user.name)}</td>
            <td>${f:h(user.dateOfBirth)}</td>
            <td>${f:h(user.email)}</td>
            <td>${f:h(CL_USERSTATUS[user.status.name()])}</td>
            <td>
                <a href="<c:url value="/users?gotoUpdateForm&userUuid=${f:h(user.userUuid)}&${paginationQueryString}&${f:query(_flow.asIdMap())}" />"
                   class="btn btn-default" title="編集">
                    <span class="glyphicon glyphicon-edit"></span></a>
                <c:if test="${user.status != 'DELETED'}">
                    <button class="btn btn-default deleteBtn" data-user-uuid="${f:h(user.userUuid)}" title="削除">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${1 < page.totalPages}">
    <div class="paginationContainer">
        <t:pagination page="${page}" criteriaQuery="${f:query(userSearchForm)}&${f:query(_flow.asIdMap())}"
                      outerElementClass="pagination"/>
    </div>
</c:if>

<div>
    <ul class="list-inline">
        <li>
            <a href="<c:url value="/users?searchRedo&${f:h(f:query(userSearchForm))}&${f:query(_flow.asIdMap())}"/>">
                <span class="glyphicon glyphicon-search"></span>
                <spring:message code="title.user.searchForm"/></a>
        </li>
        <li>
            <a href="<c:url value="/users?gotoCreateForm&${paginationQueryString}&${f:query(_flow.asIdMap())}"/>">
                <span class="glyphicon glyphicon glyphicon-plus"></span>
                <spring:message code="title.user.createForm"/>
            </a>
        </li>
        <li>
            <a href="<c:url value="/users?download&${f:h(f:query(userSearchForm))}&${f:h(f:query(_flow.asIdMap()))}"/>"
               target="download"><span class="glyphicon glyphicon-download"></span> ダウンロード</a>
        </li>
    </ul>
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
                <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).USER_DELETE_CONFIRM"
                             var="messageEnum"/>
                <span class="glyphicon glyphicon-${messageEnum.typeString}-sign" style="font-size: x-large;"></span>
                <spring:message code="${messageEnum.code}"/>
            </div>
            <div class="modal-footer">
                <form:form id="deleteForm">
                    <input type="hidden" name="delete">
                    <button type="button" class="btn btn-default" data-dismiss="modal">いいえ</button>
                    <button name="gotoDelete" class="btn btn-default">はい</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
