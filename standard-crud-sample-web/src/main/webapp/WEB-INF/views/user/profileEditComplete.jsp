<spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).PROFILE_WAS_UPDATED" var="messageEnum"/>
<spring:message code="${messageEnum.code}" var="message"/>
<t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>

<ul class="list-inline">
    <li>
        <a href="<c:url value="${_flow.cancelPath}"/>">
            <span class="glyphicon glyphicon-backward"></span>
            戻る
        </a>
    </li>
</ul>