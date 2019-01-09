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
    	//先销毁表格  
        $('#cusTable').bootstrapTable('destroy'); 
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            //url: "/student/findStudentByLimit", //获取数据的Servlet地址
            url: "http://192.168.1.120:8045/api-b/student/findStudent", //获取数据的Servlet地址
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
                    studentName : $("#studentName").val(),
                    accessToken : "token"
                };
                return param;
            },
            columns: [{
                checkbox: true
            }, {
                field: 'studentName',
                title: '姓名'
            }, {
                field: 'studentSex',
                title: '性别',
                formatter : function(val, row, index) {
                	if(val == 0) {
                		return "男";
                	} else {
                		return "女";
                	}
                }
            }, {
                field: 'studentSchool',
                title: '学校'
            }, {
                field: 'studentClass',
                title: '班级'
            }, {
                field: 'studentNo',
                title: '学号'
            }]
        });
    }
    $(document).ready(function () {          
    	//调用函数，初始化表格  
    	initTable();  
    	//当点击查询按钮的时候执行  
    	$("#search").bind("click", initTable); 
    	//添加：
        $('#btn_add').on("click", function () {
            $('#myModal').modal();//显示模态框(子窗体)
        });
    	//删除
    	$('#btn_delete').on('click', function() {
    		if(confirm("确认删除吗？")) {
    		//获取到选中数据的用户id,$.map()方法适用于将数组或对象每个项目新阵列映射到一个新数组的函数
    		var row = $.map($("#cusTable").bootstrapTable('getSelections'),function(row){
                return row ;
            });
            var ids="";
            for (var i = 0; i < row.length; i++) {
                //获取自定义table 的中的checkbox值
                var id = row[i].id;   //OTRECORDID这个是你要在列表中取的单个id
                ids += id + ","; //然后把单个id循环放到ids的数组中
            }
            var deleteUserId = ids;
            $.ajax({
            	type : 'GET',
            	//url : '/student/deleteStudent',
            	url: "http://192.168.1.120:8045/api-b/student/deleteStudent", //获取数据的Servlet地址
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
    		    $('#studentId').val(selectData[0].id);
    		    $("#studentName_update").val(selectData[0].studentName);
    	    	$("#studentSex_update").val(selectData[0].studentSex);
    	    	$("#studentSchool_update").val(selectData[0].studentSchool);
    	    	$("#studentClass_update").val(selectData[0].studentClass);
    	    	$("#studentNo_update").val(selectData[0].studentNo);
    		} else {
    			alert("一次只能更新一条记录!!");
    		}
    		
    	});
    });
    
    //添加用户函数
    function add() {
    	var studentName = $("#studentName_add").val();
    	var studentSex = $("#studentSex_add").val();
    	var studentSchool = $("#studentSchool_add").val();
    	var studentClass = $("#studentClass_add").val();
    	var studentNo = $("#studentNo_add").val();
    	$.ajax({
    		type : 'GET',
    		dataType : 'text',
    		//url : '/student/addStudent',
    		url: "http://192.168.1.120:8045/api-b/student/addStudent", //获取数据的Servlet地址
    		data : {
    			studentName : studentName,
    			studentSex : studentSex,
    			studentSchool : studentSchool,
    			studentClass : studentClass,
    			studentNo : studentNo,
    			accessToken : "token"
    		},
    		success : function(msg) {
    			if("success" == msg) {
    				$('#form_add')[0].reset();//清空表单内容
    				$('#cusTable').bootstrapTable('refresh');
    			} else {
    				alert("保存失败!!");
    			}
    		}
    	});S
    }
    //更新提交数据
    function update() {
    	var id = $('#studentId').val();
    	var studentName = $("#studentName_update").val();
    	var studentSex = $("#studentSex_update").val();
    	var studentSchool = $("#studentSchool_update").val();
    	var studentClass = $("#studentClass_update").val();
    	var studentNo = $("#studentNo_update").val();
    	$.ajax({
    		type : 'GET',
    		dataType : 'text',
    		//url : '/student/updateStudent',
    		url: "http://192.168.1.120:8045/api-b/student/updateStudent", //获取数据的Servlet地址
    		data : {
    			id : id,
    			studentName : studentName,
    			studentSex : studentSex,
    			studentSchool : studentSchool,
    			studentClass : studentClass,
    			studentNo : studentNo,
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
    </script>
    
    
    <nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div>
				<ul class="nav navbar-nav">
				    <li ><a href="index.jsp">主页</a></li>
					<li ><a href="user.jsp">用户</a></li>
					<li class="active"><a href="student.jsp">学生</a></li>
				</ul>
			</div>
		</div>
	</nav>
    
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">实现学生增删改查</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="studentName" class="col-sm-1 control-label">姓名</label>
						<div class="col-lg-2">
							<input type="text" class="form-control input-sm" id="studentName"
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
								<label for="studentName_add">姓名</label> 
								<input type="text"name="namepname" class="form-control" id="studentName_add" placeholder="姓名">
							</div>

							<div class="form-group">
								<label for="studentSex_add">性别</label> 
								<select id="studentSex_add" class="form-control">
								  <option value="0">男</option>
								  <option value="1">女</option>
								</select>
							</div>
							
							<div class="form-group">
								<label for="studentSchool_add">学校</label> 
								<input type="text" name="namepid" class="form-control" id="studentSchool_add" placeholder="学校">
							</div>
							
							<div class="form-group">
								<label for="studentClass_add">班级</label> 
								<input type="text" name="namepid" class="form-control" id="studentClass_add" placeholder="班级">
							</div>
							
							<div class="form-group">
								<label for="studentNo_add">学号</label> 
								<input type="text" name="namepid" class="form-control" id="studentNo_add" placeholder="学号">
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
						    <input type="hidden" name="studentId" id="studentId" value="">
							<div class="form-group">
								<label for="studentName_update">姓名</label> 
								<input type="text"name="namepname" class="form-control" id="studentName_update" placeholder="姓名">
							</div>

							<div class="form-group">
								<label for="studentSex_update">性别</label> 
								<select id="studentSex_update" class="form-control">
								  <option value="0">男</option>
								  <option value="1">女</option>
								</select>
							</div>
							
							<div class="form-group">
								<label for="studentSchool_update">学校</label> 
								<input type="text" name="namepid" class="form-control" id="studentSchool_update" placeholder="学校">
							</div>
							
							<div class="form-group">
								<label for="studentClass_update">班级</label> 
								<input type="text" name="namepid" class="form-control" id="studentClass_update" placeholder="班级">
							</div>
							
							<div class="form-group">
								<label for="studentNo_update">学号</label> 
								<input type="text" name="namepid" class="form-control" id="studentNo_update" placeholder="学号">
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

