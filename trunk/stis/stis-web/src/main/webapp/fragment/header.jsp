<%@include file="taglibs.jsp"%>
<div id="header">
    <div>
        <h1>STIS (So Tired Information System)</h1>
    </div>
    <div id="navigation">
        <div class="navbar">
            <div class="navbar-inner">
                <s:link class="brand" href="/">STIS</s:link>
                <ul class="nav">
                    <li><s:link href="/tyre/list.jsp">Tyre catalog</s:link></li>
                    <li><s:link href="/extraservice/list.jsp">Extra service catalog</s:link></li>
                    <li><s:link href="/order/create.jsp">Create order</s:link></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><s:link href="/order/list.jsp">Orders</s:link></li>
                            <li><s:link href="/customer/list.jsp">Customers</s:link></li>
                            <li><s:link href="/tyre/list.jsp">Tyres</s:link></li>
                            <li><s:link href="/extraservice/list.jsp">Extra services</s:link></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>