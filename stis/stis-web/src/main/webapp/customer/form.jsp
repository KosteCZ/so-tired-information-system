<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table class="table">
    <tr>
        <th style="vertical-align: middle"><s:label for="firstname" name="FirstName"/></th>
        <td><s:text id="firstname" name="customerTO.firstName"/></td>
        
        <th><s:label for="lastname" name="LastName"/></th>
        <td ><s:text id="lastname" name="customerTO.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="address" name="address"/></th>
        <td><s:text id="address" name="customerTO.address"/></td>
        <th><s:label for="phone" name="phone:"/></th>
        <td><s:text id="phone" name="customerTO.phone"/></td>
    </tr>          
</table>