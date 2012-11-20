<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="extraService.table.name"/></th>
                <th><f:message key="extraService.table.description"/></th>
                <th><f:message key="extraService.table.price"/></th>
                <th style="width: 32px;"><f:message key="extraService.table.actions"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item" varStatus="it">
                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td>
                        <div class="btn-group">
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="edit"><s:param name="extraService.id" value="${item.id}"/><i class="icon-pencil"></i> <f:message key="button.edit"/></s:link>
                            <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="delete"><s:param name="extraService.id" value="${item.id}"/><i class="icon-trash"></i> <f:message key="button.remove"/></s:link>
                            <!--<a href="#confirmDelete${it.index}" role="button" class="btn btn-small" data-toggle="modal"><i class="icon-trash"></i> <f:message key="button.remove"/></a>-->
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div id="confirmDelete" class="modal fade hide">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3>Confirm</h3>
        </div>
        <div class="modal-body">
            <p>Delete? Huha</p>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal"><f:message key="button.no"/></a>
            <s:link class="btn btn-primary" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="delete"><s:param name="extraService.id" value="${item.id}"/><f:message key="button.yes"/></s:link>
        </div>
    </div>
</s:layout-definition>