<!-- Preloader -->
<div class="mask"><div id="loader"></div></div>
<#--changeUser-->

<#--<div class="modal fade" id="change-user-model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">-->
    <#--<div class="modal-dialog" role="document">-->
        <#--<div class="modal-content">-->
            <#--<form id="change-form" method="post" action="" >-->
                <#--<input type="hidden" id="uId"   value="${(currentUser.id)!}">-->
                <#--<div class="form-group">-->
                    <#--<label for="title" class="col-sm-2 control-label">昵称：</label>-->
                    <#--<div class="col-sm-10">-->
                        <#--<input type="text" id="nickName" value="${(currentUser.nickName)!}">-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="form-group">-->
                    <#--<label for="title" class="col-sm-2 control-label">原密码</label>-->
                    <#--<div class="col-sm-10">-->
                        <#--<input type="text" id="oldPass" value="">-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="form-group">-->
                    <#--<label for="title" class="col-sm-2 control-label">新密码：</label>-->
                    <#--<div class="col-sm-10">-->
                        <#--<input type="text" id="newPass" value="">-->
                    <#--</div>-->
                <#--</div>-->
            <#--</form>-->
        <#--</div>-->

        <#--<div class="modal-footer">-->
            <#--<div id="user-error-div">-->
                <#--<span style="color: red"></span>-->
            <#--</div>-->
            <#--<button type="button" class="btn btn-primary" id="change-user-btn">确定</button>-->
        <#--</div>-->

    <#--</div>-->
<#--</div>-->



<div class="theme-popover" id="change-user-model">
    <div class="theme-poptit">
        <a href="javascript:;" title="关闭" class="close" id="user-change-close">×</a>
    </div>
    <div class="theme-popbod dform">
        <input type="hidden" id="uId"   value="${(currentUser.id)!}">
        <form class="theme-signin" name="" action="" method="">
            <ol>
                <li><h3>修改密码！</h3></li>
                <li><strong>昵称：</strong><input class="ipt" type="text" id="nickName"  value="${(currentUser.nickName)!}" size="20" /></li>
                <li><strong>原密码：</strong><input class="ipt" type="password" id="oldPass" value="" size="20" /></li>
                <li><strong>新密码：</strong><input class="ipt" type="password" id="newPass" value="" size="20" /></li>
                <li><input class="btn btn-primary" type="button" id="change-user-btn" name="submit" value=" 确 定 " /></li>
                <li> <div id="user-error-div">
               <span style="color: red"></span>
                </div></li>
            </ol>
        </form>
    </div>
</div>
<div class="theme-popover-mask"></div>
<!--/Preloader -->
<div class="header">
	<nav class="navbar navbar-inverse">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <img class="title_logo" src="${(project.staticDomain!)}/images/logo.png" />
	      <#--<a class="navbar-brand nav-left-100" href="#"> ${(project.title!)}</a>-->
	    </div>

	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav nav-left-100">

                <li class="dropdown <#if requestPath! == 'home'   >active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">首页管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/home/banner">轮播图设置</a></li>
                        <li><a href="${base}/home/brand">首页活动位设置</a></li>
                    </ul>
                </li>


                <li class="dropdown <#if requestPath! == 'solution'>active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">方案管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/solution/5g">5G-解决方案</a></li>
                        <li role="separator	" class="divider"></li>
                        <li><a href="${base}/solution/let">LTE-解决方案</a></li>
                    </ul>
                </li>
                <li class="dropdown <#if requestPath! == 'product'>active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">产品管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/product/list">产品列表</a></li>

                    </ul>
                </li>
                <li class="dropdown <#if requestPath! == 'media' && requestPath! == 'support'>active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">媒体与支持管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/media/news">新闻稿管理</a></li>
                        <li><a href="${base}/media/brand">活动管理</a></li>
                        <li><a href="${base}/media/customer">客户内容管理</a></li>
                        <li><a href="${base}/support/downloads">下载内容管理</a></li>
                    </ul>
                </li>

                <li class="dropdown <#if requestPath! == 'system' >active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">页面设置 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/system/solution/5g">5G-导航页面设置</a></li>
                    </ul>
                </li>

                <li class="dropdown <#if requestPath! == 'about' >active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">简介信息页面管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/about/list">客户信息管理</a></li>
                    </ul>
                </li>



                <#--<li class="dropdown <#if requestPath! == 'media'>active</#if>">-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理 <span class="caret"></span></a>-->
                    <#--<ul class="dropdown-menu">-->
                        <#--<li><a href="${base}/media/news">新闻稿管理</a></li>-->
                        <#--<li><a href="${base}/media/brand">活动管理</a></li>-->
                        <#--<li><a href="${base}/media/customer">客户管理</a></li>-->
                    <#--</ul>-->
                <#--</li>-->


                <#--<li class="dropdown <#if requestPath! == 'media'>active</#if>">-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理 <span class="caret"></span></a>-->
                    <#--<ul class="dropdown-menu">-->
                        <#--<li><a href="${base}/media/news">支持与简介页面管理</a></li>-->
                        <#--<li><a href="${base}/media/brand">下载管理</a></li>-->
                        <#--<li><a href="${base}/media/customer">页面其余参数管理</a></li>-->
                    <#--</ul>-->
                <#--</li>-->


            </ul>


	      <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎:${(currentUser.nickName)!} <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="javascript:void(0)" id="changeUser">修改密码</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="${base}/logout">登出</a></li>
	          </ul>
	        </li>
	      </ul>


	    </div><!-- /.navbar-collapse -->
	</nav>
</div>