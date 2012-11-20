<%-- 
    Document   : list
    Created on : Nov 18, 2012, 8:51:44 PM
    Author     : Honza Koscak
--%>
<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Tyres">
    <s:layout-component name="content">

        <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
            <fieldset><legend>New Tyre</legend>
                <%@include file="/tyre/form.jsp"%>
                <s:submit name="newTyre">Create tyre</s:submit>
                </fieldset>
        </s:form>
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="all" var="actionBean"/>
        <c:choose>
            <c:when test="${not empty ActionBean.tyres}">
                <span>Tyres</span>
                <table class="table">
                    <tr>
                        <th>id</th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Diameter</th>
                        <th>Price</th>
                        <th>Vendor</th>
                        <th>Edit</th>
                        <th>Remove</th>
                    </tr>
                    <%-- --%>
                    <c:forEach items="${actionBean.tyres}" var="tto">
                        <tr>
                            <td>${tto.id}</td>
                            <td><c:out value="${tto.type}"/></td>
                            <td><c:out value="${tto.name}"/></td>
                            <td><c:out value="${tto.diameter}"/></td>
                            <td><c:out value="${tto.price}"/></td>
                            <td><c:out value="${tto.vendor}"/></td>
                            <%--
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="delete"><s:param name="tyre.id" value="${tyre.id}"/>delete</s:link></td>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="edit"><s:param name="tyre.id" value="${tyre.id}"/>edit</s:link></td>
                            --%>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="edit"><s:param name="tto.id" value="${tto.id}"/><i class="icon-edit"></i></s:link> </td>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="deleteTyre">
                                    <s:param name="tto.id" value="${tto.id}"/><i class="icon-remove"></i>
                                </s:link>
                            </td>
                        </tr>
                    </c:forEach>
                    <%-- --%>
                </table>
            </c:when>
                <c:otherwise>
                    <hr>
                    <span class="noresults"> Tyre Catalog is empty</span>
                </c:otherwise>
        </c:choose>
        <%-- 
        <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
            <fieldset><legend>New pneumatic</legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Add new pneumatic</s:submit>
                </fieldset>
        </s:form>
        --%>

    </s:layout-component>
</s:layout-render>

<%--
</body>
</html>
--%>
