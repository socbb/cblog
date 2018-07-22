<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录 | 竹子的博客</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/animate.css/3.5.0/animate.min.css" rel="stylesheet">
    <link href="/static/inspinia/css/style.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">IN+</h1>
        </div>
        <h3>竹子的博客</h3>
        <form id="form" class="m-t" role="form" method="post">
            <div class="form-group">
                <input type="text" name="username" class="form-control required" placeholder="用户名、手机号、邮箱">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control required" placeholder="密码">
            </div>
            <button type="button" onclick="login()" class="btn btn-primary block full-width m-b">Login</button>
            <a href="/login"><small>忘记密码?</small></a>
            <p class="text-muted text-center"><small>Do not have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="/register">注册</a>
        </form>
        <p class="m-t"> <small>竹子的博客 &copy; 2018</small> </p>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/localization/messages_zh.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-growl/1.0.6/bootstrap-growl.min.js"></script>
<script src="/static/js/Utils.js"></script>
<script type="text/javascript">
    $(function(){
        $("#form").validate();
    });
    
    var login = function () {
        if (!$("#form").valid()) {
            return;
        }
        $.post('/login', Utils.getParam($("#form")), function(res){
            if (res.code === 200) {
                window.location.href = '/';
            } else {
                $.growl(res.msg);
            }
        }, 'json');
    }
</script>
</body>
</html>
