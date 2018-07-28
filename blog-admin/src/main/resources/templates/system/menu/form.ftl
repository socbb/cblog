<#if bean??>
    <#assign title = "修改菜单">
<#else>
    <#assign title = "添加菜单">
</#if>
<div class="modal inmodal" id="modal-form" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content animated flipInY">
            <div class="modal-header" style="padding: 15px 15px">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <small class="font-bold pull-left">${title}</small>
            </div>
            <div class="modal-body">
                <form id="form" class="form-horizontal form-label-left">
                    <input type="hidden" name="id" value="<#if bean??>${bean.id?c}</#if>">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">名称</label>
                        <div class="col-lg-10">
                            <input type="text" name="name" class="form-control required" value="${bean.name!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">路径</label>
                        <div class="col-lg-10">
                            <input type="text" name="url" class="form-control" value="${bean.url!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">权限</label>
                        <div class="col-lg-10">
                            <input type="text" name="perms" class="form-control required" value="${bean.perms!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">父菜单</label>
                        <div class="col-lg-10">
                            <select class="form-control m-b" name="parentId">
                                <option value=""></option>
                                <@system method="menus" type=1; menus>
                                <#if menus?? && menus?size gt 0>
                                    <#list menus as m>
                                        <option value="${m.id?c}">${m.name}</option>
                                        <#if m.children?? && m.children?size gt 0>
                                            <#list m.children as child>
                                                <option value="${child.id?c}">|-${child.name}</option>
                                            </#list>
                                        </#if>
                                    </#list>
                                </#if>
                                </@system>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">图标</label>
                        <div class="col-lg-10">
                            <input type="text" name="icon" class="form-control" value="${bean.icon!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">类型</label>
                        <div class="col-lg-10">
                            <select class="form-control m-b required" name="type">
                                <option value="1" <#if bean.type == 1>selected</#if>>菜单</option>
                                <option value="2" <#if bean.type == 2>selected</#if>>按钮</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">序号</label>
                        <div class="col-lg-10">
                            <input type="text" name="seq" class="form-control" value="<#if bean??>${bean.seq?c}</#if>">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="save()">保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $("#form").validate();
    });

    var save = function () {
        var $form = $("#form");
        if (!$form.valid()) {
            return;
        }
        $.post('/menu/save', $form.param(), function(res){
            $.modal.close("#modal-form");
            if (res.code === 200) {
                $.table.refresh();
                $.sweet.success("保存成功");
            } else {
                $.sweet.warn(res.msg)
            }
        }, 'json');
    }
</script>