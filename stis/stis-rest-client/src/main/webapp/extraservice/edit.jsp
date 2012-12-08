<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.title.edit" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <div id="editExtraService">
            <s:form beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean">
                <s:hidden name="extraService.id"/>
                <fieldset><legend><f:message key="extraService.title.edit"/></legend>            
                    <%@include file="/extraservice/form.jsp"%>
                </fieldset>
                <div >
                    <button type="submit" name="save"><f:message key="button.edit"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" event="list"><f:message key="button.cancel"/></s:link>
                    </div>
            </s:form>
        </div>


    </s:layout-component>
</s:layout-render>
