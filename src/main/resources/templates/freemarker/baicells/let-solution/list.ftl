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
        <li><a href="#">方案管理</a></li>
        <li class="active">let-解决方案</li>
    </ol>

    <div class="search">
        <form class="form-inline">
            <div class="form-group">
                <input type="text" class="form-control" id="titleName" placeholder="标题名称">
            </div>

            <div class="form-group" style="margin-left: 0px;">
                <select class="form-control" id="status">
                    <option value="-1">全部</option>
                    <option value="1">已启用</option>
                    <option value="0">未启用</option>
                </select>
            </div>

            <div class="form-group my-form-group">
                <label for="beginTime">创建时间:</label>
                <input id="beginTime" name="beginTime" class="form-control"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                <label for="endTime">到</label>
                <input id="endTime" name="endTime" class="form-control"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>

            <button type="button" id="search" class="btn btn-default">搜索</button>
        </form>
    </div>
    <div>
        <hr/>
    </div>
    <div class="content">
        <div class="ext-btn-div" style="float: right">
            <button type="button" id="add_btn" class="btn btn-default">新建</button>
        </div>
        <table id="content_table" class="table table-striped" cellspacing="0" width="100%"
               style="word-break:break-all;">
            <thead>
            <tr>
                <th class="sorting_disabled" rowspan="1" colspan="1">
                    <input type="checkbox" id="chk_all" name="chk_all">
                </th>
                <th>标题</th>
                <th>首页轮播图片</th>
                <th>导航页图片</th>
                <th>详情页头背景图片</th>
                <th>创建时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>

    </div>
</div>
<#--创建let-解决方案窗口-->
<div class="modal fade" id="model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:90%;overflow-y:auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新建</h4>
            </div>
            <div class="modal-body" style="padding-left: 2%;padding-right: 2%">
                <form class="form-horizontal" method="post" action="" id="content_form"
                      data-parsley-validate enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">标题<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="title" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">首页轮播图片<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <img style='width: 100px;height: 100px' src='' id="portalPicUrl" data-src=""
                                 onerror="this.src='${(base)}/static/web/images/noimage.png'" class='img-rounded '/>
                            <button type="button" name="to_upload" class="btn btn-default">上传</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">导航页图片<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <img style='width: 100px;height: 100px' src='' id="navPicUrl" data-src=""
                                 onerror="this.src='${(base)}/static/web/images/noimage.png'" class='img-rounded '/>
                            <button type="button" name="to_upload" class="btn btn-default">上传</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">详情页头背景图片<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <img style='width: 100px;height: 100px' src='' id="bgPicUrl" data-src=""
                                 onerror="this.src='${(base)}/static/web/images/noimage.png'" class='img-rounded '/>
                            <button type="button" name="to_upload" class="btn btn-default">上传</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">正文详情<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <div id='content-editor'>
                            </div>
                        </div>
                    </div>

            </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="submit_btn">确定</button>
        </div>
    </div>
</div>
</div>
<#--shangchuan-->
<div class="modal fade" id="upload-model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="uploadForm" method="post" action="${(base)!}/upload" enctype='multipart/form-data'>
                <input type="file" id="file" name="file" target-src-name="">
                <input type="hidden" name="tag" value="solution_let_img">

            </form>
        </div>
    </div>
</div>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script type="text/javascript" src="${(project.staticDomain!)}/libs/My97DatePicker/WdatePicker.js"></script>
<script src="${ (project.staticDomain)! }/libs/wangEditor/wangEditor.min.js"></script>
<script src="${ (project.staticDomain)! }/js/let-solution/list.js"></script>
<script>
    var E = window.wangEditor;
    var contentEditor = new E('#content-editor');
    contentEditor.customConfig.uploadImgServer = '${(base)!}/editorUpload';
    contentEditor.customConfig.uploadFileName = 'file';
    contentEditor.create();

</script>