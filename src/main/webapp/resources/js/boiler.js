
var chart = new CanvasJS.Chart("chartContainer4023121486600112",{
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

var dataPoints = [];

function pushDataCompleted() {
    chart.options.data[0].dataPoints = dataPoints;
    chart.render();    
};




