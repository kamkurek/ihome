<#import "/spring.ftl" as spring />
<#setting locale="en_us">
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "common-headers.ftl"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/boiler.css'/>"/>
    <script src="<@spring.url '/resources/js/canvasjs.min.js'/>"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 col-md-offset-0">
            <div id="chartContainer"></div>
        </div>
    </div>
</div>

<script src="<@spring.url '/resources/js/boiler.js'/>"></script>
<script type="text/javascript">
    <#list data as row>
        dataPoints.push({x: new Date('${row.getDate()}+02:00'), y: parseFloat(${row.getTemperature()})});
    </#list>
    pushDataCompleted();
</script>

</body>
</html>