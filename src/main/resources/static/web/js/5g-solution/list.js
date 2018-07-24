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
        initDataTable();
    }


    /**
     * 组装event监听
     */
    function addEventListener() {
        $("#search").click(search);
        $("#chk_all").click(checkAllClick);

    }

    function checkAllClick() {
        var flag = $("#chk_all").prop("checked");//判断全选按钮的状态
        $("input[name='chk_list']").each(function () {//查找每一个name为chk_list 复选框
            if ($("#chk_all").prop("checked")) {
                $(this).prop("checked", flag);//选中
            } else {
                $(this).prop("checked", false);//取消选中
            }
        });
    }


    function initDataTable() {
        table = $('#content_table').DataTable(
            {
                "bProcessing": true,// 加载中开启
                "bServerSide": true,
                "bSort": false,
                "bDeferRender": true,// 是否启用延迟加载：当你使用AJAX数据源时，可以提升速度。
                "aLengthMenu": [20, 50, 80],
                "columns": [{
                    "data": "id"
                }, {
                    "data": null
                }, {
                    "data": null
                }, {
                    "data": null
                }, {
                    "data": null
                }, {
                    "data": null
                }, {
                    "data": null
                }, {
                    "data": null
                }],
                "bFilter": false,// 搜索栏
                "oLanguage": language,
                "rowCallback": function (row, data, index) {


                },
                "fnServerParams": function (data) {
                    data.push(
                    );
                },
                "sAjaxSource": base + "/solution/5g/ajax",
                "fnServerData": function (sSource, data, fnCallback) {
                    $.ajax({
                        "type": 'post',
                        "url": sSource,
                        "dataType": "json",
                        "data": {'data': JSON.stringify(data)},
                        "success": function (resp) {
                            console.log(resp);
                            fnCallback(resp.data);
                        }
                    });

                },
                "drawCallback": function (settings) {

                }

            });
    }


    /**
     * 清除搜索条件
     */
    function clearSearch() {

    }

    /**
     * 搜索
     */
    function search() {
        table.draw();
    }

    function error() {
        $.Zebra_Dialog('信息加载失败，请刷新后重试.', {
            type: 'error',
            title: 'Error'
        });
    }

});
