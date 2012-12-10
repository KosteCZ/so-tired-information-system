<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.error" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.rest.client.ErrorHandlerActionBean" var="actionBean"/>

        <f:message key="error.message"/>
        <br>

        <hr>
        <ul>
            <li><f:message key="error"/> ${actionBean.exception}</li>
            <br>
            <c:choose>                
                <c:when test="${not empty actionBean.rootCause}">
                    <li><f:message key="rootCause"/> ${actionBean.rootCause}</li>               
                </c:when>
            </c:choose>
        </ul>
        <br>                      
    </s:layout-component>
</s:layout-render>