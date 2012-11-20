<%@include file="/fragment/taglibs.jsp"%>
<s:layout-render name="/layout.jsp" nadpis="Edit customer">
    <s:layout-component name="content">
       <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" var="CustomerActionBean"/>
 
       <s:form class="form-horizontal" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean">
           <s:hidden name="cto.id"/>
            <fieldset><legend>Change info</legend>
                <%@include file="form.jsp"%>
            <s:submit name="save">Update</s:submit>
            </fieldset>
        </s:form>
 
    </s:layout-component>
</s:layout-render>