<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="tyre.table.type"/></th>
                <th><f:message key="tyre.table.name"/></th>
                <th><f:message key="tyre.table.diameter"/></th>
                <th><f:message key="tyre.table.vendor"/></th>
                <th><f:message key="tyre.table.price"/></th>
                <th style="width: 32px;"><f:message key="tyre.table.actions"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="tto" varStatus="it">
                <tr>
                    <td><c:out value="${tto.type}"/></td>
                    <td><c:out value="${tto.name}"/></td>
                    <td><c:out value="${tto.diameter}"/></td>
                    <td><c:out value="${tto.vendor}"/></td>
                    <td><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${tto.price}"/></td>
                    <td>
                        <div class="btn-group">
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="edit"><s:param name="tto.id" value="${tto.id}"/><i class="icon-pencil"></i> <f:message key="button.edit"/></s:link>
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="delete"><s:param name="tto.id" value="${tto.id}"/><i class="icon-trash"></i> <f:message key="button.remove"/></s:link>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</s:layout-definition>