<script type="text/javascript">
    var timeZoneOffset = new Date().toString().match(/([-\+][0-9]+)\s/)[1];
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
        chart${uuid}.options.data[0].dataPoints.push({x: new Date('${row.getDate()}'+timeZoneOffset), y: parseFloat(${row.getValue()})});
        </#list>

        <#if dataMap[uuid]?size!=0>
        chart${uuid}.render();
        </#if>
    </#list>
</script>