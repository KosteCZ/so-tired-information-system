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
                <th>Order-ids??</th>
                <th></th>
            </tr>
            <c:forEach items="${CustomerActionBean.customers}" var="cto">
                <tr>
                    <td>${cto.id}</td>                    
                    <td><c:out value="${cto.firstName}"/></td>
                    <td><c:out value="${cto.lastName}"/></td>
                    <td><c:out value="${cto.address}"/></td>
                    <td><c:out value="${cto.phone}"/></td>
                </tr>                
            </c:forEach>
                <tr>
                    <td>4</td>                    
                    <td><c:out value="${cto.firstName}"/>dsada</td>
                    <td><c:out value="${cto.lastName}"/>das</td>
                    <td><c:out value="${cto.address}"/>adsa</td>
                    <td><c:out value="${cto.phone}"/>fsd</td>
                </tr>
                <tr>
                    <td>4</td>                    
                    <td><c:out value="${cto.firstName}"/>dsada</td>
                    <td><c:out value="${cto.lastName}"/>das</td>
                    <td><c:out value="${cto.address}"/>adsa</td>
                    <td><c:out value="${cto.phone}"/>fsd</td>
                </tr>
        </table>
                
                
    </s:layout-component>
</s:layout-render>