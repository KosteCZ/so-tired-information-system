<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" nadpis="Edit tyre">
    <s:layout-component name="content">
       <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" var="TyreActionBean"/>
 
       <s:form beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean">
           <s:hidden name="tto.id"/>
            <fieldset><legend>Change info</legend>
                <%@include file="form.jsp"%>
            <s:submit name="save">save</s:submit>
            </fieldset>
        </s:form>
 
    </s:layout-component>
</s:layout-render>