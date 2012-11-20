<%@include file="taglibs.jsp"%>

<div id="header">
    <div id="navigation">
        <div class="navbar navbar-fixed-top navbar-inverse">
            <div class="navbar-inner">
                <div class="container">
                    <s:link class="brand" href="/">STIS</s:link>
                    <ul class="nav">
                        
                        <li><s:link href="/tyre/list.jsp" ><f:message key="tyre.catalog"/></s:link></li>
                        <li><s:link href="/extraservice/list.jsp"><f:message key="extraService.catalog"/></s:link></li>
                        <li><s:link href="/order/create.jsp"><f:message key="order.create.title"/></s:link></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><f:message key="admin"/><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><s:link href="/order/list.jsp"><f:message key="order.catalog"/></s:link></li>
                                <li><s:link href="/customer/list.jsp"><f:message key="customer.catalog"/></s:link></li>
                                <li><s:link href="/tyre/list.jsp"><f:message key="tyre.catalog"/></s:link></li>
                                <li><s:link href="/extraservice/list.jsp"><f:message key="extraService.catalog"/></s:link></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    </div>
