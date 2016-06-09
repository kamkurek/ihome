<#import "/spring.ftl" as spring />
<#setting locale="en_us">
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "common-headers.ftl"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/index.css'/>"/>
    <script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse" style="border-radius:0px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">IHome</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <#list latestDataMap?keys as uuid>
        <div class="panel panel-default">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-stats"></span>
                ${sensorNames[uuid]} temperature: ${latestDataMap[uuid]}<sup>o</sup>C
                <div class="pull-right">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            ${interval}
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right" role="menu">
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
                    </div>
                </div>
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

<script type="text/javascript">
    <#list dataMap?keys as uuid>
        var chart${uuid} = new CanvasJS.Chart("chartContainer${uuid}",{
            title:{
                fontSize: 18
            },
            toolTip:{
                content: "<font size='6' color='red'>{y}</font><br>{x}"
            },
            axisX:{
                valueFormatString: "DD-HH:mm",
                gridThickness: 0.5,
                interval:3,
                intervalType: "hour",
                labelFontSize: 14,
                labelAngle: -50
            },
            axisY: {
            },
            data: [
                {
                    type: "area",
                    xValueFormatString: "HH:mm",
                    dataPoints: []
                }
            ]
        });

        <#list dataMap[uuid] as row>
            chart${uuid}.options.data[0].dataPoints.push({x: new Date('${row.getDate()}+02:00'), y: parseFloat(${row.getValue()})});
        </#list>

        <#if dataMap[uuid]?size!=0>
            chart${uuid}.render();
        </#if>
    </#list>
</script>

</body>
</html>