<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Edit extra service">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" var="actionBean"/>
        
        <s:form beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" class="form-horizontal">
            <fieldset><legend>Edit extra service</legend>
            <s:hidden name="extraService.id"/>
            <%@include file="/extraservice/form.jsp"%>
            <div class="control-group">
                <div class="controls">
                    <s:submit class="btn" name="save">Save</s:submit>
                </div>
            </div>
            </fieldset>
        </s:form>
        
    </s:layout-component>
</s:layout-render>