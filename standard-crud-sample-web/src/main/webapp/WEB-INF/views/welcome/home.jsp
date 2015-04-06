<div id="wrapper">

    <h1><spring:message code="${titleKey}"/></h1>

    <p>The time on the server is ${serverTime}.</p>

    <div>
        <p>
            <a href="<c:url value="/users?initSearchForm" />"
               class="btn btn-default">
                <span class="glyphicon glyphicon-search"></span>
                <spring:message code="title.user.searchForm"/></a>
        </p>

        <p>
            <a href="<c:url value="/users?createForm" />"
               class="btn btn-default">
                <span class="glyphicon glyphicon glyphicon-plus"></span> <spring:message code="title.user.createForm"/></a></li>
        </p>
    </div>

</div>
