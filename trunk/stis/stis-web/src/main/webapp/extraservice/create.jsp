<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Edit extra service">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" var="actionBean"/>
        
        <div id="createExtraService" class="collapse in">
            <s:form beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" class="form-horizontal">
                <fieldset><legend>Create extra service</legend>
                    <%@include file="/extraservice/form.jsp"%>
                </fieldset>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary" name="create"><i class="icon-plus"></i> Create</button>
                    <s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" class="btn" event="list"><i class="icon-ban-circle"></i> Cancel</s:link>
                </div>
            </s:form>
        </div>
        
    </s:layout-component>
</s:layout-render>