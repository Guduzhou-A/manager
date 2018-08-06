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
        $("button[name='to_upload']").click(toUploadPic);
        $("#file").change(changeFileInput);
        $("#add").click(submitData);
    }

    function initDataTable() {
        table = $('#content_table').DataTable(
            {
                "bProcessing": true,// 加载中开启
                "bServerSide": true,
                "bSort": false,
                "bDeferRender": true,// 是否启用延迟加载：当你使用AJAX数据源时，可以提升速度。
                "aLengthMenu": [20, 30, 50],
                "columns": [ {
                    "data": "title"
                }, {
                    "data": null
                }, {
                    "data": null
                }],
                "bFilter": false,// 搜索栏
                "oLanguage": language,
                "rowCallback": function (row, data, index) {
                    $('td:eq(1)', row).html("<a name='image-a' href='" + data.imgUrl + "' target='_blank'><img style='width: 100px;height: 100px' src='" + data.imgUrl + "'    class='img-rounded '/></a>");


                    var editHtml ="";
                    editHtml += "<a href='javascript:void(0);' name='edit-brand-delete' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='删除'>" +
                        "<i class='glyphicon glyphicon-trash'></i>" +
                        "</a>";

                    //
                    $('td:eq(2)', row).html(editHtml);
                    $("a[name='edit-brand-delete']", row).click(editBrandPageDelete);
                    $(".img-rounded", row).bind("error", imgError);
                    $('[data-toggle="tooltip"]', row).tooltip();
                },
                "fnServerParams": function (data) {
                    data.push(
                    );
                },
                "sAjaxSource": base + "/home/banner/ajax",
                "fnServerData": function (sSource, data, fnCallback) {
                    $.ajax({
                        "type": 'post',
                        "url": sSource,
                        "dataType": "json",
                        "data": {'data': JSON.stringify(data)},
                        "success": function (resp) {
                            fnCallback(resp.data);
                        }
                    });

                },
                "drawCallback": function (settings) {
                }

            });
    }

    function editBrandPageDelete() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/home/banner/delete/' + id,
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



    function clearData() {
        $("#title").val("");
        $("#imgUrl").attr("src", "");
    }


    function toUploadPic() {
        var id = $(this).prev().attr("id");
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
        var title = $("#title").val();
        var imgUrl = $("#imgUrl").attr("src");

        var data = {
            "title": title,
            "imgUrl": imgUrl

        };

        $.ajax({
            url: base + '/home/banner/add',
            type: 'POST',
            async: false,
            cache: false,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (resp) {
                clearData();
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
