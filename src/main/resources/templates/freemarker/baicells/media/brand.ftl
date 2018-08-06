<link rel="stylesheet" href="${(project.staticDomain!)}/libs/dataTables-1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="${(project.staticDomain!)}/libs/zebra_dialog-1.4.0/css/flat/zebra_dialog.min.css"">
<style>
    .table {
        border-bottom: 0px;
    }
</style>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">方案管理</a></li>
        <li class="active">活动管理</li>
    </ol>
    <table class="table table-striped">
        <tbody>
        <tr>
            <td style="text-align: right!important;"><label for="title" class="control-label">标题<font class="red">*</font>：</label></td>
            <td><input type="text" class="form-control" id="title" placeholder="标题名称"></td>
        </tr>
        <tr>
            <td style="text-align: right!important;"><label for="title" class="control-label">图片<font class="red">*</font>：</label></td>
            <td> <img style='width: 100px;height: 100px' src='' id="imgUrl" data-src=""
                      onerror="this.src='${(base)}/static/web/images/noimage.png'" class='img-rounded '/>
                <button type="button" name="to_upload" class="btn btn-default">上传</button></td>
        </tr>
        <tr>
            <td style="text-align: right!important;"></td>
            <td><button type="button" id="add" class="btn btn-default">添加</button></td>
        </tr>
        </tbody>

    </table>

    <#--<div style="padding-left: 2%;padding-right: 2%">-->
            <#--<div class="form-group">-->
                <#---->
            <#--</div>-->
            <#--<div class="form-group">-->
                <#--<div class="col-sm-10">-->
                    <#--<img style='width: 100px;height: 100px' src='' id="imgUrl" data-src=""-->
                         <#--onerror="this.src='${(base)}/static/web/images/noimage.png'" class='img-rounded '/>-->
                    <#--<button type="button" name="to_upload" class="btn btn-default">上传</button>-->
                <#--</div>-->
            <#--</div>-->
        <#--<div class="form-group">-->
            <#--<button type="button" id="add" class="btn btn-default">添加</button>-->
        <#--</div>-->
    <#--</div>-->

    <div>
        <hr/>
    </div>
    <div class="content">
        <table id="content_table" class="table table-striped" cellspacing="0" width="100%"
               style="word-break:break-all;">
            <thead>
            <tr>
                <th>标题</th>
                <th>图片</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>

    </div>
</div>
<#--shangchuan-->
<div class="modal fade" id="upload-model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="uploadForm" method="post" action="${(base)!}/upload" enctype='multipart/form-data'>
                <input type="file" id="file" name="file" target-src-name="">
                <input type="hidden" name="tag" value="media_brand_img">
            </form>
        </div>
    </div>
</div>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script src="${ (project.staticDomain)! }/js/media/brand.js"></script>
