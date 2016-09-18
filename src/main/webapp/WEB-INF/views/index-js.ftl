<script type="text/javascript">
    <#list widgets as widget>
    <#if widget.type == "CHART">
        <#assign colorSet = "'colorSet"+widget.id+"'">
        CanvasJS.addColorSet(${colorSet}, ["${widget.color!'#000000'}"]);
        var chart${widget.id} = new CanvasJS.Chart("chartContainer${widget.id}",{
            colorSet:  ${colorSet},
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

        <#list dataMap[widget.sensor] as row>
            chart${widget.id}.options.data[0].dataPoints.push({x: new Date('${row.getDate()}+02:00'), y: parseFloat(${row.getValue()})});
        </#list>

        <#if dataMap[widget.sensor]?size!=0>
            chart${widget.id}.render();
        </#if>
    </#if>
    </#list>
</script>