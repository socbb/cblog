<#assign menu = "system_menu">
<#include '/common/macro.ftl'>
<@layout>
<link href="https://cdn.bootcss.com/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css" rel="stylesheet">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2></h2>
        <ol class="breadcrumb">
            <li><a href="javascript:;">系统管理</a></li>
            <li class="active"><strong>菜单管理</strong></li>
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
                    <div class="table-responsive">
                        <div id="toolbar">
                            <div class="btn-group">
                            <@shiro.hasPermission name="menu:add">
                                <button class="btn btn-sm btn-primary" onclick="$.operate.form('/menu/form')">
                                    <i class="glyphicon glyphicon-plus"></i>
                                </button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="menu:delete">
                            <button class="btn btn-sm btn-danger" onclick="$.operate.delete('/menu/delete')">
                                <i class="glyphicon glyphicon-trash"></i>
                            </button>
                            </@shiro.hasPermission>
                            </div>
                        </div>
                        <table id="table" data-classes="table table-hover" data-mobile-responsive="true"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/common/footer.ftl">
<script src="https://cdn.bootcss.com/bootstrap-table/1.12.0/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-treegrid/0.2.0/js/jquery.treegrid.min.js"></script>
<script type="text/javascript">
    var operateFormatter = function(code, row, index) {
        return "<@shiro.hasPermission name='menu:edit'>" + "<a title='修改' onclick=\"$.operate.form('/menu/form?id="+row.id+"')\" class=\"btn btn-xs btn-success\"><i class=\"fa fa-edit\"></i>编辑</a>" + "</@shiro.hasPermission>";
    }

    $(function () {
        var option = {
            url: "/menu/list",
            treeShowField: 'name',
            parentIdField: 'parentId',
            columns: [{
                checkbox: true
            }, {
                field: 'name',
                title: '名称'
            }, {
                field: "type",
                title: '类型',
                formatter: function (code, row, index) {
                    if (code == 1) {
                        return "<a class=\"btn btn-xs btn-primary\" href=\"javascript:;\">菜单</a>";
                    } else {
                        return "<a class=\"btn btn-xs btn-warning\" href=\"javascript:;\">按钮</a>";
                    }
                }
            },{
                field: 'url',
                title: '路径'
            }, {
                field: 'perms',
                title: '权限标识'
            },{
                field: 'icon',
                title: '图标'
            }, {
                field: 'seq',
                title: '序号'
            },{
                field: 'operate',
                title: '操作',
                formatter: operateFormatter
            }],
            onLoadSuccess: function(data) {
                $("#table").treegrid({
                    treeColumn: 1,
                    onChange: function() {
                        $("#table").bootstrapTable('resetWidth');
                    }
                });
            }
        };
        $.table.init(option);
    })
</script>
</@layout>
