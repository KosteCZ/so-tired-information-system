<%@include file="/fragment/taglibs.jsp"%>
<s:layout-render name="/layout.jsp" nadpis="Edit customer">
    <s:layout-component name="content">
       <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" var="CustomerActionBean"/>
 
       <s:form class="form-horizontal" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean">
           <s:hidden name="cto.id"/>
           <s:hidden name="uto.id"/>
           <s:hidden name="uto.username"/>
            <fieldset><legend><f:message key="customer.title.edit"/></legend>
                <%@include file="form.jsp"%>
                <div class="form-actions">
                    <button type="submit" name="save" class="btn btn-primary"><i class="icon-ok"></i> <f:message key="button.save"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="list" class="btn"><i class="icon-ban-circle"></i> <f:message key="button.cancel"/></s:link>
                </div>
            </fieldset>
        </s:form>
 
    </s:layout-component>
</s:layout-render>