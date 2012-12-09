<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.title.create" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <div id="createExtraService">
            <s:form beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" class="form-horizontal">
                <fieldset><legend><f:message key="extraService.title.create"/></legend>
                    <%@include file="/extraservice/form.jsp"%>
                </fieldset>
                <div class="form-actions">                   
                    <button type="submit" class="btn btn-primary" name="create"><i class="icon-plus"></i> <f:message key="button.create"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" class="btn" event="list"><i class="icon-ban-circle"></i><f:message key="button.cancel"/></s:link>
                </div>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>



