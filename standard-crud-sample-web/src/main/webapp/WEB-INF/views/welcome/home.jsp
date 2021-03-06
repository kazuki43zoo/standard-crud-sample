<div id="wrapper">

    <t:messagesPanel/>

    <div class="row">
        <div id="userNav" class="col col-xs-6">
            <div class="well">
                <h3>利用者向けメニュー</h3>

                <p>
                    <a href="<c:url value="/profile?_flowOperation=BEGIN" />">
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
                            <a href="<c:url value="/users?searchForm&_flowOperation=BEGIN" />">
                                <span class="glyphicon glyphicon-search"></span>
                                <spring:message code="title.user.searchForm"/>
                            </a>
                        </p>
                        <p>
                            <a href="<c:url value="/users?_flowOperation=BEGIN" />">
                                <span class="glyphicon glyphicon-list"></span>
                                <spring:message code="title.user.searchResult"/>
                            </a>
                        </p>
                        <p>
                            <a href="<c:url value="/users?createForm&_flowOperation=BEGIN" />">
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
