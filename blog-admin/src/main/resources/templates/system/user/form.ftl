<#if bean??>
<#assign title = "修改用户">
<#else>
<#assign title = "添加用户">
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
                        <label class="col-lg-2 control-label">用户名</label>
                        <div class="col-lg-4">
                            <input type="text" name="username" class="form-control required" value="${bean.username!}" <#if bean??>disabled</#if> >
                        </div>
                        <label class="col-lg-2 control-label">昵称</label>
                        <div class="col-lg-4">
                            <input type="text" name="nickname" class="form-control required" value="${bean.nickname!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">密码</label>
                        <div class="col-lg-4">
                            <input type="password" name="password" placeholder="不修改密码请不要填写" class="form-control <#if bean??><#else>required</#if>">
                        </div>
                        <label class="col-lg-2 control-label">手机号</label>
                        <div class="col-lg-4">
                            <input type="text" name="mobile" class="form-control" value="${bean.mobile!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">邮箱</label>
                        <div class="col-lg-4">
                            <input type="text" name="email" class="form-control" value="${bean.email!}">
                        </div>
                        <label class="col-lg-2 control-label">状态</label>
                        <div class="col-lg-4">
                            <select class="form-control m-b required" name="status">
                                <option value="1" <#if bean.status == 1>selected</#if>>正常</option>
                                <option value="0" <#if bean.status == 0>selected</#if>>禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">头像</label>
                        <div class="col-lg-10">
                            <input type="text" name="avatar" class="form-control" value="${bean.avatar!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">简介</label>
                        <div class="col-lg-10">
                            <input type="text" name="description" class="form-control" value="${bean.description!}">
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
        $.post('/user/save', $form.param(), function(res){
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