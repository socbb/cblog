<#assign nav_active = "系统管理_角色管理">
<#include '/common/macro.ftl'>
<@layout>
<link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/metroStyle/metroStyle.min.css" rel="stylesheet">
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

<div class="modal inmodal" id="allot_menu" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content animated flipInY">
            <div class="modal-header" style="padding: 15px 15px">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <small class="font-bold pull-left">权限设置</small>
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
            url: "/role/list",
            columns: [
            {
                checkbox: true
            }, {
                field: 'name',
                title: '名称',
                editable: false
            }, {
                field: "status",
                title: '状态',
                formatter: function (code, row, index) {
                    if (code == 1) {
                        return "<a class=\"btn btn-xs btn-primary\" href=\"javascript:;\">正常</a>";
                    } else {
                        return "<a class=\"btn btn-xs btn-danger\" href=\"javascript:;\">禁用</a>";
                    }
                }
            },{
                field: 'remark',
                title: '备注',
                editable: true
            }, {
                field: 'operate',
                title: '操作',
                formatter: function (code, row, index) {
                    var btn = "<@shiro.hasPermission name='role:edit'>" + "<a title='修改' onclick=\"$.operate.form('/role/form?id="+row.id+"')\" class=\"btn btn-xs btn-success\"><i class=\"fa fa-edit\"></i>编辑</a>" + "</@shiro.hasPermission>";
                    if (row.id != 1 || "${user.id?c}" == "1") {
                        btn += " <@shiro.hasPermission name='role:allot'>" + "<a title='权限设置' onclick=\"tree.init("+row.id+")\" class=\"btn btn-xs btn-info\"><i class=\"fa fa-wrench\"></i>权限设置</a>" + "</@shiro.hasPermission>";
                    }
                    return btn;
                }
            }]
        }
        $.table.init(option);
    })

    var tree = {};
    tree.roleId = null;
    tree.init = function (id) {
        tree.roleId = id;
        $.ajax({
            async: false,
            type: "POST",
            url: '/role/allot/get/' + id,
            dataType: 'json',
            success: function (json) {
                var data = json.data;
                console.log(data);
                var setting = {
                    check: {
                        enable: true,
                        chkboxType: {"Y": "ps", "N": "ps"},
                        chkStyle: "checkbox"
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

                $('#allot_menu').modal('show');
            }
        });
    }

    tree.save = function () {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var ids = new Array();
        for (var i = 0; i < nodes.length; i++) {
            //获取选中节点的值
            ids.push(nodes[i].id);
        }
        $.post('/role/allot/save', {"roleId": tree.roleId, "menuIds": JSON.stringify(ids)}, function (res) {
             if (res.code === 200) {
                $("#allot_menu").modal('hide');
                $.sweet.success("权限更新成功! ");
             } else {
                $.sweet.success("保存失败! ");
             }
        }, 'json');
    }
</script>
</@layout>
