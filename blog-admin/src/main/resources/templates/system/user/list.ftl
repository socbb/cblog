<#assign nav_active = "系统管理_用户管理">
<#include '/common/macro.ftl'>
<@layout>
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
                <div class="table-responsive">
                    <div id="toolbar">
                        <div class="btn-group">
                            <@shiro.hasPermission name="user:add">
                            <button class="btn btn-sm btn-primary" onclick="$.operate.form('/user/form')">
                                <i class="glyphicon glyphicon-plus"></i>
                            </button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="user:delete">
                            <button class="btn btn-sm btn-danger" onclick="$.operate.delete('/user/delete')">
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
            url: "/user/list",
            columns: [
            {
                checkbox: true
            }, {
                field: 'username',
                title: '用户名',
                editable: false
            }, {
                field: 'nickname',
                title: '昵称',
                editable: true
            }, {
                field: 'email',
                title: '邮箱'
            }, {
                field: 'mobile',
                title: '手机'
            }, {
                field: 'status',
                title: '状态',
                formatter: function (code, row, index) {
                    if (code == 1) {
                        return "<a class=\"btn btn-xs btn-primary\" href=\"javascript:;\">正常</a>";
                    } else {
                        return "<a class=\"btn btn-xs btn-danger\" href=\"javascript:;\">禁用</a>";
                    }
                }
            }, {
                field: 'createTime',
                title: '创建时间',
                sortTable: true,
                formatter: function (code) {
                    return (!code) ? "" : new Date(code).format("yyyy-MM-dd hh:mm:ss")
                }
            }, {
                field: 'lastLoginTime',
                title: '最后登录时间',
                editable: false,
                sortTable: true,
                formatter: function (code) {
                    return (!code) ? "" : new Date(code).format("yyyy-MM-dd hh:mm:ss")
                }
            }, {
                field: 'lastLoginIp',
                title: '最后登录IP'
            }, {
                field: 'loginCount',
                title: '登录次数',
                editable: false
            }, {
                field: 'operate',
                title: '操作',
                formatter: function (code, row, index) {
                    return "<@shiro.hasPermission name='user:edit'>" + "<a title='修改' onclick=\"$.operate.form('/user/form?id="+row.id+"')\" class=\"btn btn-xs btn-success\"><i class=\"fa fa-edit\"></i>编辑</a>" + "</@shiro.hasPermission>";
                }
            }]
        }
        $.table.init(option);
    })
</script>
</@layout>
