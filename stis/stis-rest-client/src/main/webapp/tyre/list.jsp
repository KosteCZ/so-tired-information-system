<%@include file="/fragment/taglibs.jsp" %>
<f:message key="tyre.catalog" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" var="actionBean"/>

        <div class="row-fluid">
            <div style="float: left; margin-bottom: 5px;">                       
                <s:link  beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" event="newTyre" class="btn"><i class="icon-plus"></i><f:message key="button.create"/></s:link>
            </div>

            <c:choose>
                <c:when test="${not empty actionBean.allTyres}">
                    <table class="table table-striped table-bordered">
                        <tr>                     
                            <th><f:message key="tyre.table.type"/></th>                                
                            <th><f:message key="tyre.table.name"/></th>                                
                            <th><f:message key="tyre.table.diameter"/></th>
                            <th><f:message key="tyre.table.vendor"/></th>
                            <th><f:message key="tyre.table.price"/></th>
                            <th style="width: 32px;"><f:message key="tyre.table.actions"/></th>
                        </tr>    
                        <c:forEach items="${actionBean.allTyres}" var="item">
                            <tr>                             
                                <td><c:out value="${item.type}"/></td>
                                <td><c:out value="${item.name}"/></td>
                                <td><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.diameter}"/></td>
                                <td><c:out value="${item.vendor}"/></td>                    
                                <td><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.price}"/></td>
                                <td>
                                    <div class="btn-group">
                                        <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" event="edit"><s:param name="tyre.id" value="${item.id}"/><i class="icon-pencil"></i><f:message key="button.edit"/></s:link>                                    
                                        <!--                                    <s:link  id="confirmButton" class="btn btn-primary" beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" event="delete"><s:param name="tyre.id" value="${item.id}"/><f:message key="button.remove"/></s:link>-->
                                        <a href="#confirmDelete" role="button" onclick="setLink(this);" class="btn btn-small" data-toggle="modal" data-id="${item.id}"><i class="icon-trash"></i> <f:message key="button.remove"/></a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>


                <div id="confirmDelete" class="modal fade hide">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h3><f:message key="confirmDelete.title"/></h3>
                    </div>
                    <div class="modal-body">
                        <p><f:message key="confirmDelete.tyre.message"/></p>
                    </div>
                    <div class="modal-footer">
                        <s:link id="confirmButton" class="btn btn-primary" beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" event="delete"><s:param name="tyre.id" value=""/><i class="icon-ok"></i> <f:message key="button.yes"/></s:link>
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


            </c:when>

            <c:otherwise> 
                <br><hr>               
                <h4><f:message key="tyre.catalogEmpty"/></h4>
            </c:otherwise>
        </c:choose>        
    </s:layout-component>
</s:layout-render>
