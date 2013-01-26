<%@include file="/fragment/taglibs.jsp" %>

<f:message key="customer.catalog" var="title"/>
<s:layout-render name="/layout.jsp" title="${title}">    
    <s:layout-component name="content">                                            
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="all" var="actionBean"/>

        <div class="row-fluid" style="margin-bottom: 5px;">
            <div style="float: left;">
                <s:link beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" event="newRegistration" class="btn"><i class="icon-plus"></i> <f:message key="button.create"/></s:link>
                
            </div>
            <div style="float: right;">                
                <s:form beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" class="form-inline" style="margin-bottom: 0px;"> 
                    <div class="input-append input-prepend">
                        <f:message key="customer.firstName" var="firstname"/>
                        <input style="width: 118px;" placeholder="${firstname}" type="text" name="firstname"/>                    
                        <f:message key="customer.lastName" var="lastname"/>
                        <input style="width: 118px; margin-left: -1px;" placeholder="${lastname}" type="text"  id="appendedPrependedInputButton" name="lastname"/>
                        <button type="submit" name="findByName" class="btn"><i class="icon-search"></i></button>
                    </div>                                        
                </s:form>
            </div>
        </div>



        <c:choose>
            <c:when test="${not empty actionBean.customers}">                                
                <s:layout-render name="/customer/table.jsp" items="${actionBean.customers}"/>
            </c:when>
            <c:otherwise>
                <hr>
                <h4><f:message key="customer.list.empty"/></h4>
            </c:otherwise>
        </c:choose>

    </s:layout-component>
</s:layout-render>
