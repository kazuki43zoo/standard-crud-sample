<div id="wrapper">

    <div class="row">
        <sec:authorize access="hasRole('USER')">
            <div id="userNav" class="col col-xs-6">
                <div class="well">
                    <h3>利用者向けメニュー</h3>

                    <p>
                        <a href="<c:url value="/profile?editForm" />"
                           class="btn btn-default">
                            <span class="glyphicon glyphicon-search"></span>
                            <spring:message code="title.profile.editForm"/></a>
                    </p>
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <div id="adminNav" class="col col-xs-6">
                <div class="well">
                    <h3>管理者向けメニュー</h3>
                    <sec:authorize url="/users">
                        <p>
                            <a href="<c:url value="/users?initSearchForm" />"
                               class="btn btn-default">
                                <span class="glyphicon glyphicon-search"></span>
                                <spring:message code="title.user.searchForm"/></a>
                        </p>

                        <p>
                            <a href="<c:url value="/users?createForm" />"
                               class="btn btn-default">
                                <span class="glyphicon glyphicon glyphicon-plus"></span> <spring:message
                                    code="title.user.createForm"/></a></li>
                        </p>
                    </sec:authorize>
                </div>
            </div>
        </sec:authorize>
    </div>


</div>
