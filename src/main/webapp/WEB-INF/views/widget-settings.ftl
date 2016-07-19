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
            <a class="navbar-brand" href="">IHome</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <form role="form" method="post">
        <#list parameters?keys as parameter>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="${parameter}">${parameter}</label>
                        <input type="text" class="form-control" id="${parameter}" value="${parameters[parameter]}">
                    </div>
                </div>
            </div>
        </#list>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

</body>
</html>