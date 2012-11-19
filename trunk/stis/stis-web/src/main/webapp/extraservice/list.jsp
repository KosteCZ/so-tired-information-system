<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Extra service catalog">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" var="actionBean" event="list"/>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${actionBean.allExtraServices}" var="item" varStatus="it">
                    <tr>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.description}"/></td>
                        <td><c:out value="${item.price}"/></td>
                        <td><s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="edit"><s:param name="extraService.id" value="${item.id}"/><i class="icon-edit"></i></s:link></td>
                        <td><s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="delete"><s:param name="extraService.id" value="${item.id}"/><i class="icon-remove"></i></s:link></td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="createExtraService">
            <s:form beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" class="form-horizontal">
                <fieldset><legend>Create extra service</legend>
                    <%@include file="/extraservice/form.jsp"%>
                </fieldset>
                <div class="control-group">
                    <div class="controls">
                        <s:submit class="btn" name="create">Create</s:submit>
                        </div>
                    </div>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>