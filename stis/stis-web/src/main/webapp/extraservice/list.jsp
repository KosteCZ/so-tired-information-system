<%@include file="/fragment/taglibs.jsp" %>
<s:layout-render name="/layout.jsp" title="Extra service catalog">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" var="actionBean" event="list"/>

        <div class="row-fluid" style="margin-bottom: 5px;">
            <div style="float: left;">
                <s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="newExtraService" class="btn"><i class="icon-plus"></i> Create</s:link>
            </div>
            <div style="float: right;">
                <s:form beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" class="form-inline" style="margin-bottom: 0px;">
                    <div class="input-append">
                        <input id="searchByName" name="name" id="appendedInputButton" type="text" placeholder="Name"/>
                        <button name="findByName" class="btn"><i class="icon-search"></i></button>
                    </div>
                </s:form>
            </div>
        
        </div>

        <c:choose>
            <c:when test="${not empty actionBean.allExtraServices}">
                <s:layout-render name="/extraservice/tableLayout.jsp" items="${actionBean.allExtraServices}"/>
            </c:when>
            <c:otherwise>                
                <h4>Extra Service Catalog is empty</h4>
            </c:otherwise>
        </c:choose>
    </s:layout-component>
</s:layout-render>