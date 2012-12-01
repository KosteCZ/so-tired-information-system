<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>    
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><f:message key="customer.firstName"/></th>
                <th><f:message key="customer.lastName"/></th>
                <th><f:message key="customer.address"/></th>
                <th><f:message key="customer.phone"/></th>                                
                <th><f:message key="customer.table.action"/></th>
                <!--<th><f:message key="customer.remove"/></th>-->
            </tr>
            </head>

        <tbody>  
            <c:forEach items="${items}" var="cto">
                <tr>
                    <td><c:out value="${cto.firstName}"/></td>
                    <td><c:out value="${cto.lastName}"/></td>
                    <td><c:out value="${cto.address}"/></td>
                    <td><c:out value="${cto.phone}"/></td>
                    <td>
                        <div class="btn-group">
                        <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="edit">
                            <s:param name="cto.id" value="${cto.id}"/><i class="icon-pencil" alt="edit"></i><f:message key="button.edit"/></s:link>
                    <%--<s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="delete">--%>
                            <%--<s:param name="cto.id" value="${cto.id}"/><i class="icon-trash" alt="remove"></i><f:message key="button.remove"/></s:link>--%>
                                <a href="#confirmDelete" role="button" onclick="setLink(this)" class="btn btn-small" data-toggle="modal" data-id="${cto.id}">
                                <i class="icon-trash"></i> <f:message key="button.remove"/></a>    
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
            <p>Are you sure, you want to delete customer? </p>
            <%--<f:message key="${cto.firstName} ${cto.lastName}"/>--%>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal"><f:message key="button.no"/></a>
            <s:link id="confirmButton" class="btn btn-primary" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="delete"><s:param name="cto.id" value="${cto.id}"/><f:message key="button.yes"/></s:link>
        </div>
    </div>

    <script type="text/javascript">
        function setLink(anchor) {
            var id = $(anchor).data('id');
            var url = $('#confirmButton').attr('href');
            $('#confirmButton').attr('href', url + id);
        }
    </script>    
                
</s:layout-definition>