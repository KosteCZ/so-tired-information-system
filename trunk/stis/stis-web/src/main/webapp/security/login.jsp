<%@include file="/fragment/taglibs.jsp" %>

<f:message key="login.title" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.SecurityActionBean" var="actionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" var="registrationActionBean"/>

        <c:if test="${not empty actionBean.error}">
		<div class="errorblock">
                    <f:message key="login.error" />
		</div>
	</c:if>
        
        <c:if test="${not empty registrationActionBean.regSucc}">
		<div class="successblock">
                    <font color="green">
                    <f:message key="registration.successful" />
                    </font>
		</div>
	</c:if>
                
        <c:url value="/j_spring_security_check" var="checkUrl"/>
        <form class="form-horizontal" action="${checkUrl}" method="POST">
            <div class="control-group">
                <f:message key="login.label.username" var="lblUsername"/>
                <label class="control-label" for="inputUsername">${lblUsername}:</label>
                <div class="controls">
                    <input type="text" id="inputUsername" name="j_username" placeholder="${lblUsername}">
                </div>
            </div>
            <div class="control-group">
                <f:message key="login.label.password" var="lblPassword"/>
                <label class="control-label" for="inputPassword">${lblPassword}:</label>
                <div class="controls">
                    <input type="password" id="inputPassword" name="j_password" placeholder="${lblPassword}">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn"><f:message key="login.button.login"/></button>
                </div>
            </div>
        </form>
    </s:layout-component>
</s:layout-render>
