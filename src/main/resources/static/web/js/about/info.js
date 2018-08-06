$(function () {
    var table;
    var editors = [];
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

        $("img[class='img-rounded']").change(function () {
            if ($(this).attr("src").trim() == "") {
                $(this).attr("src", base + "/static/web/images/noimage.png");
            }
        });

        $("button[name='to_upload']").click(toUpload);
        $("button[name='current_down_load']").click(toDownload);
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
                    "data": null
                }, {
                    "data": "country"
                }, {
                    "data": "phone"
                }, {
                    "data": "email"
                }, {
                    "data": "companyName"
                }, {
                    "data": "createTime"
                }, {
                    "data": null
                }],
                "bFilter": false,// 搜索栏
                "oLanguage": language,
                "rowCallback": function (row, data, index) {
                    $('td:eq(0)', row).html("<span>"+data.firstName+" - "+data.lastName+"</span>");
                    var editHtml ="";
                    editHtml += "<a href='javascript:void(0);' name='edit-info' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='详情'>" +
                        "<i class='glyphicon glyphicon-th-list'></i>" +
                        "</a>";
                    editHtml += "<a href='javascript:void(0);' name='delete-info' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='编辑'>" +
                        "<i class='glyphicon glyphicon-trash'></i>" +
                        "</a>";
                    //
                    $('td:eq(6)', row).html(editHtml);
                    $("a[name='edit-info']", row).click(editPage);
                    $("a[name='delete-info']", row).click(deletePage);
                    $('[data-toggle="tooltip"]', row).tooltip();
                    //

                    $('[data-toggle="tooltip"]', row).tooltip();
                },
                "fnServerParams": function (data) {
                    data.push(
                        {"name": "nickName", "value": $("#nickNameQuery").val()},
                        {"name": "country", "value": $("#countryQuery").val()},
                        {"name": "beginTime", "value": $("#beginTime").val()},
                        {"name": "endTime", "value": $("#endTime").val()}
                    );
                },
                "sAjaxSource": base + "/about/ajax",
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


    function builEditorModel(data) {
        $("#name").val(data.firstName + " - " + data.lastName);
        $("#email").val(data.email);
        $("#phone").val(data.phone);
        $("#companyName").val(data.companyName);
        $("#country").val(data.country);
        $("#city").val(data.city);
        $("#region").val(data.region);
        $("#state").val(data.state);
        $("#streetAddress").val(data.streetAddress);
        $("#zip").val(data.zip);
        $("#website").val(data.website);
        $("#questionOne").val(data.questionOne);
        $("#questionTwo").val(data.questionTwo);
        $("#questionThree").val(data.questionThree);
        $("#jobTitle").val(data.jobTitle);
    }

    function editPage() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/about/edit/' + id,
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

    function deletePage() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/about/delete/' + id,
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

    function toDownload() {
        var url =  $("#url").attr("src");
        if (url == ""){
            alert("暂无文件无法下载");
            return ;
        }
        window.open(url);
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
                var url = resp.data.url;
                $("#upload-error-success").show();
                $("#url").attr("src", url);
                $("#pdf_file").val("");
                $("#current_file_title").html(url.substring(url.lastIndexOf("/")+1,url.length))
            },
            error: function (data) {
                alert("上传失败");
                $("#url").attr("src", "")
                $("#pdf_file").val("");
            }
        });

    }


    function submitData() {

        var url = $("#url").attr("src");
        if (url == ""){
            return;
        }

        var data = {
            "url": url
        };

        $.ajax({
            url: base + '/about/addFile',
            type: 'POST',
            async: false,
            cache: false,
            data: data,
            dataType: "json",
            success: function (resp) {
                if (resp.code == 200) {
                    $("#upload-error-success").hide();
                    $("#upload-error-fail").hide();
                    $("#url").attr("src","");
                    $.Zebra_Dialog('添加成功', {
                        type: 'error',
                        title: 'Error'
                    });
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