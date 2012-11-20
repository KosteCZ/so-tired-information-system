<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@include file="/fragment/taglibs.jsp" %>
<s:errors/>
<table class="table">
    <tr>
        <th><s:label for="c2" name="tyreTO.type"/></th>
        <td><s:text id="c2" name="tyreTO.type"/></td>

        <th><s:label for="c1" name="tyreTO.name"/></th>
        <td><s:text id="c1" name="tyreTO.name"/></td>
        
    </tr>
    <tr>
        <th><s:label for="c3" name="tyreTO.diameter"/></th>
        <td><s:text id="c3" name="tyreTO.diameter"/></td>
   
        <th><s:label for="c4" name="tyreTO.price"/></th>
        <td><s:text id="c4" name="tyreTO.price"/></td>
    </tr>
    <tr>
        <th><s:label for="c5" name="tyreTO.vendor"/></th>
        <td><s:text id="c5" name="tyreTO.vendor"/></td>
   </tr>
</table>