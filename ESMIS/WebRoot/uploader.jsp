<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lanyuan.web.LoginAuthentication.LoginUser"%>
<%
	//获取前台自定义上传数据的类型
	String myType = request.getParameter("myfilters");
	String parentobjectNo = request.getParameter("parentobjectNo");
	myType = java.net.URLDecoder.decode(myType, "UTF-8");
	/**由于action获取当前操作人出错，故这里暂将session的值拿出来，单独穿参**/
	//modifyer,modifyerName,modifyTime,modifyIP
    LoginUser user = null;
    if (!(request.getSession().getAttribute(LoginUser.SESSIONID) == null))
    {
        user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
    }
    /* 最后修改人 */
    String modifyer = user.getUserId();
    /* 最后修改人姓名 */
    String modifyerName = user.getRealName();
    String modifyIP = request.getRemoteAddr();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>
    <link rel="stylesheet" href="plupload/queue/css/jquery.plupload.queue.css" type="text/css"></link>
    <script type="text/javascript" src="scripts/jquery-1.8.2.js"></script>

    <link rel="stylesheet" href="easyui/themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="easyui/themes/icon.css" type="text/css"></link>
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    
	<script type="text/javascript" src="plupload/plupload.js"></script>
	<script type="text/javascript" src="plupload/plupload.html4.js"></script>
	<script type="text/javascript" src="plupload/plupload.html5.js"></script>
	<script type="text/javascript" src="plupload/plupload.flash.js"></script>
	<script type="text/javascript" src="plupload/zh_CN.js"></script>
    <script type="text/javascript" src="plupload/queue/jquery.plupload.queue.js"></script>
<body style="padding: 0;margin: 0;">
    <div id="uploader">&nbsp;</div>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";
var parentobjectNo = '<%=parentobjectNo%>';
var modifyer ='<%=modifyer%>';
var modifyerName= '<%=modifyerName%>';
var modifyIP='<%=modifyIP%>';
var files = [];
var errors = [];
var type = 'file';
var chunk = eval('${param.chunk}');
var max_file_size = '5mb';
var minfilters = '<%=myType %>';
//判断参数是否含有自定义上传类型
var filters="";
var defaultFilters = "{title:\"文档\",extensions:\"pdf,doc,docx,xls,xlsx\"}";
if(minfilters=="undefined" || minfilters==""){
	filters = eval('(' + defaultFilters + ')');
}else{
	filters = eval('(' + minfilters + ')');
}
$("#uploader").pluploadQueue($.extend({
	runtimes : 'flash,html4,html5',
	url :'batchupload/batchupload.action?parentobjectNo='+parentobjectNo+'&modifyerName='+encodeURIComponent(encodeURIComponent(modifyerName))+'&modifyer='+modifyer +"&modifyIP="+modifyIP,
	max_file_size : max_file_size,
	file_data_name:'file',
	unique_names:true,
	filters : [filters],
	flash_swf_url : 'plupload/plupload.flash.swf',
	init:{
		FileUploaded:function(uploader,file,response){
			if(response.response){
				var rs = $.parseJSON(response.response);
				if(rs.status){
					files.push(file.name);
				}else{
					errors.push(file.name);
				}
			}
		},
		UploadComplete:function(uploader,fs){
			var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
			$.messager.alert("信息提示！","上传完成！共"+fs.length+"个文件。成功"+fs.length+"个","",function(r){
					window.parent.location.reload();
					target.window("close");
			});
		}
	}
},(chunk ? {chunk_size:'1mb'} : {})));
</script>
  </body>
</html>
