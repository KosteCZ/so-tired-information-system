<%@include file="fragment/taglibs.jsp" %>
<f:message key="index.title" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:label name="index.aboutProject"/>
        <hr/>
        <s:label name="index.text"/>
        <s:label name="authors"/>
    </s:layout-component>
</s:layout-render>
