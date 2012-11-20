<%@include file="/fragment/taglibs.jsp" %>

<f:message key="customer.title.create" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" var="actionBean"/>

        <div id="createCustomer" class="collapse in">
            <s:form class="form-horizontal" beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean">
                <fieldset>
                    <legend>&nbsp;
                        <!--                        <f:message key="customer.title.create"/> -->
                    </legend>
                    <%@include file="/customer/form.jsp"%>    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary" name="add"><i class="icon-plus"></i> <f:message key="button.create"/></button>
                        <s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" class="btn" event="list"><i class="icon-ban-circle"></i> <f:message key="button.cancel"/></s:link>
                    </div>
                </fieldset>            
            </s:form>
        </div>        
    </s:layout-component>
</s:layout-render>
