<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>

<div class="control-group">
    <s:label class="control-label" for="order1" name="order.customer"><f:message key="order.form.label.customer"/></s:label>
    <s:select name="order.customer"> 
        <s:options-collection collection="${actionBean.allCustomers}" value="id" label="fullName"/>
    </s:select>
</div>
<div class="control-group">
    <s:label class="control-label" for="order2" name="order.carType"><f:message key="order.form.label.carType"/></s:label>
    <div class="controls">
        <s:text id="order2" name="order.carType"/>
    </div>
</div>
<div class="control-group">
    <s:label class="control-label" for="order3" name="order.tyre"><f:message key="order.form.label.tyreFR"/></s:label>
    <s:select name="order.tyre"> 
        <s:option label="null"/>
        <s:options-collection collection="${actionBean.allTyres}" value="id" label="name"/>
    </s:select>
</div>
<div class="control-group">
    <s:label class="control-label" for="order4" name="order.tyre"><f:message key="order.form.label.tyreFL"/></s:label>
    <s:select name="order.tyre"> 
        <s:option label="null"/>
        <s:options-collection collection="${actionBean.allTyres}" value="id" label="name"/>
    </s:select>
</div>
<div class="control-group">
    <s:label class="control-label" for="order5" name="order.tyre"><f:message key="order.form.label.tyreRR"/></s:label>
    <s:select name="order.tyre"> 
        <s:option label="null"/>
        <s:options-collection collection="${actionBean.allTyres}" value="id" label="name"/>
    </s:select>
</div>
<div class="control-group">
    <s:label class="control-label" for="order6" name="order.tyre"><f:message key="order.form.label.tyreRL"/></s:label>
    <s:select name="order.tyre"> 
        <s:option label="null"/>
        <s:options-collection collection="${actionBean.allTyres}" value="id" label="name"/>
    </s:select>
</div>
<div class="control-group">
    <s:label class="control-label" for="order7" name="order.es"><f:message key="order.form.label.es"/></s:label>
    <s:select multiple="multiple" name="order.es"> 
        <s:options-collection collection="${actionBean.allExtraServices}" value="id" label="name"/>
    </s:select>
</div>