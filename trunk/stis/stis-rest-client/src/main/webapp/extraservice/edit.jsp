<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.title.edit" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <div id="editExtraService">
            <s:form beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" class="form-horizontal">
                <s:hidden name="extraService.id"/>
                <fieldset><legend><f:message key="extraService.title.edit"/></legend>            
                    <%@include file="/extraservice/form.jsp"%>
                </fieldset>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary" name="save"><i class="icon-ok"></i> <f:message key="button.save"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" class="btn" event="list"><i class="icon-ban-circle"></i><f:message key="button.cancel"/></s:link>
                </div>
            </div>
        </s:form>
    </s:layout-component>
</s:layout-render>
