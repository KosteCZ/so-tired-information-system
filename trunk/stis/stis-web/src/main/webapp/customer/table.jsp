<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>    
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="customer.firstName"/></th>
                <th><f:message key="customer.lastName"/></th>
                <th><f:message key="customer.address"/></th>
                <th><f:message key="customer.phone"/></th>                                
                <th><f:message key="customer.edit"/></th>
                <th><f:message key="customer.remove"/></th>
            </tr>
            </head>

        <tbody>  
            <c:forEach items="${items}" var="cto">
                <tr>
                    <td><c:out value="${cto.firstName}"/></td>
                    <td><c:out value="${cto.lastName}"/></td>
                    <td><c:out value="${cto.address}"/></td>
                    <td><c:out value="${cto.phone}"/></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="edit">
                            <s:param name="cto.id" value="${cto.id}"/><i class="icon-pencil" alt="edit"></i></s:link></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="delete">
                            <s:param name="cto.id" value="${cto.id}"/><i class="icon-trash" alt="remove"></i></s:link></td>
                    </tr>
            </c:forEach>
        </tbody>                
    </table>
</s:layout-definition>