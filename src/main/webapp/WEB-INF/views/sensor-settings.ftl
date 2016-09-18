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
        <thead><tr><th>#</th><th>Id</th><th>Number of rows</th><th>Latest date</th><th>Name</th></tr></thead>
        <tbody>
        <#list data as dataRow>
            <input type="hidden" name="id" value="${dataRow.id}"/>
            <tr>
                <td>${dataRow?counter}</td>
                <td>${dataRow.id}</td>
                <td>${dataRow.numberOfRows}</td>
                <td>${dataRow.latestData}</td>
                <td>
                    <input class="form-control" type="text" name="name" <#if sensors[dataRow.id]??>value="${sensors[dataRow.id].name!""}"</#if> >
                </td>
                <#--<td>-->
                   <#--<input type="checkbox" name="id" value="${dataRow.id}" <#if sensor.id?? && sensor.id == dataRow.id>checked="true"</#if>>-->
                <#--</td>-->
            </tr>
        </#list>
    </table>
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