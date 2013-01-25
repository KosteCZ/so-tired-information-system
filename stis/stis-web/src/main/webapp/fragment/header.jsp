<%@include file="taglibs.jsp"%>

<div id="header">
    <div id="navigation">
        <div class="navbar navbar-fixed-top navbar-inverse">
            <div class="navbar-inner">
                <div class="container">
                    <s:link class="brand" href="/">STIS</s:link>
                    <ul class="nav">
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list"><f:message key="tyre.catalog"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="list"><f:message key="extraService.catalog"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="newOrder"><f:message key="order.create.title"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" event="newRegistration"><f:message key="registration.create"/></s:link></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><f:message key="admin"/><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="list"><f:message key="order.catalog"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="list"><f:message key="customer.catalog"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list"><f:message key="tyre.catalog"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="list"><f:message key="extraService.catalog"/></s:link></li>
                                </ul>
                            </li>
                        </sec:authorize>
                    </ul>
                    <sec:authorize access="isAuthenticated()">
                        <ul class="nav pull-right">
                            <c:url value="/security/logout/" var="url"/>
                            <li>
                                <a href="${url}"><i class="icon-off icon-white"></i> LOGOUT</a>
                            </li>
                        </ul>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
    </div>
