<%@include file="/fragment/taglibs.jsp" %>

<f:message key="order.list" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" var="actionBean" event="list"/>

        <div class="row-fluid" style="margin-bottom: 5px;">
            <div style="float: left;">
                <s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="newOrder" class="btn"><i class="icon-plus"></i> <f:message key="button.create"/></s:link>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div style="float: right;">
                    <s:form beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" class="form-inline" style="margin-bottom: 0px;">
                        <div class="input-append">
                            <f:message key="order.placeholder.customer" var="msg"/>
                            <s:select id="appendedInputButton" name="customerId"> 
                                <s:option value="null" label="${msg}"/>
                                <s:options-collection collection="${actionBean.allCustomers}" value="id" label="fullName"/>
                            </s:select>
                            <button name="findByCustomer" class="btn"><i class="icon-search"></i></button>
                        </div>
                    </s:form>
                </div>
            </sec:authorize>
        </div>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:choose>
                <c:when test="${not empty actionBean.allOrders}">
                    <s:layout-render name="/order/table.jsp" items="${actionBean.allOrders}"/>
                </c:when>
                <c:otherwise>
                    <h4><f:message key="order.listEmpty"/></h4>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
                    
        <sec:authorize access="hasRole('ROLE_USER')">
            <c:choose>
                <c:when test="${not empty actionBean.userOrders}">
                    <s:layout-render name="/order/table.jsp" items="${actionBean.userOrders}"/>
                </c:when>
                <c:otherwise>
                    <h4><f:message key="order.listEmpty"/></h4>
                </c:otherwise>
            </c:choose>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>