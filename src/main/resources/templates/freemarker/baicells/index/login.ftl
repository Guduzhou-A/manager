<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <base id="base" href="${base}">
    <title><#if project??> ${ (project.title)! }</#if></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${(project.staticDomain!)}/css/login/login.css">
    <link rel="stylesheet" href="${(project.staticDomain!)}/css/login/base.css">
    <link rel="stylesheet" href="${(project.staticDomain!)}/libs/jqueryMy-message-master/css/jquery.my-message.1.1.css">
    <script src="${ (project.staticDomain)! }/libs/jquery-3.2.1/jquery-3.2.1.min.js"></script>
    <script src="${ (project.staticDomain)! }/libs/jqueryMy-message-master/js/jquery.my-message.1.1.js"></script>
    <script src="${ (project.staticDomain)! }/js/common.js"></script>
</head>

<body>

<div class="login">
    <form action="" method="post" id="form">
        <div class="logo"></div>
        <div class="login_form">
            <div class="user">
                <input class="text_value" value="" name="username" type="text" id="username" required >
                <input class="text_value" value="" name="password" type="password" id="password" required >
            </div>
            <button class="button" id="submit" type="button">登录</button>
        </div>

        <div id="tip"></div>
    </form>
</div>
<script src="${ (project.staticDomain)! }/js/index/login.js"></script>
</body>

</html>
