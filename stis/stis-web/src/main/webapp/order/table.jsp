<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="order.table.customer"/></th>
                <th><f:message key="order.table.carType"/></th>
                <th><f:message key="order.table.tyres"/></th>
                <th><f:message key="order.table.extraServices"/></th>
                <th><f:message key="order.table.date"/></th>
                <th><f:message key="order.table.price"/></th>
                <th style="width: 120px;"><f:message key="extraService.table.actions"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item" varStatus="it">
                <tr>
                    <td><c:out value="${item.customer.firstName} ${item.customer.lastName}"/></td>
                    <td><c:out value="${item.carType}"/></td>
                    <td>
                        <table class="table-condensed nostyle">
                        <c:forEach items="${item.tyres}" var="tr" varStatus="vs">
                            <tr>
                                <td><f:message key="order.form.label.${tr.key}"/></td>
                                <td><c:out value="${tr.value.name}"/></td>
                            </tr>
                        </c:forEach>
                        </table>
                    </td>
                    <td>
                        <c:forEach items="${item.extraServices}" var="es">
                            <c:out value="${es.name}"/>
                            <c:if test="${not vs.last}">
                                <br/>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <table class="table-condensed nostyle">
                            <tr>
                                <td><f:message key="order.table.created"/></td>
                                <td><f:formatDate value="${item.orderNewDate}"/></td>
                            </tr>
                            <tr>
                                <td><f:message key="order.table.serviced"/></td>
                                <td><f:formatDate value="${item.orderServicedDate}"/></td>
                            </tr>
                            <tr>
                                <td><f:message key="order.table.paid"/></td>
                                <td><f:formatDate value="${item.orderPaidDate}"/></td>
                            </tr>
                        </table>
                    </td>
                    <td><f:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${item.totalPrice}" /></td>
                    <td>
                        <div class="btn-group btn-group-vertical">
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="edit"><s:param name="order.id" value="${item.id}"/><i class="icon-pencil"></i> <f:message key="button.edit"/></s:link>
                            <a href="#confirmDelete" role="button" onclick="setLink(this);" class="btn btn-small" data-toggle="modal" data-id="${item.id}"><i class="icon-trash"></i> <f:message key="button.remove"/></a>
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="done"><s:param name="order.id" value="${item.id}"/><i class="icon-wrench"></i> <f:message key="button.done"/></s:link>
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="paid"><s:param name="order.id" value="${item.id}"/><i class="icon-thumbs-up"></i> <f:message key="button.paid"/></s:link>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
                
    <div id="confirmDelete" class="modal fade hide">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3><f:message key="confirmDelete.title"/></h3>
        </div>
        <div class="modal-body">
            <p><f:message key="confirmDelete.order.message"/></p>
        </div>
        <div class="modal-footer">
            <s:link id="confirmButton" class="btn btn-primary" beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="delete"><s:param name="order.id" value=""/><i class="icon-ok"></i> <f:message key="button.yes"/></s:link>
            <a href="#" class="btn" data-dismiss="modal"><i class="icon-ban-circle"></i> <f:message key="button.no"/></a>
        </div>
    </div>

    <script type="text/javascript">
        function setLink(anchor) {
            var url = $('#confirmButton').attr('href');
            if ($('#confirmButton').data('url') === undefined) {
                $('#confirmButton').data('url', url);
            }
            url = $('#confirmButton').data('url');
            var id = $(anchor).data('id');
            $('#confirmButton').attr('href', url + id);
        }
    </script>
</s:layout-definition>