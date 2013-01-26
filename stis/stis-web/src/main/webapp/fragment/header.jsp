<%@include file="taglibs.jsp"%>

<div id="header">
    <div id="navigation">
        <div class="navbar navbar-fixed-top navbar-inverse">
            <div class="navbar-inner">
                <div class="container">
                    <s:link class="brand" href="/"><f:message key="brand" /></s:link>
                    <ul class="nav">
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list"><f:message key="tyre.catalog"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="list"><f:message key="extraService.catalog"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="newOrder"><f:message key="order.create.title"/></s:link></li>
                    </ul>
                    
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><f:message key="admin"/><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="list"><f:message key="order.catalog"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.CustomerActionBean" event="list"><f:message key="customer.catalog"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.TyreActionBean" event="list"><f:message key="tyre.catalog"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.ExtraServiceActionBean" event="list"><f:message key="extraService.catalog"/></s:link></li>
                                    <li class="divider"></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.SecurityActionBean" event="logout"><i class="icon-off"></i> <f:message key="header.logout"/></s:link></li>
                                </ul>
                            </li>
                        </ul>
                    </sec:authorize>
                    
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><sec:authentication property="principal.customer.firstName"/> <sec:authentication property="principal.customer.lastName"/> <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.OrderActionBean" event="list"><i class="icon"></i> <f:message key="header.userOrders"/></s:link></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" event="edit">
                                            <s:param name="id"><sec:authentication property="principal.customer.id"/></s:param><i class="icon"></i> <f:message key="header.userEdit"/></s:link></li>
                                    <li class="divider"></li>
                                    <li><s:link beanclass="cz.muni.fi.pa165.stis.web.SecurityActionBean" event="logout"><i class="icon-off"></i> <f:message key="header.logout"/></s:link></li>
                                </ul>
                            </li>
                        </ul>
                    </sec:authorize>
                    
                    <sec:authorize access="not isAuthenticated()">
                        <ul class="nav pull-right">
                            <li><s:link beanclass="cz.muni.fi.pa165.stis.web.SecurityActionBean" event="login"><f:message key="login.title"/></s:link></li>
                            <li><s:link beanclass="cz.muni.fi.pa165.stis.web.RegistrationActionBean" event="add"><f:message key="registration.create"/></s:link></li>
                        </ul>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>
