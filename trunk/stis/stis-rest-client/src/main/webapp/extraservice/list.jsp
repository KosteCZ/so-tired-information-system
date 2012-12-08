<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Short hand for the context root. --%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<s:useActionBean beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" var="actionBean"/>

<s:link  beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" event="newExtraService"><f:message key="button.create"/></s:link>

<table>
    <tr>                     
        <th><f:message key="extraService.table.name"/></th>                                
        <th><f:message key="extraService.table.description"/></th>
        <th><f:message key="extraService.table.price"/></th>
        <th><f:message key="extraService.table.actions"/></th>
    </tr>    
    <c:forEach items="${actionBean.allExtraServices}" var="item">
        <tr>                             
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.description}"/></td>                    
            <td><c:out value="${item.price}"/></td>              
            <td>
                <div><!--<div class="btn-group">-->
                    <s:link  beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" event="edit"><s:param name="extraService.id" value="${item.id}"/><f:message key="button.edit"/></s:link>
                    <s:link  beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" event="delete"><s:param name="extraService.id" value="${item.id}"/><f:message key="button.remove"/></s:link>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

