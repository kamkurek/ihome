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
        <table class="table table-striped">
            <thead><tr><th>#</th><th>Name</th><th>Edit</th></tr></thead>
        <tbody>
        <#list widgets as widget>
            <tr>
                <td>${widget?counter}</td>
                <td>${widget.name}</td>
                <td>
                    <div id="edit" class="btn btn-primary">Edit</div>
                </td>
            </tr>
        </#list>
        </table>
        <form method="GET" action="<@spring.url '/widget-settings'/>">
            <button type="submit" class="btn btn-default">Create widget</button>
        </form>
    </div>
    </body>
</html>