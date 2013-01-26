<%@include file="/fragment/taglibs.jsp" %>

<f:message key="registration.create" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" var="actionBean"/>

        <div id="createCustomer">
            <s:form class="form-horizontal" beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean">
                <fieldset>
                    <legend>
                        <f:message key="registration.create"/>
                    </legend>
                    <font color="red">
                    <c:if test="${not empty actionBean.passwordError}">
                        <div class="errorblock">
                            <f:message key="password.error" />
                        </div>
                    </c:if>
                    <c:if test="${not empty actionBean.usernameError}">
                        <div class="errorblock">
                            <f:message key="username.error" />
                        </div>
                    </c:if>
                    </font>
                    <%@include file="/registration/form.jsp"%>    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary" name="add"><i class="icon-plus"></i> <f:message key="button.create"/></button>
                        <s:link beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" class="btn" event="list"><i class="icon-ban-circle"></i> <f:message key="button.cancel"/></s:link>
                    </div>
                </fieldset>            
            </s:form>
        </div>        
    </s:layout-component>
</s:layout-render>
