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
        $("#chk_all").click(checkAllClick);
        $("#add_btn").click(toAddProductPage);
        $("button[name='to_upload']").click(toUploadPic);
        $("#file").change(changeFileInput);
        $("#pdf_file").change(changePDFFileInput);
        $("button[name='create_new_group']").click(createNewGroup);
        $("span[name='delete-groupDetail']").click(deleteGroupDetail);
        $("button[name='create_new_group_detail']").click(createNewGroupDetail);

        $("img[class='img-rounded']").change(function () {
            if ($(this).attr("src").trim() == "") {
                $(this).attr("src", base + "/static/web/images/noimage.png");
            }
        });
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
                    "data": null
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
                    $('td:eq(2)', row).html("<a name='image-a' href='" + data.portalPicUrl + "' target='_blank'><img  style='width: 100px;height: 100px' src='" + data.portalPicUrl + "'   class='img-rounded '/></a>");
                    $('td:eq(3)', row).html("<a name='image-a' href='" + data.navPicUrl + "' target='_blank'><img style='width: 100px;height: 100px' src='" + data.navPicUrl + "'   class='img-rounded '/></a>");
                    $('td:eq(4)', row).html("<a name='image-a' href='" + data.bgPicUrl + "' target='_blank'><img style='width: 100px;height: 100px' src='" + data.bgPicUrl + "'    class='img-rounded '/></a>");

                    var enableHtml = "";
                    if (data.enable) {
                        enableHtml = "<span class='label1'>已启用</span>"
                    } else {
                        enableHtml = "<span class='label'>未启用</span>"
                    }
                    $('td:eq(6)', row).html(enableHtml);

                    var editHtml = "<a href='javascript:void(0);' name='edit-product-page' data-id='" + data.id + "' style='margin-left: 2%;margin-right: 2%' data-toggle='tooltip' data-placement='left' title='详情'>" +
                        "<i class='glyphicon glyphicon-th-list'></i>" +
                        "</a>";
                    if (data.enable) {
                        editHtml += "<a href='javascript:void(0);' name='edit-product-status' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='禁用'>" +
                            "<i class='glyphicon glyphicon-remove'></i>" +
                            "</a>";
                    } else {
                        editHtml += "<a href='javascript:void(0);' name='edit-product-status' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='启用'>" +
                            "<i class='glyphicon glyphicon-ok'></i>" +
                            "</a>";
                    }
                    editHtml += "<a href='javascript:void(0);' name='edit-product-delete' data-id='" + data.id + "' style='margin-left: 4%;margin-right: 4%' data-toggle='tooltip' data-placement='left' title='删除'>" +
                        "<i class='glyphicon glyphicon-trash'></i>" +
                        "</a>";

                    //
                    $('td:eq(7)', row).html(editHtml);
                    $("a[name='edit-product-page']", row).click(editProductPage);
                    $("a[name='edit-product-status']", row).click(editProductPageStatus);
                    $("a[name='edit-product-delete']", row).click(editProductPageDelete);
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
                "sAjaxSource": base + "/product/ajax",
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

    function editProductPageDelete() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/product/delete/' + id,
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
    function editProductPageStatus() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/product/editStatus/' + id,
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

    function editProductPage() {
        clearData();
        var id = $(this).attr("data-id");
        $.ajax({
            url: base + '/product/edit/' + id,
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
        console.log(data);
        $("#title").attr("data-id", data.id);
        $("#title").val(data.title);
        $("#portalPicUrl").attr("src", data.portalPicUrl);
        $("#navPicUrl").attr("src", data.navPicUrl);
        $("#bgPicUrl").attr("src", data.bgPicUrl);
        var groups = data.productGroups;
        var tags = new Array();
        if (groups && groups.length > 0) {
            for (var i in groups) {
                var groupHtml = '<div class="groupDetail" data-id="' + groups[i].id + '">'
                    + '<input type="text" class="form-control" name="title" style="margin-top:1%;margin-bottom: 1%" placeholder="组标题" value="' + groups[i].groupTitle + '">'
                    + '<span class="label" style="cursor:pointer" name="delete-groupDetail">删除</span>'
                    + '<span style="margin-left:3%">每组最多展示4个产品,只有单个产品为组展示才能展示详情</span>'
                    + '<table class="table table-striped">'
                    + '<tbody>'
                    + '<tr>';
                var p = groups[i].products;

                if (p && p.length > 0) {
                    //

                    for (var j in p) {
                        var imgTag = "group-img-" + UUID();
                        var pdfTag = "group-pdf-" + UUID();

                        groupHtml += "<td data-id='" + p[j].id + "'>" +
                            "<div class='col-sm-5'>" +
                            "<div>" +
                            "<img style='width:100%' src='" + p[j].url + "' id='" + imgTag + "' class='img-rounded '/>" +
                            "</div>" +
                            "<div>" +
                            "<input type='text' class='form-control'   name='' style='width: 100%;margin-top:1%;margin-bottom: 1%' placeholder='规格' value='" + p[j].specifications + "'>" +
                            "<a href='javascript:void(0);' name='upload-group-pic' img-id='" + imgTag + "' data-toggle='tooltip' data-placement='left' title='上传图片'>" +
                            "<span class='label'>图片上传</span>" +
                            "</a>" +
                            "<span class='label1'>PDF</span>" +
                            "<a href='javascript:void(0);' name='upload-group-pdf'  pdf-id='" + pdfTag + "' style='margin-left: 2%;margin-right: 2%' data-toggle='tooltip' data-placement='left' title='上传PDF'>" +
                            "<i class='glyphicon glyphicon-upload'></i>" +
                            "</a>" +
                            "<a href='javascript:void(0);' name='download-group-pdf' src='" + p[j].pdf + "' id='" + pdfTag + "' data-toggle='tooltip' data-placement='left' title='下载PDF'>" +
                            "<i class='glyphicon glyphicon-download'></i>" +
                            "</a>" +
                            "</div>" +
                            "</div>" +
                            "<div class='col-sm-7' style='vertical-align: middle !important;'>";
                        if (p.length == 1) {
                            var tag = UUID();
                            tags.push(tag);
                            groupHtml += "<div name='editor-" + tag + "' data-name='editor'>" +
                                p[j].desc +
                                "</div>" +
                                "<button type='button' name='create_product_detail_desc' style='display: none'  class='btn btn-default'>添加描述详情</button>";


                        } else {


                        }
                        groupHtml += "<span class='label' style='cursor:pointer;' name='delete-groupDetail-desc'>删除</span>" +
                            "</div>" +
                            "</td>";
                    }


                    if (p.length == 1 || p.length == 4) {
                        groupHtml += '<td style="display:none;"><button type="button" name="create_new_group_detail" class="btn btn-default">添加</button></td>'
                            + '</tr>'
                            + '</tbody>'
                            + '</table>'
                            + '</div>';
                    } else {
                        groupHtml += '<td><button type="button" name="create_new_group_detail" class="btn btn-default">添加</button></td>'
                            + '</tr>'
                            + '</tbody>'
                            + '</table>'
                            + '</div>';
                    }
                } else {
                    groupHtml += '<td><button type="button" name="create_new_group_detail" class="btn btn-default">添加</button></td>'
                        + '</tr>'
                        + '</tbody>'
                        + '</table>'
                        + '</div>';

                }


                $(".newGroup button[name='create_new_group']").before(groupHtml);
            }
        }
        for (var k in tags) {
            var E = window.wangEditor;
            var editor = new E('div[name="editor-' + tags[k] + '"]');
            editor.customConfig.uploadImgServer = base + '/editorUpload';
            editor.customConfig.uploadFileName = 'file';
            editor.create();
            var editorName = "editor-" + tags[k];
            var editorObj = {
                "name": editorName,
                "value": editor
            };
            editors.push(editorObj);
        }


        $("span[name='delete-groupDetail']").click(deleteGroupDetail);
        $("table tbody tr td:last button[name='create_new_group_detail']").click(createNewGroupDetail);


        $("button[name='create_product_detail_desc']").click(createProductDetailDesc);
        $("span[name='delete-groupDetail-desc']").click(deleteGroupDetailDesc);

        $(".img-rounded").bind("error", imgError);
        $('[data-toggle="tooltip"]').tooltip();
        $("a[name='upload-group-pic']").unbind();
        $("a[name='upload-group-pic']").bind("click", function () {
            $("#file").val("");
            $("#file").attr("target-src-name", $(this).attr("img-id"));
            $("#file").click();
        });
        $("a[name='upload-group-pdf']").unbind();
        $("a[name='upload-group-pdf']").bind("click", function () {
            $("#pdf_file").val("");
            $("#pdf_file").attr("target-src-name", $(this).attr("pdf-id"));
            $("#pdf_file").click();
        });
        $("a[name='download-group-pdf']").unbind();
        $("a[name='download-group-pdf']").bind("click", PDFClick);

        $("img[class='img-rounded']").change(function () {
            if ($(this).attr("src").trim() == "") {
                $(this).attr("src", base + "/static/web/images/noimage.png");
            }
        });
        //content
        var contents = data.contents;
        if (data.isTwoContent) {
            if (contents && contents.length > 0) {
                $("#content-editor-1").attr("data-id", contents[0].id);
                $("#content-editor-2").attr("data-id", contents[1].id);
                $("#editor-title-1").val(contents[0].title);
                $("#editor-title-1").val(contents[1].title);
                contentEditor1.txt.html(contents[0].content)
                contentEditor2.txt.html(contents[1].content)
            }
        } else {

            if (contents && contents.length > 0) {
                $("#content-editor").attr("data-id", contents[0].id);
                contentEditor.txt.html(contents[0].content)
            }

        }

    }

    function toAddProductPage() {
        clearData();
        $("#model").modal("show");
    }

    function clearData() {
        $("#title").val("");
        $("#portalPicUrl").attr("src", "");
        $("#navPicUrl").attr("src", "");
        $("#bgPicUrl").attr("src", "");
        $(".groupDetail").remove();
        contentEditor.txt.clear();
        contentEditor1.txt.clear();
        contentEditor2.txt.clear();
        $("#editor-title-1").val("");
        $("#editor-title-2").val("");
        editors = [];
        $("#choose_ul li:first a").click();


    }

    function createNewGroup() {
        $(this).before(buildGroup());
        $(this).prev().find("span[name='delete-groupDetail']").click(deleteGroupDetail);
        $(this).prev().find("table tbody tr td:last button[name='create_new_group_detail']").click(createNewGroupDetail);


    }

    function deleteGroupDetail() {
        $(this).parent().remove();
    }

    function createNewGroupDetail() {
        var beforTds = $(this).parent().parent().find("td");
        var desc_html = "<button type='button' name='create_product_detail_desc'  class='btn btn-default'>添加描述详情</button>";
        if (beforTds.length > 1) {
            desc_html = "";
            $(beforTds).find("button[name='create_product_detail_desc']").hide();
        }

        var imgTag = "group-img-" + UUID();
        var pdfTag = "group-pdf-" + UUID();
        var _html = "<td>" +
            "<div class='col-sm-5'>" +
            "<div>" +
            "<img style='width:100%' src='' id='" + imgTag + "' class='img-rounded '/>" +
            "</div>" +
            "<div>" +
            "<input type='text' class='form-control'   name='' style='width: 100%;margin-top:1%;margin-bottom: 1%' placeholder='规格' value=''>" +
            "<a href='javascript:void(0);' name='upload-group-pic' img-id='" + imgTag + "' data-toggle='tooltip' data-placement='left' title='上传图片'>" +
            "<span class='label'>图片上传</span>" +
            "</a>" +
            "<span class='label1'>PDF</span>" +
            "<a href='javascript:void(0);' name='upload-group-pdf'  pdf-id='" + pdfTag + "' style='margin-left: 2%;margin-right: 2%' data-toggle='tooltip' data-placement='left' title='上传PDF'>" +
            "<i class='glyphicon glyphicon-upload'></i>" +
            "</a>" +
            "<a href='javascript:void(0);' name='download-group-pdf' id='" + pdfTag + "' data-toggle='tooltip' data-placement='left' title='下载PDF'>" +
            "<i class='glyphicon glyphicon-download'></i>" +
            "</a>" +
            "</div>" +
            "</div>" +
            "<div class='col-sm-7' style='vertical-align: middle !important;'>" +
            desc_html +
            "<span class='label' style='cursor:pointer' name='delete-groupDetail-desc'>删除</span>" +
            "</div>" +
            "</td>";

        $(this).parent().before(_html);

        var afterTds = $(this).parent().parent().find("td");
        if (afterTds.length > 4) {
            $(this).parent().hide();
        }

        $(afterTds).find("button[name='create_product_detail_desc']").click(createProductDetailDesc);
        $(afterTds).find("span[name='delete-groupDetail-desc']").click(deleteGroupDetailDesc);

        $(".img-rounded").bind("error", imgError);
        $('[data-toggle="tooltip"]').tooltip();
        $("a[name='upload-group-pic']").unbind();
        $("a[name='upload-group-pic']").bind("click", function () {
            $("#file").val("");
            $("#file").attr("target-src-name", $(this).attr("img-id"));
            $("#file").click();
        });
        $("a[name='upload-group-pdf']").unbind();
        $("a[name='upload-group-pdf']").bind("click", function () {
            $("#pdf_file").val("");
            $("#pdf_file").attr("target-src-name", $(this).attr("pdf-id"));
            $("#pdf_file").click();
        });
        $("a[name='download-group-pdf']").unbind();
        $("a[name='download-group-pdf']").bind("click", PDFClick);

        $("img[class='img-rounded']").change(function () {
            if ($(this).attr("src").trim() == "") {
                $(this).attr("src", base + "/static/web/images/noimage.png");
            }
        });

    }

    function createProductDetailDesc() {
        var tag = UUID();
        var _html = "<div name='editor-" + tag + "' data-name='editor'>" +
            "<p>请编辑详情信息</p>" +
            "</div>";
        $(this).before(_html);
        // $(this).
        var E = window.wangEditor;
        var editor = new E('div[name="editor-' + tag + '"]');
        editor.customConfig.uploadImgServer = base + '/editorUpload';
        editor.customConfig.uploadFileName = 'file';
        editor.create();
        var editorName = "editor-" + tag;
        var editorObj = {
            "name": editorName,
            "value": editor
        };
        editors.push(editorObj);
        $(this).hide();
        $(this).parent().parent().parent().find("td button[name='create_new_group_detail']").parent().hide();

    }

    function deleteGroupDetailDesc() {

        var afterTds = $(this).parent().parent().parent().find("td");
        if (afterTds.length < 6) {
            $(afterTds).last().show();
        }
        if (afterTds.length == 3) {
            $(afterTds).first().find("button[name='create_product_detail_desc']").show();
        }
        $(this).parent().parent().remove();
        $(afterTds).find("button[name='create_product_detail_desc']").unbind();
        $(afterTds).find("button[name='create_product_detail_desc']").bind("click", createProductDetailDesc);
    }

    function buildGroup() {
        var _html = "" +
            "<div class='groupDetail'>" +
            "<input type='text' class='form-control' name='title' style='margin-top:1%;margin-bottom: 1%' placeholder='组标题' value=''>" +
            "<span class='label' style='cursor:pointer' name='delete-groupDetail'>删除</span>" +
            "<span  style='margin-left:3%' >每组最多展示4个产品,只有单个产品为组展示才能展示详情</span>" +
            "<table class='table table-striped'>" +
            "<tr>" +
            "<td>" +
            "<button type='button' name='create_new_group_detail' class='btn btn-default'>添加</button>" +
            "</td>" +
            "</tr>" +
            "</table>" +
            "</div>"
        return _html;
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

    function changePDFFileInput() {
        var pdfId = $("#pdf_file").attr("target-src-name");
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
                $("#" + pdfId).attr("src", resp.data.url);
                $(this).val("");
            },
            error: function (data) {
                alert("上传失败");
                $(this).val("");
            }
        });


    }

    function PDFClick() {
        if (!$(this).attr("src")) {
            alert("无法下载，暂无文件");
            return;
        }
        window.open($(this).attr("src"));

    }


    function submitData() {
        var id = parseInt($("#title").attr("data-id"));
        if (!id) {
            id = -1
        }
        var title = $("#title").val();
        var portalPicUrl = $("#portalPicUrl").attr("src");
        var navPicUrl = $("#navPicUrl").attr("src");
        var bgPicUrl = $("#bgPicUrl").attr("src");
        var isTwoContent = false;
        var contents = [];
        if ($("#choose-two").hasClass("active")) {
            isTwoContent = true;
            var cId1 = parseInt($("#content-editor-1").attr("data-id"));
            if (!cId1) {
                cId1 = -1
            }
            var contentObject1 = {
                "id": cId1,
                "title": $("#editor-title-1").val(),
                "content": contentEditor1.txt.html()
            }
            var cId2 = parseInt($("#content-editor-2").attr("data-id"));
            if (!cId2) {
                cId2 = -1
            }
            var contentObject2 = {
                "id": cId2,
                "title": $("#editor-title-2").val(),
                "content": contentEditor2.txt.html()
            };
            contents.push(contentObject1);
            contents.push(contentObject2);
        } else {
            var cId = parseInt($("#content-editor").attr("data-id"));
            if (!cId) {
                cId = -1
            }
            var contentDesc = {
                "id": cId,
                "content": contentEditor.txt.html()
            };
            contents.push(contentDesc);
        }
        var productGroup = new Array();
        $(".newGroup .groupDetail").each(function (i) {
            var products = new Array();
            var groupTitle = $(this).find("input[name='title']").val();
            $(this).find("table tbody tr td").each(function (j) {
                var obj = {};
                var img = $(this).find(".col-sm-5 div img");
                var url = $(img).attr("src");
                var specifications = $(this).find(".col-sm-5 div input").val();
                var pdf = $(this).find(".col-sm-5 div a[name='download-group-pdf']").attr("src");
                var editorName = $(this).find(".col-sm-7 div").attr("name");
                var gpId = parseInt($(this).attr("data-id"));
                if (!gpId) {
                    gpId = -1
                }
                ;
                if (editorName) {
                    var desc = getEditorObjByName(editorName).txt.html();
                    obj = {
                        "id": gpId,
                        "url": url,
                        "pdf": pdf,
                        "specifications": specifications,
                        "desc": desc
                    };
                    products.push(obj);
                    return;
                }
                if (specifications) {
                    obj = {
                        "id": gpId,
                        "url": url,
                        "pdf": pdf,
                        "specifications": specifications
                    };
                    products.push(obj)
                }


            });
            var gId = $(this).attr("data-id");
            if (!gId) {
                gId = -1
            }
            var groupObj = {
                "products": products,
                "groupTitle": groupTitle,
                "id": gId
            };
            productGroup.push(groupObj);


        });

        var data = {
            "id": id,
            "title": title,
            "portalPicUrl": portalPicUrl,
            "navPicUrl": navPicUrl,
            "bgPicUrl": bgPicUrl,
            "productGroups": productGroup,
            "isTwoContent": isTwoContent,
            "contents": contents
        };

        $.ajax({
            url: base + '/product/addOrUpdate',
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


    function getEditorObjByName(name) {
        for (var i in editors) {
            if (editors[i].name == name) {
                return editors[i].value;
            }
        }

    }


});