<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.error" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.rest.client.ErrorHandlerActionBean" var="actionBean"/>
        <br>
        <f:message key="error.message"/>
        
        <hr>
        <div class="container-fluid">
            <div class="form-actions">
                <c:choose>                
                    <c:when test="${not empty actionBean.rootCause}">
                        <f:message key="rootCause"/> ${actionBean.rootCause}
                    </c:when>
                </c:choose>
            </div>
            <div class="form-actions">
            <br>
            <f:message key="error"/> ${actionBean.exception}
            </div>
        </div>        
    </s:layout-component>
</s:layout-render>