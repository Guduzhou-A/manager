<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <base id="base" href="${base}">
    <title><#if project??> ${ (project.title)! }</#if></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${(project.staticDomain!)}/css/common/style.default.css">
    <link rel="stylesheet" href="${(project.staticDomain!)}/libs/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${(project.staticDomain!)}/libs/bootstrap-3.3.7/css/bootstrap-theme.min.css">

    <script src="${ (project.staticDomain)! }/libs/jquery-3.2.1/jquery-3.2.1.min.js"></script>
    <script src="${ (project.staticDomain)! }/libs/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${ (project.staticDomain)! }/js/common.js"></script>
    <script src="${ (project.staticDomain)! }/libs/wangEditor/wangEditor.min.js"></script>
</head>

<body>
<@tiles.insertAttribute name="header"/>
	  <@tiles.insertAttribute name="body"/>
	  <@tiles.insertAttribute name="footer"/>
</body>
</html>