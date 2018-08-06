<link rel="stylesheet" href="${(project.staticDomain!)}/libs/dataTables-1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="${(project.staticDomain!)}/libs/zebra_dialog-1.4.0/css/flat/zebra_dialog.min.css"">
<style>
    .table {
        border-bottom: 0px;
    }

    #model input[type=text] {
        width: 150px;
        display: inline;
    }

    .modal-dialog {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }

    .modal-content {
        /*overflow-y: scroll; */
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;
    }

    .modal-body {
        overflow-y: scroll;
        position: absolute;
        top: 55px;
        bottom: 65px;
        width: 100%;
    }

    .modal-header .close {
        margin-right: 15px;
    }

    .modal-footer {
        position: absolute;
        width: 100%;
        bottom: 0;
    }
</style>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">页面设置</a></li>
        <li class="active">5G-导航页面设置</li>
    </ol>


    <div>
        <hr/>
    </div>
    <div class="content">

        <form class="form-horizontal" method="post" action="" id="content_form"
              data-parsley-validate enctype="multipart/form-data">

            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">导航页头部详情<font class="red">*</font>：</label>
                <div class="col-sm-10">
                    <div id='content-editor'>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-primary" id="submit_btn">确定</button>


    </div>
</div>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script type="text/javascript" src="${(project.staticDomain!)}/libs/My97DatePicker/WdatePicker.js"></script>
<script src="${ (project.staticDomain)! }/libs/wangEditor/wangEditor.min.js"></script>
<script src="${ (project.staticDomain)! }/js/system/5g.js"></script>
<script>
    var E = window.wangEditor;
    var contentEditor = new E('#content-editor');
    contentEditor.customConfig.uploadImgServer = '${(base)!}/editorUpload';
    contentEditor.customConfig.uploadFileName = 'file';
    contentEditor.create();
    <#if data.solution5gNavDesc??>
    var content = '${(data.solution5gNavDesc)!}';
    contentEditor.txt.html(content);

    </#if>


</script>