<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.title.create" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <div id="createExtraService">
            <s:form beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean">
                <fieldset><legend><f:message key="extraService.title.create"/></legend>
                    <%@include file="/extraservice/form.jsp"%>
                </fieldset>
                <div>
                    <button type="submit" name="create"><f:message key="button.create"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" event="list"> <f:message key="button.cancel"/></s:link>
                    </div>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>



