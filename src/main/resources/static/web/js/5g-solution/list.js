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
                    "data": "title"
                }, {
                    "data": null
                }, {
                    "data": "navDesc"
                }, {
                    "data": 'contentDesc'
                }, {
                    "data": null
                }, {
                    "data": "createTime"
                }, {
                    "data": null
                }, {
                    "data": null
                }],
                "bFilter": false,// 搜索栏
                "oLanguage": language,
                "rowCallback": function (row, data, index) {
                    $('td:eq(0)', row).html("<input name='chk_list' type='checkbox' id='" + data.id + "'>");
                    $('td:eq(2)', row).html("<a name='image-a' href='" + data.navPicUrl + "' target='_blank'><img style='width: 100px;height: 100px' src='" + data.navPicUrl + "'   class='img-rounded '/></a>");
                    $('td:eq(3)', row).html("<a name='image-a' href='" + data.bgPicUrl + "' target='_blank'><img style='width: 100px;height: 100px' src='" + data.bgPicUrl + "'    class='img-rounded '/></a>");

                    var enableHtml = "";
                    if (data.enable) {
                        enableHtml = "<span class='label1'>已启用</span>"
                    } else {
                        enableHtml = "<span class='label'>未启用</span>"
                    }
                    $('td:eq(5)', row).html(enableHtml);

                    var editHtml = "<a href='javascript:void(0);' name='edit-solution-page' data-id='" + data.id + "' style='margin-left: 2%;margin-right: 2%' data-toggle='tooltip' data-placement='left' title='详情'>" +
                        "<i class='glyphicon glyphicon-th-list'></i>" +
                        "</a>";
                    if (data.enable) {
                        editHtml += "<a href='javascript:void(0);' name='edit-solution-status' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='禁用'>" +
                            "<i class='glyphicon glyphicon-remove'></i>" +
                            "</a>";
                    } else {
                        editHtml += "<a href='javascript:void(0);' name='edit-solution-status' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='启用'>" +
                            "<i class='glyphicon glyphicon-ok'></i>" +
                            "</a>";
                    }
                    editHtml += "<a href='javascript:void(0);' name='edit-solution-delete' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='删除'>" +
                        "<i class='glyphicon glyphicon-trash'></i>" +
                        "</a>";

                    //
                    $('td:eq(6)', row).html(editHtml);
                    $("a[name='edit-solution-page']", row).click(editSolutionPage);
                    $("a[name='edit-solution-status']", row).click(editSolutionPageStatus);
                    $("a[name='edit-solution-delete']", row).click(editSolutionPageDelete);
                    $(".img-rounded", row).bind("error", imgError);
                    $('[data-toggle="tooltip"]', row).tooltip();
                },
                "fnServerParams": function (data) {
                    data.push(
                        {"name": "title", "value": $("#titleName").val()},
                        {"name": "status", "value": $("#status").val()},
                        {"name": "beginTime", "value": $("#beginTime").val()},
                        {"name": "endTime", "value": $("#endTime").val()}
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

    function editSolutionPageDelete() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/solution/5g/delete/' + id,
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

    function editSolutionPageStatus() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/solution/5g/editStatus/' + id,
            type: 'POST',
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

    function editSolutionPage() {
        clearData();
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/solution/5g/edit/' + id,
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

    function toAddPage() {
        clearData();
        $("#model").modal("show");

    }

    function clearData() {
        $("#title").val("");
        $("#navDesc").val("");
        $("#contentDesc").val("");
        $("#navPicUrl").attr("src", "");
        $("#bgPicUrl").attr("src", "");

        $("#middleTitle").val("");
        $("#middleBgUrl").attr("src", "");
        $("#middle-pic-1").attr("src", "");
        $("#middle-pic-2").attr("src", "");
        $("#middle-pic-3").attr("src", "");
        $("#middle-title-1").val("");
        $("#middle-title-2").val("");
        $("#middle-title-1").val("");
        contentTopEditor.txt.clear();
        contentBottomEditor.txt.clear();


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
        var id = parseInt($("#title").attr("data-id"));
        if (!id) {
            id = -1
        }
        var title = $("#title").val();
        var navPicUrl = $("#navPicUrl").attr("src");
        var navDesc = $("#navDesc").val();
        var bgPicUrl = $("#bgPicUrl").attr("src");
        var contentDesc = $("#contentDesc").val();
        var contentTop = contentTopEditor.txt.html();
        var contentBottom = contentBottomEditor.txt.html();
        var middleTitle = $("#middleTitle").val("");
        var middleBgUrl = $("#middleBgUrl").attr("src");
        var middlePic1 = $("#middle-pic-1").attr("src");
        var middlePic2 = $("#middle-pic-2").attr("src");
        var middlePic3 =  $("#middle-pic-3").attr("src");
        var middleTitle1 = $("#middle-title-1").val();
        var middleTitle2 = $("#middle-title-2").val();
        var middleTitle3 = $("#middle-title-1").val();
        var data = {
            "id": id,
            "title": title,
            "navPicUrl": navPicUrl,
            "navDesc": navDesc,
            "bgPicUrl": bgPicUrl,
            "contentDesc": contentDesc,
            "contentTop": contentTop,
            "contentBottom":contentBottom,
            "middleTitle": middleTitle,
            "middleBgUrl": middleBgUrl,
            "middlePic1": middlePic1,
            "middlePic2": middlePic2,
            "middlePic3": middlePic3,
            "middleTitle1": middleTitle1,
            "middleTitle2": middleTitle2,
            "middleTitle3": middleTitle3,

        };

        $.ajax({
            url: base + '/solution/5g/addOrUpdate',
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
