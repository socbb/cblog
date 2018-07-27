/**
 * jquery 扩展方法
 *
 * 调用方式: $(this).method()
 *
 */
$.extend($.fn, {
    /**
     * 获取form中的参数对象
     */
    param: function() {
        var pv = {};
        $.each($(this).serializeArray(),function(index, obj){
            var key = $.trim(obj.name);
            var val = $.trim(obj.value);
            if (val) {
                var pvElement = pv[key];
                var arr = [];
                if (pvElement) {
                    arr.push(pvElement, val);
                    val = arr.join(",");
                }
                pv[key] = val;
            }

        });
        return pv;
    }
});

/**
 * 扩展 Date 方法
 */
$.extend(Date.prototype, {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    format: function(format){
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        };

        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
        }

        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
});

/**
 * 扩展String方法
 */
$.extend(String.prototype, {
    trim: function () {
        return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
    },
    startsWith: function (pattern) {
        return this.indexOf(pattern) === 0;
    },
    endsWith: function (pattern) {
        var d = this.length - pattern.length;
        return d >= 0 && this.lastIndexOf(pattern) === d;
    },
    replaceSuffix: function (index) {
        return this.replace(/\[[0-9]+\]/, '[' + index + ']').replace('#index#', index);
    },
    getRequestURI: function () {
        var indexOf = this.indexOf("?");
        return (indexOf == -1) ? this : this.substr(0, indexOf);
    },
    getParams: function (encode) {
        var params = {},
            indexOf = this.indexOf("?");
        if (indexOf != -1) {
            var str = this.substr(indexOf + 1),
                strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                var item = strs[i].split("=");
                var val = encode ? item[1].encodeParam() : item[1];
                params[item[0]] = item.length > 1 ? val : '';
            }
        }
        return params;
    },
    encodeParam: function () {
        return encodeURIComponent(this);
    },
    replaceAll: function (os, ns) {
        return this.replace(new RegExp(os, "gm"), ns);
    },
    skipChar: function (ch) {
        if (!this || this.length === 0) {
            return '';
        }
        if (this.charAt(0) === ch) {
            return this.substring(1).skipChar(ch);
        }
        return this;
    },
    isPositiveInteger: function () {
        return (new RegExp(/^[1-9]\d*$/).test(this));
    },
    isInteger: function () {
        return (new RegExp(/^\d+$/).test(this));
    },
    isNumber: function (value, element) {
        return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
    },
    isValidPwd: function () {
        return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this));
    },
    isValidMail: function () {
        return (new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
    },
    isSpaces: function () {
        for (var i = 0; i < this.length; i += 1) {
            var ch = this.charAt(i);
            if (ch != ' ' && ch != "\n" && ch != "\t" && ch != "\r") {
                return false;
            }
        }
        return true;
    },
    isMobile: function () {
        return (new RegExp(/(^[0-9]{11,11}$)/).test(this));
    },
    isUrl: function () {
        return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
    },
    isExternalUrl: function () {
        return this.isUrl() && this.indexOf("://" + document.domain) == -1;
    },
    parseCurrency: function (num) {
        var numberValue = parseFloat(this);
        return parseFloat(numberValue.toFixed(num || 2));
    }
});

(function ($) {
    $.extend({
        /**
         * growl 提示
         */
        tip: {
            msg: function (msg, type) {
                $.growl({
                    type: type,
                    message: msg
                });
            },
            info: function(msg) {
                $.growl.msg(msg, 'info')
            },
            warn: function(msg) {
                $.growl.msg(msg, 'danger')
            },
            success: function(msg) {
                $.growl.msg(msg, 'success')
            }
        },
        /**
         * sweetalert 弹窗
         */
        sweet: {
            alert: function (msg, type) {
                swal({
                    title: msg,
                    type: type
                })
            },
            info: function(msg) {
                $.sweet.alert(msg)
            },
            warn: function(msg) {
                $.sweet.alert(msg, "warning")
            },
            success: function(msg) {
                $.sweet.alert(msg, 'success')
            },
            confirm: function (msg, ok, cancal){
                swal({
                        title: msg,
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "yes",
                        closeOnConfirm: false
                    },
                    function(){
                        if (ok) {
                            ok();
                        }
                    });
            }
        },
        /**
         * bootstrap modal
         */
        modal: {
            close: function (selector) {
                $(selector).modal('hide');
                $(selector).remove();
            }
        }
    });
})(jQuery);