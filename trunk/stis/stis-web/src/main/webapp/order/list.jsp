<%@include file="/fragment/taglibs.jsp" %>

<f:message key="order.list" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" var="actionBean" event="list"/>

        <div class="row-fluid" style="margin-bottom: 5px;">
<!--            <div style="float: left;">
                <s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="newOrder" class="btn"><i class="icon-plus"></i> <f:message key="button.create"/></s:link>
            </div>-->
            <div style="float: right;">
                <s:form beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" class="form-inline" style="margin-bottom: 0px;">
                    <div class="input-append">
                        <f:message key="order.placeholder.name" var="msg"/>
                        <input id="searchByName" name="name" id="appendedInputButton" type="text" placeholder="${msg}"/>
                        <button name="findByCustomer" class="btn"><i class="icon-search"></i></button>
                    </div>
                </s:form>
            </div>
        
        </div>

        <s:layout-render name="/order/table.jsp" items="${actionBean.allOrders}"/>
        <%--<c:choose>--%>
            <%--<c:when test="${not empty actionBean.allOrders}">--%>
                <%--<s:layout-render name="/order/table.jsp" items="${actionBean.allOrders}"/>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>                
                <!--<h4><f:message key="order.listEmpty"/></h4>-->
            <%--</c:otherwise>--%>
        <%--</c:cedhoose>--%>
    </s:layout-component>
</s:layout-render>