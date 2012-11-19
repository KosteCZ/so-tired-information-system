<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table class="table">
    <tr>
        <th><s:label for="c1" name="customerTO.firstName"/></th>
        <td><s:text id="c1" name="customerTO.firstName"/></td>
        
        <th><s:label for="c2" name="customerTO.lastName"/></th>
        <td>
            <s:errors field="lastname"/>
            <s:text id="c2" name="customerTO.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="c3" name="customerTO.address"/></th>
        <td><s:text id="c3" name="customerTO.address"/></td>
   
        <th><s:label for="c4" name="customerTO.phone"/></th>
        <td><s:text id="c4" name="customerTO.phone"/></td>
    </tr>          
</table>