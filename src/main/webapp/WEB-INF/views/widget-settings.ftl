<#import "/spring.ftl" as spring />
<#setting locale="en_us">
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "common-headers.ftl"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/index.css'/>"/>
</head>

<body>
<nav class="navbar navbar-inverse" style="border-radius:0px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<@spring.url '/'/>">IHome</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <form role="form" method="post">
    Sensors:
    <table class="table table-striped">
        <thead><tr><th>#</th><th>Id</th><th>Select</th></tr></thead>
        <tbody>
        <#list sensors as sensor>
            <tr>
                <td>${sensor?counter}</td>
                <td>${sensor}</td>
                <td>
                   <input type="checkbox" name="sensor" value="${sensor}" <#if currentSensor == sensor>checked="true"</#if>>
                </td>
            </tr>
        </#list>
    </table>

        <#list parameters?keys as parameter>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="${parameter}">${parameter}</label>
                        <input type="text" class="form-control" value="${parameters[parameter]}" name="${parameter}">
                    </div>
                </div>
            </div>
        </#list>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
<script>
    $('input[type="checkbox"]').on('change', function() {
        $('input[name="' + this.name + '"]').not(this).prop('checked', false);
    });
</script>
</body>
</html>