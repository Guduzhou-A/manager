/**
 * 转换语种
 */
function convertLanguage(language) {
    var language_str = "";
    switch (language) {
        case 0:
            language_str = "其它";
            break;
        case 1:
            language_str = "国语";
            break;
        case 2:
            language_str = "粤语";
            break;
        case 3:
            language_str = "闽南语";
            break;
        case 4:
            language_str = "英语";
            break;
        case 5:
            language_str = "日语";
            break;
        case 6:
            language_str = "汉语";
            break;
        default:
            language_str = "未知";
            break;
    }
    return language_str;
}

/**
 * 字符串长度
 * @param str
 * @returns {Number}
 */
function strlenght(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            len++;
        }
        else {
            len += 2;
        }
    }
    return len;
}

/**
 * 截取字符串(支持中英文混合)
 * @param str
 * @param n
 * @returns
 */
function sub(str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}

$(document).ready(function () {
    $("#loader").delay(500).fadeOut(300);
    $(".mask").delay(800).fadeOut(300);
});

/**
 * 淡入
 */
function fadeIn(time) {
    $("#loader").fadeIn(time);
    $(".mask").fadeIn(time);
}

/**
 * 淡出
 */
function fadeOut() {
    $("#loader").delay(500).fadeOut(300);
    $(".mask").delay(800).fadeOut(300);
}

function loading() {
    fadeIn();
    fadeOut();
}

var language = {
    "sLengthMenu": "每页显示 _MENU_ 条记录",
    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条",
    "sInfoEmpty": "没有数据",
    "sInfoFiltered": "",
    "sSearch": "搜索",
    'sProcessing': '加载中...',
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "前一页",
        "sNext": "后一页",
        "sLast": "尾页"
    },
    "sZeroRecords": "对不起，查询不到相关数据！"
};

var base = $("#base").attr("href");