<#import "/spring.ftl" as spring />
<#setting locale="en_us">
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "common-headers.ftl"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/index.css'/>"/>
    <script src="<@spring.url '/resources/js/canvasjs.min.js'/>"></script>
</head>

<body>
<nav class="navbar navbar-inverse" style="border-radius:0px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">IHome</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${interval}<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="?hours=3">3 hours</a></li>
                <li><a href="?hours=8">8 hours</a></li>
                <li><a href="?hours=24">1 day</a></li>
                <li><a href="?hours=48">2 days</a></li>
                <li><a href="?hours=72">3 days</a></li>
                <li><a href="?hours=168">7 days</a></li>
                <li class="divider"></li>
                <li>
                    <div class="row">
                        <div class="col-md-10 col-md-offset-1">
                            <form action="">
                                <input type="text" class="form-control input-sm" name="hours" placeholder="custom (hours)">
                            </form>
                        </div>
                    </div>
                </li>
            </ul>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <#list latestDataMap?keys as uuid>
        <div class="panel panel-default">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-stats"></span>
                ${sensorNames[uuid]} temperature: ${latestDataMap[uuid]}<sup>o</sup>C
            </div>
            <div class="panel-body">
                <#if dataMap[uuid]?size!=0>
                    <div id="chartContainer${uuid}" class="chartContainer"></div>
                <#else>
                    <div class="text-center"><h3>No data</h3></div>
                </#if>
            </div>
        </div>
    </#list>
</div>
<#include "index-js.ftl"/>
</body>
</html>