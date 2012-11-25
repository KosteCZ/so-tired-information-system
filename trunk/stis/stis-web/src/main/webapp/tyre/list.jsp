<%-- 
    Document   : list
    Created on : Nov 18, 2012, 8:51:44 PM
    Author     : Honza Koscak
--%>
<%@include file="/fragment/taglibs.jsp" %>
<f:message key="tyre.catalog" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">

        <%--        <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
            <fieldset><legend>New Tyre</legend>
                <%@include file="/tyre/form.jsp"%>
                <s:submit name="newTyre">Create tyre</s:submit>
                </fieldset>
        </s:form>--%>
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list" var="actionBean"/>
        
        
        <div class="row-fluid" style="margin-bottom: 5px;">
            <div style="float: left;">
                <s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="newTyre" class="btn"><i class="icon-plus"></i> <f:message key="button.create"/></s:link>
            </div>
            <div style="float: right;">
                <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" class="form-inline" style="margin-bottom: 0px;">
                    <div class="input-append">
                        <f:message key="tyre.placeholder.name" var="msg"/>
                        <input id="searchByName" name="name" id="appendedInputButton" type="text" placeholder="${msg}"/>
                        <button name="findByName" class="btn"><i class="icon-search"></i></button>
                    </div>
                </s:form>
            </div>
        </div>
        
        <c:choose>
            <c:when test="${not empty actionBean.allTyres}">
                <s:layout-render name="/tyre/tableLayout.jsp" items="${actionBean.allTyres}"/>
            </c:when>
            <c:otherwise>                
                <h4><f:message key="tyre.catalogEmpty"/></h4>
            </c:otherwise>
        </c:choose>
        
            
            
            
            
            
            
        <%--     
            
        <c:choose>
            <c:when test="${not empty actionBean.tyres}">
                <span>Tyres</span>
                <table class="table">
                    <tr>                       
                        <th>Type</th>
                        <th>Name</th>
                        <th>Diameter</th>
                        <th>Price</th>
                        <th>Vendor</th>
                        <th>Edit</th>
                        <th>Remove</th>
                    </tr>
                    <c:forEach items="${actionBean.tyres}" var="tto">
                        <tr>
                            <td><c:out value="${tto.type}"/></td>
                            <td><c:out value="${tto.name}"/></td>
                            <td><c:out value="${tto.diameter}"/></td>
                            <td><c:out value="${tto.price}"/></td>
                            <td><c:out value="${tto.vendor}"/></td>
                            </%/-/-/
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="delete"><s:param name="tyre.id" value="${tyre.id}"/>delete</s:link></td>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="edit"><s:param name="tyre.id" value="${tyre.id}"/>edit</s:link></td>
                            /-/-/%/>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="edit"><s:param name="tto.id" value="${tto.id}"/><i class="icon-edit"></i></s:link> </td>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="deleteTyre">
                                    <s:param name="tto.id" value="${tto.id}"/><i class="icon-remove"></i>
                                </s:link>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
                <c:otherwise>
                    <hr>
                    <span class="noresults"> Tyre Catalog is empty</span>
                </c:otherwise>
        </c:choose>
        --%> 
        <%-- 
        <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
            <fieldset><legend>New pneumatic</legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Add new pneumatic</s:submit>
                </fieldset>
        </s:form>
        --%>

    </s:layout-component>
</s:layout-render>

<%--
</body>
</html>
--%>
