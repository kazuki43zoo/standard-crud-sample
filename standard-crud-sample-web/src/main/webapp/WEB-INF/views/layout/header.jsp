<div class="container">
    <div class="navbar-header">
        <h1>
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/" />" class="navbar-brand" title="ホームへ">
                    <span class="glyphicon glyphicon-home"></span> 標準的なCRUDアプリのサンプル</a>
            </sec:authorize>
        </h1>
    </div>
    <div class="navbar-header">
        <h4 class="navbar-text"><spring:message code="${titleKey}"/></h4>
    </div>
    <sec:authorize access="isAuthenticated()">
        <div class="navbar-header pull-right">
            <div class="navbar-form">
                <span class="navbar-text"></span><sec:authentication property="principal.user.name"/> さん
                <button type="button" class="btn btn-default" title="ログアウト" data-toggle="modal"
                        data-target="#logoutConfirmationDialog">
                    <span class="glyphicon glyphicon-log-out"></span>
                </button>
            </div>
        </div>
    </sec:authorize>
</div>


