<%@include file="/fragment/taglibs.jsp" %>

<f:message key="search.results" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" var="actionBean"/>
        <s:link class="btn" beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" style="margin-bottom: 5px;" event="list"><i class="icon-arrow-left"></i> <f:message key="button.back"/></s:link>

        <c:choose>
            <c:when test="${not empty actionBean.results}">
                <s:layout-render name="/tyre/tableLayout.jsp" items="${actionBean.results}"/>
            </c:when>
            <c:otherwise>
                <h4><f:message key="tyre.noResults"/></h4>
            </c:otherwise>
        </c:choose>
    </s:layout-component>
</s:layout-render>
