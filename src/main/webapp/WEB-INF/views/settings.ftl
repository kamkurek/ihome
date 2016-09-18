<#import "/spring.ftl" as spring />
<#setting locale="en_us">
<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "common-headers.ftl"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/jquery.dataTables.min.css'/>"/>
        <script src="<@spring.url '/js/jquery.dataTables.min.js'/>"></script>
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

        <h3>Widgets</h3>
        <table class="table table-striped">
            <thead><tr><th>#</th><th>Name</th><th>Edit</th><th>Delete</th></tr></thead>
        <tbody>
        <#list widgets as widget>
            <tr>
                <td>${widget?counter}</td>
                <td>${widget.name}</td>
                <td>
                    <form method="GET" action="<@spring.url '/widget-settings'/>">
                        <input type="hidden" name="id" value="${widget.id}">
                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>
                </td>
                <td>
                    <form method="POST" action="<@spring.url '/widget-delete'/>">
                        <input type="hidden" name="id" value="${widget.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </#list>
        </table>
        <form method="GET" action="<@spring.url '/widget-settings'/>">
            <button type="submit" class="btn btn-default">Create widget</button>
        </form>

        <h3>Sensors</h3>
        <table class="table table-striped">
            <thead><tr><th>#</th><th>Id</th><th>Name</th><th>Edit</th><th>Delete</th></tr></thead>
        <tbody>
        <#list sensors as sensor>
        <tr>
            <td>${sensor?counter}</td>
            <td>${sensor.id}</td>
            <td>${sensor.name}</td>
            <td>
                <form method="GET" action="<@spring.url '/sensor-settings'/>">
                    <input type="hidden" name="id" value="${sensor.id}">
                    <button type="submit" class="btn btn-primary">Edit</button>
                </form>
            </td>
            <td>
                <form method="POST" action="<@spring.url '/sensor-delete'/>">
                    <input type="hidden" name="id" value="${sensor.id}">
                    <button type="submit" class="btn btn-primary">Delete</button>
                </form>
            </td>
        </tr>
        </#list>
        </table>
        <form method="GET" action="<@spring.url '/sensor-settings'/>">
            <button type="submit" class="btn btn-default">Create sensor</button>
        </form>

    </div>
    </body>
</html>