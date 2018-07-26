<link rel="stylesheet" href="${(project.staticDomain!)}/libs/dataTables-1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="${(project.staticDomain!)}/libs/zebra_dialog-1.4.0/css/flat/zebra_dialog.min.css"">
<style>
    .table {
        border-bottom: 0px;
    }

    .groupDetail table tr td {
        border-right: 1px solid #A3C6C8;
        border-left: 1px solid #A3C6C8;
    }

    .newGroup {
        border: 1px solid rgb(245, 245, 245)
    }

    .groupDetail {
        border-bottom: 2px solid #dddddd;
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
        <li><a href="#">产品介绍管理</a></li>
        <li class="active">产品页面管理</li>
    </ol>
    <div class="search">
        <form class="form-inline">
            <div class="form-group">
                <input type="text" class="form-control" id="titleName" placeholder="标题名称">
            </div>
            <div class="form-group" style="margin-left: 0px;">
                <select class="form-control" id="status">
                    <option value="">全部</option>
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
            <button type="button" id="delete_btn" class="btn btn-default">删除</button>
        </div>
        <table id="content_table" class="table table-striped" cellspacing="0" width="100%"
               style="word-break:break-all;">
            <thead>
            <tr>
                <th class="sorting_disabled" rowspan="1" colspan="1">
                    <input type="checkbox" id="chk_all" name="chk_all">
                </th>
                <th>标题</th>
                <th>首页图片</th>
                <th>导航页封面</th>
                <th>详情页背景图片</th>
                <th>创建时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<#--新建及编辑窗口-->
<div class="modal fade" id="model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:90%;overflow-y:auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑窗口</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="" id="content_form"
                      data-parsley-validate enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">标题<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="title" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">首页展示图片<font class="red">*</font>：</label>
                        <div class="col-sm-10">
                            <img style='width: 100px;height: 100px' src='' id="portalPicUrl" data-src=""
                                 onerror="this.src='${(base)}/static/web/images/noimage.png'" class='img-rounded'/>
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
                        <label for="title" class="col-sm-2 control-label">产品描述详情<font class="red">*</font>：</label>
                        <div class="col-sm-10 newGroup">
                        <#--<#if asd??>-->
                        <#--<div class="groupDetail">-->
                        <#--<input type="text" class="form-control" name="title" placeholder="组标题" value="">-->
                        <#--<table class="table table-striped">-->
                        <#--<tr>-->
                        <#--<td>-->
                        <#--<button type="button" name="to_upload" class="btn btn-default">上传</button>-->
                        <#--<button type="button" name="to_delete" class="btn btn-default">删除</button>-->
                        <#--</td>-->
                        <#--</tr>-->
                        <#--</table>-->
                        <#--</div>-->
                        <#--</#if>-->
                            <button type="button" name="create_new_group" class="btn btn-default">创建新的分组</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">正文详情<font class="red">*</font>：</label>


                        <div class="col-sm-10">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#choose-one" data-toggle="tab">单内容</a></li>
                                <li><a href="#choose-two" data-toggle="tab">带选项卡的多内容</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div class="tab-pane fade in active" id="choose-one">
                                    <div id='content-editor'>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="choose-two">
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="editor-title-1" style="margin-left:2%;margin-bottom: 2%" placeholder="切换标题" value="">
                                        <div id='content-editor-1'>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="editor-title-1" style="margin-left:2%;margin-bottom: 2%" placeholder="切换标题" value="">
                                        <div id='content-editor-2'>
                                        </div>
                                    </div>
                                </div>

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
                <input type="hidden" name="tag" value="product">
            </form>
        </div>
    </div>
</div>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script type="text/javascript" src="${(project.staticDomain!)}/libs/My97DatePicker/WdatePicker.js"></script>
<script src="${ (project.staticDomain)! }/js/product/list.js"></script>
<script>
    var E = window.wangEditor;
    var contentEditor = new E('#content-editor');
    contentEditor.create();
    var contentEditor1 = new E('#content-editor-1');
    contentEditor1.create();
    var contentEditor2 = new E('#content-editor-2');
    contentEditor2.create();


</script>