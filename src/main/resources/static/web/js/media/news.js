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
        $("#add_btn").click(toAddPage);
        $("button[name='to_upload']").click(toUploadPic);
        $("#file").change(changeFileInput);
        $("a[name='middle-upload']").click(toUploadMiddlePic);

        //提交
        $("#submit_btn").click(submitData);
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
                "aLengthMenu": [20, 30, 50],
                "columns": [{
                    "data": null
                }, {
                    "data": "navTitle"
                }, {
                    "data": null
                }, {
                    "data": "navDesc"
                }, {
                    "data": "createTime"
                }, {
                    "data": null
                }],
                "bFilter": false,// 搜索栏
                "oLanguage": language,
                "rowCallback": function (row, data, index) {
                    $('td:eq(0)', row).html("<input name='chk_list' type='checkbox' id='" + data.id + "'>");
                    $('td:eq(2)', row).html("<a name='image-a' href='" + data.navPicUrl + "' target='_blank'><img style='width: 100px;height: 100px' src='" + data.navPicUrl + "'    class='img-rounded '/></a>");


                    var editHtml = "<a href='javascript:void(0);' name='edit-media-page' data-id='" + data.id + "' style='margin-left: 2%;margin-right: 2%' data-toggle='tooltip' data-placement='left' title='详情'>" +
                        "<i class='glyphicon glyphicon-th-list'></i>" +
                        "</a>";
                    editHtml += "<a href='javascript:void(0);' name='edit-media-delete' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='删除'>" +
                        "<i class='glyphicon glyphicon-trash'></i>" +
                        "</a>";

                    //
                    $('td:eq(5)', row).html(editHtml);
                    $("a[name='edit-media-page']", row).click(editMediaPage);
                    $("a[name='edit-media-delete']", row).click(editMediaPageDelete);
                    $(".img-rounded", row).bind("error", imgError);
                    $('[data-toggle="tooltip"]', row).tooltip();
                },
                "fnServerParams": function (data) {
                    data.push(
                        {"name": "title", "value": $("#titleName").val()},
                        {"name": "beginTime", "value": $("#beginTime").val()},
                        {"name": "endTime", "value": $("#endTime").val()}
                    );
                },
                "sAjaxSource": base + "/media/news/ajax",
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

    function editMediaPageDelete() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/media/news/delete/' + id,
            type: 'DELETE',
            async: false,
            cache: false,
            dataType: "json",
            success: function (resp) {
                if (resp.code == 200) {
                    search();
                }

            },
            error: function (data) {
                error();
            }
        });
    }



    function editMediaPage() {
        clearData();
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/media/news/edit/' + id,
            type: 'POST',
            async: false,
            cache: false,
            dataType: "json",
            success: function (resp) {
                if (resp.code == 200) {
                    builEditorModel(resp.data);
                    $("#model").modal("show");
                }

            },
            error: function (data) {
                error();
                $("#model").modal("hide");
            }
        });

    }

    function builEditorModel(data) {

        $("#navTitle").attr("data-id", data.id);

        $("#navTitle").val(data.navTitle);
        $("#navPicUrl").attr("src", data.navPicUrl);
        $("#navDesc").val(data.navDesc);
        contentEditor.txt.html(data.content);

    }

    function toAddPage() {
        clearData();
        $("#model").modal("show");

    }

    function clearData() {
        $("#navTitle").attr("data-id", -1);
        $("#navTitle").val("");
        $("#navPicUrl").attr("src", "");
        $("#navDesc").val("");
        contentEditor.txt.clear();


    }


    function toUploadPic() {
        var id = $(this).prev().attr("id");
        $("#file").val("");
        $("#file").attr("target-src-name", id);
        $("#file").click();

    }

    function toUploadMiddlePic() {
        var id = $(this).attr("target-id");
        $("#file").val("");
        $("#file").attr("target-src-name", id);
        $("#file").click();
    }

    function changeFileInput() {
        var imgId = $("#file").attr("target-src-name");
        var file = this.files[0];
        if (!file.type.match('image.*')) {
            alert("请上传图片");
            $(this).val("");
            return;
        }
        var data = new FormData($('#uploadForm')[0]);
        $.ajax({
            url: base + '/upload',
            type: 'POST',
            data: data,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (resp) {
                $("#" + imgId).attr("src", resp.data.url);
                $(this).val("");
            },
            error: function (data) {
                alert("上传失败");
                $(this).val("");
            }
        });


    }

    function submitData() {
        var id = parseInt($("#navTitle").attr("data-id"));
        if (!id) {
            id = -1
        }
        var navTitle = $("#navTitle").val();
        var navPicUrl = $("#navPicUrl").attr("src");
        var navDesc = $("#navDesc").val();
        var content = contentEditor.txt.html();

        var data = {
            "id": id,
            "navTitle": navTitle,
            "navPicUrl": navPicUrl,
            "navDesc": navDesc,
            "content": content
        };

        $.ajax({
            url: base + '/media/news/addOrUpdate',
            type: 'POST',
            async: false,
            cache: false,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (resp) {
                $("#model").modal("hide");
                if (resp.code == 200) {
                    search();
                } else {
                    error();
                }

            },
            error: function (data) {
                alert("操作失败,服务器错误");
                $("#model").modal("hide");
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
