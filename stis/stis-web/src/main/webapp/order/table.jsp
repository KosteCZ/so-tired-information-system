<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="order.table.customer"/></th>
                <th><f:message key="order.table.carType"/></th>
                <th><f:message key="order.table.tyres"/></th>
                <th><f:message key="order.table.extraServices"/></th>
                <th><f:message key="order.table.newDate"/></th>
                <th><f:message key="order.table.servicedDate"/></th>
                <th><f:message key="order.table.paidDate"/></th>
                <th><f:message key="order.table.price"/></th>
                <th style="width: 32px;"><f:message key="extraService.table.actions"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item" varStatus="it">
                <tr>
                    <td><c:out value="${item.customer.firstname} ${item.customer.lastname}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td>
                        <div class="btn-group">
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="edit"><s:param name="order.id" value="${item.id}"/><i class="icon-pencil"></i> <f:message key="button.edit"/></s:link>
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="delete"><s:param name="order.id" value="${item.id}"/><i class="icon-trash"></i> <f:message key="button.remove"/></s:link>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</s:layout-definition>