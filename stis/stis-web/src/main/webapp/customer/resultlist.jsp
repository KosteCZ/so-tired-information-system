<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<f:message key="customer.catalog" var="title"/>
<s:layout-render name="/layout.jsp" title="${title}">
    <s:layout-component name="content">   
        <s:link class="btn" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" style="margin-bottom: 5px;" event="all"><i class="icon-arrow-left"></i> <f:message key="button.back"/></s:link>        
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="findByName" var="CustomerActionBean"/>
        <hr>
        <c:choose>
        <c:when test="${not empty CustomerActionBean.foundList}">        
        <s:layout-render name="/customer/table.jsp" items="${actionBean.foundList}"/>
        </c:when>
        <c:otherwise>
            <h4><f:message key="customer.noResults"/></h4>
        </c:otherwise>
        </c:choose>          
    </s:layout-component>
</s:layout-render>