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
                <h2></h2>
                <ol class="breadcrumb">
                    <li><a href="javascript:;">系统管理</a></li>
                    <li class="active"><strong>用户管理</strong></li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>用户列表</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="table_foo_table.html#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="table_foo_table.html#">Excel</a>
                                    </li>
                                    <li><a href="table_foo_table.html#">CSV</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <table id="table" data-classes="table table-hover" data-search="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"></table>
                        </div>
                    </div>
                </div>
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
