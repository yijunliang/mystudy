<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 引入bootstrap样式 -->
<link href="bootstrap3.7/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3.7/bootstrap-table.min.css" rel="stylesheet">
<link href="bootstrap3.7/fileinput.min.css" rel="stylesheet">
<link href="bootstrap3.7/bootstrap-select.min.css" rel="stylesheet">
<!-- jquery -->
<script src="jquery-2.1.4/jquery.min.js"></script>
<script src="bootstrap3.7/bootstrap.min.js"></script>
<script src="bootstrap3.7/bootstrap-table.min.js"></script>
<script src="bootstrap3.7/bootstrap-table-zh-CN.min.js"></script>
<script src="bootstrap3.7/fileinput.min.js"></script>
<script src="bootstrap3.7/zh.js"></script>
<script src="bootstrap3.7/bootstrap-select.min.js"></script>
</head>
<body>
<script type="text/javascript">


    $(document).ready(function () {
    	
    	$.ajax({  
    		// get请求地址  
    		    url: '/springboot/importExcelController/queryExcelModel',  
    		    dataType: "json",  
    		    success: function (data) {  
    		        var resultArray = data.results; 
    		        $("#modelName").find('option').remove();
    		        $("#modelName").append($('<option value=\'\'>' + "---请选择---" + '</option>'));
    		        for (i = 0; i < resultArray.length; i++) {
    		            $("#modelName").append($('<option value=' + resultArray[i].id + '>' + resultArray[i].text + '</option>'));
    		        }
    		        /* var optionString = ""
    		        for (var i = 0; i < resultArray.length; i++) {  
    		            optionString +="<option value=" + resultArray[i].id + ">" + resultArray[i].text + "</option>";  
    		        } 
    		        var myobj = document.getElementById("modelName");
    		        if (myobj.options.length == 0) {
    		               $("#modelName").html(optionString);
    		               $("#modelName").selectpicker('refresh');
    		        } */
    		        // 缺一不可  
    		        /* $('#modelName').selectpicker('refresh');  
    		        $('#modelName').selectpicker('render');  */ 
    		    }  
    		});
 		
 		$("#uploadfile").fileinput({ 
     	    language: 'zh', 
     	    uploadUrl: '/springboot/importExcelController/importExcelData',//后台上传方法 
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
    });
    	
</script>

	<form class="form-horizontal" role="form">
		<div class="form-group form-group-lg">
			<label for="modelName" class="col-sm-1 control-label">模板名</label>
			<div class="col-xs-2">
				<select class="form-control" id="modelName">
					<option value="1">请选择模板</option>
					<option value="2">广西省</option>
					<option value="3">福建省</option>
					<option value="4">湖南省</option>
					<option value="5">山东省</option>
				</select>
			</div>
		</div>
		<input type="file" id="uploadfile" name="fileData" class="file"/>
	</form>
</body>
</html>