<div id="wrapper">

    <t:messagesPanel/>

    <div class="row">
        <div id="userNav" class="col col-xs-6">
            <div class="well">
                <h3>利用者向けメニュー</h3>

                <p>
                    <a href="<c:url value="/profile" />" class="btn btn-default">
                        <span class="glyphicon glyphicon-edit"></span>
                        <spring:message code="title.user.profileEditForm"/>
                    </a>
                </p>
                <sec:authorize access="hasRole('USER')">
                </sec:authorize>
            </div>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div id="adminNav" class="col col-xs-6">
                <div class="well">
                    <h3>管理者向けメニュー</h3>
                    <sec:authorize url="/users">
                        <p>
                            <a href="<c:url value="/users?searchForm" />" class="btn btn-default">
                                <span class="glyphicon glyphicon-search"></span>
                                <spring:message code="title.user.searchForm"/>
                            </a>
                        </p>

                        <p>
                            <a href="<c:url value="/users?createForm" />" class="btn btn-default">
                                <span class="glyphicon glyphicon glyphicon-plus"></span>
                                <spring:message code="title.user.createForm"/>
                            </a>
                        </p>
                    </sec:authorize>
                </div>
            </div>
        </sec:authorize>
    </div>

</div>
