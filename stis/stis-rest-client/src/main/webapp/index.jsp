<%@include file="fragment/taglibs.jsp" %>
<f:message key="stis.client.title" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">                          
        <hr>
        <h3><f:message key="index.welcome"/></h3>
        
    </s:layout-component>
</s:layout-render>
