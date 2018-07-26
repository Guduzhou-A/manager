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

        $("button[name='create_new_group']").click(createNewGroup);
        $("span[name='delete-groupDetail']").click(deleteGroupDetail);
        $("button[name='create_new_group_detail']").click(createNewGroupDetail);

        $("img[class='img-rounded']").change(function () {
            if ($(this).attr("src").trim() == "") {
                $(this).attr("src", base + "/static/web/images/noimage.png");
                $(this).attr("data-src", "");
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
                "aLengthMenu": [1, 2, 3],
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

                    $(".img-rounded", row).bind("error", imgError);
                    $('[data-toggle="tooltip"]', row).tooltip();
                },
                "fnServerParams": function (data) {
                    data.push(
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

    function toAddProductPage() {
        clearData();
        $("#model").modal("show");
    }
    function clearData() {
        $("#title").val("");
        $("#portalPicUrl").attr("src","");
        $("#portalPicUrl").attr("data-src","");
        $("#navPicUrl").attr("src","");
        $("#navPicUrl").attr("data-src","");
        $("#bgPicUrl").attr("src","");
        $("#bgPicUrl").attr("data-src","");
        $(".groupDetail").remove();
        contentEditor.txt.clear();
        editors = [];


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
        ;

        var imgTag = "group-img-"+UUID();
        var _html = "<td>" +
            "<div class='col-sm-5'>" +
            "<div>" +
            "<img style='width:100%' src='' id='"+imgTag+"' class='img-rounded '/>" +
            "</div>" +
            "<div>" +
            "<input type='text' class='form-control'   name='' style='width: 100%;margin-top:1%;margin-bottom: 1%' placeholder='规格' value=''>" +
            "<a href='javascript:void(0);' name='upload-group-pic' img-id='"+imgTag+"' data-toggle='tooltip' data-placement='left' title='上传图片'>" +
            "<i class='glyphicon glyphicon-upload'></i>" +
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
        $("a[name='upload-group-pic']").bind("click",function () {
            $("#file").val("");
            $("#file").attr("target-src-name", $(this).attr("img-id"));
            $("#file").click();
        });
        $("img[class='img-rounded']").change(function () {
            if ($(this).attr("src").trim() == "") {
                $(this).attr("src", base + "/static/web/images/noimage.png");
                $(this).attr("data-src", "");
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
        editor.create();
        var editorName = "editor-" + tag ;
        var editorObj ={
            "name":editorName,
            "value":editor
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
        $(afterTds).find("button[name='create_product_detail_desc']").bind("click",createProductDetailDesc);
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

                $("#" + imgId).attr("src", resp.data.nginx);
                $("#" + imgId).attr("data-src", resp.data.file);
                $(this).val("");
            },
            error: function (data) {
                alert("上传失败");
                $("#" + imgId).attr("src", "");
                $("#" + imgId).attr("data-src", "");
                $(this).val("");
            }
        });


    }

    function submitData() {
        var title = $("#title").val();
        var portalPicUrl = $("#portalPicUrl").attr("data-src");
        var navPicUrl = $("#navPicUrl").attr("data-src");
        var bgPicUrl =  $("#bgPicUrl").attr("data-src");
        var contentDesc = contentEditor.txt.html();
        var productGroup = new Array();
        $(".newGroup .groupDetail").each(function (i) {
            var products = new Array();
            var groupTitle = $(this).find("input[name='title']").val();
            $(this).find("table tbody tr td").each(function (j) {
                var obj = {};
                var img = $(this).find(".col-sm-5 div img");
                var url = $(img).attr("data-src");
                var specifications = $(this).find(".col-sm-5 div input").val();
                var editorName = $(this).find(".col-sm-7 div").attr("name");
                if (editorName){
                    var desc = getEditorObjByName(editorName).txt.html();
                    obj = {
                        "url":url,
                        "specifications":specifications,
                        "desc":desc
                    };
                    products.push(obj);
                    return;
                }
                if (specifications) {
                    obj = {
                        "url":url,
                        "specifications":specifications
                    };
                    products.push(obj)
                }


            });
            var groupObj = {
                "products":products,
                "groupTitle":groupTitle
            };
            productGroup.push(groupObj);


        });

        var data = {
            "title":title,
            "portalPicUrl":portalPicUrl,
            "navPicUrl":navPicUrl,
            "bgPicUrl":bgPicUrl,
            "productGroup":productGroup,
            "contentDesc":contentDesc
        };

        $.ajax({
            url: base + '/product/addP',
            type: 'POST',
            async: false,
            cache: false,
            data : JSON.stringify(data),
            contentType : "application/json",
            dataType : "json",
            success: function (resp) {
                $("#model").modal("hide");
                search();
            },
            error: function (data) {
                alert("新建失败");
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

    function imgError() {
        $(this).attr("src", base + "/static/web/images/noimage.png");
    }

    function getEditorObjByName(name) {
        for (var i in editors){
            if (editors[i].name == name){
                return editors[i].value;
            }
        }

    }


});