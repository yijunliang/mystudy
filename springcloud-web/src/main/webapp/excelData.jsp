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
    
    
    function initTable(fieldColumns) {
    	//先销毁表格  
        $('#cusTable').bootstrapTable('destroy'); 
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            //url: "/springboot/importExcelController/showExcelData", //获取数据的Servlet地址
            url: "http://localhost:8006/springcloud/showExcelData", 
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
                    modelName : $("#modelName").find('option:selected').text()
                };
                return param;
            },
            columns: fieldColumns
        });
        
        return fieldColumns;
    }
    $(document).ready(function () {
    	//请求模板下拉框数据
    	$.ajax({  
    		    // get请求地址  
    		    //url: '/springboot/importExcelController/queryExcelModel',  
    		    url: 'http://localhost:8006/springcloud/queryExcelModel',  
    		    dataType: "json",
    		    async : false,   
    		    success: function (data) {  
    		        var resultArray = data.results; 
    		        $("#modelName").find('option').remove();
    		        if(resultArray.length <= 0) {
    		        	return;
    		        } else {
    		        for (i = 0; i < resultArray.length; i++) {
    		            $("#modelName").append($('<option value=' + resultArray[i].id + '>' + resultArray[i].text + '</option>'));
    		        }
    		        //设置下拉框默认选中值
    		        //$("#modelName option:eq(1)").attr("selected","selected");
    		        $("#modelName").find("option:eq(0)").attr("selected","selected");
    		        //根据默认选中数据查询该模板的列头信息
    		        getHeadData();
    		        }
    		    }  
    		});
    	//下拉框选项变化时重新加载模板列头
    	$("#modelName").change(function() {
    		getHeadData();
    	});
    	//导入模板
    	$("#uploadfile").fileinput({ 
    	    language: 'zh', 
    	    uploadUrl: 'http://localhost:8006/springcloud/uploadFile',//后台上传方法 
    	    allowedFileExtensions: ['xls', 'docx','xlsx'],//上传文件扩展名 
    	    shouUpload: false, 
    	    showRemove: false, 
    	    browseClass: 'btn btn-danger', 
    	    maxFileSize: 5000, 
    	    maxFileNum: 10, 
    	    allowedPreviewTypes: null, 
    	    previewFileIconSettings: { 
    	      'doc': '<i class="fa fa-file-word-o text-muted"></i>'
    	    }, 
    	    previewFileExtSettings: { 
    	      'doc': function (ext) { 
    	        return ext.match(/(doc|docx)$/i); 
    	      } 
    	    } 
    	  });
    	
    	//导入模板数据
    	$("#uploadfileData").fileinput({ 
     	    language: 'zh', 
     	    uploadUrl: 'http://localhost:8006/springcloud/importExcelData',//后台上传方法 
     	    allowedFileExtensions: ['xls', 'docx','xlsx'],//上传文件扩展名 
     	    shouUpload: false, 
     	    showRemove: false, 
     	    browseClass: 'btn btn-danger', 
     	    maxFileSize: 5000, 
     	    maxFileNum: 10, 
     	    allowedPreviewTypes: null, 
     	    uploadExtraData:function(){//向后台传递参数
     		   var data={
     		        fileModelName : $("#modelName").find("option:selected").text(),
     		        };
     		   return data; 
     		},
     	    previewFileIconSettings: { 
     	      'doc': '<i class="fa fa-file-word-o text-muted"></i>'
     	    }, 
     	    previewFileExtSettings: { 
     	      'doc': function (ext) { 
     	        return ext.match(/(doc|docx)$/i); 
     	      } 
     	    } 
     	  });
    	
    	//异步上传返回结果处理（回调函数）
    	$("#uploadfileData").on("fileuploaded", function (event, data) {
    	        var obj = data.response;
    	        if("success"==obj.results) {
    	        	//导入模板数据文件成功后关闭模态框，并刷新数据
    	        	 $('#updateModal').modal('hide');//隐藏模态框(子窗体)
    	        	 getHeadData();
    	        }
    	});
    	$("#uploadfileData").on("filebatchuploadsuccess", function (event, data) {
  	        var obj = data.response;
  	        alert(obj);
  	   });
    	//导入模板
        $('#btn_add').on("click", function () {
            $('#myModal').modal();//显示模态框(子窗体)
        });
    	
      //导出全部数据
        $('#btn_export').on("click", function () {
        	$.ajax({  
        	    // get请求地址  
        	    url: 'http://localhost:8006/springcloud/queryExcelHeadsAndDataIndexs',  
        	    async : false, 
        	    dataType: "json",  
        	    data : {
        	    	modelName : $("#modelName").find('option:selected').text()
        	    },
        	    success: function (data) {
        		var fieldColumns = [{
                    checkbox: true
                }]; 
                var dataObj = eval("(" + data +")");
        		for(var i = 0; i < dataObj.dataIndex.length; i++) {
        			var titleJson = {
        					 field : dataObj.dataIndex[i],
        					 title : dataObj.head[i]
        				};
        			fieldColumns.push(titleJson);
        		}
                exportExcel(fieldColumns); 
        	   }  
        	});
        	
        });
      
    	//删除
    	/* $('#btn_delete').on('click', function() {
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
    	}); */
    	//导入模板数据
    	$('#btn_update').on('click',function() {
    		$('#updateModal').modal();
    	});
    });
    //添加excel模板函数
    function add() {
    	
    }
    //导出数据
    function exportExcel(result) {
    	var fileName = $("#modelName").find("option:selected").text();
    	var fieldStr = "id,";
    	var titleStr = "序号,";
    	for(var i = 0; i < result.length; i++) {
    		if(result[i].field != '' && result[i].field != null ) {
    			fieldStr += result[i].field + ",";
    			titleStr += result[i].title + ",";
    		}
    	}
    	window.location.href = "http://localhost:8006/springcloud/exportExcel?fileName="+fileName+"&field="+fieldStr+"&title="+titleStr+"&pageSize=100"+"&pageNumber=1";
    }
    //请求列头数据
    function getHeadData() {
		$.ajax({  
    	    // get请求地址  
    	    url: 'http://localhost:8006/springcloud/queryExcelHeadsAndDataIndexs',  
    	    async : false, 
    	    dataType: "json",  
    	    data : {
    	    	modelName : $("#modelName").find('option:selected').text()
    	    },
    	    success: function (data) {
    		var fieldColumns = [{
                checkbox: true
            }]; 
            var dataObj = eval("("+data+")");
    		for(var i = 0; i < dataObj.dataIndex.length; i++) {
    			var titleJson = {
    					 field : dataObj.dataIndex[i],
    					 title : dataObj.head[i]
    				};
    			fieldColumns.push(titleJson);
    		}
    	    initTable(fieldColumns); 
    	   }  
    	});
    }
    </script>
    
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">excel操作</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="modelName" class="col-sm-1 control-label">模板</label>
					<div class="col-xs-2">
						<select class="form-control input-sm" id="modelName">
							<option value="1">请选择模板</option>
							<option value="2">广西省</option>
							<option value="3">福建省</option>
							<option value="4">湖南省</option>
							<option value="5">山东省</option>
						</select>
					</div>
					<div class="col-lg-1">
						<button type="button" id="search" class="btn btn-primary input-sm">查询</button>
					</div>
				</div>
			</form>
		</div>
		
		
	<div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增模板
        </button>
        <button id="btn_update" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>导入模板数据
        </button>
        <!-- <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button> -->
        <button id="btn_export" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出全部数据
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
						<h4 class="modal-title" id="myModalLabel">新增模板</h4>
					</div>
					<div class="modal-body">
						<form id="form_add" method="post">
							<div class="form-group">
								<input type="file" id="uploadfile" name="file" class="file"/> 
								<input type="hidden" id="filePath" name="filePath" value="" />
							</div>
							<!-- <div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
								</button>
								<button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="add()">
									<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
								</button>
							</div> -->
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
						<h4 class="modal-title" id="myModalLabel">导入模板数据</h4>
					</div>
					<div class="modal-body">
						<form id="form_update" method="post">
						    <input type="hidden" name="userId" id="userId_update" value="">
							<div class="form-group">
								<input type="file" id="uploadfileData" name="fileData" class="file"/> 
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

