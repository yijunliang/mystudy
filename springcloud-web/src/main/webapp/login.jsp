<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bootstrap 101 Template</title>

<!-- 引入bootstrap样式 -->
<link href="bootstrap3.7/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3.7/bootstrap-table.min.css" rel="stylesheet">
<!-- jquery -->
<script src="jquery-2.1.4/jquery.min.js"></script>
<script src="bootstrap3.7/bootstrap.min.js"></script>
<script src="bootstrap3.7/bootstrap-table.min.js"></script>
<script src="bootstrap3.7/bootstrap-table-zh-CN.min.js"></script>

</head>
<body>
    <script type="text/javascript">

    $(document).ready(function () { 
    	$("#search").on("click",function(){
    		alert("执行登录操作");
    		$.ajax({
        		type : 'GET',
        		url : 'http://localhost:8045/api-b/doLogin',
        		dataType : 'text',
        		xhrFields: {
        		    withCredentials: true // 携带跨域cookie
        		},
        		data : {
        			uid : $("#userName").val(),
        			pwd : $("#password").val()
        		},
        		success : function(msg) {//使用json格式传递参数，后台需要返回正确的json格式结果才能执行成功函数
        			alert(msg);
        			if(msg == "login success") {
        				window.location.href = "/index.jsp";
        			} else {
        				alert("登录失败!");
        			}
        		}
        	});
    	});
    });
    </script>
    
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">用户登录</h3>
		</div>
		
		<div class="panel-body">
			<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="userName_add">用户名</label> 
							<input type="text"name="namepname" class="form-control" id="userName" placeholder="用户名">
						</div>
						<div class="form-group">
							<label for="password_add">密码</label> 
							<input type="text" name="namepid" class="form-control col-lg-1" id="password" placeholder="密码">
						</div>
						<div class="col-lg-1">
						    <button type="button" id="search" class="btn btn-primary input-sm">登录</button>
						</div>
			</form>
		</div>
	</div>
</body>
</html>

