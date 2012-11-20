<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>

<div class="control-group">
    <s:label class="control-label" for="es1" name="extraService.name"><f:message key="extraService.form.label.name"/></s:label>
    <div class="controls">
        <s:text id="es1" name="extraService.name"/>
    </div>
</div>
<div class="control-group">
    <s:label class="control-label" for="es2" name="extraService.price"><f:message key="extraService.form.label.price"/></s:label>
    <div class="controls">
        <s:text id="es2" name="extraService.price"/>
    </div>
</div>
<div class="control-group">
    <s:label class="control-label" for="es3" name="extraService.description"><f:message key="extraService.form.label.description"/></s:label>
    <div class="controls">
        <s:textarea rows="5" id="es3" name="extraService.description"/>
    </div>
</div>