<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>                    
<div class="control-group">
    <s:label class="control-label" for="inputFirstName" name="ffirstName"/>
    <div class="controls">
        
        <input type="text" id="inputFirstName" placeholder="Firstname" name="cto.firstName"/>
    </div>               
</div>

<div class="control-group">
    <s:label class="control-label" for="inputLastName" name="flastName"/>
    <div class="controls">
        <input class="input.error" type="text" id="inputLastName" placeholder="Lastname" name="cto.lastName"/>     
    <span class="help-inline" name="wrongInput"></span>
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="inputAddress" name="faddress"/>
    <div class="controls">
        <input type="text" id="inputAddress" placeholder="Address" name="cto.address"/>               
    </div>
</div>

<div class="control-group">
<s:label class="control-label" for="inputPhone" name="fphone"/>                               
    <div class="controls">
        <input type="text" id="inputPhone" placeholder="Phone number" name="cto.phone"/>               
    </div>
</div>
    <div class="controls">
    <button type="submit" class="btn" name="add">Create customer</button>
    </div>

<!--
<table class="table">
    <tr>
        <th><s:label for="c1" name="Firstname"/></th>
        <td><s:text id="c1" name="cto.firstName"/></td>

        <th><s:label for="c2" name="Lastname"/></th>
        <td><s:text id="c2" name="cto.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="c3" name="address"/></th>
        <td><s:text id="c3" name="cto.address"/></td>

        <th><s:label for="c4" name="phone"/></th>
        <td><s:text id="c4" name="cto.phone"/></td>
    </tr>          
</table>-->