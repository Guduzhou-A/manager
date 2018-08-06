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
        <li><a href="#">简介信息页面管理</a></li>
        <li class="active">客户信息管理</li>
    </ol>
    <div class="search">
        <form class="form-inline">
            <div class="form-group">
                <input type="text" class="form-control" id="nickNameQuery" placeholder="客户姓名">
            </div>

            <div class="form-group" style="margin-left: 0px;">
                <select class="form-control" id="countryQuery">
                    <option value="">所属国家</option>
                    <#if countryMap??>
                        <#list countryMap?keys as k>
                            <option value="${(k)} /${(countryMap[k])!}">${(k)} /${(countryMap[k])!} </option>

                        </#list>
                    </#if>

                </select>
            </div>
            <div class="form-group my-form-group">
                <label for="beginTime">提交时间:</label>
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

        <tr>
            <td style="text-align: right!important;word-wrap:break-word;word-break:break-all;"><label for="title" class="control-label" >当前文件： <span id="current_file_title"> ${(file)!}</span></label></td>
            <td><button type="button" id="current_down_load" class="btn btn-default">下载</button></td>
        </tr>

        </tbody>

    </table>

    <div>
        <hr/>
    </div>
    <div class="content">

        <table id="content_table" class="table table-striped" cellspacing="0" width="100%"
               style="word-break:break-all;">
            <thead>
            <tr>
                <th>用户姓名</th>
                <th>国家</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>公司名称</th>
                <th>提交时间</th>
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
                <h4 class="modal-title" id="myModalLabel">详情窗口</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">e-mail：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="email" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">phone：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phone" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">公司名称：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="companyName" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">国家：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="country" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">所在洲区域：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="state" value="">
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">城市：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="city" value="">
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">地区：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="region" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">街道地址：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="streetAddress" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">zip：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="zip" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">网站地址：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="website" value="">
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">职业信息：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="jobTitle" value="">
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">问答一： Do you currently sell wireless infrastructure? </label>
                    <div class="col-sm-10">
                        <textarea id="questionOne" style="resize:none;overflow:scroll;width: 100%" rows="5"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">问答二： Do you currently sell 4G infrastructure? </label>
                    <div class="col-sm-10">
                        <textarea id="questionTwo" style="resize:none;overflow:scroll;width: 100%" rows="5"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">问答三： What other vendors do you sell products for? </label>
                    <div class="col-sm-10">
                        <textarea id="questionThree" style="resize:none;overflow:scroll;width: 100%" rows="5"></textarea>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script type="text/javascript" src="${(project.staticDomain!)}/libs/My97DatePicker/WdatePicker.js"></script>
<script src="${ (project.staticDomain)! }/js/about/info.js"></script>
