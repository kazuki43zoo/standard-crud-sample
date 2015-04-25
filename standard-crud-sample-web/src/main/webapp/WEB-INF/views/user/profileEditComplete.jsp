<spring:message code="i.sc.um.1009" var="message"/>
<t:messagesPanel messagesType="success" messagesAttributeName="message"/>

<ul class="list-inline">
    <li>
        <a href="<c:url value="${_flow.cancelPath}"/>">
            <span class="glyphicon glyphicon-backward"></span>
            戻る
        </a>
    </li>
</ul>