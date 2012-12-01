<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>
<div class="control-group">
    <label class="control-label" for="inputType" name="tyre.form.label.type"><f:message key="tyre.form.label.type"/></label>
    <div class="controls">
        <s:text id="inputType" name="tto.type"/>
    </div>
</div>
    
<div class="control-group">
    <label class="control-label" for="inputName" name="tyre.form.label.name"><f:message key="tyre.form.label.name"/></label>
    <div class="controls">
        <s:text id="inputName" name="tto.name"/>
    </div>
</div>

<div class="control-group">
    <label class="control-label" for="inputDiameter" name="tyre.form.label.diameter"><f:message key="tyre.form.label.diameter"/></label>
    <div class="controls">
        <s:text id="inputDiameter" name="tto.diameter"/>
    </div>
</div>
    
<div class="control-group">
     <label class="control-label" for="inputVendor" name="tyre.form.label.vendor"><f:message key="tyre.form.label.vendor"/></label>
    <div class="controls">
        <s:text id="inputVendor" name="tto.vendor"/>
    </div>
</div>
    
<div class="control-group">
    <label class="control-label" for="inputPrice" name="tyre.form.label.price"><f:message key="tyre.form.label.price"/></label>
    <div class="controls">
        <s:text id="inputPrice" name="tto.price"/>
    </div>
</div>

