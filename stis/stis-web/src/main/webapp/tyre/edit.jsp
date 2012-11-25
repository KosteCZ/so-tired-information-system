<%@include file="/fragment/taglibs.jsp"%>
<f:message key="tyre.title.edit" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
       <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" var="TyreActionBean"/>
 
       <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
           <s:hidden name="tto.id"/>
            <fieldset>
                <legend><f:message key="tyre.title.edit"/></legend>
                <%@include file="form.jsp"%>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary" name="save"><i class="icon-ok"></i> <f:message key="button.save"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list" class="btn"><i class="icon-ban-circle"></i> <f:message key="button.cancel"/></s:link>
                </div>
            </fieldset>
        </s:form>
 
    </s:layout-component>
</s:layout-render>