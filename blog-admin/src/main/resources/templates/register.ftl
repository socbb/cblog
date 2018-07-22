<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>注册 | Register</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/animate.css/3.5.0/animate.min.css" rel="stylesheet">
    <link href="static/inspinia/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">IN+</h1>
        </div>
        <h3>Register to IN+</h3>
        <p>Create account to see it in action.</p>
        <form id="form" class="m-t" role="form" action="/register">
            <div class="form-group">
                <input type="text" class="form-control required" placeholder="用户名">
            </div>
            <div class="form-group">
                <input type="email" class="form-control required" placeholder="邮箱">
            </div>
            <div class="form-group">
                <input type="password" class="form-control required" placeholder="密码">
            </div>
            <button type="button" onclick="register()" class="btn btn-primary block full-width m-b">注册</button>

            <p class="text-muted text-center">
                <small>已经有账号？</small>
            </p>
            <a class="btn btn-sm btn-white btn-block" href="/login">登陆</a>
        </form>
        <p class="m-t">
            <small>竹子的博客 &copy; 2014</small>
        </p>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/localization/messages_zh.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-growl/1.0.6/bootstrap-growl.min.js"></script>
<script src="static/js/Utils.js"></script>
<script type="text/javascript">
    $(function(){
        $("#form").validate();
    });

    var register = function () {
        if (!$("#form").valid()) {
            return;
        }
        $.post('/register', Utils.getParam($("#form")), function(res){
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
