<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${config.name!"竹子的博客"} | 后台</title>
	<link rel="shortcut icon" href="/static/img/favicon.ico">

    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/animate.css/3.5.0/animate.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-sweetalert/1.0.1/sweetalert.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/select2/4.0.6-rc.1/css/select2.min.css" rel="stylesheet">

    <link href="/static/inspinia/css/style.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
    <#include "/common/nav.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/common/top.ftl">

        <#nested>
    </div>
</div>
</body>
</html>
</#macro>