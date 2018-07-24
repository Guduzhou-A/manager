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
        $("#add_btn").click(toAddProductPage);
        $("#to_upload").click(toUploadPic);
        $('#uploadSubmit').click(uploadPic);

        $("button[name='create_new_group']").click(createNewGroup);
        $("span[name='delete-groupDetail']").click(deleteGroupDetail);
        $("button[name='create_new_group_detail']").click(createNewGroupDetail);

        $(".img-remove").click(removeImg);
        $(".img-add-icon").click(addImg);
        $(".img-file-input").change(changeFileInput);
        $("#toUpdateImg").click(toUpdateImg);


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
                    $('td:eq(2)', row).html("<img  style='width: 100px;height: 100px' src='" + data.portalPicUrl + "'   class='img-rounded '/>");
                    $('td:eq(3)', row).html("<img style='width: 100px;height: 100px' src='" + data.navPicUrl + "'   class='img-rounded '/>");
                    $('td:eq(4)', row).html("<img style='width: 100px;height: 100px' src='" + data.bgPicUrl + "'    class='img-rounded '/>");

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
                    // initEzView();
                }

            });
    }

    function toAddProductPage() {
        $("#model").modal("show");
    }

    function createNewGroup() {
        $(this).before(buildGroup());
        $("span[name='delete-groupDetail']").click(deleteGroupDetail);
        $("button[name='create_new_group_detail']").click(createNewGroupDetail);


    }

    function deleteGroupDetail() {
        $(this).parent().remove();
    }

    function createNewGroupDetail() {
        var beforTds = $(this).parent().parent().find("td");
        var desc_html = "<button type='button' name='create_product_detail_desc'  class='btn btn-default'>添加描述详情</button>";
        if (beforTds.length > 1) {
            desc_html = "";
            $("button[name='create_product_detail_desc']").remove();
        }
        ;

        var _html = "<td>" +
            "<div class='col-sm-5'>" +
            "<div>" +
            "<img style='width:100%' src='' class='img-rounded '/>" +
            "</div>" +
            "<div>" +
            "<input type='text' class='form-control' name='' style='width: 100%;margin-top:1%;margin-bottom: 1%' placeholder='规格' value=''>" +
            "</div>" +
            "</div>" +
            "<div class='col-sm-7' style='vertical-align: middle !important;'>" +
            desc_html +
            "</div>" +
            "</td>";

        $(this).parent().before(_html);
        $(".img-rounded").bind("error", imgError);
        var afterTds = $(this).parent().parent().find("td");
        if (afterTds.length > 4) {
            $(this).parent().hide();
        }

        $("button[name='create_product_detail_desc']").click(createProductDetailDesc);

    }

    function createProductDetailDesc() {
        var _html = "<div name='editor'>" +
            "<p>请编辑详情信息</p>" +
            "</div>";
        $(this).before(_html);
        var E = window.wangEditor;
        var editor = new E('div[name="editor"]');
        editor.create()

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
        clearImg();
        $("#upload-model").modal("show");
    }

    function clearImg() {
        $("img[name='posterImgSrc']").attr("src", "");
        $(".img-file-input").val("");
    }

    function addImg() {
        var name = $(this).attr("name");
        $("input[name='" + name + "']").click();
    }

    function removeImg() {
        $(this).attr("src", "");
        var name = $(this).attr("name");
        $(this).parent().addClass("img-view");
        $(this).parent().next().removeClass("img-view");
        $("input[imgName='" + name + "']").val("");
    }

    function changeFileInput() {
        var file = this.files[0];
        var objUrl = getObjectURL(this.files[0]);
        var imgDivName = $(this).attr("imgName");
        var imgObj = $("img[name='" + imgDivName + "']");
        if (objUrl) {
            if (!file.type.match('image.*')) {
                alert("请上传图片");
                imgObj.attr("src", "");
                $(this).val("");
                return;
            }
            if (file.size > (1024 * 2 * 1000)) {
                alert("图片文件不能超过2M");
                imgObj.attr("src", "");
                $(this).val("");
                return;
            }
            var img = document.createElement("img");
            img.src = objUrl;
            imgObj.attr("src", objUrl); // 将图片路径存入src中，显示出图片
            imgObj.parent().removeClass("img-view");
            imgObj.parent().next().addClass("img-view");

        }
    }

    function toUpdateImg() {
        $("#updateImgForm").submit();
    }


    function uploadPic() {
        var data = new FormData($('#uploadForm')[0]);
        $.ajax({
            url: 'xxx/xxx',
            type: 'POST',
            data: data,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.status) {
                    console.log('upload success');
                } else {
                    console.log(data.message);
                }
            },
            error: function (data) {
                console.log(data.status);
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


});
