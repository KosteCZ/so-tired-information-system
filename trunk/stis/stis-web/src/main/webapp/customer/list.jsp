<%@include file="/fragment/taglibs.jsp" %>

<s:layout-render name="/layout.jsp" title="Customers">
    <s:layout-component name="content">

        <s:form beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean"> 
            <fieldset><legend>Search</legend>
                <form class="form-search">
                    <div class="input-append input-prepend">
                        <input placeholder="firstname" type="text" class="span2" name="firstname"/>                    
                        <input placeholder="lastname" type="text" id="appendedPrependedInputButton" class="span2" name="lastname"/>
                        <!--<span class="btn" type="" name="findByName">-->
                        <%--<s:submit class="btn" value="Search" name="findByName"/>--%>
                        <button type="submit" name="findByName" class="btn"><i class="icon-search"></i> Search</button>
                        <!--</span>-->
                    </div>                                        
                </form>
            </fieldset>     
        </s:form>


        <s:form class="form-horizontal" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean">
            <fieldset>
                <legend>New Customer</legend>
                <%@include file="/customer/form.jsp"%>                
                <%--<s:submit name="add">Create customer</s:submit>--%>
            </fieldset>            
        </s:form>



        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="all" var="CustomerActionBean"/>
        <c:choose>
            <c:when test="${not empty CustomerActionBean.customers}">
                <span>Customers</span>
                <table class="table">
                    <tr>
                        <th><s:label class="table" name="firstName"/></th>
                        <th><s:label class="table" name="lastName"/>Last Name</th>
                        <th><s:label class="table" name="address"/>Address</th>
                        <th><s:label class="table" name="phone"/>Phone</th>                                
                        <th><s:label class="table" name="edit"/>Edit</th>
                        <th><s:label class="table" name="remove"/>Remove</th>
                    </tr>
                    <c:forEach items="${CustomerActionBean.customers}" var="cto">
                        <tr>                  
                            <td><c:out value="${cto.firstName}"/></td>
                            <td><c:out value="${cto.lastName}"/></td>
                            <td><c:out value="${cto.address}"/></td>
                            <td><c:out value="${cto.phone}"/></td>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="edit">
                                    <s:param name="cto.id" value="${cto.id}"/><i class="icon-edit" alt="edit"></i>
                                </s:link></td>
                            <td><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="delete">
                                    <s:param name="cto.id" value="${cto.id}"/><i class="icon-remove" alt="remove"></i>
                                </s:link></td>
                        </tr>                
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <hr>
                <span class="noresults"> Customer Catalog is empty</span>
            </c:otherwise>
        </c:choose>

    </s:layout-component>
</s:layout-render>