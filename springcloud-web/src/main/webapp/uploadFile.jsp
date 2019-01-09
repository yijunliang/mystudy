<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<!-- 文件上传 -->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bootstrap 101 Template</title>

<!-- 引入bootstrap样式 -->
<link href="bootstrap3.7/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3.7/bootstrap-table.min.css" rel="stylesheet">
<link href="bootstrap3.7/fileinput.min.css" rel="stylesheet">
<!-- jquery -->
<script src="jquery-2.1.4/jquery.min.js"></script>
<script src="bootstrap3.7/bootstrap.min.js"></script>
<script src="bootstrap3.7/bootstrap-table.min.js"></script>
<script src="bootstrap3.7/bootstrap-table-zh-CN.min.js"></script>
<script src="bootstrap3.7/fileinput.min.js"></script>
<script src="bootstrap3.7/zh.js"></script>

</head>
<body>
    <script type="text/javascript">
    
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
            	type : 'POST',
            	url : '/springboot/user/springbootDeleteUser',
            	dataType : 'text',
            	data : {
            		 deleteUserId : deleteUserId
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
    		type : 'POST',
    		url : '/springboot/user/springbootAddUser',
    		dataType : 'text',
    		data : {
    			userName : userName,
    			password : password
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
    		type : 'POST',
    		dataType : 'text',
    		url : '/springboot/user/sprinbootUpdateUser',
    		data : {
    			id : id,
    			userName : userName,
    			password : password
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
    	/* var a = fieldStr + "|" + titleStr;
    	 $.ajax({
    		type : 'GET',
    		url : '/springboot/export/exportExcel',
    		dataType : 'text',
    		data : {
    			fileName : fileName,
    			field : fieldStr,
    			title : titleStr,
    			pageSize : 10,
    			pageNumber : 1
    		},
    		success : function(msg) {
    			 if(msg == "success") {
    				alert("导出成功！！");
    			} else {
    				alert("导出失败！！");
    		} 
    	}});  */
    }
    </script>
    
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Excel模板文件上传</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" enctype="multipart/form-data">
					<div class="form-group">
						<label for="fileName" class="col-sm-1 control-label">选择文件</label>
						<div class="col-lg-2">
							<input type="file" class="file" id="fileName" >
						</div>
						<div class="col-lg-1">
						    <button type="button" id="search" class="btn btn-primary input-sm">上传</button>
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
        <button id="btn_export" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出全部
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

