<#if bean??>
<#assign title = "修改角色">
<#else>
<#assign title = "添加角色">
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
                        <label class="col-lg-2 control-label">状态</label>
                        <div class="col-lg-10">
                            <select class="form-control m-b required" name="status">
                                <option value="1" <#if bean.status == 1>selected</#if>>正常</option>
                                <option value="0" <#if bean.status == 0>selected</#if>>禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">备注</label>
                        <div class="col-lg-10">
                            <input type="text" name="remark" class="form-control" value="${bean.remark!}">
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
        $.post('/role/save', $form.param(), function(res){
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