<%-- 
    Document   : list
    Created on : Nov 18, 2012, 8:51:44 PM
    Author     : Honza Koscak
--%>
<%@include file="/fragment/taglibs.jsp" %>
<f:message key="tyre.catalog" var="msg"/>
<s:layout-render name="/layout.jsp" title="${msg}">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list" var="actionBean"/>        
        <div class="row-fluid" style="margin-bottom: 5px;">
            <div style="float: left;">
                <s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="newTyre" class="btn"><i class="icon-plus"></i> <f:message key="button.create"/></s:link>
            </div>
            <div style="float: right;">
                <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" class="form-inline" style="margin-bottom: 0px;">
                    <div class="input-append">
                        <f:message key="tyre.placeholder.name" var="msg"/>
                        <input id="searchByName" name="name" id="appendedInputButton" type="text" placeholder="${msg}"/>
                        <button name="findByName" class="btn"><i class="icon-search"></i></button>
                    </div>
                </s:form>
            </div>
        </div>
        
        <c:choose>
            <c:when test="${not empty actionBean.allTyres}">
                <s:layout-render name="/tyre/tableLayout.jsp" items="${actionBean.allTyres}"/>
            </c:when>
            <c:otherwise>                
                <h4><f:message key="tyre.catalogEmpty"/></h4>
            </c:otherwise>
        </c:choose>
    </s:layout-component>
</s:layout-render>
