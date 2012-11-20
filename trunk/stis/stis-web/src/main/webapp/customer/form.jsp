<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>                    
<div class="control-group">
    <s:label class="control-label" for="inputFirstName" name="ffirstName"/>
    <div class="controls">        
        <!--<input type="text" id="inputFirstName" placeholder="Firstname" name="cto.firstName"/>-->
        <s:text id="inputFirstName" name="cto.firstName"/>
    </div>               
</div>

<div class="control-group">        
    <s:label class="control-label" for="inputLastName" name="flastName"/>
    <div class="controls">
        <!--<input class="input.error" type="text" id="inputLastName" placeholder="Lastname" name="cto.lastName"/>-->     
        <s:text id="inputLastName" name="cto.lastName"/>             
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputAddress" name="faddress"/>
    <div class="controls">
        <!--<input type="text" id="inputAddress" placeholder="Address" name="cto.address"/>-->               
        <s:text id="inputLastName" name="cto.address"/>             
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputPhone" name="fphone"/>                               
    <div class="controls">
        <!--<input type="text" id="inputPhone" placeholder="Phone number" name="cto.phone"/>-->               
        <s:text id="inputLastName" name="cto.phone"/>             
    </div>
</div>
<div class="controls">
    <button type="submit" class="btn" name="add">Create customer</button>
</div>
