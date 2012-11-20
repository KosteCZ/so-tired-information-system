<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Edit extra service">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" var="actionBean"/>
        
        <s:form beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" class="form-horizontal">
            <fieldset><legend>Edit extra service</legend>
            <s:hidden name="extraService.id"/>
            <%@include file="/extraservice/form.jsp"%>
            <div div class="form-actions">
                <button type="submit" class="btn btn-primary" name="save"><i class="icon-ok"></i> Save</button>
                <s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="list" class="btn"><i class="icon-ban-circle"></i> Cancel</s:link>
            </div>
            </fieldset>
        </s:form>
        
    </s:layout-component>
</s:layout-render>