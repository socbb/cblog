<#include '/common/macro.ftl'>
<@layout>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2></h2>
        <ol class="breadcrumb">
            <li><a href="javascript:;">系统管理</a></li>
            <li class="active"><strong>角色管理</strong></li>
        </ol>
    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>角色列表</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="table_foo_table.html#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="javascript:;">Excel</a>
                        </li>
                        <li><a href="javascript:;">CSV</a>
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
                            <@shiro.hasPermission name="role:add">
                            <button class="btn btn-sm btn-primary" onclick="$.operate.form('/role/form')">
                                <i class="glyphicon glyphicon-plus"></i>
                            </button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="role:delete">
                            <button class="btn btn-sm btn-danger" onclick="$.operate.delete('/role/delete')">
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
<script type="text/javascript">
    $(function () {
        var option = {
            url: "/role/list",
            columns: [
            {
                checkbox: true
            }, {
                field: 'name',
                title: '名称',
                editable: false
            }, {
                field: 'remark',
                title: '备注',
                editable: true
            }, {
                field: 'operate',
                title: '操作',
                formatter: function (code, row, index) {
                    return "<@shiro.hasPermission name='role:edit'>" + "<a title='修改' onclick=\"$.operate.form('/role/form?id="+row.id+"')\" class=\"btn btn-xs btn-success\"><i class=\"fa fa-edit\"></i>编辑</a>" + "</@shiro.hasPermission>";
                }
            }]
        }
        $.table.init(option);
    })
</script>
</@layout>
