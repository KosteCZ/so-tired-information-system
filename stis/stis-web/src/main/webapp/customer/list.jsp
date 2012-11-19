<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" title="Customers">
    <s:layout-component name="content">
        
        <s:form beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean">
            <fieldset><legend>New Customer</legend>
                <%@include file="/customer/form.jsp"%>
                <s:submit name="newCustomer">Create customer</s:submit>
                </fieldset>
        </s:form>
        
        
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="all" var="CustomerActionBean"/>
        <hr>
        <span >Customers</span>
        <table class="table">
            <tr>
                <th>id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>Phone</th>                                
                <th>Edit</th>
                <th>Remove</th>
            </tr>
            <c:forEach items="${CustomerActionBean.customers}" var="cto">
                <tr>
                    <td>${cto.id}</td>                    
                    <td><c:out value="${cto.firstName}"/></td>
                    <td><c:out value="${cto.lastName}"/></td>
                    <td><c:out value="${cto.address}"/></td>
                    <td><c:out value="${cto.phone}"/></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="edit"><s:param name="cto.id" value="${cto.id}"/>edit</s:link> </td>
                    <td><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="deleteCustomer">
                            <s:param name="cto.id" value="${cto.id}"/><img alt="remove" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTgerrfVO8sAIilDDea0cIVBMoNW37dNNDG6C6RH9T279yBMfzwgw" width="20px" height="20px"/>
                        </s:link> </td>
                </tr>                
            </c:forEach>
        </table>
                
                
    </s:layout-component>
</s:layout-render>