<%@include file="taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="css/style.css" />

<div id="header">
    <div id="navigation">
        <div class="navbar navbar-fixed-top navbar-inverse">
            <div class="navbar-inner">
                <div class="container">
                  
                    <s:link class="brand" href="/"><f:message key="stis.client"/></s:link>
                    <ul class="nav">                        
                        <li><s:link href="${ctx}/tyre/list.jsp" ><f:message key="tyre.catalog"/></s:link></li>
                        <li><s:link href="${ctx}/extraservice/list.jsp"><f:message key="extraService.catalog"/></s:link></li>                                                
                    </ul>
                </div>
            </div>
        </div>
    </div>
    </div>
