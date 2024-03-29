<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>

<s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" var="actionBean"/>
<c:choose>
    <c:when test="${actionBean.user.isAdmin}">
        <div class="control-group">
            <label class="control-label" for="order1" name="order.customer.id"><f:message key="order.form.label.customer"/></label>
            <div class="controls">
                <s:select name="order.customer.id"> 
                    <s:option label=""/>
                    <s:options-collection collection="${actionBean.allCustomers}" value="id" label="fullName"/>
                </s:select>
            </div>
        </div>
        </c:when>
    <c:otherwise>
        <s:hidden name="order.customer.id" value="${actionBean.user.customer.id}"/>
    </c:otherwise>
</c:choose>
<div class="control-group">
    <label class="control-label" for="order2" name="order.carType"><f:message key="order.form.label.carType"/></label>
        <div class="controls">
        <s:text id="order2" name="order.carType"/>
    </div>
</div>

<c:forEach items="${actionBean.tyrePositions}" var="position" varStatus="status">
    <div class="control-group">
        <label class="control-label" for="orderTyres${position}" name="order.tyres[${position}].id"><f:message key="order.form.label.${position}"/></label>
        <div class="controls">
            <s:select name="order.tyres[${position}].id" id="orderTyres${position}"> 
                <s:option value="null" label=""/>
                <s:options-collection collection="${actionBean.allTyres}" value="id" label="name"/>
            </s:select>
        </div>
    </div>
</c:forEach>
    
<div class="control-group">
    <label class="control-label" for="order7" name="order.extraServiceIds"><f:message key="order.form.label.es"/></label>
        <div class="controls">
        <s:select id="order7" multiple="multiple" name="order.extraServiceIds"> 
            <s:options-collection collection="${actionBean.allExtraServices}" value="id" label="name"/>
        </s:select>
    </div>
</div>