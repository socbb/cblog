<#include "/common/macro.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title = (config.name!"竹子的博客")>
    <@header></@header>
</head>
<body>
<div id="wrapper">
    <#include "/common/nav.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/common/top.ftl">
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>This is main title</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">This is</a>
                    </li>
                    <li class="active">
                        <strong>Breadcrumb</strong>
                    </li>
                </ol>
            </div>
            <div class="col-sm-8">
                <div class="title-action">
                    <a href="empty_page.html" class="btn btn-primary">This is action area</a>
                </div>
            </div>
        </div>
        <div class="wrapper wrapper-content">
            <div class="middle-box text-center animated fadeInRightBig">
                <table id="table"></table>
            </div>
        </div>
        <#include "/common/footer.ftl">
    </div>
</div>
<@footer></@footer>
<script type="text/javascript">
    $('#table').bootstrapTable({
        columns: [{
            field: 'id',
            title: 'Item ID'
        }, {
            field: 'name',
            title: 'Item Name'
        }, {
            field: 'price',
            title: 'Item Price'
        }],
        data: [{
            id: 1,
            name: 'Item 1',
            price: '$1'
        }, {
            id: 2,
            name: 'Item 2',
            price: '$2'
        }]
    });
</script>
</body>
</html>
