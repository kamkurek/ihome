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
        <thead><tr><th>#</th><th>Name</th><th>Select</th></tr></thead>
        <tbody>
        <#list sensors as sensor>
            <tr>
                <td>${sensor?counter}</td>
                <td>${sensor.name}</td>
                <td>
                   <input type="checkbox" name="sensor" value="${sensor.id}" <#if widget.sensor?? && widget.sensor == sensor.id>checked="true"</#if>>
                </td>
            </tr>
        </#list>
    </table>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input id="name" type="text" class="form-control" name="name" value="${widget.name!""}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="color">Color</label>
                    <input id="color" type="text" class="form-control" name="color" value="${widget.color!""}">
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