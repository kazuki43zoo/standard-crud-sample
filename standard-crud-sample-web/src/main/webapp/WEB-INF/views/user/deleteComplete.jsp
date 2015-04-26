<spring:eval expression="T(com.github.kazuki43zoo.core.message.Message).USER_WAS_DELETED" var="messageEnum"/>
<spring:message code="${messageEnum.code}" var="message"/>
<t:messagesPanel messagesType="${messageEnum.typeString}" messagesAttributeName="message"/>

<a href="<c:url value="${_flow.finishPath}" />">
    <span class="glyphicon glyphicon-backward"></span> 戻る</a>
</a>
