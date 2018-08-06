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
        $("button[name='to_upload']").click(toUpload);
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
                "columns": [{
                    "data": "title"
                },  {
                    "data": null
                },{
                    "data": null
                }, {
                    "data": null
                }],
                "bFilter": false,// 搜索栏
                "oLanguage": language,
                "rowCallback": function (row, data, index) {
                    $('td:eq(2)', row).html("<a href='javascript:void(0);' src='" + data.url + "' name='edit-support-download' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='下载'>" +
                        "<i class='glyphicon glyphicon-download'></i>" +
                        "</a>");

                    var typeHtml="";
                    switch (data.type){
                        case 1:
                            typeHtml="公司产品";
                            break;
                        case 2:
                            typeHtml="产品及解决方案";
                            break;
                        case 3:
                            typeHtml="客户支持";
                            break;
                    }
                    $('td:eq(1)', row).html("<span class='label'>"+typeHtml+"</span>");
                    var editHtml = "";
                    editHtml += "<a href='javascript:void(0);' name='edit-support-delete' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='删除'>" +
                        "<i class='glyphicon glyphicon-trash'></i>" +
                        "</a>";

                    //
                    $('td:eq(3)', row).html(editHtml);
                    $("a[name='edit-support-download']", row).click(editSupportPageDownload);
                    $("a[name='edit-support-delete']", row).click(editSupportPageDelete);
                    $('[data-toggle="tooltip"]', row).tooltip();
                },
                "fnServerParams": function (data) {
                    data.push(
                        {"name": "title", "value": $("#titleName").val()},
                        {"name": "type", "value": $("#typeName").val()},
                        {"name": "beginTime", "value": $("#beginTime").val()},
                        {"name": "endTime", "value": $("#endTime").val()}
                    );
                },
                "sAjaxSource": base + "/support/downloads/ajax",
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

    function editSupportPageDownload() {
        if ($(this).attr("src") != "") {
            window.open($(this).attr("src"));
        }

    }

    function editSupportPageDelete() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/support/downloads/delete/' + id,
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
        $("#url").attr("src", "");
        $("#upload-error-success").hide();
    }


    function toUpload() {
        if ($("#pdf_file").val() == "") {
            alert("无法上传");
            return;
        }


        var data = new FormData($('#uploadForm')[0]);
        $.ajax({
            url: base + '/uploadPDF',
            type: 'POST',
            data: data,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (resp) {
                $("#upload-error-success").show();
                $("#url").attr("src", resp.data.url);
                $("#pdf_file").val("");
            },
            error: function (data) {
                alert("上传失败");
                $("#url").attr("src", "")
                $("#pdf_file").val("");
            }
        });

    }


    function submitData() {
        var title = $("#title").val();
        var url = $("#url").attr("src");
        var type =$("#type").val();

        var data = {
            "title": title,
            "url": url,
            "type":type

        };

        $.ajax({
            url: base + '/support/downloads/addOrUpdate',
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
