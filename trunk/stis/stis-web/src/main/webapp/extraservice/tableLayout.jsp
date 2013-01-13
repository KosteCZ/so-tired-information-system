<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="extraService.table.name"/></th>
                <th><f:message key="extraService.table.description"/></th>
                <th><f:message key="extraService.table.price"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th style="width: 32px;"><f:message key="extraService.table.actions"/></th>
                </sec:authorize>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item" varStatus="it">
                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.price}"/></td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <div class="btn-group">
                                <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="edit"><s:param name="extraService.id" value="${item.id}"/><i class="icon-pencil"></i> <f:message key="button.edit"/></s:link>
                                <a href="#confirmDelete" role="button" onclick="setLink(this);" class="btn btn-small" data-toggle="modal" data-id="${item.id}"><i class="icon-trash"></i> <f:message key="button.remove"/></a>
                            </div>
                        </td>
                    </sec:authorize>
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
            <p><f:message key="confirmDelete.extraService.message"/></p>
        </div>
        <div class="modal-footer">
            <s:link id="confirmButton" class="btn btn-primary" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="delete"><s:param name="extraService.id" value=""/><i class="icon-ok"></i> <f:message key="button.yes"/></s:link>
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
