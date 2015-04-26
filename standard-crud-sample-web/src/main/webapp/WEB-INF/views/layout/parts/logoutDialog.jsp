<sec:authorize access="isAuthenticated()">
    <div class="modal fade" id="logoutConfirmationDialog" tabindex="-1" role="dialog" aria-labelledby="modalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="modalLabel">ログアウト</h4>
                </div>
                <div class="modal-body">
                    <spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).LOGOUT_CONFIRM" var="messageEnum"/>
                    <span class="glyphicon glyphicon-${messageEnum.typeString}-sign" style="font-size: x-large;"></span>
                    <sec:authentication property="principal.user.name" var="userName"/>
                    <spring:message code="${messageEnum.code}" arguments="${userName}" />
                </div>
                <div class="modal-footer">
                    <c:url value="/logout" var="logoutUrl"/>
                    <form:form action="${logoutUrl}">
                        <button type="button" class="btn btn-default" data-dismiss="modal">いいえ</button>
                        <button class="btn btn-default">はい</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>