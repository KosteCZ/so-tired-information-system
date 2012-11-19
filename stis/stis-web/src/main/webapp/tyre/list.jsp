<%-- 
    Document   : list
    Created on : Nov 18, 2012, 8:51:44 PM
    Author     : Honza Koščák
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<%-- 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
--%>        
<s:layout-render name="/layout.jsp" title="Tyres">
    <s:layout-component name="content">
        
        <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
            <fieldset><legend>New Tyre</legend>
                <%@include file="/tyre/form.jsp"%>
                <s:submit name="newTyre">Create tyre</s:submit>
                </fieldset>
        </s:form>
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="all" var="actionBean"/>
        <hr>
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
                    <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="edit"><s:param name="tto.id" value="${tto.id}"/>edit</s:link> </td>
                    <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="deleteTyre">
                            <s:param name="tto.id" value="${tto.id}"/><img alt="remove" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTgerrfVO8sAIilDDea0cIVBMoNW37dNNDG6C6RH9T279yBMfzwgw" width="20px" height="20px"/>
                        </s:link>
                    </td>
                </tr>
            </c:forEach>
            <%-- --%>
        </table>
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
