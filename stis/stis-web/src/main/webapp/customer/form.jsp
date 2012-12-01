<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>                    
<div class="control-group">
    <label class="control-label" for="inputFirstName" name="customer.form.firstName"/>
    <div class="controls">        
        <s:text id="inputFirstName" name="cto.firstName"/>
    </div>               
</div>

<div class="control-group">        
    <label class="control-label" for="inputLastName" name="customer.form.lastName"/>
    <div class="controls">     
        <s:text id="inputLastName" name="cto.lastName"/>             
    </div>
</div>

<div class="control-group">
    <label class="control-label" for="inputAddress" name="customer.form.address"/>
    <div class="controls">               
        <s:text id="inputAddress" name="cto.address"/>             
    </div>
</div>

<div class="control-group">
    <label class="control-label" for="inputPhone" name="customer.form.phone"/>                               
    <div class="controls">             
        <s:text id="inputPhone" name="cto.phone"/>             
    </div>
</div>