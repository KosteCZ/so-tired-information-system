<%@include file="/fragment/taglibs.jsp"%>
<s:layout-render name="/layout.jsp" nadpis="Edit tyre">
    <s:layout-component name="content">
       <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" var="TyreActionBean"/>
 
       <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
           <s:hidden name="tto.id"/>
            <fieldset><legend>Change info</legend>
                <%@include file="form.jsp"%>
            <s:submit name="save">save</s:submit>
            </fieldset>
        </s:form>
 
    </s:layout-component>
</s:layout-render>