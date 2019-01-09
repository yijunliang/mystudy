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
    	
    	$("#uploadfile").fileinput({ 
    	    language: 'zh', 
    	    uploadUrl: '/springboot/file/uploadFile',//后台上传方法 
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
    	
    	//异步上传返回结果处理
    	$('#uploadfile').on('fileerror', function(event, data, msg) {
    	      console.log(data.id);
    	      console.log(data.index);
    	      console.log(data.file);
    	      console.log(data.reader);
    	      console.log(data.files);
    	      // get message
    	      alert(msg);
    	});
    	//异步上传返回结果处理
    	$("#uploadfile").on("fileuploaded", function (event, data, previewId, index) {
    	        console.log(data.id);
    	        console.log(data.index);
    	        console.log(data.file);
    	        console.log(data.reader);
    	        console.log(data.files);
    	        var obj = data.response;
    	        alert(obj);
    	        //alert(JSON.stringify(data.success));
    	      });
    	//同步上传错误处理
    	    $('#uploadfile').on('filebatchuploaderror', function(event, data, msg) {
    	      console.log(data.id);
    	      console.log(data.index);
    	      console.log(data.file);
    	      console.log(data.reader);
    	      console.log(data.files);
    	      // get message
    	      alert(msg);
    	     });
    	  //同步上传返回结果处理
    	  $("#uploadfile").on("filebatchuploadsuccess", function (event, data, previewId, index) {
    	      console.log(data.id);
    	        console.log(data.index);
    	        console.log(data.file);
    	        console.log(data.reader);
    	        console.log(data.files);
    	        var obj = data.response;
    	        alert(JSON.stringify(data.success));
    	   });
    	//上传前
    	$('#uploadfile').on('filepreupload', function(event, data, previewId, index) {
    	    var form = data.form, files = data.files, extra = data.extra,
    	      response = data.response, reader = data.reader;
    	    console.log('File pre upload triggered');
    	  });
    	
    });
    	
</script>
<input type="file" id="uploadfile" name="file" class="file"/>
<input type="hidden" id="filePath" name="filePath" value="" />
</body>
</html>