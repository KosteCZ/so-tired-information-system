<%@include file="fragment/taglibs.jsp" %>
<s:layout-definition>
<html>
<head>
    <title><c:out value="${title}"/></title>
    <s:layout-component name="includes">
        <jsp:include page="/fragment/includes.jsp"/>
    </s:layout-component>
    <s:layout-component name="head"/>
</head>
    <body>
        <s:layout-component name="header">
            <jsp:include page="/fragment/header.jsp"/>
        </s:layout-component>
        <div id="content">
            <s:layout-component name="content"/>
        </div>
    </body>
</html>
</s:layout-definition>