<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Search results">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" var="actionBean"/>
        <s:link class="btn" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="list"><i class="icon-arrow-left"></i> Back</s:link>

        <c:choose>
            <c:when test="${not empty actionBean.results}">
                <s:layout-render name="/extraservice/tableLayout.jsp" items="${actionBean.results}"/>
            </c:when>
            <c:otherwise>
                <h4>No results found</h4>
            </c:otherwise>
        </c:choose>
    </s:layout-component>
</s:layout-render>
