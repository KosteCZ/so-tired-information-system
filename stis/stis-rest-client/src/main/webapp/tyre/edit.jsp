<%@include file="/fragment/taglibs.jsp" %>
<f:message key="tyre.title.edit" var="msg"/>

<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <div id="editTyre">
            <s:form beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" class="form-horizontal">
                <s:hidden name="tyre.id"/>
                <fieldset><legend><f:message key="tyre.title.edit"/></legend>            
                    <%@include file="/tyre/form.jsp"%>
                </fieldset>
                <div class="form-actions">
                    <button type="submit" name="save" class="btn btn-primary"><i class="icon-ok"></i><f:message key="button.edit"/></button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.rest.client.TyreClientActionBean" class="btn" event="list"><i class="icon-ban-circle"></i><f:message key="button.cancel"/></s:link>
                    </div>
            </s:form>
        </div>


    </s:layout-component>
</s:layout-render>
