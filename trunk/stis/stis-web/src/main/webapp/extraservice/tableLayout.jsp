<%@include file="/fragment/taglibs.jsp" %>
<s:layout-definition>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th style="width: 32px;">Actions</th>
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
                        <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="edit"><s:param name="extraService.id" value="${item.id}"/><i class="icon-pencil"></i> Edit</s:link>
                        <s:link class="btn btn-small" beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="delete"><s:param name="extraService.id" value="${item.id}"/><i class="icon-trash"></i> Delete</s:link>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</s:layout-definition>