<%@include file="/fragment/taglibs.jsp" %>
<f:message key="extraService.title.create" var="msg"/>



<div id="createExtraService">
    <s:form beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean">
        <fieldset><legend><f:message key="extraService.title.create"/></legend>

            <s:errors/>

            <div >
                <label  for="es1" name="extraService.name"><f:message key="extraService.form.label.name"/></label>
                <div >
                    <s:text id="es1" name="extraService.name"/>
                </div>
            </div>
            <div >
                <label  for="es2" name="extraService.price"><f:message key="extraService.form.label.price"/></label>
                <div >
                    <s:text id="es2" name="extraService.price"/>
                </div>
            </div>
            <div >
                <label for="es3" name="extraService.description"><f:message key="extraService.form.label.description"/></label>
                <div >
                    <s:textarea rows="5" id="es3" name="extraService.description"/>
                </div>
            </div>


        </fieldset>
        <div>
            <button type="submit" name="create"><f:message key="button.create"/></button>
            <s:link beanclass="cz.muni.fi.pa165.stis.rest.client.ExtraServiceClientActionBean" event="list"> <f:message key="button.cancel"/></s:link>
            </div>
    </s:form>
</div>




