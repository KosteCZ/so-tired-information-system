<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>

<div class="control-group">
    <s:label class="control-label" for="order1" name="order.customer.id"><f:message key="order.form.label.customer"/></s:label>
        <div class="controls">
        <s:select name="order.customer.id"> 
            <s:option id="null" label="..."/>
            <s:options-collection collection="${actionBean.allCustomers}" value="id" label="fullName"/>
        </s:select>
    </div>
</div>
<div class="control-group">
    <s:label class="control-label" for="order2" name="order.carType"><f:message key="order.form.label.carType"/></s:label>
        <div class="controls">
        <s:text id="order2" name="order.carType"/>
    </div>
</div>

<c:forEach items="${actionBean.tyrePositions}" var="position" varStatus="status">
    <div class="control-group">
        <s:label class="control-label" name="order.tyres[${position}].id"><f:message key="order.form.label.${position}"/></s:label>
        <div class="controls">
            <s:select name="order.tyres[${position}].id"> 
                <s:option id="null" label="..."/>
                <s:options-collection collection="${actionBean.allTyres}" value="id" label="name"/>
            </s:select>
        </div>
    </div>
</c:forEach>
    
<div class="control-group">
    <s:label class="control-label" for="order7" name="order.extraServiceIds"><f:message key="order.form.label.es"/></s:label>
        <div class="controls">
        <s:select multiple="multiple" name="order.extraServiceIds"> 
            <s:options-collection collection="${actionBean.allExtraServices}" value="id" label="name"/>
        </s:select>
    </div>
</div>