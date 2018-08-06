$(function () {

    /** 初始化所有组件 */
    initComponents();
    /** 监听所有event事件 */
    addEventListener();

    /**
     * 组装时间控件
     */
    function initComponents() {

    }


    /**
     * 组装event监听
     */
    function addEventListener() {
        $("#changeUser").click(changeUser);
        $("#change-user-btn").click(submit);
        $("#user-change-close").click(close);



    }

    function close() {
        $('.theme-popover-mask').fadeOut(100);
        $('.theme-popover').slideUp(200);
    }

    function changeUser() {
        $("#user-error-div span").html("");
        $("#newPass").val("");
        $("#oldPass").val("");
        $('.theme-popover-mask').fadeIn(100);
        $('.theme-popover').slideDown(200);


    }

    function submit() {
        var id = $("#uId").val();
        var nickName = $("#nickName").val();
        var newPass =$("#newPass").val();
        var oldPass =$("#oldPass").val();

        var data = {
            "id":id,
            "nickName":nickName,
            "newPass":newPass,
            "oldPass":oldPass
        };
        $.ajax({
            url: base + '/changeUser',
            type: 'POST',
            async: false,
            cache: false,
            data: data,
            dataType: "json",
            success: function (resp) {
                if (resp.code == 200) {
                    window.location.href=base;
                } else {
                    $("#user-error-div span").html(resp.message);
                }

            },
            error: function (data) {
                $("#user-error-div span").html("服务器错误");
            }
        });


    }




});
