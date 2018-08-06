$(function () {
    var table;
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
        //提交
        $("#submit_btn").click(editSolutionPage);
    }


    function editSolutionPage() {

        var content = contentEditor.txt.html();
        $.ajax({
            url: base + '/system/solution/5g/update?content=' + content,
            type: 'POST',
            async: false,
            cache: false,
            dataType: "json",
            success: function (resp) {
                if (resp.code == 200) {
                    window.location.reload();
                    clearData();
                }

            },
            error: function (data) {
                error();
                $("#model").modal("hide");
            }
        });

    }


    function clearData() {
        contentEditor.txt.clear();
    }


    function error() {
        $.Zebra_Dialog('信息加载失败，请刷新后重试.', {
            type: 'error',
            title: 'Error'
        });
    }

});
