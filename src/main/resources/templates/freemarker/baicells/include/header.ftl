<!-- Preloader -->
<div class="mask"><div id="loader"></div></div>
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
                <li class="dropdown <#if requestPath! == 'media'>active</#if>">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">媒体管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/media/news">新闻稿管理</a></li>
                        <li><a href="${base}/media/brand">活动管理</a></li>
                        <li><a href="${base}/media/customer">客户内容管理</a></li>
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
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎:${(user.username)!} <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="${base}/logout">登出</a></li>
	          </ul>
	        </li>
	      </ul>


	    </div><!-- /.navbar-collapse -->
	</nav>
</div>