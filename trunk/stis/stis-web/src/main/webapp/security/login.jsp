<%@include file="/fragment/taglibs.jsp" %>

<f:message key="registration.create" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.SecurityActionBean" var="actionBean"/>

        <c:if test="${not empty actionBean.error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
        
        <c:url value="/j_spring_security_check" var="checkUrl"/>
        <form class="form-horizontal" action="${checkUrl}" method="POST">
            <div class="control-group">
                <label class="control-label" for="inputUsername">Username:</label>
                <div class="controls">
                    <input type="text" id="inputUsername" name="j_username" placeholder="Username">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputPassword">Password:</label>
                <div class="controls">
                    <input type="password" id="inputPassword" name="j_password" placeholder="Password">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn">Login</button>
                </div>
            </div>
        </form>
    </s:layout-component>
</s:layout-render>
