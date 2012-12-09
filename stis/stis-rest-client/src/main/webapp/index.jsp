<%@include file="fragment/taglibs.jsp" %>

<<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">                          
            <ul>
                <h3><f:message key="index.welcome"/></h3>
            </ul>       
    </s:layout-component>
</s:layout-render>
