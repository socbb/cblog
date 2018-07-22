var Utils = {};

Utils.getParam = function (form) {
    var pv = {};
    $.each(form.serializeArray(),function(index, obj){
        var key = $.trim(obj.name);
        var val = $.trim(obj.value);
        if(val != '') pv[key] = val;
    });
    return pv;
};