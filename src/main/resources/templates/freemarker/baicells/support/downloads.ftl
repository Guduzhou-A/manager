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
        <li class="active">下载列表管理</li>
    </ol>

    <div class="search">
        <form class="form-inline">
            <div class="form-group">
                <input type="text" class="form-control" id="titleName" placeholder="标题名称">
            </div>
            <div class="form-group" style="margin-left: 0px;">
                <select class="form-control" id="typeName">
                    <option value="-1">类型</option>
                    <option value="1">公司产品</option>
                    <option value="2">产品及解决方案</option>
                    <option value="3">客户支持</option>
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

    <table class="table table-striped">
        <tbody>
        <tr>
            <td style="text-align: right!important;"><label for="title" class="control-label">标题<font class="red">*</font>：</label></td>
            <td><input type="text" class="form-control" id="title" placeholder="标题名称"></td>
        </tr>
        <tr>
            <td style="text-align: right!important;"><label for="title" class="control-label">下载类型<font class="red">*</font>：</label></td>
            <td> <select class="form-control" id="type">
                <option value="1">公司产品</option>
                <option value="2">产品及解决方案</option>
                <option value="3">客户支持</option>
            </select></td>
        </tr>

        <tr>
            <td style="text-align: right!important;"><label for="title" class="control-label">文件<font class="red">*</font>：</label></td>
            <td>
            <form id="uploadForm" method="post" action="${(base)!}/upload" enctype='multipart/form-data'>
                <input type="file" id="pdf_file" name="pdf_file" target-src-name="">
                <input type="hidden" name="pdf_tag" id="url" src="" value="support_downloads_file">
                <button type="button" name="to_upload" class="btn btn-default">上传</button>
                <span class="label1" id="upload-error-success" style="margin-left: 5%;display: none">上传成功</span>
                <span class="label" id="upload-error-fail" style="margin-left: 5%;display: none">上传失败</span>
            </td>
            </form>

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
                <th>类型</th>
                <th>文件下载</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>

    </div>
</div>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script src="${ (project.staticDomain)! }/js/support/downloads.js"></script>
