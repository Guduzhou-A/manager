<link rel="stylesheet" href="${(project.staticDomain!)}/libs/dataTables-1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="${(project.staticDomain!)}/libs/zebra_dialog-1.4.0/css/flat/zebra_dialog.min.css"">


<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">方案管理</a></li>
        <li class="active">5G-解决方案</li>
    </ol>

    <div class="search">
        <form class="form-inline">
            <div class="form-group">
                <input type="text" class="form-control" id="title" placeholder="标题名称">
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
                <input id="endTime" name="endTime" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>

            <button type="button" id="search" class="btn btn-default">搜索</button>
        </form>
    </div>
    <div>
        <hr/>
    </div>
    <div class="content">
        <table id="content_table" class="table table-striped" cellspacing="0" width="100%"
               style="word-break:break-all;">
            <thead>
            <tr>
                <#--<th class="sorting_disabled" rowspan="1" colspan="1">-->
                    <#--<input type="checkbox" id="chk_all" name="chk_all">-->
                <#--</th>-->
                <th>id</th>
                <#--<th>标题</th>-->
                <#--<th>标题图片</th>-->
                <#--<th>标题描述</th>-->
                <#--<th>详情描述</th>-->
                <#--<th>创建时间</th>-->
                <#--<th>操作</th>-->
            </tr>
            </thead>
        </table>

    </div>
</div>
<#--创建新5G-解决方案窗口-->
<div class="modal fade" id="down_shelves_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新建</h4>
            </div>
            <div class="modal-body" style="padding-left: 2%;padding-right: 2%">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_submit_btn">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/dataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/zebra_dialog-1.4.0/js/zebra_dialog.min.js"></script>
<script type="text/javascript" src="${(project.staticDomain!)}/libs/My97DatePicker/WdatePicker.js"></script>
<script src="${ (project.staticDomain)! }/libs/wangEditor/wangEditor.min.js"></script>
<script src="${ (project.staticDomain)! }/js/5g-solution/list.js"></script>