<#assign nav_active = "系统管理_用户管理">
<#include '/common/macro.ftl'>
<@layout>
<link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/metroStyle/metroStyle.min.css" rel="stylesheet">
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

<#-- 分配角色 -->
<div class="modal inmodal" id="allot_role" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content animated flipInY">
            <div class="modal-header" style="padding: 15px 15px">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <small class="font-bold pull-left">分配角色</small>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="tree.save()">保存</button>
            </div>
        </div>
    </div>
</div>

<#include "/common/footer.ftl">
<script src="https://cdn.bootcss.com/zTree.v3/3.5.29/js/jquery.ztree.core.min.js"></script>
<script src="https://cdn.bootcss.com/zTree.v3/3.5.29/js/jquery.ztree.excheck.min.js"></script>
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
                    var btn = "<@shiro.hasPermission name='user:edit'>" + "<a title='修改' onclick=\"$.operate.form('/user/form?id="+row.id+"')\" class=\"btn btn-xs btn-success\"><i class=\"fa fa-edit\"></i>编辑</a>" + "</@shiro.hasPermission>";
                    if (row.id != 1 || "${user.id?c}" == "1") {
                        btn += " <@shiro.hasPermission name='user:allotRole'>" + "<a title='分配角色' onclick=\"tree.init("+row.id+")\" class=\"btn btn-xs btn-info\"><i class=\"fa fa-wrench\"></i>分配角色</a>" + "</@shiro.hasPermission>";
                    }
                    return btn;
                }
            }]
        }
        $.table.init(option);
    })

    var tree = {};
    tree.id = null;
    tree.init = function (id) {
        tree.id = id;
        $.ajax({
            async: false,
            type: "get",
            url: '/role/allotRole/list/' + id,
            dataType: 'json',
            success: function (json) {
                var data = json.data;
                var setting = {
                    check: {
                        enable: true,
                        chkboxType: {"Y": "ps", "N": "ps"},
                        chkStyle: "radio"
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: function (event, treeId, treeNode) {
                            console.log(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
                        }
                    }
                };
                var tree = $.fn.zTree.init($("#treeDemo"), setting, data);
                tree.expandAll(true);//全部展开

                $('#allot_role').modal('show');
            }
        });
    }

    tree.save = function () {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var ids = [];
        for (var i = 0; i < nodes.length; i++) {
            //获取选中节点的值
            ids.push(nodes[i].id);
        }
        if (ids.length <= 0) {
            $.sweet.warn("请至少选择一个角色!");
            return;
        }
        $.post('/user/allotRole/' + tree.id, {"roleId": JSON.stringify(ids)}, function (res) {
            if (res.code === 200) {
                $("#allot_role").modal('hide');
                $.sweet.success("角色更新成功! ");
            } else {
                $.sweet.success("保存失败! ");
            }
        }, 'json');
    }
</script>
</@layout>
