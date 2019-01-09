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
    function initTable() {
    	var fieldColumns = [{
            checkbox: true
        }, {
            field: 'id',
            title: '用户ID'
        }, {
            field: 'userName',
            title: '用户名'
        }, {
            field: 'password',
            title: '密码'
        }];
    	//先销毁表格  
        $('#cusTable').bootstrapTable('destroy'); 
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            //url: "http://192.168.1.120:8006/user/findUser", //获取数据的Servlet地址
            url: "http://192.168.1.120:8045/api-b/user/findUser", //获取数据的Servlet地址
            toolbar: '#toolbar',                //工具按钮用哪个容器
            contentType: "application/x-www-form-urlencoded",
            striped:true,//隔行变色
            cache:false,  //是否使用缓存
            showColumns:false,// 列
            pagination: true, //分页
            paginationLoop:false,
            paginationPreText:'上一页',
            paginationNextText:'下一页',
            showPaginationSwitch:false,//是否显示数据条数选择框
            sortable: false,           //是否启用排序
            singleSelect: false,
            search:false, //显示搜索框
            buttonsAlign: "right", //按钮对齐方式
            showRefresh:false,//是否显示刷新按钮
            sidePagination: "server", //服务端处理分页
            pageNumber:1,
            pageSize:3,
            pageList:[5,10, 25, 50, 100],
            undefinedText:'--',
            uniqueId: "id", //每一行的唯一标识，一般为主键列
            queryParamsType:'',
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    userName : $("#userName").val(),
                    accessToken : "token"
                };
                return param;
            },
            columns: fieldColumns
        });
        return fieldColumns;
    }
    $(document).ready(function () { 
    	//调用函数，初始化表格  
    	var result = initTable();  
    	//当点击查询按钮的时候执行  
    	$("#search").bind("click", initTable); 
    	//添加：
        $('#btn_add').on("click", function () {
            $('#myModal').modal();//显示模态框(子窗体)
        });
    	//导出全部数据
        $('#btn_export').on("click", function () {
        	exportExcel(result);
        });
    	//删除
    	$('#btn_delete').on('click', function() {
    		//获取到选中数据的用户id,$.map()方法适用于将数组或对象每个项目新阵列映射到一个新数组的函数
    		var row = $.map($("#cusTable").bootstrapTable('getSelections'),function(row){
                return row ;
            });
    		if(row.length <= 0) {
    			alert("请选择需要删除的记录!!");
    			return;
    		}
    		if(confirm("确认删除吗？")) {
            var ids="";
            for (var i = 0; i < row.length; i++) {
                //获取自定义table 的中的checkbox值
                var id = row[i].id;   //OTRECORDID这个是你要在列表中取的单个id
                ids += id + ","; //然后把单个id循环放到ids的数组中
            }
            var deleteUserId = ids;
            $.ajax({
            	type : 'GET',
            	//url : '/springboot/user/springbootDeleteUser',
            	url : 'http://192.168.1.120:8045/api-b/user/deleteUser',
            	dataType : 'text',
            	data : {
            		 deleteUserId : deleteUserId,
            		 accessToken : "token"
            	},
            	success : function (msg) {
            		if("success" == msg) {
            			$('#cusTable').bootstrapTable('refresh');
            		} else {
            			alert('删除失败!');
            		}
                 }
    	     });
    		 } 
    	});
    	//更新
    	$('#btn_update').on('click',function() {
    		//获取到选中行数据
    		var selectData = $.map($('#cusTable').bootstrapTable('getSelections'),function(row){
    			return row;
    		});
    		if(selectData.length == 1) {//只选中一行
    			//弹出更新用户模态框
    			$('#updateModal').modal();
    		    //设置选中数据的值
    		    $('#userId_update').val(selectData[0].id);
    		    $('#userName_update').val(selectData[0].userName);
    		    $('#password_update').val(selectData[0].password);
    		} else {
    			alert("一次只能更新一条记录!!");
    		}
    		
    	});
    });
    
    //添加用户函数
    function add() {
    	var userName = $("#userName_add").val();
    	var password = $("#password_add").val();
    	$.ajax({
    		type : 'GET',
    		//url : '/springboot/user/springbootAddUser',
    		url : 'http://192.168.1.120:8045/api-b/user/addUser',
    		dataType : 'text',
    		data : {
    			userName : userName,
    			password : password,
    			accessToken : "token"
    		},
    		success : function(msg) {//使用json格式传递参数，后台需要返回正确的json格式结果才能执行成功函数
    			if(msg == "success") {
    				$('#form_add')[0].reset();//清空表单内容
    				$('#cusTable').bootstrapTable('refresh');
    			} else {
    				alert("添加失败!");
    			}
    		}
    	});
    }
    //更新提交数据
    function update() {
    	var id = $('#userId_update').val();
    	var userName = $('#userName_update').val();
    	var password = $('#password_update').val();
    	$.ajax({
    		type : 'GET',
    		dataType : 'text',
    		//url : '/springboot/user/sprinbootUpdateUser',
    		url : 'http://192.168.1.120:8045/api-b/user/updateUser',
    		data : {
    			id : id,
    			userName : userName,
    			password : password,
    			accessToken : "token"
    		},
    		success : function(msg) {
    			if("success" == msg) {
    				$('#cusTable').bootstrapTable('refresh');
    			} else {
    				alert("更新失败!!");
    			}
    		}
    	});
    }
    //导出数据
    function exportExcel(result) {
    	var fileName = $('.panel-title').text();
    	var fieldStr = "";
    	var titleStr = "";
    	for(var i = 0; i < result.length; i++) {
    		if(result[i].field != '' && result[i].field != null ) {
    			fieldStr += result[i].field + ",";
    			titleStr += result[i].title + ",";
    		}
    	}
    	window.location.href = "/springboot/export/exportExcel?fileName="+fileName+"&field="+fieldStr+"&title="+titleStr+"&pageSize=10"+"&pageNumber=1";
    }
    </script>
    <nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div>
				<ul class="nav navbar-nav">
				    <li ><a href="index.jsp">主页</a></li>
					<li class="active"><a href="user.jsp">用户</a></li>
					<li><a href="student.jsp">学生</a></li>
				</ul>
			</div>
		</div>
	</nav>
    
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">实现用户增删改查</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="userName" class="col-sm-1 control-label">用户名</label>
						<div class="col-lg-2">
							<input type="text" class="form-control input-sm" id="userName"
								placeholder="请输入用户名">
						</div>
						<div class="col-lg-1">
						    <button type="button" id="search" class="btn btn-primary input-sm">查询</button>
						</div>
					</div>
			</form>
		</div>
		
		
	<div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
        </button>
        <button id="btn_update" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
     </div>
    
    
		<table class="table table-hover" id="cusTable">
		</table>



		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">新增</h4>
					</div>
					<div class="modal-body">
						<form id="form_add" method="post">
							<div class="form-group">
								<label for="userName_add">用户名</label> 
								<input type="text"name="namepname" class="form-control" id="userName_add" placeholder="用户名">
							</div>

							<div class="form-group">
								<label for="password_add">密码</label> 
								<input type="text" name="namepid" class="form-control" id="password_add" placeholder="密码">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
								</button>
								<button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="add()">
									<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">更新</h4>
					</div>
					<div class="modal-body">
						<form id="form_update" method="post">
						    <input type="hidden" name="userId" id="userId_update" value="">
							<div class="form-group">
								<label for="userName_add">用户名</label> 
								<input type="text"name="namepname" class="form-control" id="userName_update" placeholder="用户名">
							</div>

							<div class="form-group">
								<label for="password_add">密码</label> 
								<input type="text" name="namepid" class="form-control" id="password_update" placeholder="密码">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
								</button>
								<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="update()">
									<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

