$(function () {
    var message ;
    /** 初始化所有组件 */
    initComponents();
    /** 监听所有event事件 */
    addEventListener();

    /**
     * 组装时间控件
     */
    function initComponents() {
        initMsg();
    }


    /**
     * 组装event监听
     */
    function addEventListener() {
        $("#submit").click(submit);

        //回车提交事件
        $("body").keydown(function() {
            if (event.keyCode == 13) {//keyCode=13是回车键
                $("#submit").click();
            }
        });


    }


    function initMsg() {
        message= new MyMessage.message({
            /*默认参数，下面为默认项*/
            iconFontSize: "40px", //图标大小,默认为20px
            messageFontSize: "24px", //信息字体大小,默认为12px
            showTime: 1500, //消失时间,默认为3000
            align: "center", //显示的位置类型center,right,left
            positions: { //放置信息距离周边的距离,默认为10px
                top: "10px",
                bottom: "10px",
                right: "10px",
                left: "10px"
            },
            message: "", //消息内容,默认为"这是一条消息"
            type: "warning", //消息的类型，还有success,error,warning等，默认为normal
        });
        /*两种不同的设置方式*/
        message.setting({
            align: "bottom" //会覆盖前面的所有设置,可以创建不同的对象去避免覆盖
        });
        message.setting("showTime", "5000");

    }

    function submit() {

        var username = $("#username").val().trim();
        var password = $("#password").val().trim();
        if (username == "" ){
            message.add("请输入账户名！", "warning");
            return;
        }
        if (password == ""){
            message.add("请输入密码！", "warning");
            return;
        }


        var data = {
            "username":username,
            "password":password
        };
        $.ajax({
            url: base + '/login',
            type: 'POST',
            async: false,
            cache: false,
            data: data,
            dataType: "json",
            success: function (resp) {
                if (resp.code == 200) {
                    window.location.href = base;
                } else {
                    message.add("账户名或密码错误！", "warning");
                }

            },
            error: function (data) {
                message.add("服务器错误", "warning");
            }
        });


    }




});
