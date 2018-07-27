/**
 * bootstrap-table 工具类
 * 依赖bootstrap-table
 */
(function ($) {
    $.extend({
        table: {
            _option: {},
            init: function (options) {
                // 默认属性
                var defaultOption = {
                    method: 'post',                      //请求方式（*）
                    toolbar: '#toolbar',                //工具按钮用哪个容器
                    striped: true,                      //是否显示行间隔色
                    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    contentType: "application/x-www-form-urlencoded", // 发送到服务器的数据编码类型, application/x-www-form-urlencoded为了实现post方式提交
                    sortable: true,                     //是否启用排序
                    sortOrder: "asc",                   //排序方式
                    sortStable: true,                   // 设置为 true 将获得稳定的排序
                    queryParams: $.table.queryParams,//传递参数（*）
                    queryParamsType: '',
                    pagination: true,                   //是否显示分页（*）
                    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,                       //每页的记录行数（*）
                    pageList: [10, 20, 50, 100, 500],        //可供选择的每页的行数（*）
                    search: true,                       //是否启用搜索框 根据sidePagination选择从前后台搜索
                    strictSearch: true,                 //设置为 true启用 全匹配搜索，否则为模糊搜索
                    searchOnEnterKey: true,            // 设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    minimumCountColumns: 1,             //最少允许的列数
                    showColumns: true,                  //是否显示 内容列下拉框
                    showRefresh: true,                  //是否显示刷新按钮
                    showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
                    detailView: false,                   //是否显示父子表
                    showExport: true,                   //是否显示导出
                    exportDataType: "basic",              //basic', 'all', 'selected'.
                    clickToSelect: false,                //是否启用点击选中行
                    singleSelect: false,                // 是否单选
                    // height: $(window).height()                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                }
                $.table._option = $.extend(defaultOption, options);
                $('#table').bootstrapTable($.table._option);
            },
            queryParams: function (params) {
                params = $.extend({}, params);
                params.keywords = params.searchText;
                return params;
            },
            refresh: function () {
                $("#table").bootstrapTable('refresh', {url: $.table._option.url});
            },
            // 查询选中列值
            selectColumns: function(column) {
                return $.map($('#table').bootstrapTable('getSelections'), function (row) {
                    return row[column];
                });
            },
            // 查询选中首列值
            selectFirstColumn: function() {
                return $.map($('#table').bootstrapTable('getSelections'), function (row) {
                    return row[$.table._option.columns[1].field];
                });
            },
            selectIds: function () {
                var selectedJson = $("#table").bootstrapTable('getAllSelections');
                var ids = [];
                $.each(selectedJson, function (i) {
                    ids.push(selectedJson[i].id);
                });
                return ids;
            }
        },
        /**
         * 操作处理
         */
        operate: {
            form: function (url) {
                $("#modal-form").remove();
                $.get(url, function (res) {
                    $("body").append(res);
                    $('#modal-form').modal('show')
                });
            },
            delete: function (url) {
                var selectIds = $.table.selectIds();
                if (selectIds.length <= 0) {
                    $.tip.msg("请至少选择一条记录");
                    return;
                }
                $.sweet.confirm("确定删除吗?", function() {
                    $.post(url, {"ids": selectIds}, function (res) {
                        if (res.code == 200) {
                            $.table.refresh();
                            $.sweet.success("删除成功");
                        } else {
                            $.sweet.warn(res.msg);
                        }
                    }, 'json');
                })
            }
        }
    });
})(jQuery);