<%@include file="/fragment/taglibs.jsp" %>

<f:message key="order.title.edit" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" var="actionBean"/>
        
        <s:form beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" class="form-horizontal">
            <fieldset><legend><f:message key="order.title.edit"/></legend>
            <s:hidden name="order.id"/>
            <%@include file="/order/form.jsp"%>
            <div div class="form-actions">
                <button type="submit" class="btn btn-primary" name="save"><i class="icon-ok"></i> <f:message key="button.save"/></button>
                <s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="list" class="btn"><i class="icon-ban-circle"></i> <f:message key="button.cancel"/></s:link>
            </div>
            </fieldset>
        </s:form>
        
    </s:layout-component>
</s:layout-render>