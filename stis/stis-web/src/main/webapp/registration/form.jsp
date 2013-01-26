<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>                    
<div class="control-group">
    <s:label class="control-label" for="inputFirstName" name="customer.form.firstName"/>
    <div class="controls">        
        <s:text id="inputFirstName" name="cto.firstName"/>
    </div>               
</div>

<div class="control-group">        
    <s:label class="control-label" for="inputLastName" name="customer.form.lastName"/>
    <div class="controls">     
        <s:text id="inputLastName" name="cto.lastName"/>             
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputAddress" name="customer.form.address"/>
    <div class="controls">               
        <s:text id="inputAddress" name="cto.address"/>             
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputPhone" name="customer.form.phone"/>                               
    <div class="controls">             
        <s:text id="inputPhone" name="cto.phone"/>             
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="username" name="user.form.name"/>                               
    <div class="controls">             
        <s:text id="username" name="uto.username"/>             
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="password" name="user.form.password"/>                               
    <div class="controls">             
        <s:password id="password" name="uto.password"/>             
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="password2" name="user.form.password2"/>                               
    <div class="controls">             
        <s:password id="password2" name="password2"/>             
    </div>
</div>