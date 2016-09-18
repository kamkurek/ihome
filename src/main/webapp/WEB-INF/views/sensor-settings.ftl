<#import "/spring.ftl" as spring />
<#setting locale="en_us">
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "common-headers.ftl"/>
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
        <thead><tr><th>#</th><th>Id</th><th>Number of rows</th><th>Latest data</th><th>Select</th></tr></thead>
        <tbody>
        <#list data as dataRow>
            <tr>
                <td>${dataRow?counter}</td>
                <td>${dataRow.id}</td>
                <td>${dataRow.numberOfRows}</td>
                <td>${dataRow.latestData}</td>
                <td>
                   <input type="checkbox" name="sensor" value="${sensor}" <#if sensor.id?? && sensor.id == dataRow.id>checked="true"</#if>>
                </td>
            </tr>
        </#list>
    </table>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input id="name" type="text" class="form-control" name="name" value="${sensor.name!""}">
                </div>
            </div>
        </div>
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